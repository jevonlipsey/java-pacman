import java.util.ArrayList;
import java.util.Random;

/**
 * Strategy class for Inky, the blue ghost. Determines which direction the ghost will move in
 */
public class InkyStrategy implements GhostStrategy {

	private static final int CHECK_DISTANCE = 5;
	
	private Ghost ghost;
	private int column;
	private int row;
	private Map map;
	
	public InkyStrategy(Ghost ghost, Map map) {
		this.ghost = ghost;
		this.column = ghost.getColumn();
		this.row = ghost.getRow();
		this.map = map;
		
	}
	
	
	/**
	 * gets the next move chasing the pacman's location
	 */
	@Override
	public int getMove(int pacmanCol, int pacmanRow) {
		column = ghost.getColumn();
		row = ghost.getRow();
		
		
		ArrayList<int[]> possibleTargets = new ArrayList<int[]>();
		
		if (!map.isWall(pacmanCol + CHECK_DISTANCE, pacmanRow + CHECK_DISTANCE)) {
			int[] target = {pacmanCol + CHECK_DISTANCE, pacmanRow + CHECK_DISTANCE};
			possibleTargets.add(target); 
		}
		if (!map.isWall(column - CHECK_DISTANCE, row - CHECK_DISTANCE)){
			int[] target = {pacmanCol - CHECK_DISTANCE, pacmanRow - CHECK_DISTANCE};
			possibleTargets.add(target); 
		}
		if (!map.isWall(column + CHECK_DISTANCE, row - CHECK_DISTANCE)){
			int[] target = {pacmanCol + CHECK_DISTANCE, pacmanRow - CHECK_DISTANCE};
			possibleTargets.add(target); 
		}
		if (!map.isWall(column - CHECK_DISTANCE, row + CHECK_DISTANCE)){
			int[] target = {pacmanCol - CHECK_DISTANCE, pacmanRow + CHECK_DISTANCE};
			possibleTargets.add(target); 
		}
		
		
		if (possibleTargets.size() != 0) {
			int targetRow;
			int targetCol;
			Random rand = new Random();
			int index = rand.nextInt(possibleTargets.size() - 1);
			targetCol = possibleTargets.get(index)[1];
			targetRow = possibleTargets.get(index)[0];
			PathFinder pf = new PathFinder(targetCol, targetRow, column, row, map.getCells());
			pf.calculateRoute();
			return pf.getMove();
		}
	
		PathFinder pf = new PathFinder(pacmanCol, pacmanRow, column, row, map.getCells());
		pf.calculateRoute();
		return pf.getMove();
	}

}
