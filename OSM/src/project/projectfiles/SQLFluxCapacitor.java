package project.projectfiles;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.Map;

public class SQLFluxCapacitor {
    private static Connection connect;
    private Statement statement;
    public SQLFluxCapacitor()
    {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            this.connect = DriverManager
                .getConnection("jdbc:mysql://localhost/userdata?"
                    + "user=root&password=erienne");
            // Statements allow to issue SQL queries to the database
            this.statement = connect.createStatement();
          } catch (Exception e) {
              e.printStackTrace();
              
          }   
    }
    public Map retrieveData(String queryString) throws SQLException
    {
        return null;
        
    }
    public void updateData(Map newData) throws SQLException
    {
        
    }
}
