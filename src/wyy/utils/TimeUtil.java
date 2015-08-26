package wyy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	public static String getCurrentTime(boolean isformat) {
		SimpleDateFormat format;
		if (isformat) {
			format = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
		} else {
			format = new SimpleDateFormat("yyyyMMddHHmmss");
		}
		Date curdate = new Date(System.currentTimeMillis());
		String str = format.format(curdate);
		return str;
	}
}
