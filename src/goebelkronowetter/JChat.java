package goebelkronowetter;

import java.io.IOException;
import java.net.*;

import javax.swing.*;
/**
 * A chatsystem with multicastsockets
 * @author Melanie Goebel
 * @version 2014-12-1
 */
public class JChat implements Runnable,Chat{
	private Thread chat = new Thread(this);
	private String name;// the username
	private InetAddress address;//multicast address for the group
	private int port;// port where the socket run
	protected MulticastSocket socket;
	private GUIStart gui;// the gui starts when arguments are correct
	private boolean badWords = true;
	private boolean shoutIt = false;
	private boolean converterOn = false;

	/**
	 * Makes all networking things.
	 * @param username the used username
	 * @param address the multicastaddress
	 * @param port the port
	 */
	public JChat(String username, String address, int port){
		this.name = username;
		try {
			this.address = InetAddress.getByName(address);
			this.gui = new GUIStart(username,this.address,9876,this);
			this.port = port;
			socket = new MulticastSocket(port);// create a socket on this port
			socket.joinGroup(this.address);// Makes a group with the address
			this.chat.start();
			sendMessage("// "+name+" is online!");
		} catch (UnknownHostException e) {
			System.err.println("Unkown Host");
		} catch (IOException e) {
			System.err.println("Something is wrong with IO");
		}
	}
	/**
	 * Send a message
	 * @param s the message
	 */
	public void sendMessage(String s) {
		byte[] data = null;
		if(s.matches("^//[a-z A-Z 0-9_-]{3,15} (is online!|is offline!)$")){// Regex to check if the message
			//is only a information (user online or offline)
			data = s.getBytes();
		}else{
			data = (name + ": " + s).getBytes();// Gives the message a user (username: message)
		}
		DatagramPacket packet = new DatagramPacket(data,data.length,address,port);
		try {
			socket.send(packet);
		}
		catch(IOException ie) {
//			JOptionPane.showMessageDialog(null, "Data overflow !"); //untere methode ist besser 
			JOptionPane.showMessageDialog(null, "Error", ie.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Gives the InetAddress address
	 * @return the InetAddress
	 */
	public InetAddress getAddress(){
		return address;
	}
	@Override
	public void run() {
		byte[] data = new byte[1024];
		while(true)
			try {
				DatagramPacket packet = new DatagramPacket(data,data.length);
				socket.receive(packet);
				String mess = new String(data,0,packet.getLength());
				gui.appendTxt(writeMessage(mess));
			}
		catch(IOException e) {
			break;
		}

	}
	/**
	 * Write a message with backspace on the end
	 * @param message the message
	 * @return message with backspace
	 */
	public  String writeMessage(String message){
		String text = message;
		if(shoutIt == true){
			text = new Shouter(this).writeMessage(text);
		}
		if(badWords == false){
			text = new BadWords(this).writeMessage(text);
		}
		return text+"\n";
	}
	public void closeChat(){
		try {
			this.socket.leaveGroup(address);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Connection error!");
		}
		this.socket.close();
	}
	/**
	 * Set the boolean badWords
	 * @param badWords if badWords should be written
	 */
	public void setBadWords(boolean badWords){
		this.badWords = badWords;
	}
	/**
	 * Set the boolean ShoutIt
	 * @param shoutIt if the messages should be shouted (UpperCase)
	 */
	public void setShoutIt(boolean shoutIt){
		this.shoutIt = shoutIt;
	}
	/**
	 * Set the boolean converterOn
	 * @param converterOn if the message should be converted
	 */
	public void setConverterOn(boolean converterOn){
		this.converterOn = converterOn;
	}
}

