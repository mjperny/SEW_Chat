package goebelkronowetter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	private JToggleButton badwords = new JToggleButton("Censor BadWords");
	private JToggleButton shouter = new JToggleButton("Shouter");
	private JToggleButton converter = new JToggleButton("Converter");
	private JButton send = new JButton("Send");
	
	public View(ActionListener ac){
		txt.setEditable(false);
		this.setLayout(new BorderLayout());
		JPanel eing = new JPanel();
		JPanel buttons = new JPanel();
		eing.setLayout(new BoxLayout(eing,BoxLayout.LINE_AXIS)); // BoxLayout that goes with lines (from left to right)
		txt.setBackground(new Color(250,222,242));
		txt.setForeground(Color.BLACK);//Set FontColor
		add(quit,BorderLayout.NORTH);
		add(sp,BorderLayout.CENTER);
		buttons.add(badwords);
		buttons.add(shouter);
		buttons.add(converter);
		buttons.add(quit);
		eing.add(write);
		eing.add(send);
		add(buttons,BorderLayout.NORTH);
		add(eing,BorderLayout.SOUTH);
		send.addActionListener(ac);
		quit.addActionListener(ac);
		badwords.addActionListener(ac);
		shouter.addActionListener(ac);
		converter.addActionListener(ac);
		write.addActionListener(ac);
		
	}
	/**
	 * Gets the JToggleButton converter
	 * @return the JToggleButton converter
	 */
	public JToggleButton getConverter() {
		return converter;
	}
	/**
	 * Gets the JToggleButton shouter
	 * @return the JToggleButton shouter
	 */
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
	 * Gets the JToggleButton badWords
	 * @return the JToggleButton names badWords
	 */
	public JToggleButton getBadWords(){
		return this.badwords;
	}
	/**
	 * Gets the JButton Send
	 * @return the JButton names send
	 */
	public JButton getSend(){
		return this.send;
	}

}
