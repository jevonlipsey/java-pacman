/**
 * Strategy class for Blinky, the red ghost. Determines which direction the ghost will move in
 */
public class BlinkyStrategy implements GhostStrategy {
	
	private Ghost ghost;
	private int column;
	private int row;
	private Map map;
	
	public BlinkyStrategy(Ghost ghost, Map map) {
		this.ghost = ghost;
		this.map = map;
		
	}
	
	
	/**
	 * gets the next move chasing the pacman's exact location
	 */
	@Override
	public int getMove(int pacmanCol, int pacmanRow) {
		column = ghost.getColumn();
		row = ghost.getRow();
		PathFinder pf = new PathFinder(pacmanCol, pacmanRow, column, row, map.getCells());
		pf.calculateRoute();
		return pf.getMove();
	}
	

}
