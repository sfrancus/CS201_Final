package project.projectfiles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.ImageIcon;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapRectangleImpl;
import org.openstreetmap.gui.jmapviewer.Style;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import org.openstreetmap.gui.jmapviewer.interfaces.MapRectangle;

public class CarUnit {
    public MapRectangleImpl car;
    public double speed;
    public Point position;
    public CarModel model;
    public Layer layer;
    public CarUnit(Layer layer, String name, Coordinate a, Coordinate b)
    {
        this.car = new MapRectangleImpl(layer, name, a, b);
    }
    public CarUnit(Layer layer)
    {
       this.layer = layer;
    }
    public CarUnit()
    {
        
    }
    public void detachCarModel()
    {
        this.model = null;
    }
    public void attachCarModel(CarModel model)
    {
        this.model = model;
    }
    public void setLayer(Layer layer)
    {
        this.layer = layer;
    }
    public Layer getLayer()
    {
        return this.layer;
    }
}
