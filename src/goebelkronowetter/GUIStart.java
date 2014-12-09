package goebelkronowetter;

import java.awt.event.*;
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
			chat.sendMessage(panel.getWrite().getText());
			panel.getWrite().setText("");
		}
		else if(e.getSource()==panel.getQuit()) {
			chat.closeChat();
			frame.dispose();
			System.exit(0);
		}else if(e.getSource() instanceof JToggleButton) {
			if (panel.getBadWords().isSelected()){
				chat.setBadWords(false);
			}else{
				chat.setBadWords(true);
			}
			if (panel.getShouter().isSelected()){
				chat.setShoutIt(true);
			}else{
				chat.setShoutIt(false);
			}
			if (panel.getConverter().isSelected()){
				chat.setConverterOn(true);
			}else{
				chat.setConverterOn(false);
			}
		}
	}
}
