import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class LayoutOne extends GamePlay {

	public LayoutOne(JFrame parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BufferedImage getBackgroundImage() {
		BufferedImage background = null;
		try {
			background = ImageIO.read(new File("background.png")); //TODO: update this to a map
		} catch (IOException e) {
	        e.printStackTrace();
	    }
	    return background;
	}

}
