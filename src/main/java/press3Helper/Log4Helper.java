package press3Helper;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log4Helper {

    public static Logger logger = Logger.getLogger(Log4Helper.class);
	static{
	        
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
	        System.setProperty("current.date.time", dateFormat.format(new Date()));
	}
	public static void log4Initializer()
	{
		 DOMConfigurator.configure(System.getProperty("user.dir") + "\\Configs\\log4j.xml");
		 logger.info("Log4j appender configuration is successful !!");
	}
	
	public static void writeData(String className, String Message , int LineNumber  )
	{
		
		logger = Logger.getLogger(className);
		logger.info(Message +" LineNumber : " + Integer.toString(LineNumber));
	}
	
}
