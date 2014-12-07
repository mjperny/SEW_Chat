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
	private String username = null;  
	private String ip = null;
	private int port = 6789;
	private JLabel error = new JLabel();
	private JTextField input1;  
	private JTextField input2;
	private JTextField input3;

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
		JLabel txt1 = new JLabel("Username:");
		JLabel txt2 = new JLabel("Multicast-address:");
		JLabel txt3 = new JLabel("Port:");
		input2 = new JTextField("224.0.0.1",15);
		input1 = new JTextField(15);
		input3 = new JTextField("6789",4);
		arguments.setLayout(new GridLayout(4,1));
		arguments.add(txt1);
		arguments.add(input1);
		arguments.add(txt2);
		arguments.add(input2);
		arguments.add(txt3);
		arguments.add(input3);
		getContentPane().add(error, BorderLayout.NORTH);
		getContentPane().add(arguments, BorderLayout.CENTER);  
		getContentPane().add(btnPanel, BorderLayout.SOUTH);  
		pack();  
		setLocationRelativeTo(null);
		//programm wird nicht beendet bei x beim dialog
	}   

	/**
	 * Checks the arguments and start the Chat
	 */
	private void okButton() { 
		if(CheckArguments.checkUsername(input1.getText())== true){
			username = input1.getText();
			if(CheckArguments.checkMultiCastAddressFormat(input2.getText()) == true){
				ip = input2.getText();
				try{
				port = Integer.parseInt(input3.getText());
				if(port > 0 && port < 65536){
				Start.startChat(username,ip,port);
				setVisible(false);
				}else{
					error.setText("The range of port number is between 1 and 65535");
					this.repaint();
				}
				}catch(NumberFormatException e){
					error.setText("port have to be a number!!!");
					this.repaint();
				}
			}else{
				error.setText("Multicastaddress is incorrect. Use a adress between 224.0.0.0 and 239.255.255.255");
				this.repaint();
			}
		}else{
			error.setText("Please use a username with 3-15 character (incl. numbers, underline, minus)");
		}
	}  
	/**
	 * Set the visible of the window to false
	 */
	private void noButton() {  
		username = null;  
		setVisible(false);  
	}  
}  
