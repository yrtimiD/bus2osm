package org.yrtimid.bus2osm;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logging {

	static private FileHandler fileTxt;
	static private SimpleFormatter formatterTxt;

	static public void setup() throws IOException {
		// Create Logger
		Logger logger = Logger.getLogger(Logging.class.getPackage().getName());
		logger.setLevel(Level.ALL);
		fileTxt = new FileHandler("log.txt");

		// Create txt Formatter
		formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		logger.addHandler(fileTxt);
	}
}
