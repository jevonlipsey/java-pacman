import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovingCircle extends JPanel {
	
    private int circleX = 50;
    private int circleY = 50;
    private int diameter = 20;
    private int direction = 0; 
    
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    
    public static final int SPEED = 10;

    public MovingCircle() {
        setPreferredSize(new Dimension(500, 800));
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        direction = UP;
                        break;
                    case KeyEvent.VK_DOWN:
                        direction = DOWN;
                        break;
                    case KeyEvent.VK_LEFT:
                        direction = LEFT;
                        break;
                    case KeyEvent.VK_RIGHT:
                        direction = RIGHT;
                        break;
                }
            }
        });

        new Thread(() -> {
            while (true) {
                switch (direction) {
                    case UP:
                        circleY -= SPEED;
                        break;
                    case DOWN:
                        circleY += SPEED;
                        break;
                    case LEFT:
                        circleX -= SPEED;
                        break;
                    case RIGHT:
                        circleX += SPEED;
                        break;
                }
                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillOval(circleX, circleY, diameter, diameter);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Moving Circle");
        MovingCircle movingCircle = new MovingCircle();
        frame.add(movingCircle);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        movingCircle.requestFocusInWindow();
    }
}
