package com.dt.core;


import java.util.Date;
import java.text.SimpleDateFormat;


import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.Logger;

public interface LoggingMethods extends TestDataReader {
	 PatternLayout layout = new PatternLayout();
     String conversionPattern = "%-7p %d %M - %m%n";
     String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

     // creates console appender
     ConsoleAppender consoleAppender = new ConsoleAppender();
     

     // creates file appender
     FileAppender fileAppender = new FileAppender();
     

     // configures the root logger
     Logger rootLogger = Logger.getRootLogger();
     
    

     // creates a custom logger and log messages
     Logger log = Logger.getLogger(LoggingMethods.class);
     
public default void log(Boolean flag,String Message)
{
	
	layout.setConversionPattern(conversionPattern);
	
	consoleAppender.setLayout(layout);
    consoleAppender.activateOptions();
    fileAppender.setFile(System.getProperty("user.dir")+"/console"+timeStamp+".log");
    fileAppender.setLayout(layout);
    fileAppender.activateOptions();
    
    
	if(flag==true)
	{
		rootLogger.setLevel(Level.INFO);
	    rootLogger.addAppender(consoleAppender);
	    rootLogger.addAppender(fileAppender);
		log.info("PASS :"+Message);
	}
	else if(flag==false)
	{
		rootLogger.setLevel(Level.ERROR);
	    rootLogger.addAppender(consoleAppender);
	    rootLogger.addAppender(fileAppender);
		log.error("FAIL :"+Message);
	}
	else
	{
		rootLogger.setLevel(Level.FATAL);
	    rootLogger.addAppender(consoleAppender);
	    rootLogger.addAppender(fileAppender);
		log.fatal("Wrong Parameters");
	}
}
}
