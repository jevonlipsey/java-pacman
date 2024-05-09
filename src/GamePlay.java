import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Abstract class that holds the game logic
 */
public abstract class GamePlay extends JPanel{
	
	private final JFrame parent;
	
	private BufferedImage backgroundImage;
	private BufferedImage pacmanOpenImage;
	private BufferedImage pacmanClosedImage;
	
	
	public GamePlay(JFrame parent) {
		this.parent = parent;
		try {
			//TODO add images
			pacmanOpenImage = ImageIO.read(new File("pacmanOpen.png")); 
			pacmanClosedImage = ImageIO.read(new File("pacmanClosed.png")); 
			
			backgroundImage = getBackgroundImage(); 
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	 /**
     * Sets up the gamePanel, adds the background, characters, and key bindings.
     * @return gamePanel
     */
	public JPanel getJPanel() {
		JPanel gamePanel = new JPanel() {
			
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				
				//Draw background
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
			
		};
		
		return gamePanel;
	}
	
	public abstract BufferedImage getBackgroundImage();

}
