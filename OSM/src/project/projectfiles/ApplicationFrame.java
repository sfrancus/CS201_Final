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
    private ApplicationMapViewer mapView;
    private HistoricalDataViewer historicalView;
    public ApplicationFrame(String name)
    {
        super(name);
        this.setSize(new Dimension(800, 670));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel container = new JPanel();
        container.setSize(new Dimension(800, 600));
        container.setBackground(Color.WHITE);
        JTabbedPane tabs = new JTabbedPane();
        mapView = new ApplicationMapViewer();
        historicalView = new HistoricalDataViewer();
        tabs.addTab("Map View", mapView);
        tabs.addTab("Historical Data", historicalView);
        container.add(tabs, BorderLayout.CENTER);
        this.add(container, BorderLayout.CENTER);
        this.setVisible(true);
    }
    public void refreshData(JSONArray data)
    {
        CarModelFactory carModelFactory = new CarModelFactory(data);
        ArrayList<CarModel> carModels = carModelFactory.createModels();
        this.currentData = carModels;
        this.mapView.refreshData(carModels);
        DatabasePreprocessor dpp = new DatabasePreprocessor(carModels);
        Map processedData = dpp.processData("average_number_of_cars", "average_speed_of_cars");
        this.historicalView.refreshData(processedData);
    }
    public boolean hasAvailableData()
    {
        return (this.currentData != null);
    }
}
