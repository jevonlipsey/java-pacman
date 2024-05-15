import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
/**
 * Creates the JPanel that displays the leaderboard based on the scores saved in the database
 */
public class Leaderboard extends JPanel{
	private static final int FONT_SIZE = 30;
	private final JFrame parent;
	private JPanel thisJPanel;
	private JLabel theScores;
	
	private HighScoreDatabase database;
	
	/**
	 * A Constructor to set up the main JPanel
	 * @param parent
	 */
	public Leaderboard(JFrame parent, boolean volumeOn) {
		database = new HighScoreDatabase();
		this.parent = parent;
		thisJPanel = this;
		
		BackgroundPanel backgroundPanel = new BackgroundPanel("leaderboard.png");
        
        JButton playButton = createTransparentButton();
        JButton menuButton = createTransparentButton();
        JLabel scores = leaderboardLabel();
        
        add(playButton);
        
        scores.setBounds(160, 265, 270, 400);
        menuButton.setBounds(20, 650, 270, 90);
        playButton.setBounds(300, 650, 270, 90);
        
        
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getContentPane().remove(thisJPanel);
                SwingUtilities.invokeLater(() -> parent.getContentPane().add(new GamePlay(parent, volumeOn)));
                parent.getContentPane().revalidate();
                parent.getContentPane().repaint();
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getContentPane().remove(thisJPanel);
                SwingUtilities.invokeLater(() -> parent.getContentPane().add(new HomeScreen(parent, volumeOn)));
                parent.getContentPane().revalidate();
                parent.getContentPane().repaint();
            }
        });   

        backgroundPanel.add(playButton);
        backgroundPanel.add(menuButton);
        backgroundPanel.add(scores);

        backgroundPanel.setPreferredSize(new Dimension(600, 800));
        backgroundPanel.setFocusable(true);
        add(backgroundPanel);
        setVisible(true);
        
	}
	
	/**
	 * This method grabs the high scores from the data base
	 * @return a jlabel containing the scores
	 */
	private JLabel leaderboardLabel() {
		
		String scoreString = "<html>";
		ArrayList<String> scores = database.getTopNScores(10);
		for(int i = 0; i < scores.size(); i++) {
			scoreString += scores.get(i) + "<br/>";
		}
		scoreString += "<html>";
		theScores = new JLabel(scoreString.toUpperCase());
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(getArcadeFont());
		theScores.setFont(getArcadeFont());
		theScores.setForeground(Color.YELLOW);
		theScores.setHorizontalAlignment(SwingConstants.CENTER);
		theScores.setVerticalAlignment(SwingConstants.NORTH);
		
		//theScores.setOpaque(false);
		return theScores;
	}
	
	/**
	 * @return transparent button
	 */
    private JButton createTransparentButton() {
        JButton button = new JButton();
        button.setContentAreaFilled(false); 
        button.setFocusPainted(false); 
        button.setBorderPainted(false); 
        button.setOpaque(false);
        return button;
    }
	
    /**
     * imports a custom arcade-style font 
     * @return the imported font
     */
    private static Font getArcadeFont() {
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
	
    
	
}
