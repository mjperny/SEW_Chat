package goebelkronowetter;

import java.io.IOException;

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
	public static void startChat(String user, String add, int port){
		new JChat(user,add,port);
	}
}

