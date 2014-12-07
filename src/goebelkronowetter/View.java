package goebelkronowetter;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Shows all elements in the window
 * @author Melanie Goebel
 * @version 2014-12-1
 */
public class View extends JPanel{
	private JTextArea txt = new JTextArea();
	private JScrollPane sp = new JScrollPane(txt);
	private JTextField write = new JTextField();
	private JButton quit = new JButton("Go Offline");
	private JToggleButton badwords = new JToggleButton("BadWords");
	private JToggleButton shouter = new JToggleButton("Shouter");
	
	public View(ActionListener ac){
		txt.setEditable(true);
		this.setLayout(new BorderLayout());
		JPanel eing = new JPanel();
		JPanel buttons = new JPanel();
		eing.setLayout(new GridLayout(2,1));
		txt.setEnabled(false);
		add(quit,BorderLayout.NORTH);
		add(sp,BorderLayout.CENTER);
		buttons.add(badwords);
		buttons.add(shouter);
		buttons.add(quit);
		eing.add(buttons);
		eing.add(write);
		
		add(eing,BorderLayout.SOUTH);
		quit.addActionListener(ac);
		badwords.addActionListener(ac);
		shouter.addActionListener(ac);
		write.addActionListener(ac);
		
	}
	public JToggleButton getShouter() {
		return shouter;
	}
	/**
	 * Gets the JTextField names write
	 * @return the JTextField write
	 */
	public JTextField getWrite(){
		return this.write;
	}
	/**
	 * Gets the JButton quit
	 * @return the JButton names quite
	 */
	public JButton getQuit(){
		return this.quit;
	}
	/**
	 * Appends a text to the JTextField
	 * @param message the message to append
	 */
	public void appendText(String message){
		txt.append(message);
	}
	/**
	 * Gets the JButton badWords
	 * @return the JButton names badWords
	 */
	public JToggleButton getBadWords(){
		return this.badwords;
	}

}
