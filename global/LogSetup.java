package global;

import java.io.*;
import java.util.logging.*;

public class LogSetup
{
	private static Logger log;
	private static FileHandler fh;
	private static ConsoleHandler ch;
	
	public static void init()
	{
		try
		{
			log = Logger.getLogger("");
			
			fh = new FileHandler("log.log");
			ch = new ConsoleHandler();

			fh.setFormatter(new SimpleFormatter());
			ch.setFormatter(new SimpleFormatter());
			
			for (Handler h : log.getHandlers())
			{
				log.removeHandler(h);
			}
			
			log.addHandler(fh);
			log.addHandler(ch);
			
			log.setUseParentHandlers(false);
			log.setLevel(Level.ALL);
			
			Logger.getLogger("java").setLevel(Level.OFF);
			Logger.getLogger("javax").setLevel(Level.OFF);
			Logger.getLogger("sun").setLevel(Level.OFF);
			
			setConsoleLevel(Level.WARNING);
			setFileLevel(Level.ALL);
		}
		catch(IOException e){}
	}
	
	public static void setConsoleLevel(Level level)
	{
		Level fhl = fh.getLevel();
		fh.setLevel(Level.INFO);
		log.info("CONSOLE LOGGING LEVEL = " + level);
		fh.setLevel(fhl);
		ch.setLevel(level);
	}
	
	public static void setFileLevel(Level level)
	{
		
		fh.setLevel(Level.INFO);
		log.info("FILE LOGGING LEVEL = " + level);
		fh.setLevel(level);
	}
}