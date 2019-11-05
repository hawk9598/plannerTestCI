/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bbodin.planner;


import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import org.xml.sax.SAXException;

/**
 *
 * @author toky
 */
public class Main {

    
    
    static JPanel generateSectionPanel (Section s) {
         JPanel sectionPanel = new JPanel();
         sectionPanel.setName(s.getName());
         JLabel sectionLabel = new JLabel (s.getName());
         sectionLabel.setForeground(Color.red);
         sectionPanel.add(sectionLabel);
         sectionPanel.setLayout(new GridLayout(0,1));
         
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
       
        
        frame.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
   
        Planner p = Planner.demo();
        
        try {
            System.out.println(p.toXML());
            p.saveXMLFile("demo.xml");
            Planner p2 = Planner.readXMLFile("demo.xml");
            System.out.println(p2.toXML());
            //TODO : check that the planner p and p2 are identical.
        } catch (SAXException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        drawPlanner (p);
         
    }
    
    
    
    
}
