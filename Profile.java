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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Profile extends JFrame implements ActionListener {
	JPanel topPanel;
	JPanel centerPanel;
	JPanel imagePerson;

	static String username, email, gender;
	static int weight, height;
	private Insets inset = new Insets(10, 0, 5, 0);// top,left,bottom,right

	Profile(String user, String gender) {
		Image icon = Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\ramhi\\Desktop\\KevinStuffs\\kevImage\\FTLogo.png");
		setIconImage(icon);

		Container containerScreen = getContentPane();
		containerScreen.setLayout(new BorderLayout());
		// =============================topPanel==========================
		topPanel = new PanelTop();
		containerScreen.add(topPanel, BorderLayout.NORTH);

		// =============================centerPanel================
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		centerPanel.add(Box.createRigidArea(new Dimension(150, 10))); // hgap,verticalgap
		containerScreen.add(centerPanel);
		// ========================panelwithminipanel====================

		// button back to home screen
		
		JPanel panel = new JPanel();
		centerPanel.add(panel);
		panel.setPreferredSize(new Dimension(450, 100));
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbcConstraint = new GridBagConstraints();
		gbcConstraint.insets = inset;
		
		
		gbcConstraint.gridheight = 1;
		JButton back = new JButton("Back to Home Screen");
		back.setFocusable(false);
		back.addActionListener(this);
		panel.add(back, gbcConstraint);

		gbcConstraint.gridy = 2;
		JLabel lblCalGoal = new JLabel("Basal Metabolic Rate(BMR) = " + ExercisePanel.caloriegoal + " kcal");
		lblCalGoal.setFont(HomeScreen.fontSerif);
		panel.add(lblCalGoal, gbcConstraint);

		centerPanel.add(new MiniPanel("caloriesBurnt", user));
		centerPanel.add(new MiniPanel("distance_meter", user));
		centerPanel.add(new MiniPanel("time_minute", user));

		try {
			Statement queryAll = LogIn.connection().createStatement();
			String mySQLQuery = "SELECT username, email, gender, weight, height FROM `user` WHERE username = '"
					.concat(user) + "'";
			queryAll.execute(mySQLQuery);
			ResultSet rsUserData = queryAll.getResultSet();

			while (rsUserData.next()) {
				username = rsUserData.getString("username");
				email = rsUserData.getString("email");
				gender = rsUserData.getString("gender");
				weight = rsUserData.getInt("weight");
				height = rsUserData.getInt("height");
			}
			String[] arrValuer = { username, email, gender, weight + "", height + "" };
			String[] arrAttribute = { "username", "email", "gender", "weight", "height" };

			centerPanel.add(new MiniPanel(weight, height));

			for (int i = 0; i < arrAttribute.length; i++) {
				centerPanel.add(new MiniPanel(arrAttribute[i], arrValuer[i],user));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// scrollpane
		JScrollPane scroll = new JScrollPane(centerPanel);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scroll);

		setSize(550, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setResizable(false);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("Back to Home Screen")) {
			new HomeScreen(username);
			dispose();
		}
	}

}
