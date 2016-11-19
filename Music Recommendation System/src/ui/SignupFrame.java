package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import rules.MainController;

public class SignupFrame extends JFrame {
	private MainController mainController;
	JButton login_button, sign_up;
	JLabel usernamelabel, password_label;
	JTextField usernamefield;
	JPasswordField password_field;
	JPanel panel;

	public SignupFrame(MainController mainController) {
		this.mainController = mainController;
		initSwingComponents();
	}

	public SignupFrame() {
		initSwingComponents();
	}

	private void initSwingComponents() {
		setTitle("Sign Up");
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

		sign_up = new JButton("Sign up");
		sign_up.setBounds(150, 140, 100, 30);
		sign_up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					mainController.createUser(usernamefield.getText(), password_field.getText());
				} catch (SQLException e) {
					// TODO show error dialog
					e.printStackTrace();
				}
			}
		});
		panel.add(usernamelabel);
		panel.add(usernamefield);
		panel.add(password_label);
		panel.add(password_field);
		panel.add(sign_up);
		add(panel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		new SignupFrame();
	}
}
