/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bbodin.planner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author toky
 */
public class Scheduler {

    private static class NotFoundException extends Exception {

        public NotFoundException(String task_not_found) {
            super(task_not_found);
        }
    }

    class TaskExecution {
        public Date start;
        public Date end;
        public Task task;

        private TaskExecution(Task t, Date start, Date end) {
            this.task = t;
            this.start = start;
            this.end = end;
        }
        
    }
    
    private final Planner planner;
    private final List<TaskExecution> schedule;
    
    Scheduler (Planner p) {
        this.schedule = new ArrayList();
        this.planner = p;
        this.schedule();
    }

    private void schedule() {
        List<Task> tasks = this.planner.getTasks();
        Collections.sort(tasks);
        
        // generate Execution 
        
        for (Task t : tasks) {
            this.schedule.add(new TaskExecution(t,t.getStart(),new Date (t.getStart().getTime() + t.getDuration().toSeconds())));
        }
    }
    
    public Date getStart(Task t) throws NotFoundException {
        for (TaskExecution te : schedule) {
            if (te.task == t) {
                return te.start;
            }
        }
        throw new NotFoundException("Task not found");
    }
    
}
