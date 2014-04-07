package project.projectfiles;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
    private String fileName;
    private File file;
    private Document doc;
    public XMLReader(String filename)
    {
        this.fileName = filename;
        file = new File(
                filename);
    }
    public XMLReader()
    {
        this.fileName = "";
        this.file = null;
    }
    public void setFile(String fileName)
    {
        this.fileName = fileName;
        this.file = new File(fileName);
    }
    public Map<Integer, ArrayList<String> > parseByTags(String tagName, String... attributes)
    {
        Map<Integer, ArrayList<String> > nodeMap = new HashMap<Integer, ArrayList<String>>();
        ArrayList<String> attr = new ArrayList<String>();
        for(int i = 0; i < attributes.length; i++)
        {
            attr.add(attributes[i]);
        }
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try{
        dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(this.file);
        doc.getDocumentElement().normalize();
            try{
               NodeList coordinates = doc.getElementsByTagName(tagName);
               for(int i = 0; i < coordinates.getLength(); i++)
               {
                   Node coordinate = coordinates.item(i);
                   ArrayList<String> attributeArray = new ArrayList<String>();
                   for(String attribute: attr)
                   {
                       String val = ((Element) coordinate).getAttribute(attribute);
                       attributeArray.add(val);
                   }
                   nodeMap.put(i, attributeArray);
                 
               }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return nodeMap;
    }
    
}
