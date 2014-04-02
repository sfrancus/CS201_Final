package project.projectfiles;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class CarModel {
    private String id, direction, onOffRamp, freeway;
    private Coordinate position;
    private double speed;
    public CarModel()
    {
        this.id = this.direction = this.onOffRamp = this.freeway = "";
        this.speed = 0.0;
    }
    public CarModel(String id, double speed, String direction, String onOffRamp, String freeway)
    {
        this.id = id;
        this.speed = speed;
        this.direction = direction;
        this.onOffRamp = onOffRamp;
        this.freeway = freeway;
    }
    public void setId(String a)
    {
        this.id = a;
    }
    public void setDirection(String a)
    {
        this.direction = a;
    }
    public void setOnOffRamp(String a)
    {
        this.onOffRamp = a;
    }
    public void setFreeway(String a)
    {
        this.freeway = a;
    }
    public void setSpeed(double a)
    {
        this.speed = a;
    }
    public double getSpeed()
    {
        return this.speed;
    }
    public String getFreeway()
    {
        return this.freeway;
    }
    public String getOnOffRamp()
    {
        return this.onOffRamp;
    }
    public String getDirection()
    {
        return this.direction;
    }
    public String getId()
    {
        return this.id;
    }
    
}
