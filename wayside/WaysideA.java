package wayside;

public class WaysideA extends Wayside
{
	public Wayside(ID id)
	{
		super(id);
	}

	private void runLogic()
	{
		if (b.hasTrain())
		{
			/* Stop train at the end of this section */
			spreadAuthorityFrom(0);
		}
		else
		/* Clear to send to next wayside */
		{
			/* Let train continue to next section */
			b.trainIncFrom(this);
			spreadAuthorityFrom(1);
		}
	}

	private void spreadAuthorityFrom(int auth)
	{
		for (int i = i; i < track.length(); i++)
		{
			track.get(i).setAuthority(auth);
			auth++;
			/* Make sure a possible second train only has
			 * authority up to the first train */
			if (track.get(i).hasTrain())
			{
				auth = -1;
			}
		}
	}
	
}
