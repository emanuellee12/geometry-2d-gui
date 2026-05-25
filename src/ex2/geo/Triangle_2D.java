package ex2.geo;

import ex2.ex2.Ex2_Const;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex2: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle_2D implements GeoShape{
	// instance data members:
	private Point_2D _p1, _p2, _p3;


	// regular constructor get three points:
	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) {
		this._p1 = p1;
		this._p2 = p2;
		this._p3 = p3;
	}

	// copy constructor get other Triangle:
	public Triangle_2D(Triangle_2D t1) {
		this._p1 = new Point_2D(t1._p1);
		this._p2 = new Point_2D(t1._p2);
		this._p3 = new Point_2D(t1._p3);

	}
	// string constructor get string
	public Triangle_2D(String t1) {
		String[] a = t1.split(",");
		String s1 = a[0] + "," +a[1];
		String s2 = a[2] + "," +a[3];
		String s3 = a[4] + "," +a[5];
		_p1 = new Point_2D(s1);
		_p2 = new Point_2D(s2);
		_p3 = new Point_2D(s3);
	}

	// getters:
	/**
	 * This function returns the points of the Triangle object
	 * @return array with all points
	 */
	public Point_2D[] getAllPoints() {
		Point_2D[] points = new Point_2D[3];
		points[0] = this._p1;
		points[1] = this._p2;
		points[2] = this._p3;

		return points;
	}
	/**
	 * This function equals between two Triangle objects
	 * @param other get other object and equal
	 * @return boolean answer for are the objects the same
	 */
	public boolean equals(Object other){
		if (this == other) return true;
		if (other == null) return false;
		if (other.getClass() != getClass()) return false;
		Triangle_2D otherTriangle = (Triangle_2D) other;
		return ( (_p1.equals(otherTriangle._p1)) && (_p2.equals(otherTriangle._p2)) &&
				(_p3.equals(otherTriangle._p3)) );
	}
	/**
	 * This method returns a String representing this Triangle,
	 * such that one can use this string for saving Triangle into a text file.
	 * @return a String representing this Triangle
	 */
	@Override
	public String toString() {
		return _p1.toString()+", "+_p2.toString()+", "+_p3.toString();
	}

	// interface:
	/**
	 * This function get a point and check if the Triangle contains the point
	 * @param ot - a query 2D point
	 * @return boolean answer if the point is contained in the Triangle
	 */
	@Override
	public boolean contains(Point_2D ot) {
		Triangle_2D t1 = new Triangle_2D(_p1,_p2,ot);
		Triangle_2D t2 = new Triangle_2D(_p1,_p3,ot);
		Triangle_2D t3 = new Triangle_2D(_p3,_p2,ot);
		double AT123 = t1.area() + t2.area() + t3.area();
		double AT = new Triangle_2D(_p1,_p2,_p3).area();
		return Math.abs(AT123 - AT) < Ex2_Const.EPS;
	}
	/**
	 * Computes the area of Triangle
	 * @return - the area of this Triangle.
	 */
	@Override
	public double area() {
		double s = perimeter() / 2;
		return Math.sqrt( s  * (s -_p1.distance(_p2)) * (s -_p2.distance(_p3)) * (s -_p3.distance(_p1)) );
	}
	/**
	 * Computes the perimeter of Triangle
	 * @return the perimeter of this Triangle.
	 */
	@Override
	public double perimeter() {
		return (_p1.distance(_p2) + _p2.distance(_p3) + _p3.distance(_p1));
	}
	/**
	 * Move this Triangle by the vector 0,0-->vec
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
	}
	/**
	 * This method computes a new (deep) copy of Triangle
	 * @return a deep copy of Triangle.
	 */
	@Override
	public GeoShape copy() {
		return new Triangle_2D(this);
	}
	/**
	 * Rescales this Triangle with respect to the given center point.
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		this._p1.scale(center, ratio);
		this._p2.scale(center, ratio);
		this._p3.scale(center, ratio);
	}
	/**
	 * Rotates this Triangle with respect to the given center point by an angle.
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._p1.rotate(center, angleDegrees);
		this._p2.rotate(center, angleDegrees);
		this._p3.rotate(center, angleDegrees);
	}
}
