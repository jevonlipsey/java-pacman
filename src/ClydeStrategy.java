import java.util.ArrayList;
import java.util.Random;

/**
 * Strategy class for Clyde, the orange ghost. Determines which direction the ghost will move in
 */
public class ClydeStrategy implements GhostStrategy {

	private Ghost ghost;
	private int column;
	private int row;
	private Map map;
	
	public ClydeStrategy(Ghost ghost, Map map) {
		this.ghost = ghost;
		this.column = ghost.getColumn();
		this.row = ghost.getRow();
		this.map = map;
	}
	
	
	@Override
	public int getMove() {
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
