package fitnessAppfinal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

@SuppressWarnings("serial")
public class HomeScreen extends JFrame implements ActionListener {
	// class to create homescreen frame
	JLayeredPane layer;
	JPanel imagePerson;
	JPanel topPanel;
	JPanel panelRight;
	JPanel panelRightBackground;
	JPanel deckPanel;
	JComboBox<?> comboBoxDate;
	static Date combodate;
	static CardLayout cardlayout = new CardLayout(); // display by (panel,date)
	static Map<String, JPanel> MapPanelExercises; // keys date<String> , value exercisePanel <panel>
	static Set<Map.Entry<String, JPanel>> mapPanelExercisesKeys; // storing the keys
	static JPanel panelDisplayed;
	static String username;
	static int user_id;
	static String gender;
	static Font fontSerif = new Font("Serif", Font.BOLD, 15);
	Font fontButton = new Font("Serif", Font.BOLD, 12);

	HomeScreen(String user) {
		Image icon = Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\ramhi\\Desktop\\KevinStuffs\\kevImage\\FTLogo.png");
		setIconImage(icon);
		username = user;
		// create container withing the frame
		Container containerScreen = getContentPane();
		containerScreen.setLayout(new BorderLayout(2, 2));
		containerScreen.setBackground(Color.black);

		// ========================top panel=========================
		topPanel = new PanelTop();
		containerScreen.add(topPanel, BorderLayout.NORTH);

		// =======================east Panel====================================
		panelRight = new JPanel();
		panelRight.setPreferredSize(new Dimension(148, 1000));
		panelRight.setLayout(null);
		containerScreen.add(panelRight, BorderLayout.LINE_END);

		layer = new JLayeredPane();
		layer.setSize(new Dimension(148, 1000));
		panelRight.add(layer);

		// lbl of image

		try {
			Statement stmtGender = LogIn.connection().createStatement();
			String mysqlquery = "SELECT gender FROM user WHERE username='".concat(username) + "'";
			ResultSet RSGender = stmtGender.executeQuery(mysqlquery);

			while (RSGender.next()) {
				gender = RSGender.getString("gender");
				gender.toLowerCase();
			}

			if (gender.equals("female")) {
				imagePerson = new ImageScaler(100, 100, "C:\\Users\\ramhi\\Desktop\\KevinStuffs\\kevImage\\female.png");

			} else if (gender.equals("male")) {
				imagePerson = new ImageScaler(100, 100, "C:\\Users\\ramhi\\Desktop\\KevinStuffs\\kevImage\\male.png");

			} else {
				imagePerson = new ImageScaler(100, 100,
						"C:\\Users\\ramhi\\Desktop\\KevinStuffs\\kevImage\\unspecified.png");
			}
			imagePerson.setBounds(24, 0, 100, 100);
			imagePerson.setOpaque(true);
			layer.add(imagePerson, Integer.valueOf(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// JLabel of user
		JLabel lblUser = new JLabel(("User - " + username).toUpperCase(), SwingConstants.CENTER);
		lblUser.setBounds(0, 100, 148, 25);
		lblUser.setOpaque(false);
		lblUser.setForeground(Color.white);
		lblUser.setFont(fontSerif);
		layer.add(lblUser, Integer.valueOf(1));

		// buttons EDIT Profile
		JButton btnEdit = new JButton("EDIT PROFILE");
		btnEdit.addActionListener(this);
		btnEdit.setFocusable(false);
		btnEdit.setBounds(15, 130, 118, 25);
		btnEdit.setOpaque(false);
		btnEdit.setFont(fontButton);
		layer.add(btnEdit, Integer.valueOf(1));

		// JComboBox of dates
		comboBoxDate = fillComboBox();
		comboBoxDate.addActionListener(this);
		comboBoxDate.setBounds(24, 175, 100, 25);
		comboBoxDate.setOpaque(true);
		comboBoxDate.setFont(fontButton);
		((JLabel) comboBoxDate.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		layer.add(comboBoxDate, Integer.valueOf(1));

		// label of print
		JLabel lblPrint = new JLabel("Print your data", SwingConstants.CENTER);
		lblPrint.setBounds(24, 220, 100, 25);
		lblPrint.setOpaque(false);
		lblPrint.setForeground(Color.white);
		lblPrint.setFont(fontSerif);
		layer.add(lblPrint, Integer.valueOf(1));

		// buttons PRINT
		JButton btnPrint = new JButton("PRINT");
		btnPrint.addActionListener(this);
		btnPrint.setBounds(35, 250, 78, 25);
		btnPrint.setOpaque(false);
		btnPrint.setFocusable(false);
		btnPrint.setFont(fontButton);
		layer.add(btnPrint, Integer.valueOf(1));

		// JLabel to add exercise
		JLabel lblExercise = new JLabel("Add Exercise", SwingConstants.CENTER);
		lblExercise.setBounds(24, 300, 100, 25);
		lblExercise.setOpaque(false);
		lblExercise.setForeground(Color.WHITE);
		lblExercise.setFont(fontSerif);
		layer.add(lblExercise, Integer.valueOf(1));

		// buttons Add
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(35, 330, 78, 25);
		btnAdd.setOpaque(false);
		btnAdd.setFocusable(false);
		btnAdd.setFont(fontButton);
		layer.add(btnAdd, Integer.valueOf(1));

		// background for right side
		panelRightBackground = new ImageScaler(148, 600,
				"C:\\Users\\ramhi\\Desktop\\KevinStuffs\\kevImage\\background.jpg");
		panelRightBackground.setBounds(0, 0, 148, 800);
		panelRightBackground.setOpaque(true);
		layer.add(panelRightBackground, Integer.valueOf(0));

		// =================================centerpanel========================================

		deckPanel = new JPanel();
		containerScreen.add(deckPanel, BorderLayout.CENTER);

		deckPanel.setLayout(cardlayout);
		MapPanelExercises = new HashMap<String, JPanel>(); // instantiate map
		// setting 2022-07-17 as default
		String dateAsString = "2022-01-01";
		panelDisplayed = new ExercisePanel(dateAsString, username);
		MapPanelExercises.put(dateAsString, panelDisplayed);// puting default on map
		mapPanelExercisesKeys = MapPanelExercises.entrySet(); // instantiate entryset
		deckPanel.add(panelDisplayed, dateAsString); // add to cardlayout
		cardlayout.show(deckPanel, dateAsString); // show panel from deck

		JScrollPane scrollable = new JScrollPane(deckPanel);
		add(BorderLayout.CENTER, scrollable);
		scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollable.getVerticalScrollBar().setBackground(new Color(150, 150, 150));
		scrollable.getHorizontalScrollBar().setBackground(new Color(100, 100, 200));

		scrollable.getVerticalScrollBar().setUI(new BasicScrollBarUI() {

			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(200, 200, 200);
			}
		});

		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setResizable(false);

		setVisible(true);

	}

	private static JComboBox<?> fillComboBox() {
		TreeSet<Date> setOfDays = new TreeSet<>();
		Date day;
		try {
			Statement queryDates = LogIn.connection().createStatement();
			String query = "SELECT exercises.day FROM `exercises` INNER JOIN user ON exercises.user=user.user_id WHERE user.username ='"
					.concat(username) + "' ";
			queryDates.execute(query);
			ResultSet resultDates = queryDates.getResultSet();
			while (resultDates.next()) {
				day = resultDates.getDate("day");
				setOfDays.add(day);
			}

			@SuppressWarnings({ "rawtypes", "unchecked" })
			JComboBox comboBoxDate = new JComboBox(setOfDays.toArray());
			if (setOfDays.isEmpty()) {
				JOptionPane.showMessageDialog(null, "NO RECORD OF PREVIOUS WORKOUT", "INFORMATION",
						JOptionPane.INFORMATION_MESSAGE);
			}

			return comboBoxDate;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getActionCommand().equals("PRINT")) {
			try {
				JOptionPane.showMessageDialog(HomeScreen.this, event.getActionCommand() + " button has been pressed");
				Statement queryCalories = LogIn.connection().createStatement();
				String mySQLQuery = "SELECT  exercises.day, exercises.exercise_name, exercises.time_minute, exercises.repetitions, "
						+ "exercises.sets, exercises.caloriesBurnt, exercises.distance_meter FROM `exercises` INNER JOIN user ON "
						+ "exercises.user=user.user_id WHERE username ='"
						.concat(username) + "'";
				System.out.println(mySQLQuery);
				queryCalories.execute(mySQLQuery);
				ResultSet resultSetCalories = queryCalories.getResultSet();
				ResultSetMetaData metaData = resultSetCalories.getMetaData();
				int numberOfColumns = metaData.getColumnCount();

				try {
					Formatter workoutRecord = new Formatter("WorkOutRecord.txt");
					workoutRecord.format("%s\n", "WorkOut Records: ");

					for (int i = 1; i <= numberOfColumns; i++) {
						workoutRecord.format("%-20s\t", metaData.getColumnName(i));
					}

					workoutRecord.format("\n");

					while (resultSetCalories.next()) {
						for (int i = 1; i <= numberOfColumns; i++) {
							workoutRecord.format("%-20s\t", resultSetCalories.getObject(i));
						}
						workoutRecord.format("\n");
					}

					workoutRecord.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		if (event.getActionCommand().equals("ADD")) {
			new AppFrame(username);
		}

		if (event.getActionCommand().equals("EDIT PROFILE")) {
			new Profile(username, gender);
			dispose();
		}

		if (event.getActionCommand().equals("comboBoxChanged")) {
			combodate = (Date) comboBoxDate.getSelectedItem(); // get combobox date
			String dateAsString = combodate.toString(); // set new date as string
			JOptionPane.showMessageDialog(HomeScreen.this, "Date: " + dateAsString + " has been choosen");
			if (MapPanelExercises.containsKey(dateAsString)) { // if it is already in the map
				for (Entry<String, JPanel> entry : mapPanelExercisesKeys) {
					if (entry.getKey().equals(dateAsString)) {
						cardlayout.show(deckPanel, entry.getKey());
					}
				}
			} else {// if exercise is not in the hashmap
				panelDisplayed = new ExercisePanel(dateAsString, username); // create new panel for display
				MapPanelExercises.put(dateAsString, panelDisplayed); // puting the panel inthe hashmap
				mapPanelExercisesKeys = MapPanelExercises.entrySet(); // reinstantiate set with new entries from map
				deckPanel.add(panelDisplayed, dateAsString); // add to cardlayout
				cardlayout.show(deckPanel, dateAsString); // show panel from deck
			}
		}
	}
}