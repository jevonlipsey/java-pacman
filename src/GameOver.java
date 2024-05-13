import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
/**
 * The GameOver panel that pops up when you finish a game
 */
public class GameOver extends JPanel{
	private static final Font font = new Font("Dialog", Font.BOLD, 24);
	private final JFrame parent;
	private JPanel thisJPanel;
	private JLabel theScores;
	
	private HighScoreDatabase database;
	
	/**
	 * A Constructor to set up the main JPanel
	 * @param parent
	 */
	public GameOver(JFrame parent, boolean volumeOn, int score) {
		database = new HighScoreDatabase();
		this.parent = parent;
		thisJPanel = this;
		
		BackgroundPanel backgroundPanel = new BackgroundPanel("gameover.png");
        
        JLabel badLabel = createBadLabel();
        JLabel scoreLabel = createScoreLabel(score);
        
        JTextField name = createTextField();
        
        JButton enterButton = createTransparentButton();
        enterButton.setBounds(295, 490, 140, 75);
        
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(name.getText().length() != 3) {
            		badLabel.setVisible(true);
            		return;
            	}
            	String nameString = name.getText();
            	database.addNewScore(score, nameString);
                parent.getContentPane().remove(thisJPanel);
                SwingUtilities.invokeLater(() -> parent.getContentPane().add(new Leaderboard(parent, volumeOn)));
                parent.getContentPane().revalidate();
                parent.getContentPane().repaint();
            }
        });

        
        backgroundPanel.add(enterButton);
        backgroundPanel.add(scoreLabel);
        backgroundPanel.add(name);
        backgroundPanel.add(badLabel);

        backgroundPanel.setPreferredSize(new Dimension(600, 800));
        backgroundPanel.setFocusable(true);
        add(backgroundPanel);
        setVisible(true);
        
	}
	
	/**
	 * @param score
	 * @return the label that displays the final score
	 */
	private JLabel createScoreLabel(int score) {
		String scoreString = Integer.toString(score);
		JLabel scoreLabel = new JLabel(scoreString);
		
		scoreLabel.setForeground(Color.BLUE);
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 30));
        scoreLabel.setBounds(280, 280, 270, 90);
		return scoreLabel;
	}
	
	/**
	 * @return the text field for the user to enter their initials
	 */
	private JTextField createTextField() {
		JTextField name = new JTextField();
        name.setOpaque(false);
        name.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        name.setFont(new Font("Serif", Font.BOLD, 40));
        name.setForeground(Color.BLUE);
        name.setBounds(170, 500, 90, 50);
        return name;
	}
	
	/**
	 * @return a label that pops up when the user has an invalid entry
	 */
	private JLabel createBadLabel() {
		JLabel badLabel = new JLabel("Invalid: enter 3 letter initial");
		badLabel.setForeground(Color.RED);
		badLabel.setFont(new Font("Serif", Font.BOLD, 20));
		badLabel.setOpaque(false);
		badLabel.setBounds(270, 600, 280, 50);
		badLabel.setVisible(false);
		
		return badLabel;
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
	
	
	

	
}
