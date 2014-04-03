// License: GPL. For details, see Readme.txt file.
package project.projectfiles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapObjectImpl;
import org.openstreetmap.gui.jmapviewer.Style;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import java.awt.event.MouseListener;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import org.openstreetmap.gui.jmapviewer.interfaces.MapRectangle;

public class CarView extends MapObjectImpl implements MapRectangle {

    private Coordinate topLeft;
    private Coordinate bottomRight;
    private CarUnit parent;

    public CarView(CarUnit parent, Coordinate topLeft, Coordinate bottomRight) {
        this(parent, null, null, topLeft, bottomRight);
    }
    public CarView(CarUnit parent, String name, Coordinate topLeft, Coordinate bottomRight) {
        this(parent, null, name, topLeft, bottomRight);
    }
    public CarView(CarUnit parent, Layer layer, Coordinate topLeft, Coordinate bottomRight) {
        this(parent, layer, null, topLeft, bottomRight);
    }
    public CarView(CarUnit parent, Layer layer, String name, Coordinate topLeft, Coordinate bottomRight) {
        this(parent, layer, name, topLeft, bottomRight, getDefaultStyle());
    }
    public CarView(CarUnit parent, Layer layer, String name, Coordinate topLeft, Coordinate bottomRight, Style style) {
        super(layer, name, style);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.parent = parent;
    }

    @Override
    public Coordinate getTopLeft() {
        return topLeft;
    }

    @Override
    public Coordinate getBottomRight() {
        return bottomRight;
    }
    public CarUnit getCar()
    {
        return this.parent;
    }
    @Override
    public void paint(Graphics g, Point topLeft, Point bottomRight) {
        // Prepare graphics
        Color oldColor = g.getColor();
        g.setColor(getColor());
        Stroke oldStroke = null;
        if (g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D) g;
            oldStroke = g2.getStroke();
            g2.setStroke(getStroke());
        }
        // Draw
        Rectangle rect = new Rectangle(topLeft.x, topLeft.y, bottomRight.x - topLeft.x, bottomRight.y - topLeft.y);
        ((Graphics2D) g).fill(rect);
        // Restore graphics
        g.setColor(oldColor);
        if (g instanceof Graphics2D) {
            ((Graphics2D) g).setStroke(oldStroke);
        }
        int width=bottomRight.x-topLeft.x;
        int height=bottomRight.y-topLeft.y;
        Point p= new Point(topLeft.x+(width/2), topLeft.y+(height/2));
        if(getLayer()==null||getLayer().isVisibleTexts()) paintText(g, p);
    }

    public static Style getDefaultStyle(){
        return new Style(Color.BLUE, null, new BasicStroke(2), getDefaultFont());
    }

    @Override
    public String toString() {
        return "MapRectangle from " + getTopLeft() + " to " + getBottomRight();
    }
        public boolean contains(Coordinate a)
        {
            return !(a.getLat() < this.bottomRight.getLat() || a.getLon() > this.bottomRight.getLon() || a.getLat() > this.topLeft.getLat() || a.getLon() < this.topLeft.getLon());
            
        }

}