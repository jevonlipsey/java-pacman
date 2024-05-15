/**
 * Decides what to do based on the state of the ghost (chase, frightened, and scatter)
 */
public interface GhostState {
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	public static final int INVALID = -1;
	
	/**
	 * @param ghostName
	 * @return the image pathname for the ghost
	 */
	public String getImageName(String ghostName);
	
	/**
	 * @param pacmanCol
	 * @param pacmanRow
	 * @return the next move for the ghost
	 */
	public int getMove(int pacmanCol, int pacmanRow);
}
