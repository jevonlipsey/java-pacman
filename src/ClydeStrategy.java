/**
 * Strategy class for Clyde, the orange ghost. Determines which direction the ghost will move in
 */
public class ClydeStrategy implements GhostStrategy {

	@Override
	public int getMove(GhostState state) {
		if (state.getClass() == FrightenedState.class) {
			
		}
		return 0;
	}

}
