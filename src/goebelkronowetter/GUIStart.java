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
		}else if(e.getSource() instanceof JToggleButton) {
			decoratedChat = chat;
			if (panel.getBadWords().isSelected()){
				try {
					decoratedChat = new BadWords(decoratedChat);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error", e1.getMessage(), JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
			if (panel.getShouter().isSelected()){
				decoratedChat = new Shouter(decoratedChat);
			}
			if (panel.getConverter().isSelected()){
				try {
					decoratedChat = new Converter(decoratedChat);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error", e1.getMessage(), JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
	}
}
