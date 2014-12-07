package goebelkronowetter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BadWords extends Decorator {
	private Chat chat;
	private String badwords;
	
	public BadWords(Chat chat) throws IOException{
		this.chat = chat;
		badwords = "";
		readBadWords();
	}
	
	@Override
	public void sendMessage(String message) {
		String gefiltert = message.replaceAll(badwords, "******");
		chat.sendMessage(gefiltert);
	}
	
	private void readBadWords() throws IOException{
		File f = new File("badwords.txt");
		BufferedReader in = new BufferedReader(new FileReader(f));
		String s;
		while ((s = in.readLine()) != null){
			badwords += s + "|";
		}
		badwords = badwords.substring(0, badwords.length()-1);
		in.close();
	}
	
}