package ex2.gui;
/**
 * This class implements the GUI_shape.
 * Ex2: you should implement this class!
 * @author I2CS
 */
import ex2.geo.*;
import java.awt.*;
import java.util.Arrays;


public class GUIShape implements GUI_Shape {
	private GeoShape _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;
	// regular constructor get shape, true/false (filled or not), color and tag
	public GUIShape(GeoShape g, boolean f, Color c, int t) {
		_g = null;
		if (g != null) {
			_g = g.copy();
		}
		_fill = f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	// copy constructor get GUIShape and to copy
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}
	// string constructor get string and make GUIShape
	public GUIShape(String s) {
		String[] a = s.split(",");
		int i = 0;
		if (a[1].contains("[")) {
			i = 4;
			int red = Integer.parseInt(a[1].substring(a[1].indexOf("=") + 1));
			int green = Integer.parseInt(a[2].substring(a[2].indexOf("=") + 1));
			int blue = Integer.parseInt(a[3].substring(a[3].indexOf("=") + 1).replace("]", ""));
			_color = new Color(red, green, blue);
		} else {
			i = 2;
			_color = decodeColor(Integer.parseInt(a[1]));
		}
			boolean fill = Boolean.parseBoolean(a[i]);
			_fill = fill;
			int tag = Integer.parseInt(a[i+1]);
			_tag = tag;
			if (a[i+2].equals("Circle_2D")) {
				String circ = String.join(",", Arrays.copyOfRange(a, i+3, a.length));
				_g = new Circle_2D(circ);
			}
			if (a[i+2].equals("Triangle_2D")) {
				String tri = String.join(",", Arrays.copyOfRange(a, i+3, a.length));
				_g = new Triangle_2D(tri);
			}
			if (a[i+2].equals("Rect_2D")) {
				String rec = String.join(",", Arrays.copyOfRange(a, i+3, a.length));
				_g = new Rect_2D(rec);
			}
			if (a[i+2].equals("Segment_2D")) {
				String seg = String.join(",", Arrays.copyOfRange(a, i+3, a.length));
				_g = new Segment_2D(seg);
			}
			if (a[i+2].equals("Polygon_2D")) {
				String poly = String.join(",", Arrays.copyOfRange(a, i+3, a.length));
				_g = new Polygon_2D(poly);
			}
	}
	/**
	 * This function returns GeoShape of this GUIShape object
	 * @return _g - GeoShape of this GUIShape object
	 */
	@Override
	public GeoShape getShape() {
		return _g;
	}

	/**
	 * This function sets the shape of the GUIShape object
	 * @param g - the GUIShape's GeoShape to be set
	 */
	@Override
	public void setShape(GeoShape g) {
		_g = g;
	}
	/**
	 * This function returns if GeoShape is filled or not
	 * @return _fill - boolean answer of "is filled?"
	 */
	@Override
	public boolean isFilled() {
		return _fill;
	}
	/**
	 * This function sets the boolean answer "is filled?" of the GUIShape object
	 * @param filled - the GUIShape's _fill to be set
	 */
	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}
	/**
	 * This function returns Color of this GUIShape object
	 * @return _color - Color of this GUIShape object
	 */
	@Override
	public Color getColor() {
		return _color;
	}
	/**
	 * This function sets the Color of the GUIShape object
	 * @param cl - the GUIShape's Color to be set
	 */
	@Override
	public void setColor(Color cl) {
		_color = cl;
	}
	/**
	 * This function returns Tag of this GUIShape object
	 * @return _Tag - Tag of this GUIShape object
	 */
	@Override
	public int getTag() {
		return _tag;
	}
	/**
	 * This function sets the Tag of the GUIShape object
	 * @param tag - the GUIShape's Tag to be set
	 */
	@Override
	public void setTag(int tag) {
		_tag = tag;

	}
	/**
	 * This method computes a new (deep) copy of GUI_Shape
	 * @return a deep copy of GUI_Shape.
	 */
	@Override
	public GUI_Shape copy() {
		GUI_Shape cp = new GUIShape(this);
		return cp;
	}
	/**
	 * This method returns a String representing this GUIShape
	 * @return a String representing this GUIShape
	 */
	@Override
	public String toString() {
		String ans = "" + this.getClass().getSimpleName() + "," + _color + "," + _fill + "," + _tag + "," + this._g.getClass().getSimpleName() + "," + _g.toString();
		return ans;
	}
	/**
	 * This function returns boolean answer of "isSelected"  of this GUIShape object
	 * @return _isSelected - isSelected of this GUIShape object
	 */
	@Override
	public boolean isSelected() {
		return this._isSelected;
	}
	/**
	 * This function sets the isSelected of the GUIShape object
	 * @param s - the GUIShape's isSelected to be set
	 */
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
	/**
	 * This function get code Color and returns the color of this GUIShape object
	 * @return ce - color of this GUIShape object
	 */
	public static int colorEncoding(Color c) {
		int r = c.getRed();
		int b = c.getBlue();
		int g = c.getGreen();
		int ce = r * 256 * 256 + g * 256 + b;
		return ce;
	}
	/**
	 * This function get Color and returns the code color of this GUIShape object
	 * @return color(r,g,b) - color of this GUIShape object
	 */
	public static Color decodeColor(int ce) {
		int r = (ce >> 16) & 0xFF;
		int g = (ce >> 8) & 0xFF;
		int b = ce & 0xFF;
		return new Color(r, g, b);
	}
}
