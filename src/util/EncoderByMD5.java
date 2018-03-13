package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class EncoderByMD5 {
	public static String EncoderByMd5(String str){
        //确定计算方法
        MessageDigest md5 = null;
        //加密后的字符串
        String newstr = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        BASE64Encoder base64en = new BASE64Encoder();
		try {
			newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return newstr;
    }
}
