import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.imageio.ImageIO;


/**
 * Abstract class that holds the game logic
 */
public class GamePlay extends JPanel{
	
	
	private static final int UP = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;
	private static final int INVALID = -1;
	
	// the width and height of the text map
	private static final int tileWidth = 21;
	private static final int tileHeight = 26; 
	 
	public static final int GAME_PANEL_WIDTH = 550;
	public static final int GAME_PANEL_HEIGHT = 630;
	
	private static final int FONT_SIZE = 20;
		
	private static final int PACMAN_SIZE = 20;

	private final JFrame parent;
	private JPanel thisGamePlayPanel;
	private JPanel gamePanel;
	private JPanel scorePanel;
	private JPanel infoPanel;
	private JLabel currentScore;
	
	private BufferedImage pacmanUpImage;
	private BufferedImage pacmanDownImage;
	private BufferedImage pacmanLeftImage;
	private BufferedImage pacmanRightImage;
	private BufferedImage pacmanClosedImage;
	private BufferedImage pacmanImage;
	
	private BufferedImage pinkyImage;
	private BufferedImage clydeImage;
	private BufferedImage inkyImage;
	private BufferedImage blinkyImage;
	
	private int score;
	
	private int pacmanDirection;
	private int nextDirection;
	private int pacmanColumn;
	private int pacmanRow;
	private boolean pacMouthOpen;
	private int lives = 3;
	
	private int blinkyRow;
	private int blinkyColumn;
	private int pinkyRow;
	private int pinkyColumn;
	private int inkyRow;
	private int inkyColumn;
	private int clydeRow;
	private int clydeColumn;

	
    private final Timer timer;
    private final Timer mouthTimer;
    private final long startTime;
    
    private long lastEventTime;
    
    private Map map;
    

    private JPanel thisJPanel;
	
	/**
	 * Constructor for the GamePlay class
	 * Loads all of the images, sets the layout, and starts the timer for the game.
	 * @param parent - the main JFrame for the pacman game
	 */
	public GamePlay(JFrame parent) 
	{
		lastEventTime = System.currentTimeMillis();
		this.parent = parent;
		thisGamePlayPanel = this;
		setSize(600, 800);
		setLayout(null);
		this.thisJPanel = this;
		
		
		ImageIcon blackImg = null;
		
		try
		{
			pacmanUpImage = ImageIO.read(new File("pacmanUpOpen.png")); 
			pacmanDownImage = ImageIO.read(new File("pacmanDownOpen.png")); 
			pacmanLeftImage = ImageIO.read(new File("pacmanLeftOpen.png")); 
			pacmanRightImage = ImageIO.read(new File("pacmanRightOpen.png")); 
			pacmanClosedImage = ImageIO.read(new File("pacmanClosed.png")); 
			
			pinkyImage = ImageIO.read(new File("pinky.png")); 
			clydeImage = ImageIO.read(new File("clyde.png")); 
			inkyImage = ImageIO.read(new File("inky.png")); 
			blinkyImage = ImageIO.read(new File("blinky.png")); 
			
			BufferedImage blackImage = ImageIO.read(new File("black.png"));
			blackImg = new ImageIcon(blackImage.getScaledInstance(50, 800, Image.SCALE_SMOOTH));
		}
		catch(IOException e)
		{
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
        timer = new Timer(120, actionEvent -> updateSprites());
        timer.start();
        
        mouthTimer = new Timer(30, actionEvent -> updateMouth());
        mouthTimer.start();
               
        startTime = System.currentTimeMillis();
        
        setupLevel();
	}
	
	/**
	 * Initializes the level of the game.
	 */
	public void setupLevel() 
	{
		
		this.map = new Map();
		resetPositions();
	}
	
	public void resetPositions()
	{
		pacmanColumn = 5;
		pacmanRow = 24;
		pacMouthOpen = true;
		pacmanDirection = INVALID;
		nextDirection = INVALID;
	
		blinkyColumn = 10;
		blinkyRow = 11;
		pinkyColumn = 10;
		pinkyRow =13 ;
		inkyColumn = 9;
		inkyRow =13 ;
		clydeColumn = 11 ;
		clydeRow = 13;
	}
	
	 /**
     * Sets up the scorePanel
     * @return scorePanel
     */
	public JPanel getScorePanel()
	{
		JPanel scorePanel = new JPanel();
		scorePanel.setBackground(Color.BLACK);
		scorePanel.setSize(600, 70);
		scorePanel.setLayout(new GridLayout(1, 2));
		
		JLabel currentScoreLabel = new JLabel();
		currentScoreLabel.setBorder(BorderFactory.createLineBorder(Color.black, 10));
		currentScoreLabel.setLayout(new GridLayout(2, 1));
		currentScoreLabel.setBackground(Color.black);
		JLabel currentScoreText = new JLabel("Current Score");
		currentScoreText.setFont(getArcadeFont());
		currentScoreText.setForeground(Color.yellow);
		currentScore = new JLabel("0");
		currentScore.setFont(getArcadeFont());
		currentScore.setForeground(Color.yellow);
		currentScoreLabel.add(currentScoreText);
		currentScoreLabel.add(currentScore);
		
		
		JLabel highScoreLabel = new JLabel();
		highScoreLabel.setBorder(BorderFactory.createLineBorder(Color.black, 10));
		highScoreLabel.setLayout(new GridLayout(2, 1));
		highScoreLabel.setBackground(Color.black);
		JLabel highScoreText = new JLabel("High Score");
		highScoreText.setFont(getArcadeFont());
		highScoreText.setForeground(Color.yellow);
		HighScoreDatabase scores = new HighScoreDatabase();
		String topScore;
		topScore = scores.getTopScoreNumber();
		JLabel highScore = new JLabel(topScore); 
		highScore.setFont(getArcadeFont());
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
	public JPanel getInfoPanel() 
	{
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(null);
		
		
		JLabel life1 = new JLabel(new ImageIcon(new ImageIcon(pacmanRightImage).getImage().getScaledInstance(PACMAN_SIZE, PACMAN_SIZE, Image.SCALE_SMOOTH)));
		JLabel life2 = new JLabel(new ImageIcon(new ImageIcon(pacmanRightImage).getImage().getScaledInstance(PACMAN_SIZE, PACMAN_SIZE, Image.SCALE_SMOOTH)));
		JLabel life3 = new JLabel(new ImageIcon(new ImageIcon(pacmanRightImage).getImage().getScaledInstance(PACMAN_SIZE, PACMAN_SIZE, Image.SCALE_SMOOTH)));
	
		life3.setBounds(110,95,PACMAN_SIZE,PACMAN_SIZE);
		life2.setBounds(50,95,PACMAN_SIZE,PACMAN_SIZE);
		life1.setBounds(80,95,PACMAN_SIZE,PACMAN_SIZE);
		
		JLabel[] livesArray = {life1, life2, life3};
		
		infoPanel.setBackground(Color.black);
		infoPanel.setSize(600, 140);
		
		for (int i = 0; i < lives; i++)
		{
			infoPanel.add(livesArray[i]);
			livesArray[i].setVisible(true);
		}
		
		for (int i = 0; i > lives - 3; i--)
		{
			
			livesArray[i].setBounds(0,0,PACMAN_SIZE,PACMAN_SIZE);
		}
		
		System.out.print(lives);
		
		
		return infoPanel;
	}
	
	 /**
     * Sets up the gamePanel, adds the background, characters, and key bindings.
     * @return gamePanel
     */
	public JPanel getGamePanel() 
	{
	    JPanel gamePanel = new JPanel() 
	    {
	        
	        
	        @Override
	        protected void paintComponent(Graphics g) 
	        {
	            super.paintComponent(g);
	            
	            // Draw the maze
	            map.paintComponent(g);
	            
	            // Draw Pacman
	            g.drawImage(pacmanImage, pacmanColumn * Map.CELL, pacmanRow * Map.CELL, 
	            			PACMAN_SIZE, PACMAN_SIZE, this);
	            
	            g.drawImage(blinkyImage, blinkyColumn * Map.CELL, blinkyRow*Map.CELL, PACMAN_SIZE, PACMAN_SIZE, this);
	            g.drawImage(pinkyImage, pinkyColumn * Map.CELL, pinkyRow*Map.CELL, PACMAN_SIZE, PACMAN_SIZE, this);
	            g.drawImage(inkyImage, inkyColumn * Map.CELL, inkyRow*Map.CELL, PACMAN_SIZE, PACMAN_SIZE, this);
	            g.drawImage(clydeImage, clydeColumn * Map.CELL, clydeRow*Map.CELL, PACMAN_SIZE, PACMAN_SIZE, this);
	            
	        }
	        
	    };
	    gamePanel.setBorder(null);
	    gamePanel.setSize(GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT);
	    return gamePanel;
	}

	
	/**
	 * Sets up all of the keyBindings 
	 */
	private void setKeyBindings()
	{
        InputMap inputMap = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0, false), "up arrow pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "down arrow pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left arrow pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right arrow pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "space bar pressed");


        getActionMap().put("up arrow pressed",
                new Pressed("up"));
        getActionMap().put("down arrow pressed",
                new Pressed("down"));
        getActionMap().put("left arrow pressed",
                new Pressed("left"));
        getActionMap().put("right arrow pressed",
                new Pressed("right"));
        getActionMap().put("space bar pressed",
                new Pressed("space"));

    }

	/**
	 * Class for the KeyBindings that determines what each key press does.
	 */
    public class Pressed extends AbstractAction
    {
        String key;
        protected Pressed(String key)
        {
            this.key = key;
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if (this.key.equals("up"))
            {
            	   if (!map.isWall(pacmanColumn, pacmanRow - 1)) 
                   {
                       pacmanDirection = UP;
                       nextDirection = UP;
                   }
            	   else nextDirection = UP;
            }

            if (this.key.equals("down"))
            {
            	if (!map.isWall(pacmanColumn, pacmanRow + 1)) 
                {
                    pacmanDirection = DOWN;
                    nextDirection = DOWN;
                }
            	else nextDirection = DOWN;
            }
            if (this.key.equals("left"))
            {
            	if (!map.isWall(pacmanColumn - 1, pacmanRow)) 
                {
                    pacmanDirection = LEFT;
                    nextDirection = LEFT;
                }
            	else nextDirection = LEFT;
            }
            
            if (this.key.equals("right"))
            {
            	if (!map.isWall(pacmanColumn + 1, pacmanRow)) 
                {
                    pacmanDirection = RIGHT;
                    nextDirection = RIGHT;
                }
            	else nextDirection = RIGHT;
            }

            if (this.key.equals("space"))
            {
            	timer.stop();
            	showPauseOptionPane();
            }

        }
    }

    /**
     * Updates the movement of the pacman and the ghosts on the screen
     */
    public void updateSprites() {
    	
    	updatePacman();
        
        updateMap();

        SwingUtilities.invokeLater(() -> {
            repaint();
            gamePanel.repaint();
            parent.getContentPane().repaint();
            parent.getContentPane().revalidate();
        });
        
    }
    
    /**
     * Open and closes pacman's mouth
     */
    public void updateMouth()
    {
	    // open and closes mouth
	    if (!pacMouthOpen)
	    {
	    pacmanImage = pacmanClosedImage;
	    }
	    
	    pacMouthOpen = !pacMouthOpen;
	   
    }
    /**
     * Updates pacmans movement and mouth animation
     */
    public void updatePacman()
    {
    	//Check for opening for the next direction clicked
    	if (nextDirection == UP && !map.isWall(pacmanColumn, pacmanRow - 1)) pacmanDirection = nextDirection;
    	else if (nextDirection == DOWN && !map.isWall(pacmanColumn, pacmanRow + 1)) pacmanDirection = nextDirection;
    	else if (nextDirection == LEFT && !map.isWall(pacmanColumn - 1, pacmanRow)) pacmanDirection = nextDirection;
    	else if (nextDirection == RIGHT && !map.isWall(pacmanColumn + 1, pacmanRow)) pacmanDirection = nextDirection;

    	if (pacmanDirection == UP && !map.isWall(pacmanColumn, pacmanRow - 1))
        {
        	pacmanRow -= 1;
            pacmanImage = pacmanUpImage;
            
        } 
        else if (pacmanDirection == DOWN && !map.isWall(pacmanColumn, pacmanRow + 1))
        {
            pacmanRow += 1;
            pacmanImage = pacmanDownImage;
        }
        else if (pacmanDirection == LEFT && !map.isWall(pacmanColumn - 1, pacmanRow)) 
        {
            pacmanColumn -=1;
            pacmanImage = pacmanLeftImage;
        } 
        else if (pacmanDirection == RIGHT && !map.isWall(pacmanColumn + 1, pacmanRow)) 
        {
            pacmanColumn += 1;
            pacmanImage = pacmanRightImage;
        }

        // Check for edge of screen to cross over
        if (pacmanColumn < 0) pacmanColumn = tileWidth - 1;
        if (pacmanColumn >= tileWidth) pacmanColumn = 0;
        if (pacmanRow < 0) pacmanRow = tileHeight - 1;
        if (pacmanRow >= tileHeight) pacmanRow = 0;
        
        
        updateMouth();

    }
    /**
     * Allows pacman to eat pellets and beat levels
     */
    public void updateMap()
    {
    	// update map food
        if (map.getCells()[pacmanRow][pacmanColumn].getType() == 'p') {
            // Pacman is on a pill, so change the cell to an empty cell
            map.getCells()[pacmanRow][pacmanColumn].setType('o');
            score += 10;
            currentScore.setText(score + "");
            
            long currentTime = System.currentTimeMillis();
           
            if (lastEventTime - currentTime < -380) {
                (new ChompMusicPlayer()).start();
                lastEventTime = System.currentTimeMillis();


            }
            //}
            
//            
        }
        // update map power pellets
        if (map.getCells()[pacmanRow][pacmanColumn].getType() == 'P') {
            // Pacman is on an energizer, so change the cell to an empty cell
            map.getCells()[pacmanRow][pacmanColumn].setType('o');
            score += 50;
            currentScore.setText(score + "");
            (new ExtraMusicPlayer()).start();
        }
        
        // 'new' level once all pellets are eaten
        if (allPelletsGone()) {
            setupLevel();
            
        }
        
        // update pacman lives
        if ((pacmanColumn == blinkyColumn && pacmanRow == blinkyRow) ||
                (pacmanColumn == pinkyColumn && pacmanRow == pinkyRow) ||
                (pacmanColumn == inkyColumn && pacmanRow == inkyRow) ||
                (pacmanColumn == clydeColumn && pacmanRow == clydeRow)) {
                // Pacman is in the same cell as a ghost, so he loses a life
                lives--;
                if (lives <= 0) {
                    // TODO: Game over screen
                	parent.getContentPane().remove(thisJPanel);
                	SwingUtilities.invokeLater(() -> parent.getContentPane().add(new GameOver(parent, score)));;
                    parent.getContentPane().revalidate();
                    parent.getContentPane().repaint();
                	
                	
                } else {
                	resetPositions();
                	pacmanDirection = INVALID;
                }
            }
          
    }
    /**
     * Checks all cells of the map for pellets and power pellets
     * @return
     */
    public boolean allPelletsGone() {
        for (int row = 0; row < map.getCells().length; row++)
        {
            for (int column = 0; column < map.getCells()[row].length; column++) 
            {
                if (map.getCells()[row][column].getType() == 'p' 
                	|| map.getCells()[row][column].getType() == 'P' )
                {
                    
                    return false;
                }
            }
        }
        // return true if no pellets found
        return true;
    }

    
    /**
     * Shows the JOptionPane that pops up after a player hits pause, gives the options to exit or continue playing
     */
    private void showPauseOptionPane() {
        JPanel readyPanel = new JPanel(new BorderLayout());
        ImageIcon backgroundImage = new ImageIcon("pauseScreen.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        ImageIcon scaledBackgroundImage = new ImageIcon(backgroundImage.getImage().getScaledInstance(500, 680, Image.SCALE_SMOOTH));
        backgroundLabel.setIcon(scaledBackgroundImage);
        backgroundLabel.setBorder(null);
        backgroundLabel.setLayout(null);
        backgroundLabel.setPreferredSize(new Dimension(499, 679));
        JButton continueButton = createTransparentButton();
        continueButton.setBounds(150, 190, 212, 135); 
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window parentWindow = SwingUtilities.getWindowAncestor(continueButton);
                parentWindow.dispose();
                timer.start();
                //TODO: implement resume game
            }
        });
        JButton exitButton = createTransparentButton();
        exitButton.setBounds(150, 350, 212, 135);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	  Window parentWindow = SwingUtilities.getWindowAncestor(exitButton);
                  parentWindow.dispose();
                  parent.getContentPane().remove(thisGamePlayPanel);
                  SwingUtilities.invokeLater(() -> parent.getContentPane().add(new HomeScreen(parent)));
                  parent.getContentPane().revalidate();
                  parent.getContentPane().repaint();
            }
        });
        
        backgroundLabel.add(continueButton);
        backgroundLabel.add(exitButton);
        readyPanel.add(backgroundLabel, BorderLayout.CENTER);
        readyPanel.setOpaque(true);
        JOptionPane.showOptionDialog(null, readyPanel, "Game Paused", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
    }
	/**
	 * Gets pacmans current X position
	 * @return x position
	 */
    public int getPacmanColumn() {
    	return pacmanColumn;
    }
    /**
     * Gets pacmans current Y position
     * @return y position
     */
    public int pacmanRow() {
    	return pacmanRow;
    }
    
    /**
     * imports a custom arcade-style font 
     * @return the imported font
     */
    public static Font getArcadeFont() {
    	// Get custom font
        Font arcadeFont = null;
        try {
            InputStream is = new FileInputStream(new File("ARCADECLASSIC.TTF"));
            arcadeFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, FONT_SIZE);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        assert arcadeFont != null;
        return arcadeFont;
    }
    
    /**
	 * @return transparent button
	 */
    public JButton createTransparentButton() {
        JButton button = new JButton();
        button.setContentAreaFilled(false); 
        button.setFocusPainted(false); 
        button.setBorderPainted(false); 
        button.setOpaque(false);
        return button;
    }

}
