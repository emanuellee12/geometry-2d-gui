package ex2.Test;

import ex2.ex2.Ex2_Const;
import ex2.geo.Point_2D;
import ex2.geo.Triangle_2D;
import org.junit.jupiter.api.Test;

import static ex2.geo.Point_2D.ORIGIN;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Triangle_2DTest {
    Triangle_2D T1 = new Triangle_2D(new Point_2D(0,0), new Point_2D(2,0), new Point_2D(2,2));

    @Test
    void contains() {
        Point_2D _p4 = new Point_2D(1,1);
        Point_2D _p5 = new Point_2D(1,3);
        assertTrue(T1.contains(_p4));
        assertFalse(T1.contains(_p5));
    }

    @Test
    void area() {
        assertEquals(T1.area(),2 , Ex2_Const.EPS);
    }

    @Test
    void perimeter() {
        assertEquals(T1.perimeter(), 4+Math.sqrt(8), Ex2_Const.EPS);
    }

    @Test
    void translate() {
        T1.translate(new Point_2D(1,1));
        assertEquals(new Point_2D(1,1), T1.getAllPoints()[0]);
        assertEquals(new Point_2D(3,1), T1.getAllPoints()[1]);
        assertEquals(new Point_2D(3,3), T1.getAllPoints()[2]);
    }

    @Test
    void copy() {
        Triangle_2D T1_copy = (Triangle_2D) T1.copy();
        assertEquals(T1.getAllPoints()[0],T1_copy.getAllPoints()[0]);
        assertEquals(T1.getAllPoints()[1],T1_copy.getAllPoints()[1]);
        assertEquals(T1.getAllPoints()[2],T1_copy.getAllPoints()[2]);
        T1_copy.getAllPoints()[0] = new Point_2D(1,1);
        assertNotSame(T1.getAllPoints()[0],T1_copy.getAllPoints()[0]);
    }

    @Test
    void scale() {
        Point_2D _p4 = new Point_2D(1,0);
        T1.scale(_p4,1);
        assertEquals(new Point_2D(0,0),T1.getAllPoints()[0]);
        assertEquals(new Point_2D(2,0),T1.getAllPoints()[1]);
        assertEquals(new Point_2D(2,2),T1.getAllPoints()[2]);
        T1.scale(_p4,0.5);
        assertEquals(new Point_2D(0.5,0), T1.getAllPoints()[0]);
        assertEquals(new Point_2D(1.5,0), T1.getAllPoints()[1]);
        assertEquals(new Point_2D(1.5,1), T1.getAllPoints()[2]);
    }

    @Test
    void rotate() {
        //_2D T1 = new Triangle_2D(new Point_2D(0,0), new Point_2D(2,0), new Point_2D(2,2));
        Point_2D p = new Point_2D(T1.getAllPoints()[0]);
        Point_2D p2 = new Point_2D(T1.getAllPoints()[1]);
        Point_2D p3 = new Point_2D(T1.getAllPoints()[2]);
        double d00 = p.distance();
        double d10 = p2.distance();
        double d000 = p3.distance();
        p.rotate(ORIGIN,10);
        p2.rotate(ORIGIN,10);
        p3.rotate(ORIGIN,10);
        double d01 = p.distance();
        double d11 = p2.distance();
        double d001 = p3.distance();
        assertEquals(d00, d01, Ex2_Const.EPS);
        assertEquals(d10, d11, Ex2_Const.EPS);
        assertNotEquals(p,T1.getAllPoints()[1]);
        assertEquals(d000, d001, Ex2_Const.EPS);
        assertNotEquals(p,T1.getAllPoints()[2]);
    }

    @Test
    void testEquals() {
        Triangle_2D T2 = new Triangle_2D(new Point_2D(0,0), new Point_2D(2,0), new Point_2D(2,2));
        assertTrue(T2.equals(T1));
    }

    @Test
    void testToString() {
        String st1 = T1.toString();
        Triangle_2D s11 = new Triangle_2D(st1);
        assertEquals(s11, T1);
    }
}