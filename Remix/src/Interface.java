import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Random;


public class Interface {
	
	public static void main(String args[]) {
		DatabaseConnectionService dbservice = new DatabaseConnectionService();
		dbservice.connect();
		UserService userservice = new UserService(dbservice);
		
		JFrame frame = new JFrame();
		frame.setSize(500,300);
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(3, 2);
		panel.setLayout(layout);
		JLabel usernameLabel = new JLabel("Username: ");
		JLabel passwordLabel = new JLabel("Password: ");
		JTextField username = new JTextField("Enter Username Here");
		JTextField password = new JTextField("Enter Password Here");
		JButton login = new JButton("Login");
		login.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean b = userservice.login(username.getText(), password.getText());
					if(b) 
					{
						System.out.println("Login Successful!");
					}
					else
					{
						System.out.println("Login Unsuccessful");
					}
				}
			}
		);
		JButton register = new JButton("Register");
		register.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean b = userservice.register(username.getText(), password.getText());
					if(b)
					{
						System.out.println("Register Successful!");
					}
					else
					{
						System.out.println("Register Unsuccessful");
					}
				}
			}
		);
		
		panel.add(usernameLabel);
		panel.add(username);
		panel.add(passwordLabel);
		panel.add(password);
		panel.add(login);
		panel.add(register);
		
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	
}