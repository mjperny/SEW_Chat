package goebelkronowetter;

public interface Chat{
	public void sendMessage(String message);
	public void closeChat();
	public String writeMessage(String message);
	public void setBadWords(boolean badWords);
	public void setShoutIt(boolean shoutIt);
	public void setConverterOn(boolean converterOn);
}
	