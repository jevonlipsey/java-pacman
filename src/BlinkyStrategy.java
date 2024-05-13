/**
 * Strategy class for Blinky, the red ghost. Determines which direction the ghost will move in
 */
public class BlinkyStrategy implements GhostStrategy {
	
	private Ghost ghost;
	private int column;
	private int row;
	private Map map;
	private int pacmanCol;
	private int pacmanRow;
	
	public BlinkyStrategy(Ghost ghost, int pacCol, int pacRow, Map map) {
		this.ghost = ghost;
		this.column = ghost.getColumn();
		this.row = ghost.getRow();
		this.map = map;
		pacmanCol = pacCol;
		pacmanRow = pacRow;
		
	}
	
	
	/**
	 * gets the next move chasing the pacman's exact location
	 */
	@Override
	public int getMove() {
		PathFinder pf = new PathFinder(pacmanCol, pacmanRow, column, row, map.getCells());
		pf.calculateRoute();
		return pf.getMove();
	}
	

}
