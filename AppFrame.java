package fitnessAppfinal;

import javax.swing.JFrame;

import java.awt.*;

@SuppressWarnings("serial")
public class AppFrame extends JFrame {

    private AddExercise title;

    AppFrame(String user_id) {
    	Image icon = Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\ramhi\\Desktop\\KevinStuffs\\kevImage\\FTLogo.png");
		setIconImage(icon);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        title = new AddExercise(user_id);
        this.add(title, BorderLayout.SOUTH);
        this.pack();

    }
}