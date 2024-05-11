import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 * Creates the main JFrame for the game
 */
public class PacMan {
	
	
	 /**
     * Main method that creates a JFrame for the game
     *
     * @param args
     */
    public static void main(String args[]) {

        // Create JFrame for the game
        JFrame frame = new JFrame("Game");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add the next JPanel
        SwingUtilities.invokeLater(() -> frame.getContentPane().add(new HomeScreen(frame)));

        frame.setSize(600, 800);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.repaint();
        frame.revalidate();
    }
}
