package project.projectfiles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.DefaultMapController;
import org.openstreetmap.gui.jmapviewer.JMapController;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.LayerGroup;
import org.openstreetmap.gui.jmapviewer.MapRectangleImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import org.openstreetmap.gui.jmapviewer.interfaces.MapRectangle;

import com.sun.tools.javac.util.List;


public class ApplicationMapViewer extends JPanel {
    
    private ArrayList<CarUnit> cars;
    private JMapViewer newMap;
    public ApplicationMapViewer()
    {
        newMap = new JMapViewer();
        LayerGroup carLayerGroup = new LayerGroup("Active Cars");
        Layer layer = new Layer("USA");
        CarUnit car1 = new CarUnit(layer, "Car 1", new Coordinate(34.055, -118.252), new Coordinate(34.05, -118.25));
        //car1.car.setColor(Color.RED);
        Layer wales = new Layer("UK");
        newMap.addMapRectangle(car1.car);

        newMap.setScrollWrapEnabled(true);
        newMap.setFocusable(true);
        newMap.setPreferredSize(new Dimension(800,600));
        newMap.setDisplayPosition(new Coordinate(34.05, -118.25), 12);
        DefaultMapController dmc = new DefaultMapController(newMap);
       // dmc.setMovementEnabled(false);
        this.setSize(new Dimension(800, 600));
        this.add(newMap);
        this.setVisible(true);
        
    }
    public void refreshData(ArrayList<CarModel> carModels)
    {
        this.cars.clear();
        int i = 0;
        for(CarModel carModel: carModels)
        {
           CarUnit newCar = new CarUnit(new Layer(Integer.toString(i)));
           newCar.attachCarModel(carModel);
           this.cars.add(newCar);
           i++;
        }
        newMap.removeAllMapRectangles();
        for(CarUnit car: this.cars)
        {
            newMap.addMapRectangle(car.car);
        }
       
    }

}
