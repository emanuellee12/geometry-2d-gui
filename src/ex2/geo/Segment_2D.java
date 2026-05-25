package ex2.geo;

import ex2.ex2.Ex2_Const;

/**
 * This class represents a 2D segment on the plane, 
 * Ex2: you should implement this class!
 * @author I2CS
 *
 */
public class Segment_2D implements GeoShape{
	// instance data members:
private Point_2D _p1, _p2;

	// regular constructor get two points:
	public Segment_2D(Point_2D a, Point_2D b) {
		_p1 = new Point_2D(a);
		_p2 = new Point_2D(b);
	}
	// string constructor get string
	public Segment_2D(String t) {
		String[] a = t.split(",");
		String s1 = a[0] + "," +a[1];
		String s2 = a[2] + "," +a[3];
		_p1 = new Point_2D(s1);
		_p2 = new Point_2D(s2);
	}
	// copy constructor get other Segment:
	public Segment_2D(Segment_2D t1) {
		_p1 = new Point_2D(t1._p1);
		_p2 = new Point_2D(t1._p2);
	}
	// getters:
	/**
	 * This function returns point p1 of this Segment object
	 * @return p1 of this Segment object
	 */
	public Point_2D get_p1() {
		return new Point_2D(_p1);
	}
	/**
	 * This function returns point p2 of this Segment object
	 * @return p1 of this Segment object
	 */
	public Point_2D get_p2() {
		return new Point_2D(_p2);
	}
	// setters:
	/**
	 * This function sets the point p2 of this Segment object
	 * @param _p1 the Segment to be set
	 */
	public void set_p1(Point_2D _p1) {
		this._p1 = _p1;
	}
	/**
	 * This function sets the point p2 of this Segment object
	 * @param _p2 the Segment to be set
	 */
	public void set_p2(Point_2D _p2) {
		this._p2 = _p2;
	}
	/**
	 * This function equals between two Segment objects
	 * @param other get other object and equal
	 * @return boolean answer for are the objects the same
	 */
	public boolean equals(Object other){
		if (this == other) return true;
		if (other == null) return false;
		if (other.getClass() != getClass()) return false;
		Segment_2D otherSegment = (Segment_2D) other;
		return ( (_p1.equals(otherSegment._p1)) && (_p2.equals(otherSegment._p2)) );
	}
	/**
	 * This method returns a String representing this Segment,
	 * such that one can use this string for saving Segment into a text file.
	 * @return a String representing this Segment
	 */
	public String toString() {
		return _p1.toString()+", "+_p2.toString();
	}

	// interface:
	/**
	 * This function get a point and check if the Segment contains the point
	 * @param ot - a query 2D point
	 * @return boolean answer if the point is contained in the Segment
	 */
	@Override
	public boolean contains(Point_2D ot) {
		double dist = _p1.distance(_p2);
		double d1 = _p1.distance(ot);
		double d2 = ot.distance(_p2);

		return (d1+d2 < dist + Ex2_Const.EPS);
		// done
	}
	/**
	 * Computes the area of Segment
	 * @return - the area of this Segment.
	 */
	@Override
	public double area() {
		return 0;
		// done
	}
	/**
	 * Computes the perimeter of Segment
	 * @return the perimeter of this Segment.
	 */
	@Override
	public double perimeter() {
		return (2*_p1.distance(_p2));
	}
	/**
	 * Move this Segment by the vector 0,0-->vec
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		_p1.move(vec);
		_p2.move(vec);
		// done ?
	}
	/**
	 * This method computes a new (deep) copy of Segment
	 * @return a deep copy of Segment.
	 */
	@Override
	public GeoShape copy() {
		return new Segment_2D(this);
	}
	/**
	 * Rescales this Segment with respect to the given center point.
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
	}
	/**
	 * Rotates this Segment with respect to the given center point by an angle.
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		_p1.rotate(center, angleDegrees);
		_p2.rotate(center, angleDegrees);

	}
}