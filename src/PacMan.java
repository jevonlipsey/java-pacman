import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 * Creates the main JFrame for the game, which involves controlling a character named Pac-Man 
 * within a maze-like environment, eating dots while avoiding ghosts to achieve high scores and 
 * progress through levels. This class contains the main method that initializes and launches the 
 * Pac-Man game window.
 */
public class PacMan {
	
	
	 /**
     * Main method that creates a JFrame for the game
     *
     * @param args
     */
    public static void main(String args[]) {

        // Create JFrame for the game
        JFrame frame = new JFrame("Pacman");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add the next JPanel
        SwingUtilities.invokeLater(() -> frame.getContentPane().add(new HomeScreen(frame, true)));

        frame.setSize(600, 800);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.repaint();
        frame.revalidate();
    }
}
