package fitnessAppfinal;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


@SuppressWarnings("serial")
class ImageScaler extends JPanel {
	Image img;

	public ImageScaler(int width ,int height, String path) {
		
		ImageIcon iconLogo = new ImageIcon(path);
		Image imgLogo = iconLogo.getImage();
		img = imgLogo.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}