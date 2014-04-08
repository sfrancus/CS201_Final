package project.projectfiles;

import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class CarController {
    private CarUnit car;
    private static final int CONV_LONG_LAT_TO_MILE = 69;
    private static final double KM_TO_MILE = 0.621371;
    private CarGraph router;
    private List<Coordinate> coordinates;
    private int counter;
    public CarController(CarUnit e)
    {
        this.car = e;
        this.router = new CarGraph();
        String freeway = this.car.model.getFreeway();
        if(freeway.equals("101")) freeway = "105";
        this.coordinates = this.router.generateRoute(freeway+".xml", "east");
        this.car.car.coord = this.coordinates.get(this.counter);
        
        this.setCarColor();
    }
    
    public void actionOnClick()
    {
        JDialog carDialogPane = new JDialog();
        carDialogPane.setSize(new Dimension(400, 400));
        carDialogPane.setTitle("Car Information");
        carDialogPane.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        JPanel dialogPanel = new JPanel();
        JLabel speed = new JLabel("Speed: " + this.car.speed + " mph ");
        dialogPanel.add(speed);
        carDialogPane.add(dialogPanel);
        carDialogPane.setLocationRelativeTo(null);
        carDialogPane.setVisible(true);
    }
    public void update()
    {
        this.counter++;
        double a = 0.0;
        if(this.counter < this.coordinates.size())
        {
            
            a+=findDistanceBetween(this.car.car.getCoordinate(), this.coordinates.get(this.counter));
            int directedMotion = this.counter;
            if(this.car.model.getDirection().equals("West") || this.car.model.getDirection().equals("South"))
            directedMotion = this.coordinates.size() - this.counter;
            this.car.model.setPosition(this.coordinates.get(directedMotion));
            
            this.car.render();
        }
    }
    /****
     * Determines using the distance between coordinates how fast the car will be moving on the map
     */
    public void proportionalTimeAlgorithm()
    {
        double proportionalChange = this.car.model.getSpeed()/CONV_LONG_LAT_TO_MILE;
    }
    /****
     * implements the Haversine formula in Java to determine the distance (in miles) between two coordinates
     * @return double distance between points in miles
     */
    private double findDistanceBetween(Coordinate a, Coordinate b)
    {
        final int RADIUS = 6371; //Earth's radius in kilometers
        double DELTA_LATITUDE = Math.toRadians(a.getLat() - b.getLat());
        double DELTA_LONGITUDE = Math.toRadians(a.getLon() - b.getLon());
        double d = Math.sin(DELTA_LATITUDE / 2) * Math.sin(DELTA_LATITUDE / 2) + 
                Math.cos(Math.toRadians(a.getLat())) * Math.cos(Math.toRadians(b.getLat())) * 
                Math.sin(DELTA_LONGITUDE / 2) * Math.sin(DELTA_LONGITUDE/ 2);
        double c = 2 * Math.atan2(Math.sqrt(d), Math.sqrt(1-d));
        return c*RADIUS*KM_TO_MILE;
    }
    public void setCarColor()
    {
        double speed = this.car.model.getSpeed();
        if(speed <= 20.0)
        {
            this.car.car.setBackColor(Color.RED);
        }
        else if(speed > 20.0 && speed <= 50.0)
        {
            this.car.car.setBackColor(Color.YELLOW);
        }
        else if(speed > 50.0)
        {
            this.car.car.setBackColor(Color.GREEN);
        }
    }
}
