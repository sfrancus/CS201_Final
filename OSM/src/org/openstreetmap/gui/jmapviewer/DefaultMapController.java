package org.openstreetmap.gui.jmapviewer;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapRectangle;

import project.projectfiles.CarUnit;
import project.projectfiles.CarView;


public class DefaultMapController extends JMapController implements MouseMotionListener,
MouseWheelListener, KeyListener, MouseListener {

    private static final int MOUSE_BUTTONS_MASK = MouseEvent.BUTTON3_DOWN_MASK | MouseEvent.BUTTON1_DOWN_MASK
    | MouseEvent.BUTTON2_DOWN_MASK;

    private static final int MAC_MOUSE_BUTTON3_MASK = MouseEvent.CTRL_DOWN_MASK | MouseEvent.BUTTON1_DOWN_MASK;
    public DefaultMapController(JMapViewer map) {
        super(map);
    }

    private Point lastDragPoint;

    private boolean isMoving = false;
    private boolean enableMapMarkers = false;
    private boolean movementEnabled = true;

    private int movementMouseButton = MouseEvent.BUTTON3;
    private int movementMouseButtonMask = MouseEvent.BUTTON3_DOWN_MASK;
    private long lastClick;
    private boolean wheelZoomEnabled = true;
    private boolean doubleClickZoomEnabled = true;


    public void mouseWheelMoved(MouseWheelEvent e) {
        if (wheelZoomEnabled) {
           map.setZoom(map.getZoom() - e.getWheelRotation(), e.getPoint());
        }
    }

    public boolean isMovementEnabled() {
        return movementEnabled;
    }

    /**
     * Enables or disables that the map pane can be moved using the mouse.
     *
     * @param movementEnabled
     */
    public void setMovementEnabled(boolean movementEnabled) {
        this.movementEnabled = movementEnabled;
    }

    public int getMovementMouseButton() {
        return movementMouseButton;
    }

    /**
     * Sets the mouse button that is used for moving the map. Possible values
     * are:
     * <ul>
     * <li>{@link MouseEvent#BUTTON1} (left mouse button)</li>
     * <li>{@link MouseEvent#BUTTON2} (middle mouse button)</li>
     * <li>{@link MouseEvent#BUTTON3} (right mouse button)</li>
     * </ul>
     *
     * @param movementMouseButton
     */

    public boolean isWheelZoomEnabled() {
        return wheelZoomEnabled;
    }

    public void setWheelZoomEnabled(boolean wheelZoomEnabled) {
        this.wheelZoomEnabled = wheelZoomEnabled;
    }

    public boolean isDoubleClickZoomEnabled() {
        return doubleClickZoomEnabled;
    }

    public void setDoubleClickZoomEnabled(boolean doubleClickZoomEnabled) {
        this.doubleClickZoomEnabled = doubleClickZoomEnabled;
    }

    public void mouseEntered(MouseEvent e) {
       
    }

    public void mouseExited(MouseEvent e) {
    }


    /**
     * Replies true if we are currently running on OSX
     *
     * @return true if we are currently running on OSX
     */
    public static boolean isPlatformOsx() {
        String os = System.getProperty("os.name");
        return os != null && os.toLowerCase().startsWith("mac os x");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        int key = e.getKeyCode();
        double diffx = 0, diffy = 0;
        if(key == KeyEvent.VK_DOWN)
        { 
            diffy = 5;
        }
        else if(key == KeyEvent.VK_UP)
        {
            diffy = -5;
        }
        else if(key == KeyEvent.VK_LEFT)
        {
            diffx = -5;
        }
        else if(key == KeyEvent.VK_RIGHT)
        {
            diffx = 5;
        }
        map.moveMap((int)diffx, (int)diffy);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {

     //   map.addMapMarker(new MapMarkerDot(alpha.getLat(), alpha.getLon()));
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
       //synchronized(this){
        // TODO Auto-generated method stub
       // System.out.println("Count of listeners: " + arg0.getSource().toString()); 
        if(this.enableMapMarkers)
        {
            Coordinate alpha = map.getPosition(arg0.getPoint());
            map.addMapMarker(new MapMarkerDot(alpha.getLat(), alpha.getLon()));
         //   this.enableMapMarkers = false;
        }
        else {
        if(System.currentTimeMillis() < this.lastClick + 100) return;
        
        Coordinate alpha = map.getPosition(new Point(arg0.getX(), arg0.getY()));
        List<MapMarker> cars = map.getMapMarkerList();
        for(MapMarker carView: cars)
        {
            if(carView instanceof CarView)
            {
               if(((CarView) carView).contains(alpha)){
                  CarUnit car = ((CarView)carView).getCar();
                  boolean dick = car.click();
                  //System.out.println(dick);
               }
            }
        }
        }
        lastClick = System.currentTimeMillis();
     //   }
    }
}
