
public class ScatterState implements GhostState {

	private Ghost ghost;
	private int column;
	private int row;
	private int targetCol;
	private int targetRow;
	private Map map;
	
	public ScatterState(Ghost ghost, Map map) {
		this.ghost = ghost;
		this.column = ghost.getColumn();
		this.row = ghost.getRow();
		this.targetCol = ghost.getCornerColumn();
		this.targetRow = ghost.getCornerRow();
		this.map = map;
	}
	
	/**
	 * Returns the image path name for each ghosts
	 */
	@Override
	public String getImageName(String ghostName) {
		return ghostName + ".png";
	}

	/**
	 * Finds a path based on the pacman's assigned corner in scatter mode
	 * @return the next move
	 */
	@Override
	public int getMove(int pacmanCol, int pacmanRow) {
		PathFinder pf = new PathFinder(targetCol, targetRow, column, row, map.getCells());
		pf.calculateRoute();
		return pf.getMove();
	}

}
