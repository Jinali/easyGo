package com.example.pagenavigation;

import java.util.Timer;  
import java.util.TimerTask;  
  
public class TimerTaskExample extends TimerTask {  
  
    @Override  
    public void run() {  
        // The logic of task/job that is going to be executed.  
    	
    	
        
        System.out.println("This is being printed every 1 sec.");         
    }  
      
    public static void main(String[] args) {  
        // Create an instance of our task/job for execution  
        TimerTaskExample task = new TimerTaskExample();  
          
        // We use a class java.util.Timer to   
        // schedule our task/job for execution  
        Timer timer = new Timer();  
          
        // Let's schedule our task/job to be executed every 1 minute  
        timer.scheduleAtFixedRate(task, 0, 60000);  
        // First parameter: task - the job logic we   
        // created in run() method above.  
        // Second parameter: 0 - means that the task is   
        // executed in 0 millisecond after the program runs.  
        // Third parameter: 1000 - means that the task is   
        // repeated every 1000 milliseconds  
          
    }  
  
}  