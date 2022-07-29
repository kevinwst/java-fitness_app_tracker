package fitnessAppfinal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelTop extends JPanel {
	// class to create the top panel with logo and label
	JLabel lblLogo; 
	JLabel lblFitness;
	public PanelTop() {

		setBackground(new Color(52, 39, 89));
		setLayout(new FlowLayout(FlowLayout.CENTER,25,10));
		
		// add logo on left
		lblLogo = new JLabel();
		lblLogo.setSize(new Dimension(50, 50));
		ImageIcon iconLogo = new ImageIcon("C:\\Users\\ramhi\\Desktop\\KevinStuffs\\kevImage\\FTLogo.png");
		Image imgLogo = iconLogo.getImage();
		Image finalImgLogo = imgLogo.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		iconLogo = new ImageIcon(finalImgLogo);
		lblLogo.setIcon(iconLogo);
		add(lblLogo);

		// label Fitness app
		lblFitness = new JLabel("FITNESS TRACKER");
		lblFitness.setHorizontalAlignment(JLabel.CENTER);
		lblFitness.setPreferredSize(new Dimension(300, 50));
		lblFitness.setFont(new Font("Serif", Font.BOLD, 30));
		lblFitness.setForeground(Color.white);
		add(lblFitness);

		// add logo on right
		lblLogo = new JLabel();
		lblLogo.setSize(new Dimension(50, 50));
		lblLogo.setIcon(iconLogo);
		add(lblLogo);
	}
}
