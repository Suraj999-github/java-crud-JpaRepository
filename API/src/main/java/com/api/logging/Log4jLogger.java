package com.api.logging;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;

public class Log4jLogger {
	
	static Logger log = Logger.getLogger(Log4jLogger.class.getName());

	public void info(String Msg) throws IOException, SQLException {		
		//NDC.push("Aparajita ");
		//this is for userid
		log.info(Msg);
	}
	public void error(String Msg)throws IOException, SQLException {
		//NDC.push("Aparajita ");
		log.error(Msg);
	}
	public void fatal(String Msg) throws IOException, SQLException{
		//NDC.push("Aparajita ");
		log.fatal(Msg);
	}
	public void debug(String Msg)throws IOException, SQLException {
		//NDC.push("Aparajita ");
		log.debug(Msg);
	}
	public void warn(String Msg)throws IOException, SQLException {
		//NDC.push("Aparajita ");
		log.warn(Msg);
	}


}
