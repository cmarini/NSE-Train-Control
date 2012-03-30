/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator;

import CTC.CTCView;

public class Simulator 
{
    private static boolean demoMode = false;
    private static int demoEvent = 0;
    private static int timeCounter;
    private static CTCView view;
    
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
     * 
     * }
     * 
     * public void createTrain(train attributes)
     * {
     * 
     * }
     */
}
