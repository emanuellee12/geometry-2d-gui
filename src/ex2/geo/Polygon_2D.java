package ex2.geo;

import ex2.ex2.Ex2_Const;

import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Polygon_2D implements GeoShape{
	// instance data members:
	private ArrayList<Point_2D> _points = new ArrayList<>();


	// default constructor:
	public Polygon_2D() {}
	// string constructor get string
	public Polygon_2D(String t1) {
		String[] a = t1.split(",");
	  for (int i=0; i<a.length-1; i+=2){
		  _points.add(new Point_2D(a[i] + "," +a[i+1]));
	  }
	}
	// regular constructor get array list of points:
	public Polygon_2D(ArrayList<Point_2D> arr) {
		this._points = arr;
	}

	// copy constructor get other polygon:
	public Polygon_2D(Polygon_2D po) {
		Point_2D[] poPoint = po.getAllPoints();
		for (int i=0 ; i<poPoint.length ; i++){
			this._points.add(new Point_2D(poPoint[i]));
		}
	}
	// getters:
	/**
	 * This function returns the points of the Polygon object
	 * @return array with all points
	 */
	public Point_2D[] getAllPoints() {
		Point_2D[] _arrayPoint = new Point_2D[_points.size()];
		for (int i=0 ; i< _points.size() ; i++){
			_arrayPoint[i] = this._points.get(i);
		}
		return _arrayPoint;
	}
	/**
	 * This function add new point to array list of points of the Polygon object
	 * @param p point added to array list
	 */
	public void add(Point_2D p) {
		_points.add(p);
	}
	/**
	 * This function equals between two Polygon objects
	 * @param other get other object and equal
	 * @return areEqual boolean answer for are the objects the same
	 */
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null) return false;
		if (other.getClass() != getClass()) return false;
		Polygon_2D otherPolygon = (Polygon_2D) other;
		boolean areEqual = this._points.equals(otherPolygon._points);
		for (int i = 0; i < this._points.size(); i++) {
			if (!this._points.get(i).equals(otherPolygon._points.get(i))) {
				areEqual = false;
				break;
			}
		}
		return areEqual;
	}
	/**
	 * This method returns a String representing this Polygon,
	 * such that one can use this string for saving Polygon into a text file.
	 * @return a String representing this Polygon
	 */
	@Override
	public String toString() {
		StringBuilder stringBPolygon = new StringBuilder();
		for (int i=0 ; i<this._points.size() ; i++){
			if (i > 0) {
				stringBPolygon.append(", ");
			}
			stringBPolygon.append(_points.get(i).toString());
		}
		String stringPolygon = stringBPolygon.toString();
		return stringPolygon;
	}
	/**
	 * This function get a point and check if the Polygon contains the point
	 * @param ot - a query 2D point
	 * @return boolean answer if the point is contained in the Polygon
	 */
	@Override
	public boolean contains(Point_2D ot) {
		int intersectionCount = 0;
		Point_2D minimumLocal = _points.get(0);
		for (int i = 1; i < _points.size(); i++) {
			if (minimumLocal.x() > _points.get(i).x()) minimumLocal = _points.get(i);
		}
		minimumLocal = new Point_2D(minimumLocal.x() - 0.5, minimumLocal.y());
		Line2D lineMinToOt = new Line2D.Double(ot.x(), ot.y(), minimumLocal.x(), minimumLocal.y());
		for (int i = 0; i < _points.size(); i++) {
			Line2D curLine = new Line2D.Double(_points.get(i).x(), _points.get(i).y(),
					_points.get((i + 1) % _points.size()).x(), _points.get((i + 1) % _points.size()).y());
			if (lineMinToOt.intersectsLine(curLine)) intersectionCount++;
		}
		return intersectionCount % 2 != 0;
	}
	/**
	 * Computes the area of Polygon
	 * @return - the area of this Polygon.
	 */
	@Override
	public double area() {
		double areaP = 0;
		for (int i=0 ; i<_points.size() ; i++){
			if (i == getAllPoints().length-1){
				areaP += _points.get(i).x() * _points.get(1).y() - _points.get(1).x() * _points.get(i).y();
			}
			else areaP += _points.get(i).x() * _points.get(i+1).y() - _points.get(i+1).x() * _points.get(i).y();
		}
		areaP *= 0.5;
		return areaP;
	}
	/**
	 * Computes the perimeter of Polygon
	 * @return the perimeter of this Polygon.
	 */
	@Override
	public double perimeter() {
		double preimeterP = 0;
		for (int i=0 ; i<_points.size() ; i++){
			if (i == getAllPoints().length-1){
				preimeterP += _points.get(i).distance(_points.get(0));
			}
			else preimeterP += _points.get(i).distance(_points.get(i+1));
		}
		return preimeterP;
	}
	/**
	 * Move this Polygon by the vector 0,0-->vec
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		for (int i=0 ; i<getAllPoints().length ; i++){
			getAllPoints()[i].move(vec);
		}
	}
	/**
	 * This method computes a new (deep) copy of Polygon
	 * @return a deep copy of Polygon.
	 */
	@Override
	public GeoShape copy() {
		return new Polygon_2D(this);
	}
	/**
	 * Rescales this Polygon with respect to the given center point.
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		for (int i=0 ; i<this.getAllPoints().length ; i++){
			this.getAllPoints()[i].scale(center, ratio);
		}
	}
	/**
	 * Rotates this Polygon with respect to the given center point by an angle.
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		for (int i=0 ; i<this.getAllPoints().length ; i++){
			this.getAllPoints()[i].rotate(center, angleDegrees);;
		}
	}

}
