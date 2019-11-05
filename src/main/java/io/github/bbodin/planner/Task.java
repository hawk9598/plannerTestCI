/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bbodin.planner;

import java.time.Duration;
import java.util.Date;

/**
 * This Task object specifies a basic task with name and description.
 * @author toky
 */
public class Task implements XMLizable, Comparable<Task> {
    
    private final String   name;
    private final String   description;
    private final Date     start;
    private final Duration duration;
    private final Date     deadline;
    
    public Task(String name, String description, Date start, Duration duration, Date deadline) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.deadline = deadline;
        this.duration = duration;
    }

    @Override
    public String toXML() {
        return "<task name='" + name.replace("'", "\\'") + "' start='" + start.toString() + "' duration='" + duration.toString() + "' deadline='" + deadline.toString() + "' description='" + description.replace("'", "\\'") + "'/>\n";
    }

    String getName() {
       return this.name;
    }
    String getDescription() {
       return this.description;
    }
    
    Date getStart() {
       return this.start;
    }
    
    Duration getDuration() {
       return this.duration;
    }


    @Override
    public int compareTo(Task o) {
       return this.deadline.compareTo(o.deadline);
    }
    
}
