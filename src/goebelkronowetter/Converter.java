package goebelkronowetter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Converter extends Decorator {
	private Chat chat;
	private HashMap<String, String> words;
	
	public Converter(Chat chat) throws IOException{
		this.chat = chat;
		words = new HashMap<String, String>();
		readWords();
	}
	
	@Override
	public void sendMessage(String message) {
		Set<String> keys = words.keySet();
		Iterator<String> it = keys.iterator();
		String gefiltert = message;
		while(it.hasNext()){
			String key = it.next();
			gefiltert = gefiltert.replaceAll(key, words.get(key));
		}
		chat.sendMessage(gefiltert);
	}
	
	private void readWords() throws IOException{
		File f = new File("converter.txt");
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f),"UTF8"));

		String s;
		while ((s = in.readLine()) != null){
			String[] input = s.split(";");
			words.put(input[0], input[1]);
		}
		
		
		in.close();
	}
	
}