import java.util.ArrayList;
import java.util.List;

public class PathFinder {

	
	private Point end;
	private Point start;
	private Cell[][] map;
	
	private static final int UP = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;
	
	private int move;

	
	/**
	 * A constructor to put together two points
	 * @param pacmanC
	 * @param pacmanR
	 * @param ghostC
	 * @param ghostR
	 * @param map
	 */
	public PathFinder(int pacmanC, int pacmanR, int ghostC, int ghostR, Cell[][] map) {
		this.end = new Point(pacmanC,pacmanR, null);
		this.start = new Point(ghostC,ghostR, null);
		this.map = map;
	}
	
	/**
	 * Checking if a cell is equal to p, P, or o in order to check for open square
	 * @param point
	 * @return
	 */
	public boolean openCell(Point point) {
        if (point.y < 0 || point.y > map.length - 1) return false;
        if (point.x < 0 || point.x > map[0].length - 1) return false;
        return map[point.y][point.x].getType() == 'p' || map[point.y][point.x].getType() == 'P'
        		|| map[point.y][point.x].getType() == 'o' || map[point.y][point.x].getType() == 'g';
    }
	
	/**
	 * Find all the neighboring cells and if they are valid
	 * @param point
	 * @return
	 */
	public List<Point> findNeighbors(Point point) {
        List<Point> neighbors = new ArrayList<>();
        Point up = point.offset(0,  -1);
        Point down = point.offset(0,  1);
        Point left = point.offset(-1, 0);
        Point right = point.offset(1, 0);
        if (openCell(up)) neighbors.add(up);
        if (openCell(down)) neighbors.add(down);
        if (openCell(left)) neighbors.add(left);
        if (openCell(right)) neighbors.add(right);
        
        return neighbors;
    }
	
	/**
	 * Taking in two positions and a map of cells, return a path of cells
	 * @param map
	 * @param start
	 * @param end
	 * @return List of cells
	 */
	public List<Point> calculateRoute() {
		
	    boolean isComplete = false;
	    List<Point> visitedPoints = new ArrayList<>();
	    visitedPoints.add(start);

	    while (!isComplete) {
	        List<Point> newlyDiscovered = new ArrayList<>();
	        for (Point current : visitedPoints) {
	            List<Point> adjacentPoints = findNeighbors(current);
	            for (Point adjacent : adjacentPoints) {
	                if (!visitedPoints.contains(adjacent) && !newlyDiscovered.contains(adjacent)) {
	                    newlyDiscovered.add(adjacent);
	                }
	            }
	        }

	        for (Point nextPoint : newlyDiscovered) {
	            visitedPoints.add(nextPoint);
	            if (end.equals(nextPoint)) {
	                isComplete = true;
	                break;
	            }
	        }

	        if (!isComplete && newlyDiscovered.isEmpty()) {
	            return null; // No path found
	        }
	    }

	    List<Point> path = backtrackPath(visitedPoints);
	    move = convertPathToMoves(path);
	    return path;
	}

	/**
	 * Backtrack the given path to return the corrected path
	 * @param visited
	 * @return
	 */
	private List<Point> backtrackPath(List<Point> visited) {
	    List<Point> path = new ArrayList<>();
	    Point trackBack = visited.get(visited.size() - 1);

	    while (trackBack.previous != null) {
	        path.add(0, trackBack);
	        trackBack = trackBack.previous;
	    }
	    return path;
	}
	
	/**
	 * Method to take the List<Point> type and return the next move
	 * @param path
	 * @return move
	 */
	public int convertPathToMoves(List<Point> path){
		Point p1 = path.get(0);
		Point p2 = this.start;
		
		int move = 0;
		
		if (p1.y < p2.y) {
			move = UP;
		}
		else if(p1.y > p2.y) {
			move = DOWN;
		}
		else if(p1.x < p2.x) {
			move = LEFT;
		}
		else {
			move = RIGHT;
		}
		return move;
		
	}
	
	/**
	 * Get the next move
	 * @return move
	 */
	public int getMove() {
		return move;
	}
    
}
	


