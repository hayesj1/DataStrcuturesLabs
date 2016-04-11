package ach.main;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 * Created by hayesj3 on 4/8/2016.
 */
public class Point implements Comparable<Point> {

	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() { return x; }
	public int getY() { return y; }

	@Override
	public String toString() {
		return ("(" + x + "," + y + ")");
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (!(obj instanceof Point)) { return false; }
		Point p = (Point) obj;
		return (this.getX() == p.getX()) && (this.getY() == p.getY());
	}

	/**
	 * Computes a full comparison(using both x and y values)
	 * @param p the other point to compare
	 * @return -1 if this is strictly less than p, 1 if this is strictly greater than p, 0 otheriwse
	 */
	@Override
	public int compareTo(Point p) {
		int x1 = this.getX();
		int x2 = p.getX();
		int y1 = this.getY();
		int y2 = p.getY();
		int ret;

		if(x1 == x2 && y1 == y2) {
			ret = 0;
		} else if(x1 > x2 && y1 > y2) {
			ret = 1;
		} else if(x1 < x2 && y1 < y2) {
			ret = -1;
		} else {
			double angle1 = Math.asin(y1 / Math.hypot(x1, y1));
			double angle2 = Math.asin(y2 / Math.hypot(x2, y2));
			if (angle1 > Math.PI/4 && angle2 < Math.PI/4) {
				ret = 1;
			} else if (angle1 < Math.PI/4 && angle2 > Math.PI/4) {
				ret = -1;
			} else {
				ret = (Math.max(y1,y2) == y1) ? 1 : -1;
			}
		}
		return ret;
	}
}
