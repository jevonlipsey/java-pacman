/**
 * Interface for the ghost strategies, decides how each ghost will move based on their strategy
 */
public interface GhostStrategy {

	public static enum Mode {CHASE, SCATTER, FRIGHTENED};

	public Mode getMode();
	
	/**
	 * Will check whether the ghost should turn right or left at an intersection based on its strategy
	 * @return true if the ghost should turn right, false if it should turn left
	 */
	public boolean turnRight();
	
	
}
