    /*
    *	Program Name:	Simulator.java
    *	Lead Programmer:	Zachary Sweigart
    *	Date Modified:	4/19/12
    */

    package simulator;

    import ctc.*;
    import global.*;
    import traincontroller.*;
    import java.util.ArrayList;
    import wayside.*;

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
    private static CTCControl control;  // references the control for the system
    private static int clockRate = 60;  // used to determine when clock ticks will occur 
    private static ArrayList <TrainController> trainControllers = new <TrainController> ArrayList();   // references all train controllers currently in the system
    private static ArrayList <TrainController> removedTrains = new <TrainController> ArrayList();
    private int throughput; // Holds the value of the system throughput
    private int greenLineTrainCount = 0;    // Used to determine the number of train on the green line
    private int redLineTrainCount = 0;  // Used to determine the number of trains on the red line

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
    * set the reference to the the model for main GUI
    * 
    * @param c 
    * @see ctc.CTCModel
    */
    public void setControl(CTCControl c)
    {
        control = c;
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
        if(debugMode)
        {
            System.out.println("Simulator: ClockRate set to: " + clockRate);
        }
    }

    /**
        * Updates the system on a clock tick by setting the clock rates for the
        *  train and track controllers and running each object
        */
    public static void run()
    {
        TrainController tc; // Used to create new trains
        Wayside [] lineWaysides;
        demoMode = view.getDemo();
        
        /* Determine if a train is waiting to leave the train yard and add it to
         * the track when the next block is empty
         */
        for(int i = 0; i < trainControllers.size(); i++)
        {
            trainControllers.get(i).setClockRate(clockRate);
            if(trainControllers.get(i).getTrain().getTrack() == null)
            {
                switch(trainControllers.get(i).getTrain().getLine())
                {
                    case GREEN:
                        if(!model.getTrack(new ID(Line.GREEN, 'F', 0)).isOccupied())
                        {
                            if(debugMode)
                            {
                                System.out.println("Train " + trainControllers.get(i).getID() + " added");
                            }
                            trainControllers.get(i).getTrain().setTrack(model.getTrack(new ID(Line.GREEN, 'F', 0)));
                            control.setOperatorCommands(70, false, trainControllers.get(i).getID().toString());
                            control.setDispatcherAuthority(10, Line.GREEN, 'F', 0);
                        }
                        break;
                    case RED:
                        if(!model.getTrack(new ID(Line.RED, 'F', 0)).isOccupied())
                        {
                            if(debugMode)
                            {
                                System.out.println("Train " + trainControllers.get(i).getID() + " added");
                            }
                            trainControllers.get(i).getTrain().setTrack(model.getTrack(new ID(Line.RED, 'F', 0)));
                            control.setOperatorCommands(70, false, trainControllers.get(i).getID().toString());
                            control.setDispatcherAuthority(10, Line.RED, 'F', 0);
                        }
                        break;
                }
            }
            trainControllers.get(i).run();
            
        }
        
        lineWaysides = model.getLineWaysides(Line.GREEN);
        for(int j = 0; j < lineWaysides.length; j++)
        {
            lineWaysides[j].run();
        }
        
        lineWaysides = model.getLineWaysides(Line.RED);
        for(int j = 0; j < lineWaysides.length; j++)
        {
            lineWaysides[j].run();
        }
        
        /* Determine if a train has been sent the remove command and switch the 
         * train yard switch when it reaches it 
         */
        for(int j = 0; j < removedTrains.size(); j++)
        {
            if(removedTrains.get(j).getTrain().getTrack().equals(model.getTrack(new ID(Line.GREEN, 'D', 27))))
            {
                ((WaysideD)model.getWayside(Line.GREEN, 'D')).setSwitchToTrainYard();
            }
        }

        if(demoMode)
        {
            if(timeCounter % 30000*60/clockRate == 0)
            {
                if(debugMode)
                {
                    System.out.println("Simulator: Demo Event: " + demoEvent);
                    System.out.println("Simulator: Time Counter: " + timeCounter);
                }
                switch(demoEvent)
                {
                    case 0:
                        if(debugMode)
                        {
                            System.out.println("Simulator: demo mode event 1: train added");
                        }
                        tc = new TrainController(Line.GREEN, 4, clockRate, "DEMOTRAIN1");
                        trainControllers.add(tc);
                        break;
                    case 1:
                        if(debugMode)
                        {
                            System.out.println("Simulator: demo mode event 2: train added");
                        }
                        tc = new TrainController(Line.GREEN, 4, clockRate, "DEMOTRAIN2");
                        trainControllers.add(tc);
                        break;
                    case 2:
                        if(debugMode)
                        {
                            System.out.println("Simulator: demo mode event 3: train failed");
                        }
                        trainControllers.get(1).getTrain().setFailure(2);
                        break;
                    case 3:
                        if(debugMode)
                        {
                            System.out.println("Simulator: demo mode event 4: train fixed");
                        }
                        trainControllers.get(1).getTrain().fixFailure(2);
                        break;
                    case 4:
                        if(debugMode)
                        {
                            System.out.println("Simulator: demo mode event 5: track broken rail");
                        }
                        model.getTrack(new ID(Line.GREEN, 'J', 25)).setFailure(TrackFault.BROKEN);
                        break;
                    case 5:
                        if(debugMode)
                        {
                            System.out.println("Simulator: demo mode event 6: track broken rail fixed");
                        }
                        model.getTrack(new ID(Line.GREEN, 'J', 25)).setFix();
                        break;
                    default:
                        if(debugMode)
                        {
                            System.out.println("Simulator: End of demo mode");
                        }
                        demoMode = false;
                        view.setDemoMode(false);
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

    /**
     * Gets a train controller
     * 
     * @param TrainID String ID representing the train
     * @return  TrainController object or null
     */
    public TrainController getTrainController(String TrainID)
    {
        int loc = trainControllers.size(); // Holds the train controllers size
        int i;  // holds the position of the found trainID
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

    /**
     * Create a new train object for the system
     * 
     * @param line Line either RED or GREEN
     * @param crewCount integer number of crew on the train
     * @param clockRate integer current clockRate of the system
     * @param trainID String unique identifier for the train
     * @return boolean whether or not the creation was successful
     */
    public boolean createTrain(Line line, int crewCount, int clockRate, String trainID)
        {
            if(greenLineTrainCount >= 25)
            {
                return false;
            }
            if(redLineTrainCount >= 20)
            {
                return false;
            }
            switch(line)
            {
                case GREEN:
                    greenLineTrainCount++;
                    break;
                default:
                    redLineTrainCount++;
                    break;
            }
            TrainController tc = new TrainController(line, crewCount, clockRate, trainID);
            trainControllers.add(tc);
            return true;
        }

    /**
    * Get all of the train ID values currently in the system
    * 
    * @return a string array of id values for train controllers/train objects
    */
    public String [] getTrainIDs()
    {
        String trainIDs [] = new String [trainControllers.size()];  // Holds all of the train ids in the system

        for(int i = 0; i < trainControllers.size(); i++)
        {
            trainIDs[i] = trainControllers.get(i).getID();
        }

        return trainIDs;
    }

    /**
    * return the system's throughput
    * @return integer throughput
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
    * return the system's capacity
    * @return integer capacity
    */
    public int getCapacity()
    {
        int capacity = 0; // holds the calculated capacity

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
    * return the system's occupancy
    * @return integer occupancy
    */
    public int getOccupancy()
    {
        int occupancy = 0;  // holds the calculated occupancy

        for(int i = 0; i < trainControllers.size(); i++)
        {
            occupancy = occupancy + trainControllers.get(i).getTrain().getOccupancy() + trainControllers.get(i).getTrain().getCrew();
        }
        if(debugMode)
        {
            System.out.println("Simulator: occupancy: " + occupancy);
        }
        return occupancy;
    }
    
    /**
     * sends a remove signal to the provided TrainController
     * @param t TrainController to be removed
     */
    public void remove(TrainController t)
    {
        removedTrains.add(t);
    }

}
