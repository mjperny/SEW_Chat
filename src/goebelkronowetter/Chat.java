package goebelkronowetter;
/**
 * Methods what a chat have to do
 * @author Melanie Goebel, Patricia Kronowetter
 * @version 2014-12-09
 */
public interface Chat{
	/**
	 * Send the message to other Clients
	 * @param message the message to send
	 */
	public void sendMessage(String message);
	/**
	 * Close the chat forever.
	 */
	public void closeChat();
	/**
	 * Writes a message that was sended
	 * @param message the sended message
	 * @return
	 */
	public String writeMessage(String message);
	/**
	 * Set if badWords should be censored
	 * @param badWords if the badWords should be censored
	 */
	public void setBadWords(boolean badWords);
	/**
	 * Set if the text should be shouted
	 * @param shoutIt if it should be shouted
	 */
	public void setShoutIt(boolean shoutIt);
	/**
	 * Set if the words should be converted to other
	 * @param converterOn if the words should be converted to other words
	 */
	public void setConverterOn(boolean converterOn);
}
	