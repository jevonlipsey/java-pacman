import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.imageio.ImageIO;


/**
 * Abstract class that holds the game logic
 */
public abstract class GamePlay extends JPanel{
	
	private final JFrame parent;
	private JPanel gamePanel;
	private JPanel scorePanel;
	private JPanel infoPanel;
	private JLabel currentScore;
	
	private BufferedImage backgroundImage;
	private BufferedImage pacmanUpImage;
	private BufferedImage pacmanDownImage;
	private BufferedImage pacmanLeftImage;
	private BufferedImage pacmanRightImage;
	private BufferedImage pacmanClosedImage;
	private BufferedImage pacmanImage;
	
	private int pacmanDirection = UP;
	private int pacmanX = 280;
	private int pacmanY = 300;
	
    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 4;
    
    private static final int SPEED = 4;
    
    private static final int BOTTOM_GAME_BORDER = 520;
    private static final int TOP_GAME_BORDER = 23;
    
    private static final int RIGHT_BORDER = 570;
	private static final int TOP_SCREEN_EDGE = -30;
	private static final int BOTTOM_SCREEN_EDGE = 800;
	private static final int LEFT_SCREEN_EDGE = -15;
	private static final int RIGHT_SCREEN_EDGE = 570;
	
	
	public static final int GAME_PANEL_WIDTH = 550;
	public static final int GAME_PANEL_HEIGHT = 630;
	
	private static final int PACMAN_SIZE = 20;
    
    private final Timer timer;
    private final Timer mouthTimer;
    private final long startTime;
    
    private boolean mouthOpen = true;
    
    private Maze maze;
	
	
	public GamePlay(JFrame parent) {
		this.parent = parent;
		this.maze = new Maze();
		
		
		ImageIcon blackImg = null;
		
		try {
			//TODO add images
			pacmanUpImage = ImageIO.read(new File("pacmanUpOpen.png")); 
			pacmanDownImage = ImageIO.read(new File("pacmanDownOpen.png")); 
			pacmanLeftImage = ImageIO.read(new File("pacmanLeftOpen.png")); 
			pacmanRightImage = ImageIO.read(new File("pacmanRightOpen.png")); 
			pacmanClosedImage = ImageIO.read(new File("pacmanClosed.png")); 
			
			BufferedImage blackImage = ImageIO.read(new File("black.png"));
			blackImg = new ImageIcon(blackImage.getScaledInstance(50, 800, Image.SCALE_SMOOTH));
			
			backgroundImage = getBackgroundImage(); 
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		pacmanImage = pacmanUpImage;
		
		 //blank sides
        JLabel leftSide = new JLabel(blackImg);
        leftSide.setBackground(Color.black);
        leftSide.setBounds(0, 70, blackImg.getIconWidth(), blackImg.getIconHeight());
        JLabel rightSide = new JLabel(blackImg);
        rightSide.setBackground(Color.black);
        rightSide.setBounds(550, 0, blackImg.getIconWidth(), blackImg.getIconHeight());
        
        add(leftSide);
        add(rightSide);
        
		//Set layout and add panels
		gamePanel = getGamePanel();
		gamePanel.setBounds(50, 70, gamePanel.getWidth(), gamePanel.getHeight());
        add(gamePanel);
        
		scorePanel = getScorePanel();
		scorePanel.setBounds(0, 0, scorePanel.getWidth(), scorePanel.getHeight());
        add(scorePanel);
        
		infoPanel = getInfoPanel();
		infoPanel.setBounds(0, 640, infoPanel.getWidth(), infoPanel.getHeight());
        add(infoPanel);
        
       
        
        setVisible(true);
        
        setKeyBindings();
		
        // Create a timer to continuously update the sprites and objects
        timer = new Timer(20, actionEvent -> updateSprites());
        timer.start();
        
        mouthTimer = new Timer(65, actionEvent -> updateMouth());
        mouthTimer.start();
               
        startTime = System.currentTimeMillis();
	}
	
	 /**
     * Sets up the scorePanel
     * @return scorePanel
     */
	public JPanel getScorePanel() {
		JPanel scorePanel = new JPanel();
		scorePanel.setBackground(Color.BLACK);
		scorePanel.setSize(600, 70);
		scorePanel.setLayout(new GridLayout(1, 2));
		
		JLabel currentScoreLabel = new JLabel();
		currentScoreLabel.setBorder(BorderFactory.createLineBorder(Color.black, 10));
		currentScoreLabel.setLayout(new GridLayout(2, 1));
		currentScoreLabel.setBackground(Color.black);
		JLabel currentScoreText = new JLabel("Current Score");
		currentScoreText.setForeground(Color.yellow);
		currentScore = new JLabel("0"); //TODO: track score, maybe move this where it can be updated?
		currentScore.setForeground(Color.yellow);
		currentScoreLabel.add(currentScoreText);
		currentScoreLabel.add(currentScore);
		
		
		JLabel highScoreLabel = new JLabel();
		highScoreLabel.setBorder(BorderFactory.createLineBorder(Color.black, 10));
		highScoreLabel.setLayout(new GridLayout(2, 1));
		highScoreLabel.setBackground(Color.black);
		JLabel highScoreText = new JLabel("High Score");
		JLabel highScore = new JLabel("120000"); //TODO: make getHighScore() method in HighScoreDatabase class
		highScoreText.setForeground(Color.yellow);
		highScore.setForeground(Color.yellow);
		highScoreLabel.add(highScoreText);
		highScoreLabel.add(highScore);
		
		scorePanel.add(currentScoreLabel);
		scorePanel.add(highScoreLabel);
		
		return scorePanel;
	}
	
	/**
     * Sets up the infoPanel
     * @return infoPanel
     */
	public JPanel getInfoPanel() {
		JPanel infoPanel = new JPanel();
		
		infoPanel.setBackground(Color.BLACK);
		infoPanel.setSize(600, 140);
		
		return infoPanel;
	}
	
	 /**
     * Sets up the gamePanel, adds the background, characters, and key bindings.
     * @return gamePanel
     */
	public JPanel getGamePanel() {
	    JPanel gamePanel = new JPanel() {
	        
	        
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            
	            // Draw the maze
	            maze.paintComponent(g);
	            
	            // Draw Pacman
	            g.drawImage(pacmanImage, pacmanX, pacmanY, PACMAN_SIZE, PACMAN_SIZE, this);
	        }
	        
	    };
	    gamePanel.setBorder(null);
	    gamePanel.setSize(GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT);
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
  
    	
    	if (pacmanDirection == UP && !maze.isWall(pacmanX, pacmanY - SPEED)) 
    	{
    		pacmanY -= SPEED;
    		pacmanImage = pacmanUpImage;
    	}
    	else if (pacmanDirection == DOWN && !maze.isWall(pacmanX, pacmanY + SPEED + PACMAN_SIZE))
    	{
    		pacmanY += SPEED;
    		pacmanImage = pacmanDownImage;
    	}
    	else if (pacmanDirection == LEFT && !maze.isWall(pacmanX - SPEED, pacmanY))
    	{
    		pacmanX -= SPEED;
    		pacmanImage = pacmanLeftImage;
    	}
    	if (pacmanDirection == RIGHT && !maze.isWall(pacmanX + SPEED + PACMAN_SIZE, pacmanY)) {
    		pacmanX += SPEED;
    		pacmanImage = pacmanRightImage;
    	}
   
        
    	//check for edge of screen
    	
    	if (pacmanY <= TOP_SCREEN_EDGE) pacmanY = TOP_SCREEN_EDGE;
    	if (pacmanY >= BOTTOM_SCREEN_EDGE) pacmanY = BOTTOM_SCREEN_EDGE;
    	if (pacmanX <= LEFT_SCREEN_EDGE && pacmanDirection == LEFT) pacmanX = RIGHT_SCREEN_EDGE;
    	if (pacmanX >= RIGHT_SCREEN_EDGE && pacmanDirection == RIGHT) pacmanX = LEFT_SCREEN_EDGE;
    	
   
    	
    	
    	SwingUtilities.invokeLater(() -> {
            repaint();
            gamePanel.repaint();
            parent.getContentPane().repaint();
            parent.getContentPane().revalidate();
        });
    }
    
    public void updateMouth()
    {
	    // open and closes mouth
	    if (!mouthOpen)
	    {
	    pacmanImage = pacmanClosedImage;
	    }
	    mouthOpen = !mouthOpen;
    }
	
	public abstract BufferedImage getBackgroundImage();

}
