package project.projectfiles;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

public class HistoricalDataViewer extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private SQLFluxCapacitor SQLReader;
    
    private Map<String, Double[]> data;
    private Object [][] trafficVol;
    private Object [][] avgSpeed;
    
    //Timer: Updates every 3 minutes (miliseconds)
    private int delay = 180000;
    
    //format SQL Data
    private void formatData() {
        try {
            //pull data and iterate through
            data = SQLReader.retrieveData();            
            Iterator<?> it = (Iterator<?>) data.entrySet().iterator();
            
            //initialize arrays
            //if database is empty, seed data arrays and map with a bunch of zeros
            if(data.size() == 0) {
                trafficVol = new Object[][] {
                        { 0.0, 0.0, 0.0, 0.0, 0.0 },
                        { 0.0, 0.0, 0.0, 0.0, 0.0 },
                      };
                avgSpeed = new Object[][] {
                        { 0.0, 0.0, 0.0, 0.0, 0.0 },
                        { 0.0, 0.0, 0.0, 0.0, 0.0 },
                      };
            }
            else {
                trafficVol = new Object[data.size()][2];
                avgSpeed = new Object[data.size()][2];
            }
            
            int row = 0;
            for(Entry<String, Double[]> entry : data.entrySet()) {
                //convert key from hh:mm to hours
                String time = entry.getKey();
                String [] hhmm = time.split(":");
                int hours = Integer.parseInt(hhmm[0]);
                int mins = Integer.parseInt(hhmm[1]);
                double result = ((double)hours * 60 + (double)mins) / 60;
                
                //store times
                trafficVol[row][0] = result;
                avgSpeed[row][0] = result;
                //store avg speed or number of cars
                trafficVol[row][1] = entry.getValue()[0];
                avgSpeed[row][1] = entry.getValue()[1];
                row++;
            }
        }
        catch (SQLException e) {            
            e.printStackTrace();
        }
    }
    
    private void panel() {
        SQLReader = new SQLFluxCapacitor();
        formatData();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        
        //adds cards
        final JPanel cardManager = new JPanel();
        final CardLayout cl = new CardLayout();
        cardManager.setLayout(cl);
        
        cardManager.add(new GraphFactory("Average Number of Cars on the Road", "Traffic Volume", "Time (in Hours)", "Avg. Number of Cars", trafficVol), "graph1");
        cardManager.add(new GraphFactory("Average Speed of Cars on the Road", "Average Speed", "Time (in Hours)", "Speed (in Miles Per Hour)", avgSpeed), "graph2");
        
        //Menu Bar handles switching between cards + exporting
        JMenuBar jmb = new JMenuBar();
        
        final JMenuItem graph1Button = new JMenuItem("Traffic Volume");
        final JMenuItem graph2Button = new JMenuItem("Average Number of Cars");  
        
        graph1Button.setBackground(Color.LIGHT_GRAY);
        graph1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                cl.show(cardManager, "graph1");
                
                //change color
                graph1Button.setBackground(Color.LIGHT_GRAY);
                graph2Button.setBackground(Color.WHITE);
            }       
        });
            
        graph2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                cl.show(cardManager, "graph2");
                
                //change color
                graph1Button.setBackground(Color.WHITE);
                graph2Button.setBackground(Color.LIGHT_GRAY);
            }       
        });
        
        jmb.add(graph1Button);
        jmb.add(graph2Button);
        
        add(jmb, BorderLayout.SOUTH);
        add(cardManager, BorderLayout.CENTER);
    }
    
    public HistoricalDataViewer() {
        //call up UI
        panel();
        
        //Repaint Component on a timer
        new Timer(delay, this).start();
    }
    
    public void refreshData(Map avg_data, Map freeway_partitioned_data) {
       try {
           this.SQLReader.updateAggregateData(avg_data);
           this.SQLReader.updateFreewaySpecificData(freeway_partitioned_data);
       } 
       catch (SQLException e) {
         
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //remove all components, update, then add them again
        removeAll();        
        panel();
        
        revalidate();
        repaint();
    }
}