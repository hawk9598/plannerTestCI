/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bbodin.planner;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This Section object specifies a section that can contain multiple tasks.
 * @author toky
 */
public class Section implements XMLizable {

    private final List<Task> tasks;
    private final String name;
    private final Color color;
    
    Section(String name, Color color) {
        this.name = name;
        this.color = color;
        this.tasks = new ArrayList<>();
    }

    Section(String name) {
        // We use 'this' here to call a different constructor
        this(name, Color.white); 
    }
    
    void addTask(Task t) {
      this.tasks.add(t);
    }
    
    Color getColor(){
        return this.color;
    }
    String getName(){
        return this.name;
    }
    Iterable<Task> getTasks() {
        return this.tasks;
    }
    
     Task getTask(String name) throws Exception {
        for (Task t : this.getTasks()) {
            if (t.getName().equals(name)) return t;
        }
        throw new Exception();
    }
    
    @Override
    public String toXML() {
        String tasksStr = "";
        
        for (Task t : tasks) {       // There is here a nice hint from Netbeans, you should try it !
            tasksStr += t.toXML();
        }
        // TODO : add color here as well
        String res = "<section name='" + this.name.replace("'", "") + "'>\n" + tasksStr + "</section>\n";
        return res;
    }


    
}
