/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawk9598.project1;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Wayne
 */
public class Planner implements XMLizable{

    List<Board> Boards;
    String name;
    Planner(){
        this.Boards = new ArrayList<>();
        this.name = "Demo";
    }
    Planner(String name) {
        this.Boards = new ArrayList<>();
        this.name = name;
    }
    void addBoard(Board b) {
      this.Boards.add(b);
    }
    
    @Override
    public String toXML() {
        String boardString = "";
        for (Board b : Boards) {
            boardString += b.toXML();
        }
        String plannerString = "<planner name ='" + this.name + "'>\n" + boardString + "</planner>\n";
        return plannerString;
    }

    Iterable<Board> getBoards() {
        return Boards;
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
	
        File fXmlFile = new File(filename);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder;
          try {
            
            dBuilder = dbFactory.newDocumentBuilder();      
            Document doc = dBuilder.parse(fXmlFile);
       
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	    
            NodeList pList = doc.getElementsByTagName("planner");
            
            Element plannerE = doc.getDocumentElement();
            String plannerName = plannerE.getAttribute("name");
            Planner resplanner = new Planner (plannerName);
            
            NodeList boardElements = plannerE.getElementsByTagName("board");
            
            for (int i = 0; i < boardElements.getLength(); i++){
                Node bNode = boardElements.item(i);
                //System.out.println("\nCurrent Element :" + bNode.getNodeName());
                if (bNode.getNodeType() == Node.ELEMENT_NODE){
                    Element boardE = (Element) bNode; //USUALLY cannot cast superclass to a subclass, the other way is ok.
                    String boardDetails = boardE.getAttribute("name");
                    Board boardDemo = new Board(boardDetails);
                    NodeList sectionElements = boardE.getElementsByTagName("section");
                    for (int j = 0; j < sectionElements.getLength(); j++){
                        Node sNode = sectionElements.item(j);
                        Element sectionE = (Element) sNode; //USUALLY cannot cast superclass to a subclass, the other way is ok.
                        String sectionDetails = sectionE.getAttribute("name");
                        Section sectionDemo = new Section(sectionDetails);
                        NodeList taskElements = sectionE.getElementsByTagName("task");
                        for (int k = 0; k < taskElements.getLength(); k++){
                            Node tNode = taskElements.item(k);
                            Element taskE = (Element) tNode;
                            String taskName = taskE.getAttribute("name");
                            String taskDescription = taskE.getElementsByTagName("description").item(0).getTextContent();
                            String taskStart = taskE.getElementsByTagName("start").item(0).getTextContent();
                            String taskDuration = taskE.getElementsByTagName("duration").item(0).getTextContent();
                            String taskDeadline = taskE.getElementsByTagName("deadline").item(0).getTextContent();
                            Task task = new Task(taskName, taskDescription,taskStart, taskDuration, taskDeadline);
                            sectionDemo.addTask(task);
                    }
                    boardDemo.addSection(sectionDemo);
                }
                resplanner.addBoard(boardDemo);        
            } 
          }
            return resplanner;
          } catch (ParserConfigurationException ex) {
              Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
          }
        return null;
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
    static JButton returnButton(String s, Dimension size, Section sec){
            final JButton newButton = new JButton(s);
            newButton.setMaximumSize(size);
            newButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed (ActionEvent e){
                    Iterable<Task> tasklist = sec.getTasks();
                    String tasksInSection = "" ;
                    
                    tasksInSection += ("This is the " + sec.getName() + " section and has the following tasks: \n");
                    for (Task t: tasklist){
                        tasksInSection += ("\t" + t.getInfo() + "\n");
                    }
                    JOptionPane.showMessageDialog(null, tasksInSection );
                }
            });
            return (newButton);
        }
     static JPanel generateSectionPanel (Section s) {
         JPanel sectionPanel = new JPanel();
         sectionPanel.setName(s.getName());
         JLabel sectionLabel = new JLabel (s.getName());
         sectionLabel.setForeground(Color.red);
         sectionPanel.add(sectionLabel);
         sectionPanel.setLayout(new GridLayout(0,1));
         Dimension MAXSIZE = new Dimension(100,100);
         JButton sectionButton;
         sectionButton = returnButton(s.getName(), MAXSIZE, s);
         sectionPanel.add(sectionButton);
         
         for (Task t : s.getTasks()) {
            JLabel taskLabel = new JLabel (t.getName());
            sectionPanel.add(taskLabel);  
         }
        return sectionPanel;
    }
    
    static JPanel generateBoardPanel (Board b) {
         JPanel boardPanel = new JPanel();
         boardPanel.setName(b.getName());
         for (Section s : b.getSections()) {
              JPanel p = generateSectionPanel(s);
                 boardPanel.add(p);
         }
        return boardPanel;
    }
    

    

    static void drawPlanner (Planner p) {
        
        JFrame frame = new JFrame();
        JTabbedPane boardPanels = new JTabbedPane();
        
        
        for (Board b : p.getBoards()) {
            JPanel boardPanel = generateBoardPanel(b);
            boardPanels.add(boardPanel);     
        }
        frame.getContentPane().add(boardPanels);
        //JButton testButton = new JButton ("Test, click me!");
        
        
        
        frame.setVisible(true);
    }
}   
        
        
    