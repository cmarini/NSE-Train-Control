package trackmodel;

import global.ID;

public class Switch extends Track
{
	public enum SwitchState
	{
		LEFT, RIGHT;
	}
	
	public Track C;
	private boolean orientation; // True is entering from double-side
	
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
        
	public void setNext(Track t, boolean backwards)
	{
            if(!backwards)
            {
		if(B == null)
		{
			B = t;
			log.config("Track " + trackID + ": B set to " + t);
                        //System.out.println("Track " + trackID + ": B set to " + t.getID());
		}
		else
		{
			C = t;
			log.config("Track " + trackID + ": C set to " + t);
                        //System.out.println("Track " + trackID + ": C set to " + t.getID());
		}
            }
            else
            {
                if(C == null)
		{
			C = t;
			log.config("Track " + trackID + ": C set to " + t);
                        //System.out.println("Track " + trackID + ": C set to " + t.getID());
		}
		else
		{
			B = t;
			log.config("Track " + trackID + ": B set to " + t);
                        //System.out.println("Track " + trackID + ": B set to " + t.getID());
		}
            }
	}
        
        public void swapNext()
        {
            Track temp = B;
            B = C;
            C = temp;
            //System.out.println("SWITCH: swapNext");
        }
	
	public void setOrientation(boolean b)
	{
		orientation = b;
	}
	
	public Track getNext()
	{
		if (orientation == true)
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
		else
		{
			if(direction == false)
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
}
