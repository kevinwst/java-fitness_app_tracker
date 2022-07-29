package fitnessAppfinal;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ExercisePanel extends JPanel {
	// class to create the panel with all exercises in the panel

	static int caloriegoal;

	public ExercisePanel(String date, String user) {
		// getting all calories form database

		try {
			Statement queryuserdata = LogIn.connection().createStatement();
			String queryString = "SELECT gender,weight,height FROM user WHERE username = '".concat(user) + "'";
			queryuserdata.execute(queryString);
			ResultSet rsgender = queryuserdata.getResultSet();

			int weight=0;
			int height=0;
			String gender="";
			while (rsgender.next()) {
				gender = rsgender.getString("gender");
				weight = rsgender.getInt("weight");
				height = rsgender.getInt("height");			
			}
			if (gender.toLowerCase().equals("male")) {
				caloriegoal = (int) (66.5 + (13.75 * weight) + (5.003 * height) - (6.75 * 22));

			} else if (gender.toLowerCase().equals("female")) {
				caloriegoal = (int) ((655.1 + (9.563 *weight) + (1.850 * height)) - (4.676 * 22));
			} else {
				caloriegoal = 2000;
			}			
			setPreferredSize(new Dimension(330, 2000));
			setBackground(new Color(52, 39, 89));

			// getting all calories form database
			Statement queryCalories = LogIn.connection().createStatement();
			String mySQLQuery = "SELECT exercises.caloriesBurnt, exercises.time_minute FROM exercises INNER JOIN user ON exercises.user=user.user_id WHERE exercises.day = '"
					.concat(date + "' AND user.username= '".concat(user) + "'");
			queryCalories.execute(mySQLQuery);
			ResultSet resultSetCalories = queryCalories.getResultSet();

			int TotalCalorieBurnt = 0;
			int TotalDuration = 0;
			while (resultSetCalories.next()) {
				TotalCalorieBurnt = TotalCalorieBurnt + resultSetCalories.getInt("caloriesBurnt");
				TotalDuration = TotalDuration + resultSetCalories.getInt("time_minute");
			}

			if (TotalCalorieBurnt == 0) {
				add(new Exercises("NO WORKOUT", 0, 0, 0, 0, 0, caloriegoal));

			} else {
				add(new Exercises("Combined ", TotalDuration, 0, 0, 0, TotalCalorieBurnt, caloriegoal));

			}

			// getting all data from exercises to create panels
			Statement queryAll = LogIn.connection().createStatement();
			mySQLQuery = "SELECT exercises.exercise_name, exercises.time_minute,exercises.distance_meter,exercises.repetitions, exercises.sets, exercises.caloriesBurnt FROM exercises INNER JOIN user ON exercises.user=user.user_id WHERE exercises.day = '"
					.concat(date + "' AND user.username= '".concat(user) + "'");
			queryAll.execute(mySQLQuery);
			ResultSet resultSetExercises = queryAll.getResultSet();

			while (resultSetExercises.next()) {
				add(new Exercises(resultSetExercises.getString("exercise_name"),
						resultSetExercises.getInt("time_minute"), resultSetExercises.getInt("distance_meter"),
						resultSetExercises.getInt("repetitions"), resultSetExercises.getInt("sets"),
						resultSetExercises.getInt("caloriesBurnt"), caloriegoal));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
