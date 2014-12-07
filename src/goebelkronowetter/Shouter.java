package goebelkronowetter;

public class Shouter extends Decorator {
	private Chat chat;
	
	public Shouter(Chat chat){
		this.chat = chat;
	}
	
	@Override
	public void sendMessage(String message) {
		String grossmessage = message.toUpperCase();
		chat.sendMessage(grossmessage);
	}

}
