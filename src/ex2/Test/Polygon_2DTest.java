package ex2.Test;

import ex2.ex2.Ex2_Const;
import ex2.geo.Point_2D;
import ex2.geo.Polygon_2D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static ex2.ex2.Ex2_Const.EPS;
import static ex2.geo.Point_2D.ORIGIN;
import static org.junit.jupiter.api.Assertions.*;

class Polygon_2DTest {
    private ArrayList<Point_2D> points1 = new ArrayList<>(Arrays.asList(new Point_2D(0,1) ,
            new Point_2D(0,11), new Point_2D(2,5), new Point_2D(9,5), new Point_2D(10,3)));
    Polygon_2D P = new Polygon_2D(points1);


  @Test
    void contains() {
    Point_2D _p4 = new Point_2D(2,3);
    Point_2D _p5 = new Point_2D(3,6);
    assertTrue(P.contains(_p4));
    assertFalse(P.contains(_p5));
    }

    @Test
    void area() {
      assertTrue(P.area() - 35 < EPS);
    }

    @Test
    void perimeter() {
      assertTrue(P.perimeter() - 35.75867 < EPS);
    }

    @Test
    void translate() {
      P.translate(new Point_2D(1,1));
        assertEquals(new Point_2D(1,2),  P.getAllPoints()[0]);
        assertEquals(new Point_2D(1,12), P.getAllPoints()[1]);
        assertEquals(new Point_2D(3,6), P.getAllPoints()[2]);
        assertEquals(new Point_2D(10,6), P.getAllPoints()[3]);
        assertEquals(new Point_2D(11,4), P.getAllPoints()[4]);
    }

    @Test
    void copy() {
        Polygon_2D P_copy = (Polygon_2D) P.copy();
        assertEquals(P.getAllPoints()[0],P_copy.getAllPoints()[0]);
        assertEquals(P.getAllPoints()[1],P_copy.getAllPoints()[1]);
        assertEquals(P.getAllPoints()[2],P_copy.getAllPoints()[2]);
        assertEquals(P.getAllPoints()[3],P_copy.getAllPoints()[3]);
        assertEquals(P.getAllPoints()[4],P_copy.getAllPoints()[4]);
    }

    @Test
    void scale() {
        Point_2D _p4 = new Point_2D(1,0);
        P.scale(_p4,1);
        assertEquals(new Point_2D(0,1),P.getAllPoints()[0]);
        assertEquals(new Point_2D(0,11),P.getAllPoints()[1]);
        assertEquals(new Point_2D(2,5),P.getAllPoints()[2]);
        assertEquals(new Point_2D(9,5),P.getAllPoints()[3]);
        assertEquals(new Point_2D(10,3),P.getAllPoints()[4]);
        P.scale(_p4,0.5);
        assertEquals(new Point_2D(0.5,0.5), P.getAllPoints()[0]);
        assertEquals(new Point_2D(0.5,5.5), P.getAllPoints()[1]);
        assertEquals(new Point_2D(1.5,2.5), P.getAllPoints()[2]);
        assertEquals(new Point_2D(5,2.5),P.getAllPoints()[3]);
        assertEquals(new Point_2D(5.5,1.5),P.getAllPoints()[4]);
    }

    @Test
    void rotate() {
        Polygon_2D P_copy = new Polygon_2D(P);
        double d00 = P_copy.getAllPoints()[0].distance();
        double d10 = P_copy.getAllPoints()[1].distance();
        double d000 = P_copy.getAllPoints()[2].distance();
        double d100 = P_copy.getAllPoints()[3].distance();
        double d110 = P_copy.getAllPoints()[4].distance();
        P_copy.rotate(ORIGIN,80);
        double d01 = P_copy.getAllPoints()[0].distance();
        double d11 = P_copy.getAllPoints()[1].distance();
        double d001 = P_copy.getAllPoints()[2].distance();
        double d101 = P_copy.getAllPoints()[3].distance();
        double d111 = P_copy.getAllPoints()[4].distance();
        assertEquals(d00, d01, Ex2_Const.EPS);
        assertEquals(d10, d11, Ex2_Const.EPS);
        assertEquals(d000, d001, Ex2_Const.EPS);
        assertEquals(d100, d101, Ex2_Const.EPS);
        assertEquals(d110, d111, Ex2_Const.EPS);
        assertNotEquals(P.getAllPoints()[0],P_copy.getAllPoints()[0]);
        assertNotEquals(P.getAllPoints()[1],P_copy.getAllPoints()[1]);
        assertNotEquals(P.getAllPoints()[2],P_copy.getAllPoints()[2]);
        assertNotEquals(P.getAllPoints()[3],P_copy.getAllPoints()[3]);
        assertNotEquals(P.getAllPoints()[4],P_copy.getAllPoints()[4]);
    }

    @Test
    void testEquals() {
        Polygon_2D P2 = new Polygon_2D(points1);
        assertTrue(P2.equals(P));
    }

    @Test
    void testToString() {
        String sp1 = P.toString();
        Polygon_2D  p11 = new Polygon_2D (sp1);
        assertEquals(p11, P);

    }
}