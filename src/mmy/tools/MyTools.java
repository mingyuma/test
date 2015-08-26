package mmy.tools;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTools {
	public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
	/**
     * 两个时间之间的天数
     */
	public static long getDays(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        java.util.Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return Math.abs(day);
    }
	//生日转为年龄
    public static String getAge(String data){
    	long day=getDays(data, getStringDate());
    	long year=day/365;
//        long month=(day-365L*year)/30+1;
//        long week=day/7+1;
		return year+"";
    	
    }
    
  //@param @return 设定文件
  //@return boolean 返回类型
  //@throws
  public static boolean isPhoneNumberValid(String phoneNumber) {
  	boolean isValid = false;
  	String expression = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}d{1}-?d{8}$)|"
  			+ "(^0[3-9] {1}d{2}-?d{7,8}$)|"
  							+ "(^0[1,2]{1}d{1}-?d{8}-(d{1,4})$)|"
  			+ "(^0[3-9]{1}d{2}-? d{7,8}-(d{1,4})$))";
  	CharSequence inputStr = phoneNumber;
  	Pattern pattern = Pattern.compile(expression);
  	Matcher matcher = pattern.matcher(inputStr);
  	if (matcher.matches()) {
  		isValid = true;
  	}
  	return isValid;
  }
    
}
