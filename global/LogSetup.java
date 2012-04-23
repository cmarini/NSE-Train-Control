package global;

import java.io.*;
import java.util.logging.*;

public final class LogSetup
{
	private static Logger log;
	private static FileHandler fh;
	
	public static void init()
	{
		try
		{
			log = Logger.getLogger("");
			fh = new FileHandler("log.log");
			fh.setFormatter(new SimpleFormatter());
			log.addHandler(fh);
		}
		catch(IOException e){}
	}
}