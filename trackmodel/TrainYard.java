// this class has the track property Train Yard, it is responsible 
// for creating and removing trains from the system

package trackmodel;

import global.ID;

public class TrainYard extends Track
{
	private String yInfo;
	
	// constructor for TrainYard constructor
	public TrainYard(double iElevate, double iGrade, int spLimit, double blkLen, ID trkID)
	{
		super(iElevate, iGrade, spLimit, blkLen, trkID);
	}

	public String getYardInfo() // returns yard updates
	{
		return yInfo;
	}
}
