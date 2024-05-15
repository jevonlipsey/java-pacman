import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Strategy class for Blinky, the red ghost. Determines which direction the ghost will move in
 */
public class BlinkyStrategy implements GhostStrategy {
	
	private Ghost ghost;
	private int column;
	private int row;
	private Map map;
	
	/*
	 * Constructor for the class
	 */
	public BlinkyStrategy(Ghost ghost, Map map) {
		this.ghost = ghost;
		this.map = map;
		
	}
	
	
	/**
	 * gets the path for the ghost chasing the pacman's exact location
	 * @return the next valid move in the path
	 */
	@Override
	public int getMove(int pacmanCol, int pacmanRow) {
	    column = ghost.getColumn();
	    row = ghost.getRow();
	    PathFinder pf = new PathFinder(pacmanCol, pacmanRow, column, row, map.getCells());
	    List<Point> path = pf.calculateRoute();

	    if (path != null && !path.isEmpty()) {
	        return pf.getMove();
	    } else {
	        // Fallback strategy: move in a random valid direction
	        ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
	        if (!map.isWall(column, row - 1)) possibleMoves.add(UP);
	        if (!map.isWall(column, row + 1)) possibleMoves.add(DOWN);
	        if (!map.isWall(column - 1, row)) possibleMoves.add(LEFT);
	        if (!map.isWall(column + 1, row)) possibleMoves.add(RIGHT);
	        Random rand = new Random();
	        int index = rand.nextInt(possibleMoves.size());
	        return possibleMoves.get(index);
	    }
	}

	

}
