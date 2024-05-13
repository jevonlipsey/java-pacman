import java.util.ArrayList;
import java.util.Random;

public class FrightenedState implements GhostState {

	private Ghost ghost;
	private int column;
	private int row;
	private Map map;
	
	public FrightenedState(Ghost ghost, Map map) {
		this.ghost = ghost;
		this.map = map;
		column = ghost.getColumn();
		row = ghost.getRow();
	}
	
	/**
	 * returns the image path name for the frightened ghost
	 */
	@Override
	public String getImageName(String ghostName) {
		return "frightened.png";
	}

	/**
	 * Returns a random move based on available options
	 */
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
