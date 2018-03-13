package util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebTime {
	   public static String getNetworkTime() {
	        try {
	            URL url = new URL("http://www.baidu.com");
	            URLConnection conn = url.openConnection();
	            conn.connect();
	            long dateL = conn.getDate();
	            Date date = new Date(dateL);
	            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM");
	            return dateFormat.format(date);
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        java.sql.Date sd;
	        Date ud = new Date();
	        sd = new java.sql.Date(ud.getTime()); 
	        return sd.toString();
	    }
}
