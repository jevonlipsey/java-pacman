import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public  class BackgroundPanel extends JPanel {
	
	private Image backgroundImage;

    public BackgroundPanel(String image) {

        setLayout(null);
        backgroundImage = new ImageIcon(image).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	// Draw background image
    	g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight() - 17, this);
    }
 }