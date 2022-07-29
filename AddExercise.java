package fitnessAppfinal;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings("serial")
public class AddExercise extends JPanel {
	public static JTextField distanceTextField, repetitionTextField, setsTextField, textField4;
	public static JLabel distanceLabel, repsLabel, setsLabel, label4;
	public static JPanel tPanel;

	public static JRadioButton checkbox;
	public static JRadioButton checkbox1;

	public static ButtonGroup buttonGroup;

	public static JButton showCalender = new JButton("Select Date");

	public static JTextField startingTime;

	public static int exercise_id;
	ResultSet rs;

	public static int getCalorySepRep;
	static String usrid;

	AddExercise(String user_id) {
		usrid = user_id;
		JPanel innerPanel = new JPanel();
		JPanel test = new JPanel();

		JLabel selectdDate = new JLabel("Date");

		JPanel tpanel = new JPanel();

		JTextField endingTime;

		JLabel stime = new JLabel("Amount of time exercised: ");

		add(innerPanel);
		innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

		this.setPreferredSize(new Dimension(500, 500));

		JLabel titleText = new JLabel("Exercises");

		/**
		 * ------------------- START of TITLE ------------------------------
		 */
		titleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
		innerPanel.add(titleText);

		/**
		 * ------------------- End of TItle ------------------------------
		 */

		/**
		 * ------------------- START of Date Time picker ------------------------------
		 */
		showCalender.setBorderPainted(false);
		showCalender.setFocusPainted(false);

		test.add(selectdDate);
		test.add(showCalender);

		innerPanel.add(test);

		showCalender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCalender.setText(new DatePicker().setPickedDate());
			}
		});

		startingTime = new JTextField("0", 6);
		startingTime.setBorder(BorderFactory.createCompoundBorder(startingTime.getBorder(),
				BorderFactory.createEmptyBorder(3, 3, 3, 3)));
		startingTime.setToolTipText("00:10");

		endingTime = new JTextField("00:00", 6);
		endingTime.setBorder(BorderFactory.createCompoundBorder(endingTime.getBorder(),
				BorderFactory.createEmptyBorder(3, 3, 3, 3)));
		endingTime.setToolTipText("00:10");
		tpanel.add(stime);
		tpanel.add(startingTime);

		innerPanel.add(tpanel);

		/**
		 * ------------------- END of Date Time picker ------------------------------
		 */

		/**
		 * ------------------- START of checkboxes ------------------------------
		 */

		JPanel checkboxPanel = new JPanel();

		String[] arrayStrings = { "Push-ups", "Pull-ups", "Dips", "Squats", "Running" };

		JPanel panel = new JPanel();
		checkboxPanel.add(panel);
		panel.setLayout(new GridLayout(3, 2));

		buttonGroup = new ButtonGroup();

		for (String arrayString : arrayStrings) {
			checkbox = new JRadioButton(arrayString);
			checkbox.setActionCommand(arrayString);
			buttonGroup.add(checkbox);
			System.out.println("test" + checkbox.getText());

			checkbox.setBorder(BorderFactory.createCompoundBorder(checkbox.getBorder(),
					BorderFactory.createEmptyBorder(8, 0, 8, 0)));
			panel.add(checkbox);
		}

		/*----------------------- */

		innerPanel.add(checkboxPanel);
		/**
		 * ------------------- END Of checkboxes ------------------------------
		 */

		/**
		 * ------------------- START OF textfields ------------------------------
		 */

		tPanel = new JPanel();
		panel.setBorder(
				BorderFactory.createCompoundBorder(panel.getBorder(), BorderFactory.createEmptyBorder(16, 16, 16, 16)));
		tPanel.setLayout(new GridLayout(4, 1));
		add(tPanel);

		distanceLabel = new JLabel("Distance/m" + ":", JLabel.LEFT);
		distanceLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
		
		repsLabel = new JLabel("Repetition" + ":", JLabel.LEFT);
		repsLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
		
		setsLabel = new JLabel("Sets" + ":", JLabel.LEFT);
		setsLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
		
		label4 = new JLabel("Calories Burnt" + ":", JLabel.LEFT);
		label4.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));

		distanceTextField = new JTextField(10);
		distanceTextField.setBorder(BorderFactory.createCompoundBorder(distanceTextField.getBorder(),
				BorderFactory.createEmptyBorder(6, 6, 6, 6)));

		repetitionTextField = new JTextField(10);
		repetitionTextField.setBorder(BorderFactory.createCompoundBorder(repetitionTextField.getBorder(),
				BorderFactory.createEmptyBorder(6, 6, 6, 6)));
		
		setsTextField = new JTextField(10);
		setsTextField.setBorder(BorderFactory.createCompoundBorder(setsTextField.getBorder(),
				BorderFactory.createEmptyBorder(6, 6, 6, 6)));
		
		textField4 = new JTextField(10);
		textField4.setBorder(BorderFactory.createCompoundBorder(textField4.getBorder(),
				BorderFactory.createEmptyBorder(6, 6, 6, 6)));

		tPanel.add(distanceLabel);
		tPanel.add(distanceTextField);

		tPanel.add(repsLabel);
		tPanel.add(repetitionTextField);

		tPanel.add(setsLabel);
		tPanel.add(setsTextField);

		innerPanel.add(tPanel);
		/**
		 * ------------------- END OF textfields ------------------------------
		 */

		JButton saveButton = new JButton("Submit");
		innerPanel.add(saveButton);

		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand().equals("Submit")) {
					System.out.println("review" + buttonGroup.getSelection().getActionCommand());
				}
				saveToDatabase();
				distanceTextField.setText("");
				repetitionTextField.setText("");
				setsTextField.setText("");
				textField4.setText("");
				startingTime.setText("");
			}
		});
	}

	private void saveToDatabase() {
		final String username = "root";
		String password = "";
		final String database = "jdbc:mysql://localhost:3306/fitnesstracker";
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(database, username, password);
			String time = startingTime.getText();
			String rep = repetitionTextField.getText();
			String sets = setsTextField.getText();
			String getDate = showCalender.getText();
			String getDistance = distanceTextField.getText();
			Date settingDate = Date.valueOf(getDate);

			String getcheckboxValue = buttonGroup.getSelection().getActionCommand();
			System.out.println("getCheckboxValue" + getcheckboxValue);
			int timeExercised = Integer.parseInt(time);
			int reps = Integer.parseInt(rep);
			int set = Integer.parseInt(sets);
			int distance = Integer.parseInt(getDistance);

			if (distance == 0) {
				getCalorySepRep = (reps * set);
			} else {
				getCalorySepRep = distance / 50;
			}
			Statement stmt = conn.createStatement();
			System.out.println("Getting  name " + usrid + " from db");
			String mySQLQuery = "SELECT user_id FROM user WHERE username = '".concat(usrid) + "'";
			ResultSet rs = stmt.executeQuery(mySQLQuery);
			int userid = 0;
			while (rs.next()) {
				userid = rs.getInt("user_id");
				System.out.println("user id = " + userid);
			}
			if (userid >= 0) {
				String sql = "INSERT INTO exercises(exercise_name, time_minute, distance_meter, repetitions, sets, caloriesBurnt, day, user) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				System.out.println(sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, getcheckboxValue);
				ps.setInt(2, timeExercised);
				ps.setInt(3, distance);
				ps.setInt(4, reps);
				ps.setInt(5, set);
				ps.setInt(6, getCalorySepRep);
				ps.setDate(7, settingDate);
				ps.setInt(8, userid);
				ps.execute();
			}
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}
}
