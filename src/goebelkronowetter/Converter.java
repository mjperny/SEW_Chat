
package goebelkronowetter;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
 * Takes words with their convertions of a File and search for the words in a message and replace them.
 * @author Melanie Goebel, Patricia Kronowetter
 * @version 2014-10-12
 */
public class Converter extends Decorator {
	
	private HashMap<String, String> words;
	
	/**
	 * Takes a chat-object and makes a new HashMap<String,String> for the convertions
	 * @param chat the used chat
	 */
	public Converter(Chat chat){
		super(chat);
		words = new HashMap<String, String>();
		readWords();
	}
	@Override
	public String writeMessage(String message) {
		String gefiltert = "";
		String[] splitMessage = message.split(" ");
		for(int i = 0; i < splitMessage.length; i++){
			if(words.get(splitMessage[i].toLowerCase()) != null){
				splitMessage[i] = words.get(splitMessage[i].toLowerCase());
			}
			gefiltert += splitMessage[i] + " ";
		}
		return gefiltert;
	}
	/**
	 * Read the words of the file converter.txt in the directory data
	 */
	private void readWords(){
		try{
			File f = new File("data/converter.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f),"UTF8"));
			String s;
			while ((s = in.readLine()) != null){
				String[] input = s.split(";");
				words.put(input[0].toLowerCase(), input[1]);// so it is not case-sensitive
			}
			in.close();
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "File not found! It has to be in the directory data and named converter.txt");
		}catch(IOException ioe){
			JOptionPane.showMessageDialog(null, "Something went wrong with the file converter.txt");
		}
	}
}