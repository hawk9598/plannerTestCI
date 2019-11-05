/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawk9598.project1;

//import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import org.xml.sax.SAXException;

/**
 *
 * @author Wayne
 */
public class PlannerDemo {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SAXException, IOException {
       Calendar.getInstance().getTime();
        
        Section s1 = new Section("Todo");
        s1.addTask(new Task("task11" , "description11", "15 September" , "2 Days" , "17 September"));
        s1.addTask(new Task("task12", "description12", "16 September" , "5 Days", "21 September"));
        
        Section s2 = new Section("teaching");
        s2.addTask(new Task("task21", "description21",  "21 September", "4 Days" , "25 September"));
        
        Section s3 = new Section("Family");
        s3.addTask(new Task("task31", "description31", "18 September" , "5 Days" , "23 September"));
        s3.addTask(new Task("task32", "description32", "19 September" , "1 Day"  , "20 September" ));
        
        Section s4 = new Section("Friends");
        s4.addTask(new Task("task41", "description41", "10 September"  , "7 Days"  , "17 September" ));
        s4.addTask(new Task("task42", "description42", "14 September" , "5 Days" , "19 September" ));
        
        Section s5 = new Section("Health");
        s5.addTask(new Task("task51", "description51",  "9 September", "21 Days"  , "30 September" ));
        
        
        Board b1 = new Board("Work Stuff");
        Board b2 = new Board("Personal Stuff");
        b1.addSection(s1);
        b1.addSection(s2);
        b2.addSection(s3);
        b2.addSection(s4);
        b2.addSection(s5);
        
        Planner p = new Planner("demo");
        p.addBoard(b1);
        p.addBoard(b2);
        
        System.out.println(p.toXML());
        p.saveXMLFile("Planner XML.xml");
        Planner planner2 = Planner.readXMLFile("Planner XML.xml");
        System.out.println(planner2.toXML());
        planner2.saveXMLFile("Planner 2 XML.xml");
        
        Planner.drawPlanner(planner2);
        // TODO code application logic here
    }
    
}
