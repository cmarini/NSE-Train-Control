package wayside;

public class WaysideC extends Wayside
{
	public Wayside(ID id)
	{
		super(id);
	}

	private void runLogic()
	{
	
	}
	
	public void trainIncFrom(Wayside w)
	{
		if (w.equals(aL))
		{
			track.get(0).setSwitchState(SwitchState.LEFT);
			trainDir = true;
		}
		else if (w.equals(aR))
		{
			track.get(0).setSwitchState(SwitchState.RIGHT);
			trainDir = true;
		}
		else if (w.equals(bL))
		{
			track.get(track.length() - 1).setSwitchState(SwitchState.LEFT);
			trainDir = false;
		}
		else if (w.equals(bR))
		{
			track.get(track.length() - 1).setSwitchState(SwitchState.RIGHT);
			trainDir = false;
		}
	}
	
}
