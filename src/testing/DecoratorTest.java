package testing;

import static org.junit.Assert.assertEquals;
import goebelkronowetter.JChat;
import goebelkronowetter.Shouter;
import goebelkronowetter.BadWords;
import goebelkronowetter.Converter;

import org.junit.Test;
/**
 * Tests all Decorator-Classes from Chat
 * @author Melanie Goebel
 * @version 2014-10-12
 */
public class DecorationTest {
	
	@Test
	/**
	 * Test the functionality of Shouter-Class
	 */
	public void Shouter() {
		JChat c = new JChat("Mel","224.0.0.5",4444);
		Shouter s = new Shouter(c);
		assertEquals("HELLO",s.writeMessage("Hello"));	
	}
	@Test
	/**
	 * Test the functionality of Shouter-Class, when the text was shouted
	 */
	public void Shouter2() {
		JChat c = new JChat("Mel","224.0.0.5",4444);
		Shouter s = new Shouter(c);
		assertEquals("HELLO",s.writeMessage("HELLO"));	
	}
	@Test
	/**
	 * Test the functionality of BadWords-Class, without BadWords in text
	 */
	public void BadWords() {
		JChat c = new JChat("Mel","224.0.0.5",4444);
		BadWords bw = new BadWords(c);
		assertEquals("Hello you ",bw.writeMessage("Hello you"));	
	}
	@Test
	/**
	 * Test the functionality of BadWords-Class, with BadWords
	 */
	public void BadWords2() {
		JChat c = new JChat("Mel","224.0.0.5",4444);
		BadWords bw = new BadWords(c);
		int a = "Arschloch".length();
		String word = bw.writeMessage("Arschloch");
		assertEquals(true,word.matches("^.{"+a+"}[ ]$"));	
	}
	@Test
	/**
	 * Test the functionality of Converter-Class, without convertions
	 */
	public void Converter(){
		JChat c = new JChat("Mel","224.0.0.5",4444);
		Converter cv = new Converter (c);
		assertEquals("Whats up?",cv.writeMessage("Whats up?"));
	}
	@Test
	/**
	 * Test the functionality of Shouter-Class, with convertions
	 */
	public void Converter2(){
		JChat c = new JChat("Mel","224.0.0.5",4444);
		Converter cv = new Converter (c);
		assertEquals("You little sweet Mädl",cv.writeMessage("You little sweet Mädchen"));
	}
	
}
