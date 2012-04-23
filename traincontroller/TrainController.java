public class TrainController   { //implements Runnable
	
public Train T;
public GreenSchedule G;  
public RedSchedule R;
private String ID;
public boolean stopped;
public boolean onSchedule; 
public double setPoint;  
public double currentSpeed; //Speed returned by Train Model 
public double operatorSpeed; //Speed entered by user 
public double waysideSpeed; //Speed sent by the wayside 
private static double maxPower = 120; //kW
private double power; //power sent to the  train
private double init_power = 60; //kW
private double velocity; //Trains current velocity 
private	double prev_d; //previous d value
private double prev_v; //previous v value 
private double d;      //setPoint - currentSpeed
private double v;      //velocity - used in calcPower 
private static int clockRate;
private int scheduleLine;
private int crew;  
private int ran = 1; //Initailizes the initial power to 60
 

//add a clockrate 1ms * 60/Rate rates - time given  function to let CTC set the clock rate
// constructor - line int, crew count int, id string, clock rate intger  Train - Train T = new Train and whatever his constructor is 
public TrainController(int line, int crewCount, int clock, String id)  
{
	scheduleLine = line; 
	clockRate = clock; 
	crew = crewCount; 
	ID = id; 
	Train T = new Train(scheduleLine,crew,ID);
}

public void Run() 
{	
	System.out.println("Inside TrainControllers Run Method."); 
	updateTrain(); 
	updateSchedule(); 
}
 
public double calcPower( double currentSpeed,double operatorSpeed, double waysideSpeed)
{
	 
    double T = 0;      //Sample period of train model = 60/clockrate/1000 
    double Kp = 2;     //proportional gain (Guess value)
    double Ki = 1;     //integral (Guess value)
   
	if(ran == 1) //initial power  - 60
	{
		power = init_power; 
		ran++; 
	}
	
	if (scheduleLine == 1) 
	{
		onSchedule= G.onSchedule();
	}
	else if(scheduleLine == 2) 
	{
		onSchedule= R.onSchedule(); 
	}
	
	if(onSchedule == false) //means off schedule 
	{
		setPoint = waysideSpeed; 
	}
	else 
	{
		setPoint = operatorSpeed; 
	}
		
	if(power < maxPower)
	{	
		T = (60/clockRate) * .001; 
        d = setPoint - currentSpeed;
        v = prev_v + (T/2) * (d + prev_d); 
	}
    else if (power >= maxPower)
	{
        v = prev_v; //v= v(n-1)
	}
        
		power = Kp * d + Ki * v; 
              
	return power; 
}
	
	/* Update method sends all updates to the train */ 
public double updateTrain()
{	
	System.out.println("Inside TrainControllers Update Method."); 
	String info;
    power = calcPower(currentSpeed,operatorSpeed,waysideSpeed); //power sent to train 

    if(T.getTransponder())
    {   
		info = T.getTransponderInfo(); 
		if(info.equals("tunnel"))
		{
			T.setLights(); 
		}
		if(info.equals("station") && trainStopped()) 
		{
			T.openDoors(); 
			//After clock rate of 60secs close doors 
			T.closeDoors(); 
		}
    }
    return power;  
}

public void  updateSchedule()
{	
	System.out.println("Inside TrainControllers updateSchedule Method."); 
	scheduleLine = T.getLine();	
	if (scheduleLine == 1) 
	{
		G.greenSchedule(); 
	}
	else if(scheduleLine == 2) 
	{
		R.redSchedule(); 
	}
 }
	
public boolean trainStopped() 
{	
	System.out.println("Inside TrainControllers Stopped Method."); 
	velocity = T.getVelocity(); 
	 if(velocity == 0) 
	{
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

}
