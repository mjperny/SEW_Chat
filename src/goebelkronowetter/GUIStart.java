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
	JChat chat;
	String username;
	InetAddress ip;

	/**
	 * Makes a new Model and control arguments for networking
	 * @param username the username 
	 * @param ip the multicastaddress
	 * @param port the port
	 * @param chat the chatsystem
	 */
	public GUIStart(String username, InetAddress ip, int port, JChat chat){
		this.frame = new Model(panel, "Chatting in "+ip+":"+port);
		this.username = username;
		this.chat= chat;
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
			chat.sendMess(panel.getWrite().getText());
			panel.getWrite().setText("");
		}
		else if(e.getSource()==panel.getQuit()) {
			chat.sendMess("// "+username+" is offline!");
			try {
				chat.so.leaveGroup(ip);
			}
			catch(IOException ie) {
				JOptionPane.showMessageDialog(null, "Data overflow, connection error !");
			}
			chat.so.close();
			chat.dispose();
			System.exit(0);
		}else if(e.getSource()==panel.getBadWords()){
			if(panel.getBadWords().getText().equals("Censor bad words"))
				System.out.println("No bad words please!");
			else
				System.out.println("Bad words again..");
			panel.changeBadWords();
			//TODO: censor bad words
		}
	}
}
