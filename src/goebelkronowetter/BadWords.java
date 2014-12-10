package goebelkronowetter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BadWords extends Decorator{
	private ArrayList<String> ch = new ArrayList<String>();
	private FileReader fr;
	private BufferedReader br;

	public BadWords(Chat c) {
		super(c);
		try{
			fr = new FileReader("data/BadWords.txt");
			br = new BufferedReader(fr);
			ch.add("*");
			ch.add("$");
			ch.add("+");
			ch.add("?");
			ch.add("&");
			ch.add("§");
		} catch (IOException e) {
			System.err.println("File nicht gefunden!");
		}
	}
	@Override
	public String writeMessage(String message) {
		String[] words = message.split(" ");
		String censorMessage = "";
		try{
			for(int i = 0; i < words.length; i++){
				fr = new FileReader("data/BadWords.txt");
				br = new BufferedReader(fr);
				censorMessage += censorWord(words[i])+" ";
			}
		} catch (IOException e) {
			System.err.println("File nicht gefunden!");
		}
		return censorMessage;
	}
	public String censorWord(String word){
		String censorWord = "";
		try{
			boolean endloop = false;
			while(endloop == false){
				String zeile1 = br.readLine(); 
				if(zeile1 == null)
					endloop = true;
				else if((word.toUpperCase()).equals(zeile1.toUpperCase())){
					for(int i = 0; i < word.length(); i++){
						censorWord += ch.get((int) (Math.random()*ch.size()));
					}
					br.close();
					fr.close();
					return censorWord;
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return word;
	}

}
