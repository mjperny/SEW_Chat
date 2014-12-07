package goebelkronowetter;

import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import javax.swing.*;

/**
 * Starts the GUI and controls the actions of the GUI.
 * @author Melanie Goebel 
 * @version 2014-12-1
 */
public class GUIStart implements ActionListener{
	private View panel = new View(this);
	private Model frame;
	Chat chat;
	Chat decoratedChat; //das ist der chat mit einem decorator
	String username;
	InetAddress ip;

	/**
	 * Makes a new Model and control arguments for networking
	 * @param username the username 
	 * @param ip the multicastaddress
	 * @param port the port
	 * @param chat the chatsystem
	 */
	public GUIStart(String username, InetAddress ip, int port, Chat chat){
		this.frame = new Model(panel, "Chatting in "+ip+":"+port);
		this.username = username;
		this.chat= chat;
		this.decoratedChat = chat;
		this.ip = ip;
	}
	/**
	 * Append a text to panel
	 * @param message the message to append
	 */
	public void appendTxt(String message){
		panel.appendText(message);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==panel.getWrite()) {
			decoratedChat.sendMessage(panel.getWrite().getText());
			panel.getWrite().setText("");
		}
		else if(e.getSource()==panel.getQuit()) {
			decoratedChat.sendMessage("// "+username+" is offline!");
//			try {
//				chat.socket.leaveGroup(ip);
//			}
//			catch(IOException ie) {
//				JOptionPane.showMessageDialog(null, "Data overflow, connection error !");
//			}
//			chat.socket.close(); //nicht nötig, weil gleich darauf system.exit aufgerufen wird
			frame.dispose();
			System.exit(0);
		}else if(e.getSource()==panel.getBadWords()){
			if(panel.getBadWords().getText().equals("Censor bad words"))
				System.out.println("No bad words please!");
			else
				System.out.println("Bad words again..");
			panel.changeBadWords();
			//TODO: censor bad words
		} else if(e.getSource()==panel.getShouter()) {
			decoratedChat = new Shouter(decoratedChat);
		}
	}
}
