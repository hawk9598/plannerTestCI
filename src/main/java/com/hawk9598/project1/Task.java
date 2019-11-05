/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawk9598.project1;

/**
 *
 * @author Wayne
 */
public class Task implements XMLizable{
    String name;
    String description; //instance variables
    String deadline;
    String start;
    String duration;
    
    Task(String name, String description, String start, String duration, String deadline){
        this.name = name;
        this.description = description; 
        this.deadline = deadline;
        this.start = start;
        this.duration = duration;// Constructor (need put in starting time, deadline and time taken. sort by deadline, then starting time)
    } 
    
    @Override
    public String toXML() {
        String Desc = "\t\t\t\t<description>" + this.description + "</description>\n";
        String Deadline = "\t\t\t\t<deadline>" + this.deadline + "</deadline>\n";
        String Start = "\t\t\t\t<start>" + this.start + "</start>\n";
        String Duration = "\t\t\t\t<duration>" + this.duration + "</duration>\n";
        String taskres = "\t\t\t<task name ='" + this.name + "'>\n" + Desc + Start + Duration + Deadline +
                "\t\t\t</task>\n";
                
        return taskres;
    }
    
    String getName() {
        return this.name;
    }
    String getDescription(){
        return this.description;
    }
    
    String getDeadline(){
        return this.deadline;
    }
    
    String getStart(){
        return this.start;
    }
    
    String getDuration(){
        return this.duration; 
    }
    
    String getInfo(){
        return ("This Task has name " + this.name + ", has description: " + this.description +
                ", has start date " + this.start + ", a duration of " + this.duration + " and has deadline "
                + this.deadline + ".");
    }
    public int compareTo(Task o) {
       return this.deadline.compareTo(o.deadline);
    }
}
