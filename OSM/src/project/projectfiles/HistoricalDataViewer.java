package project.projectfiles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HistoricalDataViewer extends JPanel{
    private SQLFluxCapacitor SQLReader;
    public HistoricalDataViewer()
    {
        super();
        JButton dataPull = new JButton("Generate data");
        SQLReader = new SQLFluxCapacitor();
        this.add(dataPull);
    }
    public void refreshData(Map data)
    {
     /*  try {
         
        //this.SQLReader.updateData(data);
    } catch (SQLException e) {
         
        e.printStackTrace();
    }*/
    }

}
