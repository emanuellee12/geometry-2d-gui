package ex2.geo;

/**
 * This class represents a 2D axis parallel rectangle.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect_2D implements GeoShape {
	// instance data members:
	private Point_2D _p1, _p2, _p3, _p4;

	// regular constructor get two points:
	public Rect_2D(Point_2D p1, Point_2D p3) {
		_p1 = p1;
		_p3 = p3;
		_p2 = new Point_2D(_p3.x(), _p1.y());
		_p4 = new Point_2D(_p1.x(), _p3.y());
	}
	// string constructor get string
	public Rect_2D(String t1) {
		String[] a = t1.split(",");
		String s1 = a[0] + "," +a[1];
		String s2 = a[2] + "," +a[3];
		String s3 = a[4] + "," +a[5];
		String s4 = a[6] + "," +a[7];
		_p1 = new Point_2D(s1);
		_p2 = new Point_2D(s2);
		_p3 = new Point_2D(s3);
		_p4 = new Point_2D(s4);
	}

	// copy constructor get other rect:
	public Rect_2D(Rect_2D t1) {
		_p1 = new Point_2D(t1._p1);
		_p2 = new Point_2D(t1._p2);
		_p3 = new Point_2D(t1._p3);
		_p4 = new Point_2D(t1._p4);
	}

	// getters:
	/**
	 * This function returns point p1 of this Rect object
	 * @return p1 of this Rect object
	 */
	public Point_2D get_p1() {
		return _p1;
	}
	/**
	 * This function returns point p2 of this Rect object
	 * @return p2 of this Rect object
	 */
	public Point_2D get_p2() {
		return _p2;
	}
	/**
	 * This function returns point p3 of this Rect object
	 * @return p3 of this Rect object
	 */
	public Point_2D get_p3() {
		return _p3;
	}
	/**
	 * This function returns point p4 of this Rect object
	 * @return p4 of this Rect object
	 */
	public Point_2D get_p4() {
		return _p4;
	}
	/**
	 * This function equals between two Rect objects
	 * @param other get other object and equal
	 * @return areEqual boolean answer for are the objects the same
	 */
	public boolean equals(Object other){
		if (this == other) return true;
		if (other == null) return false;
		if (other.getClass() != getClass()) return false;
		Rect_2D otherRect = (Rect_2D) other;
		return ( (_p1.equals(otherRect._p1)) && (_p2.equals(otherRect._p2)) &&
				(_p3.equals(otherRect._p3)) && (_p4.equals(otherRect._p4)) );
	}
	/**
	 * This method returns a String representing this Rect,
	 * such that one can use this string for saving Rect into a text file.
	 * @return a String representing this Rect
	 */
	public String toString() {
		return _p1.toString()+", "+_p2.toString()+", "+_p3.toString()+", "+_p4.toString();
	}
	// interface:
	/**
	 * This function get a point and check if the Rect contains the point
	 * @param ot - a query 2D point
	 * @return boolean answer if the point is contained in the Rect
	 */
	@Override
	public boolean contains(Point_2D ot) {

		return ((ot.x() >= _p1.x() && ot.x() <= _p3.x() && ot.y() >= _p1.y() && ot.y() <= _p3.y()) ||
				(ot.x() <= _p1.x() && ot.x() >= _p3.x() && ot.y() <= _p1.y() && ot.y() >= _p3.y()) ||
				(ot.x() <= _p1.x() && ot.x() >= _p3.x() && ot.y() >= _p1.y() && ot.y() <= _p3.y()) ||
				(ot.x() >= _p1.x() && ot.x() <= _p3.x() && ot.y() <= _p1.y() && ot.y() >= _p3.y()));
	}
	/**
	 * Computes the area of Rect
	 * @return - the area of this Rect.
	 */
	@Override
	public double area() {
		return (_p1.distance(_p2)*_p3.distance(_p2));
	}
	/**
	 * Computes the perimeter of Rect
	 * @return the perimeter of this Rect.
	 */
	@Override
	public double perimeter() {
		return (2*_p1.distance(_p2) + 2*_p3.distance(_p2));
	}
	/**
	 * Move this Rect by the vector 0,0-->vec
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
		_p4.move(vec);
	}
	/**
	 * This method computes a new (deep) copy of Rect
	 * @return a deep copy of Rect.
	 */
	@Override
	public GeoShape copy() {
		return new Rect_2D(this);
	}
	/**
	 * Rescales this Rect with respect to the given center point.
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		this._p1.scale(center, ratio);
		this._p2.scale(center, ratio);
		this._p3.scale(center, ratio);
		this._p4.scale(center, ratio);
	}
	/**
	 * Rotates this Rect with respect to the given center point by an angle.
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._p1.rotate(center, angleDegrees);
		this._p2.rotate(center, angleDegrees);
		this._p3.rotate(center, angleDegrees);
		this._p4.rotate(center, angleDegrees);
	}
}
