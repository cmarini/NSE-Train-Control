package trackmodel;

import global.ID;

public class Switch extends Track
{
	public enum SwitchState
	{
		LEFT, RIGHT;
	}
	
	public Track C;
	
	private SwitchState switchState = SwitchState.LEFT; //defult setting
	private String sInfo;
	
	public Switch(double iElevate, double iGrade, int spLimit, double blkLen, ID trkID)
	{
		super (iElevate, iGrade, spLimit, blkLen, trkID);
	}

	public void setSwitchState(SwitchState state) //set switch state
	{
		switchState = state;
		sInfo = "info: switch state set to: " + switchState;		
	}
	
	public SwitchState getSwitchState()	//get switch state
	{
		sInfo = "info: sent switch state";
		return switchState;
	}
	
	public String switchInfo() // return switch update
	{
		return sInfo;
	}
        
	public void setNext(Track t)
	{
		if(B == null)
		{
			B = t;
			log.config("Track " + trackID + ": B set to " + t);
		}
		else
		{
			C = t;
			log.config("Track " + trackID + ": C set to " + t);
		}
	}
	
	public Track getNext()
	{
		if(direction == true)
		{
			if(switchState.equals(SwitchState.LEFT))
			{
				return B;
			}
			else
			{
				return C;
			}
		}
		else
		{
			return A;
		}
	}
}
