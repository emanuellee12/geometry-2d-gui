package ex2.Test;

import ex2.ex2.Ex2_Const;
import ex2.geo.Point_2D;
import ex2.geo.Rect_2D;
import org.junit.jupiter.api.Test;

import static ex2.geo.Point_2D.ORIGIN;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Rect_2DTest {
    private Point_2D _p3, _p4;
    Rect_2D r1 =  new Rect_2D(new Point_2D(0,0),new Point_2D(2,2));
    Rect_2D r2 =  new Rect_2D(new Point_2D(1,5),new Point_2D(7,10));

    @Test
    void contains() {
        _p3 = new Point_2D(1,1);
        _p4 = new Point_2D(2,3);
        assertTrue(r1.contains(_p3));
        assertFalse(r1.contains(_p4));
    }

    @Test
    void area() {
        assertEquals(r1.area(), 4, Ex2_Const.EPS);
        assertFalse(r1.area() == 3);
    }

    @Test
    void perimeter() {
        double p = r1.perimeter();
        assertEquals(p, 8, Ex2_Const.EPS);
    }

    @Test
    void translate() {
        r1.translate(new Point_2D(1,1)); // 1.(1,1) 2.(3,3) 3.(3,1) 4.(1,3)
        assertEquals(new Point_2D(1,1), r1.get_p1());
        assertEquals(new Point_2D(3,1), r1.get_p2());
        assertEquals(new Point_2D(3,3), r1.get_p3());
        assertEquals(new Point_2D(1,3), r1.get_p4());
    }

    @Test
    void copy() {
        Rect_2D r1_copy = (Rect_2D) r1.copy();
        assertEquals(r1.get_p1(),r1_copy.get_p1());
        assertEquals(r1.get_p2(),r1_copy.get_p2());
        assertEquals(r1.get_p3(),r1_copy.get_p3());
        assertEquals(r1.get_p4(),r1_copy.get_p4());
        r1_copy = (Rect_2D) r2.copy();
        assertNotSame(r1.get_p1(),r1_copy.get_p1());
        assertNotSame(r1.get_p2(),r1_copy.get_p2());
    }

    @Test
    void scale() {
        Point_2D _p5 = new Point_2D(1,0);
        r1.scale(_p5,1);
        assertEquals(new Point_2D(0,0),r1.get_p1());
        assertEquals(new Point_2D(2,0), r1.get_p2());
        assertEquals(new Point_2D(2,2),r1.get_p3());
        assertEquals(new Point_2D(0,2), r1.get_p4());
        r1.scale(_p5,0.5);
        assertEquals(new Point_2D(0.5,0), r1.get_p1());
        assertEquals(new Point_2D(1.5,0), r1.get_p2());
        assertEquals(new Point_2D(1.5,1), r1.get_p3());
        assertEquals(new Point_2D(0.5,1), r1.get_p4());
    }

    @Test
    void rotate() {
        //Rect_2D r2 =  new Rect_2D(new Point_2D(1,5),new Point_2D(7,10));
        Point_2D p = new Point_2D(r2.get_p1());
        Point_2D p2 = new Point_2D(r2.get_p2());
        Point_2D p3 = new Point_2D(r2.get_p3());
        Point_2D p4 = new Point_2D(r2.get_p4());
        double d00 = p.distance();
        double d10 = p2.distance();
        double d000 = p3.distance();
        double d100 = p4.distance();
        p.rotate(ORIGIN,10);
        p2.rotate(ORIGIN,10);
        p3.rotate(ORIGIN,10);
        p4.rotate(ORIGIN,10);
        double d01 = p.distance();
        double d11 = p2.distance();
        double d001 = p3.distance();
        double d101 = p4.distance();
        assertEquals(d00, d01, Ex2_Const.EPS);
        assertNotEquals(p,r2.get_p1());
        assertEquals(d10, d11, Ex2_Const.EPS);
        assertNotEquals(p,r2.get_p2());
        assertEquals(d000, d001, Ex2_Const.EPS);
        assertNotEquals(p,r2.get_p3());
        assertEquals(d100, d101, Ex2_Const.EPS);
        assertNotEquals(p,r2.get_p4());
    }

    @Test
    void testEquals() {
        Rect_2D r3 =  new Rect_2D(new Point_2D(1,5),new Point_2D(7,10));
        assertTrue(r2.equals(r3));
    }

//    @Test
//    void testToString() {
//        String st1 = r1.toString();
//        Rect_2D r11 = new Rect_2D(st1);
//        assertEquals(r11, r1);
//    }
}