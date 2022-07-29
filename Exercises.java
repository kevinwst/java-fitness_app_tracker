package fitnessAppfinal;


import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Exercises extends JPanel {
	// class to create individual exercise panel
	private static JProgressBar progressBarCalorie;
	JLabel lblExercise;
	JLabel lblCalories;
	JLabel lblTime;
	JLabel lblDistance;
	JLabel lblRepetitions;
	JLabel lblSets;
	JLayeredPane layer;
	JPanel background;

	public Exercises(String exerciseName, int time, int distance, int repetitions, int sets, int calorieNumber,
			int calorieGoal) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(250, 80));
		//setBackground(new Color(100, 240, 220));
		
		//====== layerpane==========
		layer = new JLayeredPane();
		layer.setSize(new Dimension(250, 80));
		add(layer);
		
		lblExercise = new JLabel(exerciseName);
		lblExercise.setBounds(15, 5, 125, 20);
		lblExercise.setOpaque(false);
		layer.add(lblExercise, Integer.valueOf(1));

		lblCalories = new JLabel("Calorie burnt: " + calorieNumber + " kcal");
		lblCalories.setBounds(100, 5, 130, 20);
		lblCalories.setOpaque(false);
		layer.add(lblCalories, Integer.valueOf(1));

		lblTime = new JLabel("Duration: " + Integer.toString(time) + " min");
		lblTime.setBounds(15, 25, 100, 20);
		lblTime.setOpaque(false);
		layer.add(lblTime, Integer.valueOf(1));

		if (distance != 0) {
			lblDistance = new JLabel("Distance: " + Integer.toString(distance) + " m");
			lblDistance.setBounds(115, 25, 100, 20);
			lblDistance.setOpaque(false);
			//setBackground(new Color(10, 240, 220));
			layer.add(lblDistance, Integer.valueOf(1));

			background= new ImageScaler(250,80,"C:\\Users\\ramhi\\Desktop\\KevinStuffs\\kevImage\\background2.jpg");

			
		} else if (repetitions != 0) {
			//setBackground(new Color(200, 240, 220));
			background= new ImageScaler(250,80,"C:\\Users\\ramhi\\Desktop\\KevinStuffs\\kevImage\\background1.jpg");

			
			lblRepetitions = new JLabel("Reps: " + Integer.toString(repetitions));
			lblRepetitions.setBounds(125, 25, 50, 20);
			lblRepetitions.setOpaque(false);
			layer.add(lblRepetitions, Integer.valueOf(1));

			lblSets = new JLabel("sets: " + Integer.toString(sets));
			lblSets.setBounds(190, 25, 50, 20);
			lblSets.setOpaque(false);
			layer.add(lblSets, Integer.valueOf(1));

		} else {
			background= new ImageScaler(250,80,"C:\\Users\\ramhi\\Desktop\\KevinStuffs\\kevImage\\background3.jpg");

			//setBackground(new Color(200, 100, 220));
		}
		background.setBounds(0, 0, 250, 80);
		background.setOpaque(true);
		layer.add(background,Integer.valueOf(0));
		
		float percentage = (calorieNumber * 100) / calorieGoal;
		progressBarCalorie = new JProgressBar(SwingConstants.HORIZONTAL);
		progressBarCalorie.setValue((int) percentage);
		progressBarCalorie.setStringPainted(true);
		progressBarCalorie.setVisible(true);
		progressBarCalorie.setBounds(20, 50, 210, 20);
		progressBarCalorie.setOpaque(true);
		layer.add(progressBarCalorie, Integer.valueOf(1));
	}
	

}
