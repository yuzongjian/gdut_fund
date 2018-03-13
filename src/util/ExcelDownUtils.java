package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class ExcelDownUtils {

	public static void excelDownUtils(List<Map<String, Object>> list,String []keys,String columnNames[],HttpServletResponse response,String filename) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
	    try{
		    	ExcelUtils.createWorkBook(list, keys, columnNames).write(os);
		    	byte[] content = os.toByteArray();
			    InputStream is = new ByteArrayInputStream(content);
			    response.reset();
			    response.setContentType("application/vnd.ms-excel;charset=utf-8");
			    response.setHeader("Content-Disposition", "attachment;filename="+ new String((filename + ".xls").getBytes(), "iso-8859-1"));
			    ServletOutputStream out = response.getOutputStream();
			    bis = new BufferedInputStream(is);
			    bos = new BufferedOutputStream(out);
			    byte[] buff = new byte[2048];
			    int bytesRead;
			    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			         bos.write(buff, 0, bytesRead);
			    }
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally {
	            if (bis != null)
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            if (bos != null)
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	     }
	}
}
