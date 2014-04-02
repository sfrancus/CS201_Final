package project.projectfiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.json.simple.JSONArray;

public class ApplicationFrame extends JFrame{
    private ArrayList<CarModel> currentData;
    ApplicationMapViewer mapView;
    public ApplicationFrame(String name)
    {
        super(name);
        this.setSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel container = new JPanel();
        container.setSize(new Dimension(800, 600));
        container.setBackground(Color.WHITE);
        JTabbedPane tabs = new JTabbedPane();
        mapView = new ApplicationMapViewer();
        JPanel placeholder = new JPanel();
        tabs.addTab("Map View", mapView);
        tabs.addTab("Historical Data", placeholder);
        container.add(tabs, BorderLayout.CENTER);
        this.add(container, BorderLayout.CENTER);
        this.setVisible(true);
    }
    public void refreshData(JSONArray data)
    {
        CarModelBuilder carModelBuilder = new CarModelBuilder(data);
        ArrayList<CarModel> carModels = carModelBuilder.createModels();
        this.currentData = carModels;
        this.mapView.refreshData(carModels);
    }
    public boolean hasAvailableData()
    {
        return (this.currentData != null);
    }
}
