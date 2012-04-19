import parser.*;

public class TestTrackParser
{
	public static void main(String[] args)
	{
		if(args.length < 1)
		{
			System.out.println("Must supply a .csv track file");
			System.exit(0);
		}
		try
		{
			TrackParser tp = new TrackParser(args[0]);
			while (tp.next())
			{
				System.out.println(tp);
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
			System.exit(0);
		}
	}
}