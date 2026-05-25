package ex2.Test;

import ex2.ex2.Ex2_Const;
import ex2.geo.Circle_2D;
import ex2.geo.Point_2D;
import org.junit.jupiter.api.Test;

import static ex2.geo.Point_2D.ORIGIN;
import static org.junit.jupiter.api.Assertions.*;

class Circle_2DTest {
    Circle_2D c1 = new Circle_2D(new Point_2D(0,0), 2);
    Circle_2D c2 = new Circle_2D(new Point_2D(0,0), 5);
    Circle_2D c3 = new Circle_2D(new Point_2D(1,7), 5);



    @Test
    void testToString() {
        String expected = "0.0,0.0, 2.0";
        assertEquals(expected, c1.toString());
    }

    @Test
    void contains() {
        Point_2D p1 = new Point_2D(1,1);
        Point_2D p2 = new Point_2D(3,3);
        assertTrue(c1.contains(p1));
        assertFalse(c1.contains(p2));
    }

    @Test
    void area() {
        assertEquals(c1.area(), 4*Math.PI, Ex2_Const.EPS);
        assertFalse(c1.area() == 5);
    }

    @Test
    void perimeter() {
        assertEquals(c1.perimeter(), 4*Math.PI, Ex2_Const.EPS);
        assertEquals(c2.perimeter(), 10*Math.PI, Ex2_Const.EPS);
    }

    @Test
    void translate() {
        c1.translate(new Point_2D(1,1));
        assertEquals(new Point_2D(1,1), c1.getCenter());
        c1.translate(new Point_2D(2,5.5));
        assertEquals(new Point_2D(3,6.5), c1.getCenter());
    }

    @Test
    void copy() {
        Circle_2D c1_copy = (Circle_2D) c1.copy();
        assertEquals(c1.getCenter(),c1_copy.getCenter());

        c1_copy = (Circle_2D) c2.copy();
        assertNotSame(c1.getCenter(),c1_copy.getCenter());

    }

    @Test
    void scale() {
        Point_2D p1 = new Point_2D(1,1);
        c1.scale(p1,1);
        assertEquals(new Point_2D(0,0),c1.getCenter());
        assertEquals(c1.getRadius(), 2, Ex2_Const.EPS);
        c1.scale(p1,0.5);
        assertEquals(new Point_2D(0.5,0.5),c1.getCenter());
        assertEquals(c1.getRadius(), 1, Ex2_Const.EPS);
    }

    @Test
    void rotate() {
        Point_2D p = new Point_2D(c3.getCenter());
        double d1 = p.distance();
        p.rotate(ORIGIN,10);
        double d2 = p.distance();
        assertEquals(d1, d2, Ex2_Const.EPS);
        assertNotEquals(p,c3.getCenter());
    }

    @Test
    void testEquals() {
        String sc1 = c1.toString();
        Circle_2D c11 = new Circle_2D(sc1);
        assertEquals(c11, c1);
    }
}