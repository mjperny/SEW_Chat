package goebelkronowetter;

public abstract class Decorator implements Chat {
	private Chat c; 

	public Decorator(Chat c){
		this.c = c;
	}
	public void closeChat(){
		c.closeChat();
	}
	public void sendMessage(String message){
		c.sendMessage(message);
	}
	public void setBadWords(boolean badWords){
		c.setBadWords(badWords);
	}
	public void setShoutIt(boolean shoutIt){
		c.setShoutIt(shoutIt);
	}
	public void setConverterOn(boolean converterOn){
		c.setConverterOn(converterOn);
	}
}
