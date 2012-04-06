/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import ctc.CTCView;

public class Simulator 
{
    private static boolean demoMode = false;
    private static int demoEvent = 0;
    private static int timeCounter;
    private static CTCView view;
    //private static TrainController [] trainControllers;
    
    public void setView(CTCView v)
    {
        view = v;
    }
    
    public static void run()
    {
        demoMode = view.getDemo();
        
        if(demoMode)
        {
            if(timeCounter % 30000 == 0)
            {
                switch(demoEvent)
                {
                    case 0:
                        break;
                    default:
                        break;
                }
                demoEvent++;
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
     * public void createTrain(int line, int crewCount, String trainID)
     * {
     *      Train t = new Train(crewCount, trainID);
     *      
     * }
     * 
     * public String [] getTrainIDs()
     * {
     *      String trainIDs = new String [trainControllers.length];
     * 
     *      for(int i = 0; i < trainControllers.length; i++)
     *      {
     *          trainIDs[i] = trainControllers[i].getTrainID();
     *      }
     * 
     *      return trainIDs;
     * }
     * 
     */
}
