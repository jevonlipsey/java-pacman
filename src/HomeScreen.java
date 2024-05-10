import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class HomeScreen extends JPanel {
	private static final Font font = new Font("Dialog", Font.BOLD, 24);
	private final JFrame parent;
	private final JPanel thisJPanel;
	private ImageIcon background;
	
	
	public HomeScreen(JFrame parent) {
		this.parent = parent;
		thisJPanel = this;
		
		BackgroundPanel backgroundPanel = new BackgroundPanel();
        
        JButton playButton = createTransparentButton();
        JButton scoresButton = createTransparentButton();
        
        add(playButton);
		
        playButton.setBounds(170, 220, 260, 155);
        scoresButton.setBounds(170, 410, 260, 155);
        
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getContentPane().remove(thisJPanel);
                SwingUtilities.invokeLater(() -> parent.getContentPane().add(new LayoutOne(parent)));
                parent.getContentPane().revalidate();
                parent.getContentPane().repaint();
            }
        });

        scoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getContentPane().remove(thisJPanel);
                SwingUtilities.invokeLater(() -> parent.getContentPane().add(new Leaderboard(parent)));
                parent.getContentPane().revalidate();
                parent.getContentPane().repaint();
            }
        });

   

        backgroundPanel.add(playButton);
        backgroundPanel.add(scoresButton);

        backgroundPanel.setPreferredSize(new Dimension(600, 800));
        backgroundPanel.setFocusable(true);
        add(backgroundPanel);
        setVisible(true);
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
     *  Custom panel with background
     */
    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {

            setLayout(null);

            backgroundImage = new ImageIcon("homescreen.png").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // Draw background image
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight() - 17, this);
        }
    }
        
       

}
