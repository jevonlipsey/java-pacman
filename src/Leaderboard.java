import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class Leaderboard extends JPanel{
	private static final Font font = new Font("Dialog", Font.BOLD, 24);
	private final JFrame parent;
	private JPanel thisJPanel;
	private JLabel theScores;
	
	private HighScoreDatabase database;
	
	/**
	 * A Constructor to set up the main JPanel
	 * @param parent
	 */
	public Leaderboard(JFrame parent) {
		this.parent = parent;
		thisJPanel = this;
		
		BackgroundPanel backgroundPanel = new BackgroundPanel("leaderboard.png");
        
        JButton playButton = createTransparentButton();
        JButton menuButton = createTransparentButton();
        
        add(playButton);
        
        menuButton.setBounds(20, 650, 270, 90);
        playButton.setBounds(300, 650, 270, 90);
        
        
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getContentPane().remove(thisJPanel);
                SwingUtilities.invokeLater(() -> parent.getContentPane().add(new LayoutOne(parent)));
                parent.getContentPane().revalidate();
                parent.getContentPane().repaint();
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getContentPane().remove(thisJPanel);
                SwingUtilities.invokeLater(() -> parent.getContentPane().add(new HomeScreen(parent)));
                parent.getContentPane().revalidate();
                parent.getContentPane().repaint();
            }
        });

   

        backgroundPanel.add(playButton);
        backgroundPanel.add(menuButton);

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
		theScores = new JLabel(scoreString, SwingConstants.CENTER);
		//ArrayList<String> scores = database.getTopNScores(10);
		//for(int i = 0; i < scores.size(); i++) {
		//	scoreString += scores.get(i) + "<br/>";
		//}
		scoreString += "<html>";
		theScores.setText(scoreString);
		theScores.setBackground(Color.BLUE);
		theScores.setForeground(Color.YELLOW);
		theScores.setFont(new Font("Serif", Font.BOLD, 24));
		theScores.setHorizontalAlignment(SwingConstants.CENTER);
		theScores.setVerticalAlignment(SwingConstants.NORTH);
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
	
	
	
    
	
}
