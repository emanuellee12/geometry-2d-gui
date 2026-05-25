package ex2.Test;

import ex2.geo.GeoShape;
import ex2.geo.Point_2D;
import ex2.geo.Rect_2D;
import ex2.gui.GUIShape;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GUIShapeTest {
    private GeoShape _g = new Rect_2D(new Point_2D(0,0),new Point_2D(2,2));
    private boolean _fill;
    private Color _color;
    private int _tag;
    private boolean _isSelected;
    GUIShape gui = new GUIShape(_g, true, Color.yellow, 2);

    @Test
    void isFilled() {
        assertTrue(true == gui.isFilled());
    }

    @Test
    void copy() {
        GUIShape gui_copy = (GUIShape) gui.copy();
        assertEquals(gui_copy.getShape(), gui.getShape());
        assertEquals(gui_copy.getColor(), gui.getColor());
        assertEquals(gui_copy.getTag(), gui.getTag());
        assertEquals(gui_copy.isFilled(), gui.isFilled());
        assertFalse(gui_copy == gui);
    }

    @Test
    void testToString() {
        String st1 = gui.toString();
        GUIShape r11 = new GUIShape(st1);
        assertEquals(r11.getShape(), gui.getShape());
        assertEquals(r11.getColor(), gui.getColor());
        assertEquals(r11.getTag(), gui.getTag());
        assertEquals(r11.isFilled(), gui.isFilled());
    }

}