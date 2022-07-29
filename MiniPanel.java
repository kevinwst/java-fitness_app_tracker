package fitnessAppfinal;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class MiniPanel extends JPanel implements ActionListener {
	JPanel panelDeck;
	JPanel panel1;
	JPanel panel2;
	JButton btnSwap;
	JButton btnSave;
	JLabel lbltxt;
	Border blackline;

	JTextField txtFieldname;

	String attribute;
	CardLayout cardlayout = new CardLayout();
	private String username;
	private String value;

	// constructor for achievements
	public MiniPanel(String nameColumn, String username) {

		Border blackline = BorderFactory.createLineBorder(new Color(52, 39, 89));

		panelDeck = new JPanel();

		panelDeck.setLayout(cardlayout);
		panelDeck.setPreferredSize(new Dimension(450, 50));
		panelDeck.setBorder(blackline);
		add(panelDeck);

		try {
			Statement queryAll = LogIn.connection().createStatement();
			String mySQLQuery = "SELECT SUM(".concat(nameColumn)
					+ ") FROM user INNER JOIN exercises ON exercises.user = user.user_id WHERE username='"
							.concat(username)
					+ "'";
			queryAll.execute(mySQLQuery);
			ResultSet rsUserData = queryAll.getResultSet();

			int data = 0;
			while (rsUserData.next()) {

				data = rsUserData.getInt("SUM(" + nameColumn + ")");

			}
			if (nameColumn.equals("caloriesBurnt")) {
				lbltxt = new JLabel("Total " + nameColumn + " = " + data + " Calories");

			} else if (nameColumn.equals("distance_meter")) {
				lbltxt = new JLabel("Total " + nameColumn + " = " + data + " Meter");

			} else if (nameColumn.equals("time_minute")) {
				lbltxt = new JLabel("Total " + nameColumn + " = " + data + " Minutes");

			}

			lbltxt.setBounds(20, 13, 250, 24);

			// button of view
			btnSwap = new JButton("VIEW");
			btnSwap.addActionListener(this);
			btnSwap.setFocusable(false);
			btnSwap.setBounds(370, 13, 70, 24);

			panel1 = new JPanel();
			panel1.setLayout(null);
			panel1.setBackground(new Color (100,100,100));
			panelDeck.add(panel1, "1");

			panel1.add(lbltxt);
			panel1.add(btnSwap);

			// panel2 with option to view more
			btnSwap = new JButton("BACK");
			btnSwap.addActionListener(this);
			btnSwap.setFocusable(false);
			btnSwap.setBounds(370, 13, 70, 24);

			if (nameColumn.equals("caloriesBurnt")) {
				int drumstick = data / 76; // 76calories in 1 drumstick
				lbltxt = new JLabel("Equivalent to " + drumstick + " Chicken Drumstick");
			} else if (nameColumn.equals("distance_meter")) {
				float km = data / 1000; // 42 km is 1 marathon
				lbltxt = new JLabel("Equivalent to " + km + " km");

			} else if (nameColumn.equals("time_minute")) {
				float hrs = data / 60; // minute in a day
				lbltxt = new JLabel("Equivalent to " + hrs + " of days");

			}
			lbltxt.setBounds(20, 13, 250, 24);

			panel2 = new JPanel();
			panel2.setLayout(null);
			panel2.setBackground(new Color(100,100,100));
			panelDeck.add(panel2, "2");
			panel2.add(btnSwap);
			panel2.add(lbltxt);

			cardlayout.show(panelDeck, "1");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// constructor for bmi
	public MiniPanel(float weight, float height) {
		
		DecimalFormat decimal = new DecimalFormat("000.0");
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		panelDeck = new JPanel();
		panelDeck.setLayout(cardlayout);
		panelDeck.setPreferredSize(new Dimension(450, 50));

		add(panelDeck);
		// label of name of attribute and value
		height = height / 100;
		String number =decimal.format( weight / (height * height));
		float bmi=Float.parseFloat(number);


		String body = "";
		if (bmi < 18.5) {
			blackline = BorderFactory.createLineBorder(Color.BLUE);
			body = "Underweight";
			panel1.setBackground(new Color(250,200,100));
			panel2.setBackground(new Color(250,200,100));

		} else if (bmi > 30) {
			blackline = BorderFactory.createLineBorder(Color.blue);
			body = "Overweight";
			panel1.setBackground(new Color(255,80,80));
			panel2.setBackground(new Color(255,80,80));

		} else {
			blackline = BorderFactory.createLineBorder(Color.blue);
			body = "Normal";
			panel1.setBackground(new Color(240,240,255));
			panel2.setBackground(new Color(240,240,255));

		}
		panelDeck.setBorder(blackline);

		lbltxt = new JLabel("Body Mass Index(BMI): " + bmi+" = "+body);
		lbltxt.setBounds(20, 13, 300, 24);

		// button of view
		btnSwap = new JButton("VIEW");
		btnSwap.addActionListener(this);
		btnSwap.setFocusable(false);
		btnSwap.setBounds(370, 13, 70, 24);

		// the panel that will contain lbl
		panel1.setLayout(null);
		panelDeck.add(panel1, "1");
		panel1.add(lbltxt);
		panel1.add(btnSwap);

		// panel2 with option to view more
		btnSwap = new JButton("BACK");
		btnSwap.addActionListener(this);
		btnSwap.setFocusable(false);
		btnSwap.setBounds(370, 13, 70, 24);
		
		float mass =0;
		if (bmi< 18.5) {
			//UNDERWEIGHT
			String stringMass=decimal.format((18.5-bmi)*height*height);
			mass= Float.parseFloat(stringMass);
			lbltxt = new JLabel("You need to take "+mass+" kg");


		} else if (bmi> 30) {
			//OVERWEIGHT
			String stringMass= decimal.format((bmi-30)*height*height);
			mass= Float.parseFloat(stringMass);

			lbltxt = new JLabel("You need to lose "+mass+" kg");


		} else {
			//NORMAL
			lbltxt = new JLabel("Your weight is good");


		}
		lbltxt.setBounds(20, 13, 180, 24);
		panel2.add(lbltxt);

		lbltxt = new JLabel("currently " + weight+ " kg");
		
		lbltxt.setBounds(200, 13, 150, 24);
		panel2.add(lbltxt);

		panel2.setLayout(null);
		panelDeck.add(panel2, "2");
		panel2.add(btnSwap);

	}

	// constructor for minipanels
	public MiniPanel(String attribute, String value, String username) {
		this.attribute = attribute;
		this.value=value;
		this.username = username;
		Border blackline = BorderFactory.createLineBorder(Color.black);

		panelDeck = new JPanel();
		panelDeck.setLayout(cardlayout);
		panelDeck.setPreferredSize(new Dimension(450, 50));
		panelDeck.setBorder(blackline);
		add(panelDeck);

		char symbol;
		// label of name of attribute and value
		if (attribute.equals("weight")) {
			lbltxt = new JLabel(attribute.toUpperCase() + ": " + value + " kg");
		} else if (attribute.equals("height")) {
			lbltxt = new JLabel(attribute.toUpperCase() + ": " + value + " cm");
		} else if (value.equals("male")) {
			symbol = (char) 9794;
			lbltxt = new JLabel(attribute.toUpperCase() + ": " + value + " " + symbol);

		} else if (value.equals("female")) {
			symbol = (char) 9792;
			lbltxt = new JLabel(attribute.toUpperCase() + ": " + value + " " + symbol);

		} else {
			lbltxt = new JLabel(attribute.toUpperCase() + ": " + value);

		}
		lbltxt.setBounds(20, 13, 175, 24);

		// button of edit
		btnSwap = new JButton("EDIT");
		btnSwap.addActionListener(this);
		btnSwap.setFocusable(false);
		btnSwap.setBounds(370, 13, 70, 24);

		// the panel that will contain lbl and edit
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBackground(Color.WHITE);
		panelDeck.add(panel1, "1");
		panel1.add(lbltxt);
		panel1.add(btnSwap);

		// lbl of changed panel
		lbltxt = new JLabel("New " + attribute.toUpperCase());
		lbltxt.setBounds(20, 13, 100, 24);

		// txtfield of change value
		txtFieldname = new JTextField();
		txtFieldname.setPreferredSize(new Dimension(150, 20));
		txtFieldname.setBounds(130, 13, 150, 24);

		// btn save
		btnSave = new JButton("SAVE");
		btnSave.addActionListener(this);
		btnSave.setBounds(290, 13, 70, 24);

		// btnback
		btnSwap = new JButton("BACK");
		btnSwap.addActionListener(this);
		btnSwap.setBounds(370, 13, 70, 24);

		// panel2 with option to change name
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBackground(Color.WHITE);
		panelDeck.add(panel2, "2");
		panel2.add(lbltxt);
		panel2.add(txtFieldname);
		panel2.add(btnSave);
		panel2.add(btnSwap);

		cardlayout.show(panelDeck, "1");

	}

	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("EDIT") || event.getActionCommand().equals("VIEW")) {
			cardlayout.show(panelDeck, "2");
		}
		if (event.getActionCommand().equals("BACK")) {
			cardlayout.show(panelDeck, "1");
		}
		if (event.getActionCommand().equals("SAVE")) {
			try {
				Statement queryUpdate = LogIn.connection().createStatement();
				String mySQLQuery = "UPDATE user SET ".concat(attribute)
						+ "='".concat(txtFieldname.getText() + "' WHERE username = '".concat(username) + "'");
				int answer = JOptionPane.showConfirmDialog(null, "Do you wish to change "+value+" to "+txtFieldname.getText()+" ? ", "UPDATE",JOptionPane.YES_NO_CANCEL_OPTION);
				if (answer == 0) {
					queryUpdate.execute(mySQLQuery);
					JOptionPane.showMessageDialog(null, attribute+ " changed!","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
				}else {
					txtFieldname.setText("");
				}
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
