package goebelkronowetter;

import java.io.IOException;
import java.net.InetAddress;

import javax.swing.JOptionPane;
/**
 * Starts the Chat with a hardcoding Multicast address
 * @author Melanie Goebel
 * @version 2014-12-1
 */
public class Start {
	public static void main(String[] arg) throws IOException {
		String in = JOptionPane.showInputDialog(null,"What's your name?");
		if(arg.length>0) 
			in = arg[0];
		JChat chat = new JChat(in,"228.5.6.7",9876);
	}
}
