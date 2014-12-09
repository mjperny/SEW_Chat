package goebelkronowetter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class BadWords extends Decorator {
	private Chat chat;
	private String badwords;
	
	public BadWords(Chat chat){
		this.chat = chat;
		badwords = "";
		readBadWords();
	}
	
	@Override
	public void sendMessage(String message) {
		String gefiltert = message.replaceAll(badwords, "******");
		chat.sendMessage(gefiltert);
	}
	
	private void readBadWords(){
		try{
		File f = new File("data/badwords.txt");
		BufferedReader in = new BufferedReader(new FileReader(f));
		String s;
		while ((s = in.readLine()) != null){
			badwords += s + "|";
		}
		badwords = badwords.substring(0, badwords.length()-1);
		in.close();
		}catch(FileNotFoundException fnfe){
			JOptionPane.showMessageDialog(null, "File not found! It has to be in the directory data and named badwords.txt");
		}catch(IOException ioe){
			JOptionPane.showMessageDialog(null, "Something went wrong with the file badwords.txt");
		}
	}
	
}