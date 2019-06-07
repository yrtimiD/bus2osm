package org.yrtimid.bus2osm;

import java.io.InputStream;
import java.util.logging.*;

public class Importer {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Logging.setup();
			Logger logger = Logger.getLogger(Importer.class.getPackage().getName());
			logger.log(Level.INFO, "log is ok");
			
		if (args.length<1){	
			System.out.println("Importer <url>");
		}
		else {
			System.out.println("Using "+args[0]);
			InputStream stream = URLHelper.getContentFromURL(args[0]);
			Parser.Parse(stream);
		}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
