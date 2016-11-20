package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
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
		setTitle("User Login");
        setBounds(100, 100, 800, 600);
        setLayout(null);
        setResizable(false);

        JLabel background = new JLabel(new ImageIcon("images/back.jpg"));
        setContentPane(background);
        
        usernamelabel = new JLabel("Username : ");
        usernamelabel.setBounds(160, 80, 100, 40);
        
        usernamefield = new JTextField();
        usernamefield.setBounds(280, 80, 260, 40);
        
        password_label = new JLabel("Password : ");
        password_label.setBounds(160, 130, 100, 40);
        
        password_field = new JPasswordField();
        password_field.setBounds(280, 130, 260, 40);
        
        login_button = new JButton("Login");
        login_button.setBounds(240, 200, 100, 30);
        sign_up = new JButton("Sign up");
        sign_up.setBounds(400, 200, 100, 30);
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
				mainController.register();
				setVisible(false);
			}
		});
        add(usernamelabel);
        add(usernamefield);
        add(password_label);
        add(password_field);
        add(login_button);
        add(sign_up);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(login_button);
	}
	public static void main(String args[]) {
		new LoginFrame();
	}
}
