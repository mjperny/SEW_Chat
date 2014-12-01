package goebelkronowetter;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Makes a window for the chat
 * @author Melanie Goebel
 * @version 2014-12-1
 */
public class Model  extends JFrame{
	public Model(JPanel panel, String titel){
		super(titel);
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 600, 600);
		this.setVisible(true);
	}

}
