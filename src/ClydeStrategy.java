import java.util.ArrayList;
import java.util.Random;

/**
 * Strategy class for Clyde, the orange ghost (random moves). Determines which direction the ghost will move in
 */
public class ClydeStrategy implements GhostStrategy {

	private Ghost ghost;
	private int column;
	private int row;
	private int lastMove = 0;
	private Map map;
	
	/**
	 * Constructor for the class
	 * @param ghost
	 * @param map
	 */
	public ClydeStrategy(Ghost ghost, Map map) {
		this.ghost = ghost;
		this.map = map;
	}
	
	
	
	/**
	 * @return a random valid move
	 */
	@Override
	public int getMove(int pacmanCol, int pacmanRow) {
	    column = ghost.getColumn();
	    row = ghost.getRow();

	    ArrayList<Integer> possibleMoves = new ArrayList<Integer>();

	    if (!map.isWall(column, row - 1) && lastMove != DOWN) possibleMoves.add(UP);
	    if (!map.isWall(column, row + 1) && lastMove != UP) possibleMoves.add(DOWN);
	    if (!map.isWall(column - 1, row) && lastMove != RIGHT) possibleMoves.add(LEFT);
	    if (!map.isWall(column + 1, row) && lastMove != LEFT) possibleMoves.add(RIGHT);

	    // If no other moves are possible, allow Clyde to move in the opposite direction
	    if (possibleMoves.isEmpty()) {
	        if (!map.isWall(column, row - 1)) possibleMoves.add(UP);
	        if (!map.isWall(column, row + 1)) possibleMoves.add(DOWN);
	        if (!map.isWall(column - 1, row)) possibleMoves.add(LEFT);
	        if (!map.isWall(column + 1, row)) possibleMoves.add(RIGHT);
	    }

	    Random rand = new Random();
	    int index = rand.nextInt(possibleMoves.size());
	    lastMove = possibleMoves.get(index);

	    return lastMove;
	}


}
