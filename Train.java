public class Train
{
	private double length;
	private int trainLine;
	private double mass;
	private int crewCount;
	private int maxCapacity;
	private int occupancy;
	private double distTraveled;
	private int maxTrainSpeed;
	private int maxPower;
	private boolean doors; // false = close, true = open
	private boolean lights; // false = off, true = on
	private String trainId;

	public Train(int line, int crew, String id)
	{
		length = 32.2; //m
		trainLine = line;
		mass = 40.9; // tons, what do we want?
		crewCount = crew;
		maxCapacity =  222 + crewCount;
		distTraveled = 0; // meters
		lights = false;
		maxTrainSpeed = 70; // km/h
		maxPower = // ???
		occupancy = // how many crew members?
		trainId = id;
	}

	public double calcVelocity()
	{
		// get power
		// get track info
		//
	}

	public void setLights()
	{
		lights = !lights;
	}

	public void openDoors()
	{
		doors = true;
	}

	public int getOccupancy()
	{

	}




}