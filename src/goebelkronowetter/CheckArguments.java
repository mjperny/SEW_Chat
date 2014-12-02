package goebelkronowetter;


/**
 * Helping methods to check if the arguments are correct.
 * @author Melanie Goebel
 * @version 2014-12-01
 */
public class CheckArguments {
	
	/**
	 * Checks if the username is valid (3-15 characters ( a-z, 0-9, _ , -)
	 * @param name der ueberpruefende Name
	 * @return ob es gueltig ist
	 */
	public static boolean checkUsername(String name){
		String USERNAME_PATTERN= "^[a-z A-Z 0-9_-]{3,15}$";
		if(name.matches(USERNAME_PATTERN))
			return true;
		System.err.println("Not a valid username, it need to have 3-15 characters (include numbers, underline, minus)");
		return false;
	}
	/**
	 * Checks if it is a valid multicastaddress
	 * @param address die zu pruefende adresse
	 * @return ob es gueltig ist
	 */
	public static boolean checkMultiCastAddressFormat(String address) {
		String IPADDRESS_PATTERN = "2(?:2[4-9]|3\\d)(?:\\.(?:25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]\\d?|0)){3}";
		if ((address.matches(IPADDRESS_PATTERN)))
			return true;
		System.err.println("Not a valid MultiCast-Address. Use a adress between 224.0.0.0 and 239.255.255.255");
		return false;
	}
}

