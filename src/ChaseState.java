
public class ChaseState implements GhostState {

	private Ghost ghost;
	private int column;
	private int row;
	private int pacmanCol;
	private int pacmanRow;
	private Map map;
	
	public ChaseState(Ghost ghost, Map map) {
		this.ghost = ghost;
		this.column = ghost.getColumn();
		this.row = ghost.getRow();
		this.pacmanCol = ghost.getCornerColumn();
		this.pacmanRow = ghost.getCornerRow();
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
	public int getMove() {
		// TODO Auto-generated method stub
		return 0;
	}

}
