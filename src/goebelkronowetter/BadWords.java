package goebelkronowetter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
/**
 * Censors badWords of a message, using a File named BadWords.txt in the directory data
 * @author Melanie Göbel, Patricia Kronowetter
 * @version 2014-12-10
 */
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
	/**
	 * Censor a single word if it is in the File
	 * @param word the word to censor
	 * @return the censored word if it is in the File (when not it is the same as the paramter)
	 */
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
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "File not found! It has to be in the directory data and named converter.txt");
		}catch(IOException ioe){
			JOptionPane.showMessageDialog(null, "Something went wrong with the file converter.txt");
		}
		return word;
	}

}
