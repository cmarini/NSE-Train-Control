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
			fh = new FileHandler("log.log");
		}
		catch(IOException e){}
		
		log = Logger.getLogger("");
		for (Handler h : log.getHandlers())
		{
			log.removeHandler(h);
		}
		ch = new ConsoleHandler();
		fh.setFormatter(new SimpleFormatter());
		ch.setFormatter(new SimpleFormatter());
		log.addHandler(fh);
		log.addHandler(ch);
		log.setLevel(Level.ALL);

		setConsoleLevel(Level.WARNING);
		setFileLevel(Level.CONFIG);
	}
	
	public static void setConsoleLevel(Level level)
	{
		Level fhl = fh.getLevel();
		ch.setLevel(Level.INFO);
		fh.setLevel(Level.INFO);
		log.info("CONSOLE LOGGING = " + level);
		fh.setLevel(fhl);
		ch.setLevel(level);
	}
	
	public static void setFileLevel(Level level)
	{
		
		Level chl = ch.getLevel();
		fh.setLevel(Level.INFO);
		ch.setLevel(Level.INFO);
		log.info("FILE LOGGING = " + level);
		ch.setLevel(chl);
		fh.setLevel(level);
	}
	
	public static void setLevel(Level level)
	{
		setConsoleLevel(level);
		setFileLevel(level);
	}

}