package fitnessAppfinal;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LogIn extends JFrame implements ActionListener {
	// class to create log in frame
	JPanel loginPanel;
	JPanel topPanel;
	JTextField textFieldusername;
	JPasswordField textFieldPassword;
	GridBagConstraints gridBagConstraint;
	static int udata;
	boolean isUsernameValid = false;
	boolean isPasswordValid = false;

	LogIn() {
		Image icon = Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\ramhi\\Desktop\\KevinStuffs\\kevImage\\FTLogo.png");
		setIconImage(icon);

		// create container withing the frame
		Container containerScreen = getContentPane();
		containerScreen.setLayout(new BorderLayout());
		// =============================topPanel==========================
		topPanel = new PanelTop();
		containerScreen.add(topPanel, BorderLayout.NORTH);

		// ============================log in panel=======================
		loginPanel = new JPanel();
		loginPanel.setPreferredSize(new Dimension(150, 500));
		loginPanel.setLayout(new GridBagLayout());
		gridBagConstraint = new GridBagConstraints();
		gridBagConstraint.insets = new Insets(5, 5, 5, 5);
		containerScreen.add(loginPanel, BorderLayout.CENTER);

		// JLabel of LOGIN
		JLabel lblDate = new JLabel("LOGIN");
		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 0;
		gridBagConstraint.gridwidth = 3;
		gridBagConstraint.gridheight = 1;
		gridBagConstraint.fill = 4;
		loginPanel.add(lblDate, gridBagConstraint);

		// label of username
		JLabel lblUsername = new JLabel("Username: ");
		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 1;
		gridBagConstraint.gridwidth = 1;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(lblUsername, gridBagConstraint);

		// TextField of userName
		textFieldusername = new JTextField();
		textFieldusername.setPreferredSize(new Dimension(200, 15));
		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 1;
		gridBagConstraint.gridwidth = 3;
		gridBagConstraint.gridheight = 1;
		gridBagConstraint.fill = 3;
		loginPanel.add(textFieldusername, gridBagConstraint);

		// label of password
		JLabel lblPassword = new JLabel("Password: ");
		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 2;
		gridBagConstraint.gridwidth = 1;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(lblPassword, gridBagConstraint);

		// TextField of password
		textFieldPassword = new JPasswordField();
		textFieldPassword.setPreferredSize(new Dimension(200, 15));
		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 2;
		gridBagConstraint.gridwidth = 3;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(textFieldPassword, gridBagConstraint);

		// btn login
		JButton btnLogIn = new JButton("Login");
		btnLogIn.addActionListener(this);
		btnLogIn.setFocusable(false);
		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 3;
		gridBagConstraint.gridwidth = 3;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(btnLogIn, gridBagConstraint);

		// btn registration
		JButton btnRegistration = new JButton("Register");
		btnRegistration.addActionListener(this);
		btnRegistration.setFocusable(false);
		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 4;
		gridBagConstraint.gridwidth = 3;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(btnRegistration, gridBagConstraint);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setResizable(false);

		// setSize(500, 500);
		pack();
		setVisible(true);

	}

	static Connection connection() {
		// connect with database

		try {
			final String username = "root";
			String password = "";
			final String database = "jdbc:mysql://localhost:3306/fitnesstracker";
			Connection conn = null;
			if (conn == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(database, username, password);
			}
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent loginEvent) {
		if (loginEvent.getActionCommand().equals("Login")) {
			if (!textFieldusername.getText().equals("") || !textFieldusername.getText().equals("")) {
				try {
					Statement queryAll = LogIn.connection().createStatement();
					String mySQLQuery = "SELECT username, password FROM  user";
					queryAll.execute(mySQLQuery);
					ResultSet resultSetExercises = queryAll.getResultSet();
					while (resultSetExercises.next()) {
						if (textFieldusername.getText().equals(resultSetExercises.getString("username"))) {
							if (textFieldPassword.getText().equals(resultSetExercises.getObject("password"))) {
								new HomeScreen(textFieldusername.getText());
								dispose();
								isUsernameValid = true;
								isPasswordValid = true;
							}
						}
					}
					if (!isUsernameValid || !isPasswordValid) {
						JOptionPane.showMessageDialog(null, "Invalid Credential", "ERROR", JOptionPane.ERROR_MESSAGE);
						textFieldusername.setText("");
						textFieldPassword.setText("");
					}
					isUsernameValid = false;
					isPasswordValid = false;

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "BLANK FIELDS PRESENT", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (loginEvent.getActionCommand().equals("Register")) {
			new Registration();
			dispose();
		}
	}
}
