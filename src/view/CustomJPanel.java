package view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class CustomJPanel extends JPanel{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomJPanel() {
		super();
		try {	
			image = ImageIO.read(new File("dbz.jpg"));
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this, "error no se encuentra la imagen");
		}
	}

	@Override
	public boolean isOpaque() {
		return false;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(getBackground());
		g2d.fillRect(0, 0, getWidth(), getHeight());
		if (image != null) {
			int x = getWidth() - image.getWidth();
			int y = getHeight() - image.getHeight();
			g2d.drawImage(image, x, y, this);
		}
		super.paintComponent(g2d);
		g2d.dispose();
	}

	private BufferedImage image;
}