import java.util.ArrayList;
import java.util.Random;

/**
 * Strategy class for Inky, the blue ghost. Determines which direction the ghost will move in
 */
public class InkyStrategy implements GhostStrategy {

	private static final int CHECK_DISTANCE = 2;
	
	private Ghost ghost;
	private int column;
	private int row;
	private Map map;
	
	/**
	 * Constructor for the class
	 * @param ghost
	 * @param map
	 */
	public InkyStrategy(Ghost ghost, Map map) {
		this.ghost = ghost;
		this.column = ghost.getColumn();
		this.row = ghost.getRow();
		this.map = map;
		
	}
	
	
	/**
	 * gets the path for chasing a spot 2 away from the pacman's location 
	 * @return the next move in the path
	 */
	@Override
	public int getMove(int pacmanCol, int pacmanRow) {
	    column = ghost.getColumn();
	    row = ghost.getRow();

	    ArrayList<int[]> possibleTargets = new ArrayList<int[]>();
	    int[][] offsets = {{CHECK_DISTANCE, CHECK_DISTANCE}, {-CHECK_DISTANCE, -CHECK_DISTANCE}, {CHECK_DISTANCE, -CHECK_DISTANCE}, {-CHECK_DISTANCE, CHECK_DISTANCE}};

	    for (int[] offset : offsets) {
	        int targetCol = pacmanCol + offset[0];
	        int targetRow = pacmanRow + offset[1];
	        if (!map.isWall(targetCol, targetRow)) {
	            PathFinder pf = new PathFinder(targetCol, targetRow, column, row, map.getCells());
	            if (pf.calculateRoute() != null) { // Check if there's a valid path
	                int[] target = {targetCol, targetRow};
	                possibleTargets.add(target);
	            }
	        }
	    }

	    if (possibleTargets.size() != 0) {
	        Random rand = new Random();
	        int index = rand.nextInt(possibleTargets.size()); 
	        int targetCol = possibleTargets.get(index)[0];
	        int targetRow = possibleTargets.get(index)[1];
	        PathFinder pf = new PathFinder(targetCol, targetRow, column, row, map.getCells());
	        pf.calculateRoute();
	        return pf.getMove();
	    }

	    // Fallback strategy: move in a random direction
	    int[] directions = {UP, DOWN, LEFT, RIGHT};
	    Random rand = new Random();
	    return directions[rand.nextInt(directions.length)];
	}


}
