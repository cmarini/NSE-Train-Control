/*
*	Program Name:	Simulator.java
*	Lead Programmer:	Zachary Sweigart
*	Date Modified:	4/19/12
*/

package simulator;

import ctc.CTCView;
import ctc.CTCModel;
import global.*;
import traincontroller.*;
import java.util.ArrayList;

/**
 * This file contains the specification of the simulator object which handles
 *  updating the train controller, and track controller objects on each clock
 *  tick
 * 
 * @author Zachary Sweigart
 */
public class Simulator 
{
    private static boolean debugMode;   // used to set debug mode for the simulator
    private static boolean demoMode = false;    // sets whether demo mode is on or off, demo mode will cause a set of events to occur
    private static int demoEvent = 0;   // used to determine which demo event will occur next
    private static int timeCounter = 0; // used to determine when the next demo event will occur
    private static CTCView view;    // references the main gui of the program   
    private static CTCModel model;  // references the model of the system used by the gui
    private static int clockRate = 60;  // used to determine when clock ticks will occur 
    private static ArrayList <TrainController> trainControllers = new <TrainController> ArrayList();   // references all train controllers currently in the system
    private static String TrainIDs[] = {"G000001", "G000002", "R000001"};   // holds the train ids for all train controllers
    private int throughput;
    
    /**
     * Creates a new Simulator object and sets the debugMode flag
     * 
     * @param d boolean that sets whether or not the system is in debug mode
     */
    public Simulator(boolean d)
    {
        debugMode = d;
    }
    
    /**
     * set the reference to the main GUI
     * 
     * @param v CTCView object which defines the main GUI
     * @see ctc.CTCView
     */
    public void setView(CTCView v)
    {
        view = v;
    }
    
   /**
     * set the reference to the the model for main GUI
     * 
     * @param m CTCModel object which defines the model for main GUI
     * @see ctc.CTCModel
     */
    public void setModel(CTCModel m)
    {
        model = m;
    }
    
    /**
     * set the clock rate for the system such that one system hour is equal to 
     *  the clock rate number of minutes
     * 
     * @param c integer that specifies the number of minutes equal to one system
     *  hour
     */
    public void setClockRate(int c)
    {
        clockRate = c;
    }
    
    /**
     * Updates the system on a clock tick by setting the clock rates for the
     *  train and track controllers and running each object
     */
    public static void run()
    {
        demoMode = view.getDemo();
//        for(int i = 0; i < trainControllers.length; i++)
//        {
//            trainControllers[i].setClockRate(clockRate);
//            trainControllers[i].run()
//        }
//
        model.run();
        
        if(demoMode)
        {
            if(timeCounter % 30000 == 0)
            {
                if(debugMode)
                {
                    System.out.println("Simulator: Demo Event: " + demoEvent);
                    System.out.println("Simulator: Time Counter: " + timeCounter);
                }
                switch(demoEvent)
                {
                    case 0:
                        break;
                    default:
                        break;
                }
                demoEvent++;
                timeCounter++;
            }
            else
            {
                timeCounter++;
            }
        }
        else
        {
            
            demoEvent = 0;
            timeCounter = 0;
        }
    }
    
    public TrainController getTrainController(String TrainID)
    {
        int loc = trainControllers.size();
        int i;
        for(i = 0; i < trainControllers.size(); i++)
        {
            if(trainControllers.get(i).getID().equals(TrainID))
            {
                break;    
            }
        }
        if(i < loc)
        {
            return trainControllers.get(i);
        }
        else
        {
            return null;
        }
    }
     
     public void createTrain(Line line, int crewCount, int clockRate, String trainID)
     {
          TrainController tc = new TrainController(line, crewCount, clockRate, trainID);
          trainControllers.add(tc);
     }
    
    /**
     * Get all of the train ID values currently in the system
     * 
     * @return a string array of id values for train controllers/train objects
     */
    public String [] getTrainIDs()
    {
        String trainIDs [] = new String [trainControllers.size()];
     
        for(int i = 0; i < trainControllers.size(); i++)
        {
            trainIDs[i] = trainControllers.get(i).getID();
        }

        return trainIDs;
    }
      
    /**
     * 
     * @return
     */
    public int getThroughput()
    {
        if(debugMode)
        {
            System.out.println("Simulator: throughput: " + throughput);
        }
        return throughput;
    }
    
    /**
     * 
     * @return
     */
    public int getCapacity()
    {
        int capacity = 0;
        
        for(int i = 0; i < trainControllers.size(); i++)
        {
            capacity += trainControllers.get(i).getTrain().getCapacity();
        }
        
        if(debugMode)
        {
            System.out.println("Simulator: capacity: " + capacity);
        }
        return capacity;
    }
    
    /**
     * 
     * @return
     */
    public int getOccupancy()
    {
        int occupancy = 0;
        
        for(int i = 0; i < trainControllers.size(); i++)
        {
            occupancy += trainControllers.get(i).getTrain().getOccupancy();
        }
        if(debugMode)
        {
            System.out.println("Simulator: occupancy: " + occupancy);
        }
        return occupancy;
    }
     
}
