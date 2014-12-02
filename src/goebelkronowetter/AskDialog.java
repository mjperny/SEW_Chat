package goebelkronowetter;

import java.awt.BorderLayout;    
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;  
import javax.swing.*;  

/**
 * A window to check the arguments
 * @author Melanie Goebel
 * @version 2014-12-2
 */
public class AskDialog extends JDialog {  
	private String value1 = null;  
	private String value2 = null;
	private JLabel error = new JLabel();
	private JTextField input1;  
	private JTextField input2;

	public AskDialog() {  
		super();  
		error.setForeground(Color.RED); // Set Font-Color
		JPanel btnPanel = new JPanel();  
		JButton okBtn   = new JButton("Connect");  
		JButton noBtn   = new JButton("Cancel");  
		btnPanel.add(okBtn);  
		okBtn.addActionListener(new ActionListener() {  
			public void actionPerformed(java.awt.event.ActionEvent ae) {  
				okButton();  
			}  
		});  
		noBtn.addActionListener(new ActionListener() {  
			public void actionPerformed(java.awt.event.ActionEvent ae) {  
				noButton();  
			}  
		});  
		btnPanel.add(noBtn);  
		JPanel arguments = new JPanel();
		JLabel txt1 = new JLabel("Usename:");
		JLabel txt2 = new JLabel("Multicast-address:");
		input2 = new JTextField("224.0.0.1",15);
		input1 = new JTextField(15);
		arguments.setLayout(new GridLayout(4,1));
		arguments.add(txt1);
		arguments.add(input1);
		arguments.add(txt2);
		arguments.add(input2);
		getContentPane().add(error, BorderLayout.NORTH);
		getContentPane().add(arguments, BorderLayout.CENTER);  
		getContentPane().add(btnPanel, BorderLayout.SOUTH);  
		pack();  
	}   

	/**
	 * Checks the arguments and start the Chat
	 */
	private void okButton() { 
		if(CheckArguments.checkUsername(input1.getText())== true){
			value1 = input1.getText();
			if(CheckArguments.checkMultiCastAddressFormat(input2.getText()) == true){
				value2 = input2.getText();
				Start.startChat(value1,value2);
				setVisible(false);
			}else{
				error.setText("Multicastaddress is incorrect. Use a adress between 224.0.0.0 and 239.255.255.255");
				this.repaint();
			}
		}else{
			error.setText("Username is incorrect. Please use a name with 3-15 character including numbers, underline and minus");
		}
	}  
	/**
	 * Set the visible of the window to false
	 */
	private void noButton() {  
		value1 = null;  
		setVisible(false);  
	}  
}  
