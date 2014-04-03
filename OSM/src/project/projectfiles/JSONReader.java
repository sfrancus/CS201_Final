package project.projectfiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.Callable;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;


public class JSONReader{

    private String JSONUrlString, JSONDocument;
    private JSONArrayWrapper jsonArray;
    public JSONReader(String JSONUrlString, JSONArrayWrapper jsonMap)
    {
        this.JSONUrlString = JSONUrlString;
        this.JSONDocument = "";
        this.jsonArray = jsonMap;
    }
    public String getJSON()
    {
        return this.JSONDocument;
    }
    public JSONArrayWrapper getJSONArray()
    {
        return this.jsonArray;
    }
    public void run() throws IOException{
        this.JSONDocument = "";

        URL json = new URL(this.JSONUrlString);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(json.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null){
            this.JSONDocument += inputLine;
        }
        in.close();
        Object obj = JSONValue.parse(this.JSONDocument);
        this.jsonArray.setJSONArray(((JSONArray)obj));
    }

}
