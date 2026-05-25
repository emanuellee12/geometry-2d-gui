package ex2.Test;

import ex2.ex2.Ex2_Const;
import ex2.geo.Point_2D;
import ex2.geo.Segment_2D;
import org.junit.jupiter.api.Test;

import static ex2.geo.Point_2D.ORIGIN;
import static org.junit.jupiter.api.Assertions.*;

class Segment_2DTest {

    private Point_2D _p1 , _p2;
    Segment_2D s1 = new Segment_2D(new Point_2D(0,0),new Point_2D(2,0));
    Segment_2D s2 = new Segment_2D(new Point_2D(0,0),new Point_2D(0,1));
    Segment_2D s3 = new Segment_2D(new Point_2D(1,0),new Point_2D(5,3));
    //Segment_2D s4 = new Segment_2D(new Point_2D(1,1),new Point_2D(3,1));


    @org.junit.jupiter.api.Test
    void contains() {
        _p1 = new Point_2D(1,0);
        _p2 = new Point_2D(3,0);
        assertTrue(s1.contains(_p1));
        assertFalse(s1.contains(_p2));
    }

    @org.junit.jupiter.api.Test
    void perimeter() {
        double p = s2.perimeter();
        assertEquals(p, 2, Ex2_Const.EPS);
        p = s3.perimeter();
        assertEquals(p, 10, Ex2_Const.EPS);
    }

    @org.junit.jupiter.api.Test
    void translate() {
        s1.translate(new Point_2D(1,1)); // (1,1) --> (3,1)
        assertEquals(new Point_2D(1,1), s1.get_p1());
        assertEquals(new Point_2D(3,1), s1.get_p2());
        //assertTrue();
    }

    @org.junit.jupiter.api.Test
    void copy() {
        Segment_2D s1_copy = (Segment_2D) s1.copy();
        assertEquals(s1.get_p1(),s1_copy.get_p1());
        assertEquals(s1.get_p2(),s1_copy.get_p2());
        s1_copy = (Segment_2D) s2.copy();
        assertNotSame(s1.get_p1(),s1_copy.get_p1());
        assertNotSame(s1.get_p2(),s1_copy.get_p2());
    }

    @org.junit.jupiter.api.Test
    void scale() {
        _p1 = new Point_2D(1,0);
        s1.scale(_p1,1);
        assertEquals(new Point_2D(0,0),s1.get_p1());
        assertEquals(new Point_2D(2,0),s1.get_p2());
        s1.scale(_p1,0.5);
        assertEquals(new Point_2D(0.5,0), s1.get_p1());
        assertEquals(new Point_2D(1.5,0), s1.get_p2());
    }

    @org.junit.jupiter.api.Test
    void rotate() {
        Point_2D p = new Point_2D(s3.get_p1());
        Point_2D p2 = new Point_2D(s3.get_p2());
        double d00 = p.distance();
        double d10 = p2.distance();
        p.rotate(ORIGIN,10);
        p2.rotate(ORIGIN,10);
        double d01 = p.distance();
        double d11 = p2.distance();
        assertEquals(d00, d01, Ex2_Const.EPS);
        assertNotEquals(p,s3.get_p1());
        assertEquals(d10, d11, Ex2_Const.EPS);
        assertNotEquals(p,s3.get_p2());
    }

    @Test
    void testEquals() {
        Segment_2D s2 = new Segment_2D(new Point_2D(0,0),new Point_2D(2,0));
        assertTrue(s2.equals(s1));
    }

    @Test
    void testToString() {
        String st1 = s1.toString();
        String st2 = s2.toString();
        Segment_2D s11 = new Segment_2D(st1);
        assertEquals(s11, s1);
        Segment_2D s12 = new Segment_2D(st2);
        assertEquals(s12, s2);
    }
}