package goebelkronowetter;
/**
 * Methods what a chat have to do
 * @author Melanie Goebel
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
	public void setBadWords(boolean badWords);
	public void setShoutIt(boolean shoutIt);
	public void setConverterOn(boolean converterOn);
}
	