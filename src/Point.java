import java.util.Objects;

public class Point {
	
	public int x;
	public int y;
	Point previous;

	/**
	 * Constructor that takes in x, y coords and a previous cell
	 * @param x
	 * @param y
	 * @param previous
	 */
	public Point(int x, int y, Point previous) {
		this.x = x;
		this.y = y;
		this.previous = previous;
	}
	
	/**
	 * An equals method for two different points
	 */
	public boolean equals(Object o) {
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }
	
	/**
	 * Hash a point
	 */
	@Override
    public int hashCode() {
		return Objects.hash(x, y); }

	/**
	 * Find an offset from current point
	 * @param offsetX
	 * @param offsetY
	 * @return a new point
	 */
    public Point offset(int offsetX, int offsetY) {
    	return new Point(x + offsetX, y + offsetY, this);  }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
