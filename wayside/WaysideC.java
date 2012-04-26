package wayside;

import global.*;

/**
 * Controls the specific logic for the 'C' section of track
 * @author Christian Marini
 */
public class WaysideC extends Wayside
{
	public WaysideC(ID id)
	{
		super(id);
	}

	void runLogic()
	{
		if (nextLeft().clearToReceiveFrom(this))
		{
			/* Stop train at the end of this section */
			spreadAuthority(0);
		}
		else
		/* Clear to send to next wayside */
		{
			/* Let train continue to next section */
			spreadAuthority(1);
		}
	}
}
