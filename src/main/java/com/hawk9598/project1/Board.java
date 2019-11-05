/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawk9598.project1;

import java.util.List;
import java.util.ArrayList;

//Organizes the Sections according to urgency, i.e. one board contains multiple sections of the same urgency.
/**
 *
 * @author Wayne
 */
public class Board implements XMLizable {

    private final String name;
    private final List<Section> sections;
    
    Board(String name) {
        this.sections = new ArrayList<>();
        this.name = name;
    }
    
    String getName(){
        return name;
    }
    
    List<Section> getSections() {
        return sections;
    }
    void addSection(Section t) {
      this.sections.add(t);
    }
    
    @Override
    public String toXML() {
        String sectionStr = "";
        for (Section s : sections) {
            sectionStr += s.toXML();
        }
        String res = "\t<board name='" + this.name + "'>\n" + sectionStr + "\t</board>\n";
        return res;
    }

  
    
}