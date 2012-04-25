package wayside;

import global.*;
import trackmodel.*;

public class WaysideD extends Wayside
{
	private boolean toTrainYard;
	private boolean trainOnSwitch;
		
	public WaysideD(ID id)
	{
		super(id);
		toTrainYard = false;
		trainOnSwitch = false;
	}

	void runLogic()
	{
		Switch tySwitch = (Switch) track.get(trackEnd());
		if (toTrainYard)
		{
			tySwitch.setSwitchState(Switch.SwitchState.LEFT);
			spreadAuthority(1);
			if (tySwitch.isOccupied())
			{
				trainOnSwitch = true;
			}
			else if (trainOnSwitch)
			{
				trainOnSwitch = false;
				toTrainYard = false;
			}
		}
		else
		{
			tySwitch.setSwitchState(Switch.SwitchState.RIGHT);
			if (nextRight().clearToReceiveFrom(this))
			{
				spreadAuthority(1);
			}
			else
			{
				spreadAuthority(0);
			}
		}
	}
	
	public void setSwitchToTrainYard()
	{
		toTrainYard = true;
	}
		
}
