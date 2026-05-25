package ex2.Test;

import ex2.ex2.ShapeCollection;
import ex2.geo.*;
import ex2.gui.GUIShape;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ShapeCollectionTest {
    Circle_2D c = new Circle_2D(Point_2D.ORIGIN,2);
    Triangle_2D t = new Triangle_2D(Point_2D.ORIGIN, new Point_2D(0,1), new Point_2D(2,2));
    Rect_2D r = new Rect_2D(Point_2D.ORIGIN, new Point_2D(2,2));
    Segment_2D s = new Segment_2D(Point_2D.ORIGIN, new Point_2D(2,2));
    GUIShape g = new GUIShape(c, true, Color.yellow, 0);
    GUIShape g1 = new GUIShape(t, false, Color.blue, 22);
    GUIShape g2 = new GUIShape(r, true, Color.red, 1);
    GUIShape g3 = new GUIShape(s, false, Color.green, 3);
    ShapeCollection sc = new ShapeCollection();

    @Test
    void size() {
        sc.add(g);
        sc.add(g1);
        sc.add(g2);
        assertTrue(3 == sc.size());
    }

    @Test
    void removeElementAt() {
        sc.add(g);
        sc.add(g1);
        sc.add(g2);
        sc.removeElementAt(2);
        assertTrue(2 == sc.size());
        assertTrue(g == sc.get(0));
        assertTrue(g1 == sc.get(1));
    }

    @Test
    void addAt() {
        sc.add(g);
        sc.add(g1);
        sc.add(g2);
        sc.addAt(g3, 2);
        assertTrue(4 == sc.size());
        assertTrue(g == sc.get(0));
        assertTrue(g1 == sc.get(1));
        assertTrue(g3 == sc.get(2));
        assertTrue(g2 == sc.get(3));
    }

    @Test
    void add() {
        assertTrue(0 == sc.size());
        sc.add(g);
        assertTrue(1 == sc.size());
        assertTrue(g == sc.get(0));
    }

    @Test
    void copy() {
        sc.add(g);
        sc.add(g1);
        sc.add(g2);
        ShapeCollection sc_copy = (ShapeCollection) sc.copy();
        assertTrue(sc_copy.size() == sc.size() && sc_copy.size() == 3);
        assertTrue(sc_copy.get(0) == sc.get(0));
        assertTrue(sc_copy.get(1) == sc.get(1));
        assertTrue(sc_copy.get(2) == sc.get(2));
    }

    @Test
    void sort() {
        sc.add(g);//a=12.5, p=12.5, t=0
        sc.add(g1);//area=1, p=6, t=22
        sc.add(g2);//area=4, p=8, t-1
        sc.sort(ShapeComp.CompByArea);
        assertTrue(g1 == sc.get(0));
        assertTrue(g2 == sc.get(1));
        assertTrue(g == sc.get(2));
        sc.sort(ShapeComp.CompByAntiArea);
        assertTrue(g1 == sc.get(2));
        assertTrue(g2 == sc.get(1));
        assertTrue(g == sc.get(0));
        sc.sort(ShapeComp.CompByPerimeter);
        assertTrue(g1 == sc.get(0));
        assertTrue(g2 == sc.get(1));
        assertTrue(g == sc.get(2));
        sc.sort(ShapeComp.CompByAntiPerimeter);
        assertTrue(g1 == sc.get(2));
        assertTrue(g2 == sc.get(1));
        assertTrue(g == sc.get(0));
        sc.sort(ShapeComp.CompByTag);
        assertTrue(g1 == sc.get(2));
        assertTrue(g2 == sc.get(1));
        assertTrue(g == sc.get(0));
        sc.sort(ShapeComp.CompByAntiTag);
        assertTrue(g1 == sc.get(0));
        assertTrue(g2 == sc.get(1));
        assertTrue(g == sc.get(2));
        sc.sort(ShapeComp.CompByToString);
        assertTrue(g1 == sc.get(0));
        assertTrue(g2 == sc.get(1));
        assertTrue(g == sc.get(2));
        sc.sort(ShapeComp.CompByAntiToString);
        assertTrue(g1 == sc.get(2));
        assertTrue(g2 == sc.get(1));
        assertTrue(g == sc.get(0));
    }

    @Test
    void removeAll() {
        sc.add(g);
        sc.add(g1);
        sc.add(g2);
        sc.add(g3);
        sc.removeAll();
        assertTrue(0 == sc.size());
    }

    @Test
    void saveAndLoad() {
        sc.add(g);
        sc.add(g1);
        sc.add(g2);
        sc.save("test_file_sc.txt");
        sc.load("test_file_sc.txt");
        ShapeCollection scTest = new ShapeCollection();
        scTest.load("compare_file_test.txt");
        for (int i=0; i<sc.size(); i++){
            assertTrue(sc.get(i).getShape().equals(scTest.get(i).getShape()));
            assertTrue(sc.get(i).getTag() == scTest.get(i).getTag());
            assertTrue(sc.get(i).getColor().getRGB() == scTest.get(i).getColor().getRGB());
            assertTrue(sc.get(i).isFilled() == scTest.get(i).isFilled());
        }
    }

}