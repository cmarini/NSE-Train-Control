package wayside;

import global.*;
import trackmodel.*;

public class WaysideA extends Wayside
{
	public WaysideA(ID id)
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
			/* Set next track piece's switch RIGHT */
			Switch s = (Switch) track.get(trackEnd()).getNext(direction);
			s.setSwitchState(Switch.SwitchState.RIGHT);
			/* Let train continue to next section */
			spreadAuthority(1);
		}
	}
}
