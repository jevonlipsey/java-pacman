/**
 * Interface for the ghost strategies, decides how each ghost will move based on their strategy
 */
public interface GhostStrategy {
	
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	public static final int INVALID = -1;
	
	public static enum Mode {CHASE, SCATTER, FRIGHTENED};
	
	/**
	 * @return the next move based on the chase strategy
	 */
	public int getMove(int pacmanCol, int pacmanRow);
	
	
}
