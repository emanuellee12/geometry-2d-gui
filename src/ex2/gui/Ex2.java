package ex2.gui;

import ex2.ex2.Ex2_Const;
import ex2.ex2.GUI_Shape_Collection;
import ex2.ex2.ShapeCollection;
import ex2.geo.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 *
 * my ID : 207881376
 * This class is a simple "interlayer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a
 * "Singleton-like" implementation.
 *
 * Notes:
 * 1. Make sure to add a detailed explanation of assignment 2 below:
 * 2. Your code will be run by our "main" based on the interfaces api given to you.
 *
 * @author boaz.ben-moshe
 */
public class Ex2 implements Ex2_GUI {
    private GUI_Shape_Collection _shapes = new ShapeCollection();
    private GUI_Shape _gs;
    private Polygon_2D _pp;
    private Color _color = Color.blue;
    private boolean _fill = false;
    private String _mode = "";
    private Point_2D _p1;

    private static Ex2 _winEx2 = null;

    private Ex2() {
        init(null);
    }

    /**
     * Inits this object with the GUI_Shapes
     * @param s a GUI collection of shapes
     */
    public void init(GUI_Shape_Collection s) {
        if (s == null) {
            _shapes = new ShapeCollection();
        } else {
            _shapes = s;
        }
        GUI_Shape _gs = null;
        Polygon_2D _pp = null;
        _color = Color.blue;
        _fill = false;
        _mode = "";
        Point_2D _p1 = null;
    }

    /**
     * This function draws the shapes in the GUI window
     * @param d - the maximum value of the <em>x</em>- and <em>y</em>-scales
     */
    public void show(double d) {
        StdDraw_Ex2.setScale(0, d);
        StdDraw_Ex2.show();
        drawShapes();
    }

    /**
     * This function returns instance
     * @return _winEx2 - instance
     */
    public static Ex2 getInstance() {
        if (_winEx2 == null) {
            _winEx2 = new Ex2();
        }
        return _winEx2;
    }
    /**
     * This function returns the color of Ex2
     * need it for init testing
     * @return _color
     */
    public Color get_color() {
        return _color;
    }
    /**
     * This function returns the mode of Ex2
     * need it for init testing
     * @return _mode
     */
    public String get_mode() {
        return _mode;
    }
    /* private static void drawGrid(int x, int y, int delta) {
         for(int i=0;i<x;i+=delta) {
             StdDraw_Ex2.line(i, 0, i, y);
         }
         for(int i=0;i<y;i+=delta) {
             StdDraw_Ex2.line(0, i, x, i);
         }
    } */

    /**
     * This function clear all shapes who showed and draw all shapes of GUI_Shape_Collection
     */
    public void drawShapes() {
        StdDraw_Ex2.clear();
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape sh = _shapes.get(i);

            drawShape(sh);
        }
        if (_gs != null) {
            drawShape(_gs);
        }
        StdDraw_Ex2.show();
    }

    /**
     * This function get GUI_Shape and draw him
     * @param g - the GUI_Shape that this function get
     */
    private static void drawShape(GUI_Shape g) {
        StdDraw_Ex2.setPenColor(g.getColor());
        if (g.isSelected()) {
            StdDraw_Ex2.setPenColor(Color.gray);
        }
        GeoShape gs = g.getShape();
        boolean isFill = g.isFilled();
        if (gs instanceof Circle_2D) {
            Circle_2D c = (Circle_2D) gs;
            Point_2D cen = c.getCenter();
            double rad = c.getRadius();
            if (isFill) {
                StdDraw_Ex2.filledCircle(cen.x(), cen.y(), rad);
            } else {
                StdDraw_Ex2.circle(cen.x(), cen.y(), rad);
            }
        }
        if (gs instanceof Segment_2D) {
            Segment_2D c = (Segment_2D) gs;
            Point_2D m0 = c.get_p1();
            Point_2D m1 = c.get_p2();
            StdDraw_Ex2.line(m0.x(), m0.y(), m1.x(), m1.y());
        }
        if (gs instanceof Rect_2D){
            Rect_2D r = (Rect_2D) gs;
            Point_2D p1 = r.get_p1();
            Point_2D p2 = r.get_p3();
            Point_2D p3 = r.get_p2();
            Point_2D p4 = r.get_p4();
            double[] x = {p1.x(), p3.x(), p2.x(), p4.x()};
            double[] y = {p1.y(), p3.y(), p2.y(), p4.y()};
            if (isFill){
                StdDraw_Ex2.filledPolygon(x, y);
            } else {
            StdDraw_Ex2.polygon(x, y);}
        }
        Point_2D[] ps = null;
        if (gs instanceof Polygon_2D) {
            ps = ((Polygon_2D) gs).getAllPoints();
        }
        if (gs instanceof Triangle_2D) {
            ps = ((Triangle_2D) gs).getAllPoints();
        }
        if (ps != null) {
            double[] xx = new double[ps.length];
            double[] yy = new double[ps.length];
            for (int i = 0; i < xx.length; i++) {
                xx[i] = ps[i].x();
                yy[i] = ps[i].y();
            }
            if (isFill) {
                StdDraw_Ex2.filledPolygon(xx, yy);
            } else {
                StdDraw_Ex2.polygon(xx, yy);
            }
        }
    }

    /**
     * This fanction sets the color of the GUI_Shape object
     * @param c - the GUI_Shape's color to be sets
     */
    private void setColor(Color c) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                s.setColor(c);
            }
        }
    }
    /**
     * This fanction sets the fill of the GUI_Shape object
     */

    private void setFill() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                s.setFilled(_fill);
            }
        }
    }

    /**
     * This fanction remove all the shapes who selected to remove
     */
    private void remove() {
        for (int i = _shapes.size() - 1; i >= 0; i--) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                _shapes.removeElementAt(i);
            }
        }
    }

    /**
     * This fanction action the manu
     * get string (manu option) and do it
     * @param p - manu option
     */
    public void actionPerformed(String p) {
        _mode = p;
        if (p.equals("Blue")) {
            _color = Color.BLUE;
            setColor(_color);
        }
        if (p.equals("Red")) {
            _color = Color.RED;
            setColor(_color);
        }
        if (p.equals("Green")) {
            _color = Color.GREEN;
            setColor(_color);
        }
        if (p.equals("White")) {
            _color = Color.WHITE;
            setColor(_color);
        }
        if (p.equals("Black")) {
            _color = Color.BLACK;
            setColor(_color);
        }
        if (p.equals("Yellow")) {
            _color = Color.YELLOW;
            setColor(_color);
        }
        if (p.equals("Fill")) {
            _fill = true;
            setFill();
        }
        if (p.equals("Empty")) {
            _fill = false;
            setFill();
        }
        if (p.equals("Remove")) {
            remove();
        }
        if (p.equals("Save")) {
            save();
        }
        if (p.equals("Load")) {
            load();
        }
        if (p.equals("ByArea")) {
            _shapes.sort(ShapeComp.CompByArea);
        }
        if (p.equals("ByAntiArea")) {
            _shapes.sort(ShapeComp.CompByAntiArea);
        }
        if (p.equals("ByToString")) {
            _shapes.sort(ShapeComp.CompByToString);
        }
        if (p.equals("ByAntiToString")) {
            _shapes.sort(ShapeComp.CompByAntiToString);
        }
        if (p.equals("ByTag")) {
            _shapes.sort(ShapeComp.CompByTag);
        }
        if (p.equals("ByAntiTag")) {
            _shapes.sort(ShapeComp.CompByAntiTag);
        }
        if (p.equals("ByPerimeter")) {
            _shapes.sort(ShapeComp.CompByPerimeter);
        }
        if (p.equals("ByAntiPerimeter")) {
            _shapes.sort(ShapeComp.CompByAntiPerimeter);
        }
        // ByAntiPerimeter
        if (p.equals("Clear")) {
            _shapes.removeAll();
        }
        if (p.equals("None")) {
            selectNone();
        }
        if (p.equals("All")) {
            selectAll();
        }
        if (p.equals("Anti")) {
            selectAnti();
        }
        if (p.equals("Info")) {
            System.out.println(getInfo());
        }

        drawShapes();

    }

    /**
     * This fanction save what showed to a file
     */
    private void save() {
        FileDialog chooser = new FileDialog(StdDraw_Ex2.getFrame(), "Save to Text file", FileDialog.SAVE);
        chooser.setVisible(true);
        String filename = chooser.getFile();
        if (filename != null) {
            _shapes.save(chooser.getDirectory() + File.separator + chooser.getFile());
        }
    }

    /**
     * This fanction load info of file and show him
     */
    private void load() {
        FileDialog chooser = new FileDialog(StdDraw_Ex2.getFrame(), "Load from Text file", FileDialog.LOAD);
        chooser.setVisible(true);
        String filename = chooser.getFile();
        if (filename != null) {
            _shapes.load(chooser.getDirectory() + File.separator + chooser.getFile());
        }
    }

    /**
     * This fanction get point the user clicked and do the user choice,
     * if the mode is polygon it will create a point of polygon
     * @param p - point
     */
    public void mouseClicked(Point_2D p) {
        System.out.println("Mode: " + _mode + "  " + p);
        if (_mode.equals("Rect") || _mode.equals("Circle") || _mode.equals("Segment")) {
            if (_gs == null) {
                _p1 = new Point_2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Move")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                _p1 = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y());
                move();
                _p1 = null;
            }
        }
        if (_mode.equals("Copy")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                _p1 = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y());
                copy();
                _p1 = null;
            }
        }
        if (_mode.equals("Rotate")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                rotate(p);
                _p1 = null;
            }
        }
        if (_mode.equals("Polygon")) {
            if (_pp == null) {
                _pp = new Polygon_2D();
            }
            _p1 = new Point_2D(p);
            _pp.add(p);
        }

        if (_mode.equals("Triangle")) {
            if (_pp == null) {
                _pp = new Polygon_2D();
            }
            _p1 = new Point_2D(p);
            _pp.add(p);
            if (_pp.getAllPoints().length == 3) {
                Point_2D[] pp = _pp.getAllPoints();
                Triangle_2D tt = new Triangle_2D(pp[0], pp[1], pp[2]);
                GUI_Shape s = new GUIShape(tt, _fill, _color, 0);
                _shapes.add(s);
                _pp = null;
                _p1 = null;
                _gs = null;
            }
        }
        if (_mode.equals("Point")) {
            select(p);
        }
        //
        if (_mode.equals("Scale_90%")) {
            scale(p, 0.9);
        }
        if (_mode.equals("Scale_110%")) {
            scale(p, 1.10);
        }
        drawShapes();
    }

    /**
     * This fanction get point and ratio - and scale all the shapes who chosen
     * @param p - point
     * @param ratio
     */
    private void scale(Point_2D p, double ratio) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected() && g != null) {
                g.scale(p, ratio);
            }
        }
    }

    /**
     * This fanction get point and select all shapes who chosen
     * @param p - point
     */
    private void select(Point_2D p) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (g != null && g.contains(p)) {
                s.setSelected(!s.isSelected());
            }
        }
    }

    /**
     * This fanction move all shapes who chosen
     */
    private void move() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected() && g != null) {
                g.translate(_p1);
            }
        }
    }

    /**
     * This fanction copy all shapes who chosen
     */
    private void copy() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                GUI_Shape s1 = s.copy();
                GeoShape g = s1.getShape();
                g.translate(_p1);
                _shapes.add(s1);
            }
        }
    }

    /**
     * This fanction get point and rotate all shapes who chosen
     * @param ang - point
     */
    private void rotate(Point_2D ang) {
        double dx = ang.x() - _p1.x();
        double dy = ang.y() - _p1.y();
        double da = Math.atan2(dy, dx);
        da = Math.toDegrees(da);
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected()) {
                g.rotate(_p1, da);
            }
        }
    }

    /**
     * This fanction select all shapes who shown
     */
    private void selectAll() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            s.setSelected(true);
        }
    }

    //printInfo
    private void printInfo() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                System.out.println(i + ") " + s.toString());
            }
        }
    }

    /**
     * This fanction NO select all shapes who shown
     * if there is shape who selected or not selected it change to no selectind at all
     */
    private void selectNone() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            s.setSelected(false);
        }
    }

    /**
     * This fanction select all shapes who selected and NO select the shapes who not selected
     */
    private void selectAnti() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            s.setSelected(!s.isSelected());
        }
    }

    /** This fanction create the polygon
     * stop the counter of polygon's points and create one
     * @param p - point
     */
    public void mouseRightClicked(Point_2D p) {
        if (_mode.equals("Polygon") && _pp != null) {
            GUIShape s = new GUIShape(_pp, _fill, _color, 0);
            _shapes.add(s);
        }
        _pp = null;
        _gs = null;
        _p1 = null;
        drawShapes();


    }

    /**
     * This fanction create a GUIShape
     * @param e - InputEvent
     */
    public void mouseMoved(MouseEvent e) {
        if (_p1 != null) {
            double x1 = StdDraw_Ex2.mouseX();
            double y1 = StdDraw_Ex2.mouseY();
            GeoShape gs = null;
            //	System.out.println("M: "+x1+","+y1);
            Point_2D p = new Point_2D(x1, y1);
            if (_mode.equals("Circle")) {
                double r = _p1.distance(p);
                gs = new Circle_2D(_p1, r);
            }
            if (_mode.equals("Rect")) {
                gs = new Rect_2D(_p1, p);
            }
            if (_mode.equals("Segment")) {
                gs = new Segment_2D(_p1, p);
            }

            if (_mode.equals("Polygon") || _mode.equals("Triangle")) {
                if (_pp == null) {
                    _pp = new Polygon_2D();
                    _pp.add(_p1);
                }
                Polygon_2D gg = new Polygon_2D(_pp);
                gg.add(p);
                gs = gg;
            }
            _gs = new GUIShape(gs, false, Color.pink, 0);
            drawShapes();
        }
    }

    /**
     * This fanction returns GUI_Shape_Collection of this Ex2 object
     * @return GUI_Shape_Collection of this Ex2 object
     */
    @Override
    public GUI_Shape_Collection getShape_Collection() {
        // TODO Auto-generated method stub
        return this._shapes;
    }
    /**
     * Draws the shapes in the GUI window
     */
    @Override
    public void show() {
        show(Ex2_Const.DIM_SIZE);
    }
    /** return a String with all the info (the toStrings) of the selected shapes. */
    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        String ans = "";
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            ans += s.toString() + "\n";
            //	ans +=s.toString()+"  area: "+s.getShape().area()+"  per: "+s.getShape().perimeter()+"\n";
        }
        return ans;
    }
}
