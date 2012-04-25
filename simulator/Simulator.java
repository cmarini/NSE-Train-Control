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
    private static ArrayList <TrainController> removedTrains = new <TrainController> ArrayList();
    private int throughput;
    private int greenLineTrainCount = 0;
    private int redLineTrainCount = 0;

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
        demoMode = view.getDemo();
        for(int i = 0; i < trainControllers.size(); i++)
        {
            trainControllers.get(i).setclockRate(clockRate);
            /*if(trainControllers.get(i).getTrain().getTrack())
            {
                switch(trainControllers.get(i).getTrain().getLine())
                {
                    case GREEN:
                        if(!model.getTrack(new ID(Line.GREEN, 'F', 0)).isOccupied())
                        {
                            trainControllers.get(i).getTrain().setTrack(model.getTrack(new ID(Line.GREEN, 'F', 0)));
                        }
                        break;
                    case RED:
                        
                        break;
                }
            }*/
            trainControllers.get(i).run();
        }
        
        for(int j = 0; j < removedTrains.size(); j++)
        {
            /*if(removedTrains.get(j).getTrack().equals(model.getTrack(new ID(Line.GREEN, 'D', 27))))
            {
                ((WaysideD)model.getWayside(Line.GREEN, 'D')).setSwitchToTrainYard();
            }*/
        }

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
                        if(debugMode)
                        {
                            System.out.println("Simulator: demo mode event 1: train added");
                        }
                        TrainController tc = new TrainController(Line.GREEN, 4, clockRate, "DEMOTRAIN1");
                        trainControllers.add(tc);
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
            occupancy = occupancy + trainControllers.get(i).getTrain().getOccupancy() + trainControllers.get(i).getTrain().getCrew();
        }
        if(debugMode)
        {
            System.out.println("Simulator: occupancy: " + occupancy);
        }
        return occupancy;
    }
    
    public void remove(TrainController t)
    {
        removedTrains.add(t);
    }

}
