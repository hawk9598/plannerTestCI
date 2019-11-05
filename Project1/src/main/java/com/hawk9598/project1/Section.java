/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Contains Multiple Projects from the same faculty and of the same urgency. 

package com.hawk9598.project1;

import java.util.ArrayList;
import java.util.List;
//import java.awt.Color;


/**
 *
 * @author Wayne
 */

public class Section implements XMLizable {

    private final List<Task> tasks;
    private final String name;
    

    Section(String name){
        // We use 'this' here to call a different constructor
        this.name = name; 
        this.tasks = new ArrayList<>();
    }
    
    void addTask(Task t) {
      this.tasks.add(t);
    }
    
    String getName(){
        return this.name;
    }
    Iterable<Task> getTasks() {
        return this.tasks;
    }
    
    @Override
    public String toXML() {
        String tasksStr = "";
        
        for (Task t : tasks) {       // There is here a nice hint from Netbeans, you should try it !
            tasksStr += t.toXML();
        }
        // TODO : add color here as well
       // String colour = "\t\t\t<Color>" + this.color + "</Color>\n";
        String res = "\t\t<section name='" + this.name.replace("'", "") + "'>\n" + tasksStr + "\t\t</section>\n";
        return res;
    }


    
}