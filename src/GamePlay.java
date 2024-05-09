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
	private JPanel gamePanel;
	
	private BufferedImage backgroundImage;
	private BufferedImage pacmanOpenImage;
	private BufferedImage pacmanClosedImage;
	private BufferedImage pacmanImage;
	
	
	public GamePlay(JFrame parent) {
		this.parent = parent;
		this.setSize(600, 800);
		
		try {
			//TODO add images
			pacmanOpenImage = ImageIO.read(new File("background.png")); 
			//pacmanClosedImage = ImageIO.read(new File("pacmanClosed.png")); 
			pacmanImage = pacmanOpenImage;
			
			backgroundImage = getBackgroundImage(); 
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		gamePanel = getJPanel();
        add(gamePanel);
        setVisible(true);
		
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
				g.drawImage(pacmanImage, 0, 0, 600, 800, this);
				
				//Draw pacman
				g.drawImage(pacmanImage, 300, 400, 20, 20, this);
			}
			
		};
		gamePanel.setSize(600, 800);
		return gamePanel;
	}
	
	public abstract BufferedImage getBackgroundImage();

}
