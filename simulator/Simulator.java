/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import ctc.CTCView;
import ctc.CTCModel;

public class Simulator 
{
    private static boolean debugMode;
    private static boolean demoMode = false;
    private static int demoEvent = 0;
    private static int timeCounter = 0;
    private static CTCView view;
    private static CTCModel model;
    private static int clockRate = 60;
    //private static TrainController [] trainControllers;
    private static String TrainIDs[] = {"G000001", "G000002", "R000001"};
    
    public Simulator(boolean d)
    {
        debugMode = d;
    }
    
    public void setView(CTCView v)
    {
        view = v;
    }
    
    public void setModel(CTCModel m)
    {
        model = m;
    }
    
    public void setClockRate(int c)
    {
        clockRate = c;
    }
    
    public static void run()
    {
        demoMode = view.getDemo();
//        for(int i = 0; i < trainControllers.length; i++)
//        {
//            trainControllers[i].setClockRate(clockRate);
//        }
//
//          model.setClockRate(clockRate);
        
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
    
    /*
     * public TrainController getTrainController(String TrainID)
     * {
     *      loc = trainControllers.length;
     *      for(int i = 0; i < trainControllers.length; i++)
     *      {
     *          if(trainControllers[i].getTrainID().equals(TrainID))
     *          {
     *              break;    
     *          }
     *      }
     *      if(i < loc)
     *      {
     *          return trainControllers[i]
     *      }
     *      else
     *      {
     *          return null;
     *      }
     * }
     * 
     * public void createTrain(int line, int crewCount, String trainID, int clockRate)
     * {
     *      TrainController t = new TrainController(line, crewCount, trainID, clockRate);
     * }
     */
     public String [] getTrainIDs()
     {
         return TrainIDs;
        /*String trainIDs = new String [trainControllers.length];
     
        for(int i = 0; i < trainControllers.length; i++)
        {
            trainIDs[i] = trainControllers[i].getTrainID();
        }

        return trainIDs;*/
    }
      
     
}
