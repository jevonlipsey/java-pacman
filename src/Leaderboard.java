import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Leaderboard extends JPanel{
	private static final Font font = new Font("Dialog", Font.BOLD, 24);
	private final JFrame parent;
	private JPanel leaderboardPanel;
	private JLabel theScores;
	
	private HighScoreDatabase database;
	
	
	public Leaderboard(JFrame parent) {
		database = new HighScoreDatabase();
		this.parent = parent;
        this.setSize(600, 800);
        parent.setLayout(new BorderLayout()); 
        
        leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new BorderLayout());
        
        JLabel header = new JLabel("High Scores");
        header.setForeground(Color.BLUE);
        header.setFont(new Font("Serif", Font.BOLD, 34));
        
        leaderboardPanel.add(header, BorderLayout.NORTH);
        leaderboardPanel.add(leaderboardLabel(), BorderLayout.CENTER);
        leaderboardPanel.setBackground(Color.BLACK);
        JPanel buttons = new JPanel(new GridLayout());
        buttons.setBackground(Color.BLACK);

        JButton playAgain = new JButton("Play Again");
        JButton exit = new JButton("Exit");
        
        buttons.add(playAgain);
        buttons.add(exit);
        leaderboardPanel.add(buttons,BorderLayout.SOUTH);
        
		parent.add(leaderboardPanel, BorderLayout.CENTER);
		
		
		parent.setBackground(Color.WHITE);
		parent.setVisible(true);
		
	}
	
	private JLabel leaderboardLabel() {
		theScores = new JLabel();
		String scoreString = "<html>";
		ArrayList<String> scores = database.getTopNScores(10);
		for(int i = 0; i < scores.size(); i++) {
			scoreString += scores.get(i) + "<br/>";
		}
		scoreString += "<html>";
		theScores.setText(scoreString);
		theScores.setBackground(Color.BLUE);
		theScores.setForeground(Color.YELLOW);
		return theScores;
		
	}
	
	

	
	public static void main(String[] args) {
		JFrame parent = new JFrame();
		parent.setSize(600,800);
		parent.setVisible(true);
		parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Leaderboard lb = new Leaderboard(parent);
	}
}
