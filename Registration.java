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
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Registration extends JFrame implements ActionListener {
	// class to create registration frame
	boolean prompt;
	JPanel loginPanel;
	JPanel topPanel;

	JTextField textFieldusername;
	JPasswordField textFieldPassword;
	JTextField textFieldEmail;
	JTextField textFieldGender;
	JTextField textFieldWeight;
	JTextField textFieldHeight;

	GridBagConstraints gridBagConstraint;

	public Registration() {
		// replace the java icon by our app's icon
		Image icon = Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\ramhi\\Desktop\\KevinStuffs\\kevImage\\FTLogo.png");
		setIconImage(icon);
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// create container withing the frame
		Container containerScreen = getContentPane();
		containerScreen.setLayout(new BorderLayout());
		// =============================topPanel==========================
		topPanel = new PanelTop();
		containerScreen.add(topPanel, BorderLayout.NORTH);

		loginPanel = new JPanel();
		loginPanel.setPreferredSize(new Dimension(150, 2000));
		loginPanel.setLayout(new GridBagLayout());
		gridBagConstraint = new GridBagConstraints();
		gridBagConstraint.insets = new Insets(5, 5, 5, 5);
		containerScreen.add(loginPanel, BorderLayout.CENTER);

		// JLabel of Registration
		JLabel lblRegistration = new JLabel("REGISTRATION", SwingConstants.CENTER);
		lblRegistration.setHorizontalAlignment(JLabel.CENTER);
		lblRegistration.setFont(HomeScreen.fontSerif);
		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 0;
		gridBagConstraint.gridwidth = 4;
		gridBagConstraint.gridheight = 1;
		gridBagConstraint.fill = GridBagConstraints.HORIZONTAL;
		loginPanel.add(lblRegistration, gridBagConstraint);

		// label of username
		JLabel lblUsername = new JLabel("Enter Username: ");
		lblUsername.setHorizontalAlignment(JLabel.CENTER);
		lblUsername.setFont(HomeScreen.fontSerif);
		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 1;
		gridBagConstraint.gridwidth = 1;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(lblUsername, gridBagConstraint);

		// TextField of userName
		textFieldusername = new JTextField();
		textFieldusername.setFont(HomeScreen.fontSerif);
		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 1;
		gridBagConstraint.gridwidth = 2;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(textFieldusername, gridBagConstraint);

		// label of password
		JLabel lblPassword = new JLabel("Enter Password: ");
		lblPassword.setHorizontalAlignment(JLabel.CENTER);
		lblPassword.setFont(HomeScreen.fontSerif);
		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 2;
		gridBagConstraint.gridwidth = 1;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(lblPassword, gridBagConstraint);

		// TextField of password
		textFieldPassword = new JPasswordField();
		textFieldPassword.setPreferredSize(new Dimension(200, 25));
		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 2;
		gridBagConstraint.gridwidth = 2;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(textFieldPassword, gridBagConstraint);

		// label of email
		JLabel lblEmail = new JLabel("Enter Email: ");
		lblEmail.setHorizontalAlignment(JLabel.CENTER);
		lblEmail.setFont(HomeScreen.fontSerif);
		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 3;
		gridBagConstraint.gridwidth = 1;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(lblEmail, gridBagConstraint);

		// TextField of Email
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(HomeScreen.fontSerif);
		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 3;
		gridBagConstraint.gridwidth = 2;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(textFieldEmail, gridBagConstraint);

		// label of Gender
		JLabel lblGender = new JLabel("Enter Gender: ");
		lblGender.setHorizontalAlignment(JLabel.CENTER);
		lblGender.setFont(HomeScreen.fontSerif);
		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 4;
		gridBagConstraint.gridwidth = 1;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(lblGender, gridBagConstraint);

		// TextField of Gender
		textFieldGender = new JTextField();
		textFieldGender.setFont(HomeScreen.fontSerif);
		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 4;
		gridBagConstraint.gridwidth = 2;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(textFieldGender, gridBagConstraint);

		// label of Weight
		JLabel lblWeight = new JLabel("Enter Weight(kg):");
		lblWeight.setHorizontalAlignment(JLabel.CENTER);
		lblWeight.setFont(HomeScreen.fontSerif);
		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 5;
		gridBagConstraint.gridwidth = 1;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(lblWeight, gridBagConstraint);

		// TextField of weight
		textFieldWeight = new JTextField();
		textFieldWeight.setFont(HomeScreen.fontSerif);
		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 5;
		gridBagConstraint.gridwidth = 2;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(textFieldWeight, gridBagConstraint);

		// label of Height
		JLabel lblHeight = new JLabel("Enter Height(cm): ");
		lblHeight.setHorizontalAlignment(JLabel.CENTER);
		lblHeight.setFont(HomeScreen.fontSerif);
		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 6;
		gridBagConstraint.gridwidth = 1;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(lblHeight, gridBagConstraint);

		// TextField of Height
		textFieldHeight = new JTextField();
		textFieldHeight.setFont(HomeScreen.fontSerif);
		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 6;
		gridBagConstraint.gridwidth = 2;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(textFieldHeight, gridBagConstraint);

		// button register
		JButton btnRegistration = new JButton("Register");
		btnRegistration.addActionListener(this);
		btnRegistration.setHorizontalAlignment(JLabel.CENTER);
		btnRegistration.setFont(HomeScreen.fontSerif);
		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 7;
		gridBagConstraint.gridwidth = 1;
		gridBagConstraint.gridheight = 1;
		loginPanel.add(btnRegistration, gridBagConstraint);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent loginEvent) {
		if (textFieldusername.getText().equals("") || textFieldPassword.getText().equals("")
				|| textFieldEmail.getText().equals("") || textFieldGender.getText().equals("")
				|| textFieldWeight.getText().equals("") || textFieldHeight.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "BLANK FIELDS PRESENT", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
		} else {
			if (loginEvent.getActionCommand().equals("Register")) {
				int answer = JOptionPane.showConfirmDialog(null, "Do you wish to Register? ", "Registration",
						JOptionPane.YES_NO_CANCEL_OPTION);

				if (answer == 0) {
					// answer 0= yes
					try {
						Statement queryAll = LogIn.connection().createStatement();
						String mySQLQuery = "INSERT INTO user VALUES(NULL, '".concat(textFieldusername.getText())
								+ "', '".concat(textFieldPassword.getText()) + "', '".concat(textFieldEmail.getText())
								+ "', '".concat(textFieldGender.getText()) + "', '".concat(textFieldWeight.getText())
								+ "', '".concat(textFieldHeight.getText()) + "')";
						queryAll.execute(mySQLQuery);
						new HomeScreen(textFieldusername.getText());
					} catch (SQLException e) {
						e.printStackTrace();
					}

				} else if (answer == 1) {
					// answer 1=no reset fields
					textFieldusername.setText("");
					textFieldPassword.setText("");
					textFieldEmail.setText("");
					textFieldGender.setText("");
					textFieldWeight.setText("");
					textFieldHeight.setText("");
				}
				// else answer2 =cancel nothing changes
			}
		}
	}
}
