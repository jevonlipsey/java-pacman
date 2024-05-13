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
	
	public int getMove(GhostState state);
	
	
}
