package ex2.Test;

import ex2.ex2.Ex2_Const;
import ex2.geo.Point_2D;
import org.junit.jupiter.api.Test;

import static ex2.geo.Point_2D.ORIGIN;
import static org.junit.jupiter.api.Assertions.*;

class Point_2DTest {

    private Point_2D p1_2 = new Point_2D(1,2);
    private Point_2D p4_6 = new Point_2D(4,6);
    @Test
    void add() {
        Point_2D p = p1_2.add(p4_6);
        Point_2D pp = new Point_2D(5,8);
        assertEquals(p,pp);
    }

    @Test
    void testToString() {
        String expected = "1.0,2.0";
        assertEquals(expected, p1_2.toString());
    }

    @Test
    void distance() {
        double d = p1_2.distance();
        assertEquals(d,Math.sqrt(5), Ex2_Const.EPS);
    }

    @Test
    void testDistance() {
        double d = p1_2.distance(p4_6);
        assertEquals(d,5, Ex2_Const.EPS);
    }

    @Test
    void testEquals() {
        Point_2D p1 = new Point_2D(2, 3);
        Point_2D p2 = new Point_2D(2, 3);
        Point_2D p3 = new Point_2D(4, 5);
        assertTrue(p1.equals(p2));
        assertFalse(p1.equals(p3));

    }

    @Test
    void close2equals() {
        Point_2D point1 = new Point_2D(2, 3);
        Point_2D point2 = new Point_2D(2.5, 3.5);
        assertTrue(point1.close2equals(point2, 0.8));
        assertFalse(point1.close2equals(point2, 0.01));
    }

    @Test
    void vector() {
        Point_2D p1 = new Point_2D(5, 2);
        Point_2D p2 = new Point_2D(3, 2);
        assertEquals(p2.vector(p1), new Point_2D(2,0));
        assertEquals(p1.vector(p1), new Point_2D(0,0));
    }

    @Test
    void move() {
        Point_2D p1 = new Point_2D(5, 2);
        Point_2D p2 = new Point_2D(3, 2);
        p1.move(new Point_2D(-2,0));
        assertTrue(p2.close2equals(p2,Ex2_Const.EPS));
    }

    @Test
    void scale() {
        Point_2D p = new Point_2D(p1_2);
        p.scale(new Point_2D(0.5, 1),1);
        assertEquals(new Point_2D(1, 2),p);
        p.scale(ORIGIN,0.5);
        assertEquals(new Point_2D(0.5, 1),p);
    }

    @Test
    void rotate() {
        Point_2D p = new Point_2D(p1_2);
        double d1 = p.distance();
        p.rotate(ORIGIN,10);
        double d2 = p.distance();
        assertEquals(d1, d2, Ex2_Const.EPS);
        assertNotEquals(p,p1_2);
    }
}