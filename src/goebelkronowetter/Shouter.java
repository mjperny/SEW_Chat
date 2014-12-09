package goebelkronowetter;

public class Shouter extends Decorator {
	
	public Shouter(Chat chat){
		super(chat);
	}
	
	@Override
	public String writeMessage(String message) {
		return  message.toUpperCase();
	}
}
