package ach.main;

import java.util.Comparator;

/**
 * Created by hayesj3 on 4/8/2016.
 */
public class PointComparator {
	/**
	 * Compares the X values of two points
	 * Param 1 of the Comparator is p1 first point
	 * Param 2 of the Comparator is p2 second point
	 * The Comparator returns -1 if p1.getX() < p2.getX(), 1 if p1.getX() > p2.getX(), or 0 if p1.getX() = p2.getX()
	 */
	public static final Comparator<Point> xComp = XComparator();
	/**
	 * Compares the Y values of two points
	 * This operates exactly like {@link #xComp} does, exectp with the Y values instead the X values
	 * The Comparator returns -1 if p1.getY() < p2.getY(), 1 if p1.getY() > p2.getY(), or 0 if p1.getY() = p2.getY()
	 */
	public static final Comparator<Point> yComp = YComparator();

	private static Comparator<Point> XComparator() {
		return (p1, p2) -> {
			int x1 = p1.getX();
			int x2 = p2.getX();
			return (x1 < x2) ? -1 : ((x1 > x2) ? 1 : 0);
		};
	}
	private static Comparator<Point> YComparator() {
		return (p1, p2) -> {
			int y1 = p1.getY();
			int y2 = p2.getY();
			return (y1 < y2) ? -1 : ((y1 > y2) ? 1 : 0);
		};
	}
}
