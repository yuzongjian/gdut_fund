package util;

public class IsEmpty {
	public static String isEmpty(String a) {
		if ("".equals(a)||a==null||"null".equals(a.trim())) {
			return null;
		}
		else
			return a;
	}
}