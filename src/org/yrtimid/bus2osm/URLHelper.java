package org.yrtimid.bus2osm;

import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class URLHelper {

	private static final Logger logger = Logger.getLogger(URLHelper.class.getPackage().getName());

	public static InputStream getContentFromURL(String urlString) throws MalformedURLException {
		InputStream stream = null;

		try {
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();

			// this Google's page denies access from Java/1.6.0 client, we'll
			// fake our User-Agent
			conn.addRequestProperty("User-Agent", "Mozilla/5.0");

			String contentType = conn.getContentType();

			String status = conn.getHeaderField(0);

			if (!status.contains("200 OK")) {
				logger.log(Level.FINEST, "Status is " + status + " skiping.");
			} else {
				logger.log(Level.FINEST, "Status is " + status);
				if (!contentType.contains("text/html")) {
					logger.log(Level.FINEST,
							"Skiping incompatible content type " + contentType);
				} else {
					InputStream in = conn.getInputStream();
					stream = new BufferedInputStream(in);
				}
			}
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
		
		return stream;
	}
}
