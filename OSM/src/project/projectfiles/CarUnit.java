package project.projectfiles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.concurrent.Callable;

import javax.swing.ImageIcon;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapRectangleImpl;
import org.openstreetmap.gui.jmapviewer.Style;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import org.openstreetmap.gui.jmapviewer.interfaces.MapRectangle;

public class CarUnit implements Runnable{
    public CarView car;
    public double speed;
    public Point position;
    public CarModel model;
    public Layer layer;
    private CarController controller;
    public CarUnit(CarModel model, Layer layer, String name)
    {
        this.speed = model.getSpeed();
        this.car = new CarView(this, layer, name, new Coordinate(0.0,0.0), 0.0001);
        this.model = model;
        this.controller = new CarController(this);
        //  this.car.coord = this.controller.findClosestExit();
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
        //this.update();
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
    public void render()
    {
        this.car.render();
    }
    @Override
    public synchronized void run() {
        // TODO Auto-generated method stub
        this.controller.update();
    }

    public void setElapsedTime(long d)
    {
        this.model.setElapsedTime(d);
    }
    public long getElapsedTime()
    {
        return this.model.getElapsedTime();
    }

}
