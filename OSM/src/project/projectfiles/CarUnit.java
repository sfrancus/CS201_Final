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
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import org.openstreetmap.gui.jmapviewer.interfaces.MapRectangle;

public class CarUnit {
    public MapMarker car;
    public double speed;
    public Point position;
    public CarModel model;
    public Layer layer;
    private CarController controller;
    public CarUnit(Layer layer, String name, Coordinate a)
    {
        this.car = new CarView(this, layer, name, a, 0.00007);
        this.controller = new CarController(this);
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
    public void click()
    {
        this.controller.actionOnClick();
    }
}
