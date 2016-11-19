package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import rules.MainController;

public class LoginFrame extends JFrame {
	private MainController mainController;
	JButton login_button,sign_up;
    JLabel usernamelabel, password_label;
    JTextField usernamefield;
    JPasswordField password_field;
    JPanel panel;
    	
	public LoginFrame(MainController mainController){
		this.mainController = mainController;
		initSwingComponents();
	}
	public LoginFrame() {
		initSwingComponents();
	}
	private void initSwingComponents() {
		setTitle("Portal-Login");
        setBounds(100, 100, 400, 250);
        setLayout(null);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 400, 250);
        panel.setBackground(Color.yellow);
        
        usernamelabel = new JLabel("Username : ");
        usernamelabel.setBounds(20, 20, 100, 40);
        
        usernamefield = new JTextField();
        usernamefield.setBounds(110, 20, 260, 40);
        
        password_label = new JLabel("Password : ");
        password_label.setBounds(20, 70, 100, 40);
        
        password_field = new JPasswordField();
        password_field.setBounds(110, 70, 260, 40);
        
        login_button = new JButton("Login");
        login_button.setBounds(80, 140, 100, 30);
        sign_up = new JButton("Sign up");
        sign_up.setBounds(200, 140, 100, 30);
        login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println("Lohin Button Pressed");
					mainController.login(usernamefield.getText(),password_field.getText());
				} catch (SQLException e) {
					// TODO Show error dialog
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        sign_up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SignupFrame(mainController);
			}
		});
        panel.add(usernamelabel);
        panel.add(usernamefield);
        panel.add(password_label);
        panel.add(password_field);
        panel.add(login_button);
        panel.add(sign_up);
        add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[]) {
		new LoginFrame();
	}
}
