package testing;

import static org.junit.Assert.*;
import goebelkronowetter.CheckArguments;

import org.junit.Test;
/**
 * Checks if the CheckArgument class works correct
 * @author Melanie Goebel
 * @version 2014-09-12
 */
public class CheckArgumentsTest {

	@Test
	/**
	 * Checks with right username
	 */
	public void checkUser1() {
		assertEquals(true,CheckArguments.checkUsername("melanie"));
	}
	@Test
	/**
	 * Checks with numbers in username
	 */
	public void checkUser2() {
		assertEquals(true,CheckArguments.checkUsername("melanie124"));
	}
	@Test
	/**
	 * Checks with numbers before characters in username
	 */
	public void checkUser3() {
		assertEquals(true,CheckArguments.checkUsername("12312ag"));
	}
	@Test
	/**
	 * Checks username with 2 characters
	 */
	public void checkUser4() {
		assertEquals(false,CheckArguments.checkUsername("me"));
	}
	@Test
	/**
	 * Checks username with more than 15 characters
	 */
	public void checkUser5() {
		assertEquals(false,CheckArguments.checkUsername("melaniejacquelinegoebel"));
	}
	@Test
	/**
	 * Checks a address that is not a multicastadress
	 */
	public void checkMultiCast1() {
		assertEquals(false,CheckArguments.checkMultiCastAddressFormat("8.8.8.8"));
	}
	@Test
	/**
	 * Checks a address with syntax error
	 */
	public void checkMultiCast2() {
		assertEquals(false,CheckArguments.checkMultiCastAddressFormat("5.6.1."));
	}
	@Test
	/**
	 * Checks a multicastadress
	 */
	public void checkMultiCast3() {
		assertEquals(true,CheckArguments.checkMultiCastAddressFormat("224.0.0.1"));
	}
	@Test
	/**
	 * Checks username with underline
	 */
	public void makeObject(){
		CheckArguments ca = new CheckArguments();
		assertEquals(true,ca.checkUsername("mel_19"));
	}
	

}
