package ex2.Test;

import ex2.ex2.GUI_Shape_Collection;
import ex2.geo.*;
import ex2.gui.Ex2;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;

class Ex2Test {
    Point_2D p2 = new Point_2D(3,4);
    Point_2D p3 = new Point_2D(6,8);
    Ex2 ex2 = Ex2.getInstance();
    GUI_Shape_Collection shapes = ex2.getShape_Collection();
    Circle_2D c = new Circle_2D(p2,2);
    Triangle_2D t = new Triangle_2D(p3, p2, new Point_2D(2,2));
    Rect_2D r = new Rect_2D(p2, p3);
    Segment_2D s = new Segment_2D(p2, p3);
    GUIShape g = new GUIShape(c, true, Color.yellow, 0);
    GUIShape g1 = new GUIShape(t, false, Color.blue, 22);
    GUIShape g2 = new GUIShape(r, true, Color.red, 1);
    GUIShape g3 = new GUIShape(s, false, Color.green, 3);


    @Test
    void init() {
        shapes.add(g);
        shapes.add(g1);
        ex2.init(shapes);
        ex2.show();
        assertSame(ex2.get_color(), Color.blue);
        assertEquals(ex2.get_mode(), "");
        shapes.add(g2);
        shapes.add(g3);
        ex2.actionPerformed("Red");
        assertEquals(ex2.get_mode(), "Red");
        assertEquals(ex2.get_color(), Color.red);
        ex2.init(shapes);
        ex2.show();
        assertSame(ex2.get_color(), Color.blue);
        assertEquals(ex2.get_mode(), "");
    }

    @Test
    void getInfo() {
        shapes.add(g);
        shapes.add(g1);
        ex2.init(shapes);
        ex2.show();
        String s = g.toString() + "\n" +g1.toString()+ "\n";
        String gi = ex2.getInfo();
        assertEquals( s, gi);
    }
}