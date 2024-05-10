import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class LayoutOne extends GamePlay {
	private final JFrame parent;

	public LayoutOne(JFrame parent) {
		super(parent);
		this.parent = parent;
        this.setSize(600, 800);
        setLayout(null);
		
	}

	@Override
	public BufferedImage getBackgroundImage() {
		BufferedImage background = null;
		try {
			background = ImageIO.read(new File("EMPTYBACKGROUND.png"));
		} catch (IOException e) {
	        e.printStackTrace();
	    }
	    return background;
	}

}
