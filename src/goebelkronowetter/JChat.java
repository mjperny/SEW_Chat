package goebelkronowetter;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

import javax.swing.*;
/**
 * A chatsystem with multicastsockets
 * @author Melanie Goebel
 * @version 2014-12-1
 */
public class JChat extends JFrame implements Runnable{
	private Thread activity = new Thread(this);
	private String name;
	private InetAddress address;
	private int port;
	protected MulticastSocket so;
	private GUIStart gui;

	/**
	 * Makes all networking things.
	 * @param username the used username
	 * @param address the multicastaddress
	 * @param port the port
	 */
	public JChat(String username, String address, int port){
		if(CheckArguments.checkUsername(username) == true)
			this.name = username;
		else{
			this.name="user"+(int)(Math.random()*90)+1;
			JOptionPane.showMessageDialog(null, "This username is not okay, we used "+this.name);
		}
		try {
			this.address = InetAddress.getByName(address);
			this.gui = new GUIStart(username,this.address,9876,this);
			this.port = port;
			so = new MulticastSocket(port);
			so.joinGroup(this.address);
			this.activity.start();
			sendMess("// "+name+" is online!");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			so.send(packet);
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
				so.receive(packet);
				String mess = new String(data,0,packet.getLength());
				gui.appendTxt(mess+ "\n");
			}

		catch(IOException e) {
			break;
		}

	}


}

