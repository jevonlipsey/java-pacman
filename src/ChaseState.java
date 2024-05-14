
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
	 * Returns the image path name for each ghosts
	 */
	@Override
	public String getImageName(String ghostName) {
		
		return ghostName + ".png";
	}

	@Override
	public int getMove(int pacmanCol, int pacmanRow) {
		return ghost.getStrategy().getMove(pacmanCol, pacmanRow);
	}

}
