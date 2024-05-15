/**
 * The class holding methods for the ghost's chase state, when they are each chasing the pacman
 */
public class ChaseState implements GhostState {

	private Ghost ghost;
	private int column;
	private int row;
	private Map map;
	
	public ChaseState(Ghost ghost, Map map) {
		this.ghost = ghost;
		this.column = ghost.getColumn();
		this.row = ghost.getRow();
		this.map = map;
	}
	
	/**
	 *@return the image path name for each ghost
	 */
	@Override
	public String getImageName(String ghostName) {
		
		return ghostName + ".png";
	}

	/**
	 * Calls the getMove() method in the ghost's strategy that gets their paths based on their different strategies of chasing pacman
	 * @return the next move  
	 */
	@Override
	public int getMove(int pacmanCol, int pacmanRow) {
		return ghost.getStrategy().getMove(pacmanCol, pacmanRow);
	}

}
