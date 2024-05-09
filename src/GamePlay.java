import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

import javax.imageio.ImageIO;


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
	
	private int pacmanDirection = UP;
	private int pacmanX = 260;
	private int pacmanY = 400;
	
    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 4;
    
    private static final int SPEED = 3;
    
    private final Timer timer;
    private final long startTime;
    
	
	
	public GamePlay(JFrame parent) {
		this.parent = parent;
		this.setSize(600, 800);
		
		try {
			//TODO add images
			pacmanOpenImage = ImageIO.read(new File("pacmanOpen.png")); 
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
        
        setKeyBindings();
		
        // Create a timer to continuously update the sprites and objects
        timer = new Timer(20, actionEvent -> updateSprites());
        timer.start();
        startTime = System.currentTimeMillis();
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
				g.drawImage(backgroundImage, 0, 0, 600, 800, this);
				
				//Draw pacman
				g.drawImage(pacmanImage, pacmanX, pacmanY, 30, 30, this);
			}
			
		};
		gamePanel.setSize(600, 800);
		return gamePanel;
	}
	
	private void setKeyBindings() {
        InputMap inputMap = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0, false), "up arrow pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "down arrow pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left arrow pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right arrow pressed");


        getActionMap().put("up arrow pressed",
                new Pressed("up"));
        getActionMap().put("down arrow pressed",
                new Pressed("down"));
        getActionMap().put("left arrow pressed",
                new Pressed("left"));
        getActionMap().put("right arrow pressed",
                new Pressed("right"));

    }

    public class Pressed extends AbstractAction {
        String key;
        protected Pressed(String key){
            this.key = key;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (this.key.equals("up")) {
            	pacmanDirection = UP;
            }

            if (this.key.equals("down")){
            	pacmanDirection = DOWN;
                
            }
            if (this.key.equals("left")){
            	pacmanDirection = LEFT;
            }
            
            if (this.key.equals("right")){
            	pacmanDirection = RIGHT;
            }

        }
    }
    
    public void updateSprites() {
    	if (pacmanDirection == UP) pacmanY -= SPEED;
    	if (pacmanDirection == DOWN) pacmanY += SPEED;
    	if (pacmanDirection == LEFT) pacmanX -= SPEED;
    	if (pacmanDirection == RIGHT) pacmanX += SPEED;
    		
    	
    	SwingUtilities.invokeLater(() -> {
            repaint();
            gamePanel.repaint();
            parent.getContentPane().repaint();
            parent.getContentPane().revalidate();
        });
    }
	
	public abstract BufferedImage getBackgroundImage();

}
