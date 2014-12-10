package goebelkronowetter;
/**
 * Takes a message and "shout" it (toUppterCase)
 * @author Patricia Kronowetter
 * @version 2014-12-10
 */
public class Shouter extends Decorator {
	
	public Shouter(Chat chat){
		super(chat);
	}
	
	@Override
	public String writeMessage(String message) {
		return  message.toUpperCase();
	}
}
