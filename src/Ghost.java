import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Holds all of the info for a ghost
 */
public class Ghost {
	
	private static final int UP = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;
	
	private String image;
	private String name;
	private GhostStrategy strategy;
	private GhostState state;
	private BufferedImage ghostImage;
	
	private int column;
	private int row;
	
	private int cornerColumn;
	private int cornerRow;
	
	
	/**
	 * Constructor for the class. Sets the name and image.
	 * @param name
	 */
	public Ghost(String name) {
		this.name = name;
		this.image = name + ".png";
		
		try {
		ghostImage = ImageIO.read(new File(image)); 
		
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public int getMove(int pacmanCol, int pacmanRow) {
		return state.getMove(pacmanCol, pacmanRow);
	}

	public void setStrategy(GhostStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void setState(GhostState state) {
		this.state = state;
	}
	
	public GhostState getState() {
		return state;
	}
	
	public String getImage() {
		return image;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCornerColumn() {
		return cornerColumn;
	}
	
	public int getCornerRow() {
		return cornerRow;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	public void decrementRow() {
		this.row--;
	}
	
	public void incrementRow() {
		this.row++;
	}
	
	public void decrementColumn() {
		this.column--;
	}
	
	public void incrementColumn() {
		this.column++;
	}
	
	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	public void setImage(String imageName) {
		image = imageName;
	}
	
	public GhostStrategy getStrategy() {
		return strategy;
	}

}
