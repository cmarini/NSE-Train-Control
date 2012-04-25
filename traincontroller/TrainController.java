//package traincontroller;

import global.*;
import trainmodel.*;
import java.util.logging.*;


public class TrainController implements Runnable
{ 
    private Train train;
	private Track track; 
    private GreenSchedule G;
    private RedSchedule R;
    private String id;
    private boolean stopped;
    private boolean onSchedule;
    private boolean braking;
    private double setPoint;
    private double currentSpeed;          //Speed returned by Train Model 
    private double operatorSpeed;         //Speed entered by user 
    private double waysideSpeed;          //Speed sent by the wayside 
    private static double maxPower = 120; //kW
    private double power;                 //power sent to the  train
    private double init_power = 60;       //kW
    private double velocity;              //Trains current velocity 
    private double prev_d;                //previous d value
    private double prev_v;                //previous v value 
    private double d;                     //setPoint - currentSpeed
    private double v;                     //velocity - used in calcPower 
    private static int clockRate;
    private Line scheduleLine;
    private int crew;
    private int ran = 1; 
    private String message;
	private int timewaited; 
	private int clockticValue; 
	

	//add a clockrate 1ms * 60/Rate rates - time given  function to let CTC set the clock rate
	// constructor - line int, crew count int, id string, clock rate intger  Train - Train train = new Train and whatever his constructor is 
    public TrainController(Line line, int crewCount, int clock, String idVal, Track T) 
    {
        scheduleLine = line;
        clockRate = clock;
        crew = crewCount;
        id = idVal;
        braking = false; 
        train = new Train(scheduleLine, crew, id);
        message = "";
    }

    public void run() 
    {
        System.out.println("Inside TrainControllers Run Method.");
        updateTrain();
        updateSchedule();
    }
    
    public Train getTrain() 
    {
        return train;
    }
    
    public String getID()
    {
        return id;
    }
    
    public String getMessage() //coming to the next station - arriving departing 
    {
        return message;
    }
        
    public void setBraking(boolean b) //emergency brake
    {
        braking = b;
    }
    
    public boolean getBraking()
    {
        return braking;
    }

    public double calcPower(double currentSpeed, double operatorSpeed, double waysideSpeed) 
    {
        double trainPeriod = 0; //Sample period of train model = 60/clockrate/1000 
        double Kp = 2;          //proportional gain (Guess value)
        double Ki = 1;          //integral (Guess value)
			
			//What happens if train is braking?? 
        if (ran == 1) 
        {
            power = init_power;
            ran++;
        }
		
		//when your going slower than you want to go or someone pulls the emergency break 
		if(setpoint lower than speed currently going
		{
			brake
		}
		
		if(braking = true) 
		{
			//set braking to the decelerate speed...
			
		}        
		
		if (scheduleLine.equals(Line.GREEN)) 
        {
            onSchedule = G.onSchedule();
        } 
        else if (scheduleLine.equals(Line.RED)) 
        {
            onSchedule = R.onSchedule();
        }

        if (onSchedule == false) //means off schedule 
        {
            setPoint = waysideSpeed;
        } 
        else 
        {
            setPoint = operatorSpeed;
        }

        if (power < maxPower) 
        {
            trainPeriod = (60 / clockRate) * .001; 
            d = setPoint - currentSpeed;
            v = prev_v + (trainPeriod / 2) * (d + prev_d);
        } 
        else if (power >= maxPower) 
        {
            v = prev_v; //v= v(n-1)
        }

        power = Kp * d + Ki * v;

        return power;
    }

    /*
     * Update method sends all updates to the train
     */
    public void updateTrain() 
    {
        System.out.println("Inside TrainControllers Update Method.");
        String info;
        power = calcPower(currentSpeed, operatorSpeed, waysideSpeed);  
		train.setPower(power); 
		//Service braking -- 1.2m/s^2 
		//emergency brake -- 2.73 m/s^2

        if (train.hasTransponder()) 
        {
            info = train.getTransponderInfo();
            if (info.equals("TUNNEL")) 
            {
                train.setHeadLights();
            }
            if (info.equals("STATION") && trainStopped()) 
            {  
				clockticValue = (60 / clockRate) * .001; 
				timewaited += clockticValue; 
				
				train.openDoors();
				if( timewaited >= 60 ) 
				{   //After clock rate of 60secs close doors 
					train.closeDoors();
					timewaited = 0; 
				}
				//Signal that train goes to next station 
            }
        }
    }

    public void updateSchedule() 
    {
        System.out.println("Inside TrainControllers updateSchedule Method.");
        //scheduleLine = train.getLine();
        if (scheduleLine.equals(Line.GREEN)) 
        {
            G.greenSchedule();
        } 
        else if (scheduleLine.equals(Line.RED)) 
        {
            R.redSchedule();
        }
    }

    public boolean trainStopped() 
    {
        System.out.println("Inside TrainControllers Stopped Method.");
        //velocity = train.getVelocity();
        if (velocity == 0) {
            stopped = true;
        } 
        else 
        {
            stopped = false;
        }
        return stopped;
    }

    public double getClockRate() 
    {
        System.out.println("Inside TrainControllers getClockRate Method.");
        return clockRate;
    }

    public void setclockRate(int clock) 
    {
        System.out.println("Inside TrainControllers setClockRate Method.");
        clock = clockRate;
    }
	
	public void setWaysideSpeed(double w) 
	{
		waysideSpeed = w;  
	}
	
	public void setOperatorSpeed(double opSpeed) 
	{
		operatorSpeed = opSpeed; 
	}

    
    public void remove() //send train to the train yard - modify schedule to go to the next station but not anywhere else 
    {
		set some type of remove flag whenever flags set dont accept passengers 
		continues on the every station 
		//setPassengers 
        //Sending train to the train yard 
    }
	
	//Getting authority getAuthority
	//Gettting speedLimit for the track  -- Setpoint can't be bigger than the current speedLimit for the track getSpeedLimit 
}
