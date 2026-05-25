package ex2.geo;

/** 
 * This class represents a 2D circle in the plane. 
 * Please make sure you update it according to the GeoShape interface.
 * Ex2: you should update this class!
 * @author boaz.benmoshe
 *
 */
public class Circle_2D implements GeoShape{
	// instance data members:
	private Point_2D _center;
	private double _radius;

	// regular constructor get center point and radius
	public Circle_2D(Point_2D cen, double rad) {
		this._center = new Point_2D(cen);
		this._radius = rad;
	}
	// copy constructor get other circle
	public Circle_2D(Circle_2D c) {

		this(new Point_2D(c.getCenter()),  c.getRadius());
	}

	// string constructor get string
	public Circle_2D(String c) {
		String[] a = c.split(",");
		String center = a[0] + "," +a[1];
		String rad = a[2];
		_center = new Point_2D(center);
		_radius = Double.parseDouble(rad);
	}
	// getters:

	/**
	 * This function returns the radius of the circle object
	 * @return radius
	 */
	public double getRadius() {return this._radius;}
	/**
	 * This function returns the center of the circle object
	 * @return center
	 */
	public Point_2D getCenter(){ return _center;}

	// setters:

	/**
	 * This function sets the radius of the circle object
	 * @param radius the circles radius to be set
	 */
	public void set_radius(double radius){
		this._radius = radius;
	}

	/**
	 * This function equals between two circle objects
	 * @param other get other object and equal
	 * @return boolean answer for are the objects the same
	 */
	public boolean equals(Object other){
		if (this == other) return true;
		if (other == null) return false;
		if (other.getClass() != getClass()) return false;
		Circle_2D otherCircle = (Circle_2D) other;
		return ( (_center.equals(otherCircle._center)) && (_radius == otherCircle._radius) );
	}
	/**
	 * This method returns a String representing this circle,
	 * such that one can use this string for saving circle into a text file.
	 * @return a String representing this circle
	 */
	 @Override
	 public String toString() {
		 return _center.toString()+", "+_radius;
	 }
	// interface:

	/** This function get a point and check if the circle contains the point
	 * @param ot - a query 2D point
	 * @return boolean answer if the point is contained in the circle
	 */
	@Override
	public boolean contains(Point_2D ot) {
		return ot.distance(_center) <= _radius;
	}
	/**
	 * Computes the area of circle
	 * @return - the area of this circle.
	 */
	@Override
	public double area() {
		return Math.PI * Math.pow(this._radius, 2);
	}
	/**
	 * Computes the perimeter of circle
	 * @return the perimeter of this circle.
	 */
	@Override
	public double perimeter() {
		return 2 * Math.PI * this._radius;
	}
	/**
	 * Move this circle by the vector 0,0-->vec
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		_center.move(vec);
	}
	/**
	 * This method computes a new (deep) copy of circle
	 * @return a deep copy of circle.
	 */
	@Override
	public GeoShape copy() {
		return new Circle_2D(this);
	}
	/**
	 * Rescales this circle with respect to the given center point.
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		set_radius(ratio*_radius);
		this._center.scale(center, ratio);
	}
	/**
	 * Rotates this circle with respect to the given center point by an angle.
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._center.rotate(center, angleDegrees);
	}

}
