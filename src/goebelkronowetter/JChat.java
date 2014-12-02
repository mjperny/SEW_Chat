package goebelkronowetter;

import java.io.IOException;
import java.net.*;

import javax.swing.*;
/**
 * A chatsystem with multicastsockets
 * @author Melanie Goebel
 * @version 2014-12-1
 */
public class JChat extends JFrame implements Runnable{
	private Thread chat = new Thread(this);
	private String name;
	private InetAddress address;
	private int port;
	protected MulticastSocket socket;
	private GUIStart gui;

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
			socket = new MulticastSocket(port);
			socket.joinGroup(this.address);
			this.chat.start();
			sendMess("// "+name+" is online!");
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
	public void sendMess(String s) {
		byte[] data = null;
		if(s.matches("^//[a-z A-Z 0-9_-]{3,15} (is online!|is offline!)$")){
			data = s.getBytes();
		}else{
			data = (name + ": " + s).getBytes();
		}
		DatagramPacket packet = new DatagramPacket(data,data.length,address,port);
		try {
			
			socket.send(packet);
		}
		catch(IOException ie) {
			JOptionPane.showMessageDialog(null, "Data overflow !");
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
				gui.appendTxt(mess+ "\n");
			}

		catch(IOException e) {
			break;
		}

	}


}

