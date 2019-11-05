/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bbodin.planner;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * @author toky
 */
public class Planner implements XMLizable {

      private final List<Board> boards;
      private String name;
    
    Planner(String name) {
        this.name = name;
        boards = new ArrayList<>();
    }
    void addBoard(Board b) {
      this.boards.add(b);
    }
    
    @Override
    public String toXML() {
        String boardStr = "";
        for (Board b : boards) {
            boardStr += b.toXML();
        }
        String res = "<planner name='" + this.name + "'>\n" + boardStr + "</planner>\n";
        return res;
    }

    Iterable<Board> getBoards() {
        return boards;
    }
    

    
    void saveXMLFile(String filename) {
        String str = this.toXML();
          try {
              Files.write(Paths.get(filename), str.getBytes());
          } catch (IOException ex) {
              Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
    public static Planner readXMLFile(String filename) throws SAXException, IOException {
        
        Planner resPlanner;
	
        File fXmlFile = new File(filename);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder;
          try {
            
            dBuilder = dbFactory.newDocumentBuilder();      
            Document doc = dBuilder.parse(fXmlFile);
            

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	    
            
            Element plannerE = doc.getDocumentElement();
            String ThePlannerName = plannerE.getAttribute("name");
            
            resPlanner = new Planner(ThePlannerName);
            
            NodeList boardElements = plannerE.getElementsByTagName("board");
	
            for (int i = 0; i < boardElements.getLength(); i++) {

		Node boardN = boardElements.item(i);
				
		System.out.println("\nCurrent Element :" + boardN.getNodeName());
		                
		if (boardN.getNodeType() == Node.ELEMENT_NODE) {
                    Element boardE = (Element) boardN;
                    String boardname = boardE.getAttribute("name");
                    Board b = new Board (boardname) ;

                    

                    resPlanner.addBoard(b);
                }
            }
	
            return resPlanner;
            
          } catch (ParserConfigurationException ex) {
              Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
          }
    

        return null;
    }
    
    private static Date getInDays(long days) {
        return new Date(Calendar.getInstance().getTime().getTime() + Duration.ofDays(days).toMillis());
    }
    
    public static Planner demo () {
        
        Calendar.getInstance().getTime();
        
        Section s1 = new Section("Todo", new Color(255,102,102));
        s1.addTask(new Task("task11" , "description11", getInDays(0) , Duration.ofDays(1) , getInDays(20)));
        s1.addTask(new Task("task12", "description12", getInDays(1) , Duration.ofDays(10) , getInDays(30)));
        
        Section s2 = new Section("teaching");
        s2.addTask(new Task("task21", "description21",getInDays(0) , Duration.ofDays(15) , getInDays(20)));
        Section s3 = new Section("Family", new Color(255,50,102));
        s3.addTask(new Task("task31", "description31", getInDays(3) , Duration.ofDays(1) , getInDays(20)));
        s3.addTask(new Task("task32", "description32", getInDays(3) , Duration.ofDays(1) , getInDays(40)));
        
        Section s4 = new Section("Friends");
        s4.addTask(new Task("task41", "description41", getInDays(0) , Duration.ofDays(1) , getInDays(50)));
        s4.addTask(new Task("task42", "description42", getInDays(0) , Duration.ofDays(1) , getInDays(20)));
        
        Section s5 = new Section("Health");
        s5.addTask(new Task("task51", "description51", getInDays(0) , Duration.ofDays(50) , getInDays(80)));
        
        
        Board b1 = new Board("Work");
        Board b2 = new Board("Perso");
        b1.addSection(s1);
        b1.addSection(s2);
        b2.addSection(s3);
        b2.addSection(s4);
        b2.addSection(s5);
        
        Planner p = new Planner("demo");
        p.addBoard(b1);
        p.addBoard(b2);
        
        return p;
    }

    List<Task> getTasks() {
        ArrayList res = new ArrayList();
        for (Board b : this.getBoards()) {
            for (Section s : b.getSections()) {
                for (Task t : s.getTasks()) {
                    res.add(t);
                }
            }
        }
        return res;
    }

    Board getBoard(String field) throws Exception {
        for (Board b : this.getBoards()) {
            if (b.getName().equals(field)) return b;
        }
        throw new Exception();
    }

}
