import java.util.ArrayList;
import java.util.Random;
/**
 * This class holds the methods for the ghosts' frightened state, where they turn blue and start to move in random directions.
 * this state occurs when the pacman eats a large pellet, and the ghosts are now able to be eaten by pacman.
 * 
 */
public class FrightenedState implements GhostState {

	private Ghost ghost;
	private int column;
	private int row;
	private Map map;
	
	/**
	 * Constructor for the class
	 * @param ghost
	 * @param map
	 */
	public FrightenedState(Ghost ghost, Map map) {
		this.ghost = ghost;
		this.map = map;
		column = ghost.getColumn();
		row = ghost.getRow();
	}
	
	/**
	 * returns the image path name for the frightened blue ghost
	 */
	@Override
	public String getImageName(String ghostName) {
		return "frightened.png";
	}

	/**
	 * Returns a random move based on available options
	 */
	@Override
	public int getMove(int pacmanCol, int pacmanRow) {
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		
		if (!map.isWall(column, row - 1)) possibleMoves.add(UP);
		if (!map.isWall(column, row + 1)) possibleMoves.add(DOWN);
		if (!map.isWall(column - 1, row)) possibleMoves.add(LEFT);
		if (!map.isWall(column + 1, row)) possibleMoves.add(RIGHT);
		
		Random rand = new Random();
		int index = rand.nextInt(possibleMoves.size() - 1);
		
		return possibleMoves.get(index);
	}

}
