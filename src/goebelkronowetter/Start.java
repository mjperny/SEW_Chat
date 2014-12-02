package goebelkronowetter;

import java.io.IOException;
import java.net.InetAddress;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 * Starts the Chat with a hardcoding Multicast address
 * @author Melanie Goebel
 * @version 2014-12-1
 */
public class Start {
	
	public static void main(String[] args) throws IOException {
		AskDialog ad = new AskDialog();
		ad.setVisible(true); 
	}
	/**
	 * Starts the Chat with all arguments.
	 * @param user the username
	 * @param add the multicastaddress
	 */
	public static void startChat(String user, String add){
		JChat chat = new JChat(user,add,9876);
	}
}

