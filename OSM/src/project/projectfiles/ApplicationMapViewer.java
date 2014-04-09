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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
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
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import org.openstreetmap.gui.jmapviewer.interfaces.MapRectangle;

import com.sun.tools.javac.util.List;


public class ApplicationMapViewer extends JPanel {
    private static final int REFRESH_RATE = 50;
    private ArrayList<CarUnit> cars;
    private JMapViewer newMap;
    private ArrayList<Timer> timers;
    public ApplicationMapViewer()
    {
        this.cars = new ArrayList<CarUnit>();
        newMap = new JMapViewer();
        LayerGroup carLayerGroup = new LayerGroup("Active Cars");
        Layer layer = new Layer("USA");
        //final CarUnit car1 = new CarUnit(64.7777777, layer, "Car 1", new Coordinate(34.035378, -118.329929));
        //car1.car.setColor(Color.RED);
        Layer wales = new Layer("UK");
        //newMap.addMapMarker(car1.car);
        newMap.setScrollWrapEnabled(true);
        newMap.setFocusable(true);
        newMap.setPreferredSize(new Dimension(800,600));
        newMap.setDisplayPosition(new Coordinate(34.05, -118.25), 12);
        DefaultMapController dmc = new DefaultMapController(newMap);
       // dmc.setMovementEnabled(false);
        this.setSize(new Dimension(800, 600));
        this.add(newMap);
        this.setVisible(true);
        ExecutorService exec = Executors.newFixedThreadPool(10);
        
        /*exec.scheduleAtFixedRate(new Runnable()
        {

            @Override
            public void run() {
                for(int i = 0; i < cars.size(); i++)
               {
                 cars.get(i).run();
                 
                 
                }
                
                repaint();
            }
            
        }, 0, REFRESH_RATE, TimeUnit.MILLISECONDS);*/
        
        
    }
    public void refreshData(ArrayList<CarModel> carModels)
    {
        
        this.cars.clear();
        for(int i = 0; i < carModels.size(); i++)
        {
            Layer layer = new Layer("Car" + Integer.toString(i));
            final CarUnit car = new CarUnit(carModels.get(i), layer, Integer.toString(i));
            final Timer time = new Timer();
            class Task extends TimerTask {
                @Override
                public void run() {
                    int newStepTime = car.getRate();
                    car.run();
                    repaint();
                    time.schedule(new Task(), newStepTime);
                }

            }
           Task thisTask = new Task();
           thisTask.run();
           this.cars.add(car);
        }
        
        newMap.removeAllMapMarkers();
        for(CarUnit car: this.cars)
        {
           newMap.addMapMarker(car.car);
        }
        this.newMap.repaint();
        this.repaint();
       
    }

}
