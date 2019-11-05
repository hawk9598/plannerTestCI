/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bbodin.planner;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author toky
 */
public class TaskTest {

    private final static String taskName =  "task11" ;
    private final static String taskDesc =  "TaskDesc" ;
    
    private Task instance;
    
    public TaskTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    private static Date getInDays(long days) {
        return new Date(Calendar.getInstance().getTime().getTime() + Duration.ofDays(days).toMillis());
    }
    @BeforeEach
    public void setUp() {
         instance = new Task(taskName , taskDesc, getInDays(0) , Duration.ofDays(1) , getInDays(20));
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of toXML method, of class Task.
     */
    @Test
    public void testToXML() {
        System.out.println("toXML");
        String expResult = "<task name='task11' start='Mon Nov 04 16:42:29 SGT 2019' duration='PT24H' deadline='Sun Nov 24 16:42:29 SGT 2019' description='description11'/>";
        String result = instance.toXML();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Task.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = taskName;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Task.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = taskDesc;
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }



    /**
     * Test of compareTo method, of class Task.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Task o = new Task(taskName , taskDesc, getInDays(0) , Duration.ofDays(1) , getInDays(20));
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
