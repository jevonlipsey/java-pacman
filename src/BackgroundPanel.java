import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * This class creates a JPanel with a background image 
 */
public  class BackgroundPanel extends JPanel {
	
	private Image backgroundImage;

	/**
	 * Constructor for the class
	 * @param image - The pathname to find the image
	 */
    public BackgroundPanel(String image) {

        setLayout(null);
        backgroundImage = new ImageIcon(image).getImage();
    }

    /**
     * Draws the backgroundImage onto the JPanel
     */
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	// Draw background image
    	g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight() - 17, this);
    }
 }