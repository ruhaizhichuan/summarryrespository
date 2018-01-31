package org.rhzc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
    
    /** 
     * 判断是否为浮点数或者整数 
     * @param str 
     * @return true Or false 
     */  
    public static boolean isNumeric(String str){  
          Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");  
          Matcher isNum = pattern.matcher(str);  
          if( !isNum.matches() ){  
                return false;  
          }  
          return true;  
    }  
      
    /** 
     * 判断是否为正确的邮件格式 
     * @param str 
     * @return boolean 
     */  
    public static boolean isEmail(String str){  
        if(isEmpty(str))  
            return false;  
        return str.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");  
    }  
      
    /** 
     * 判断字符串是否为合法手机号 11位 13 14 15 17 18开头 
     * @param str 
     * @return boolean 
     */  
    public static boolean isMobile(String str){  
        if(isEmpty(str))  
            return false;  
        return str.matches("^(13|14|15|17|18)\\d{9}$");  
    }  
      
    /** 
     * 判断是否为数字 
     * @param str 
     * @return 
     */  
    public static boolean isNumber(String str) {  
        try{  
            Integer.parseInt(str);  
            return true;  
        }catch(Exception ex){  
            return false;  
        }  
    }  
      
          
    /** 
     * 判断字符串是否为非空(包含null与"") 
     * @param str 
     * @return 
     */  
    public static boolean isNotEmpty(String str){  
        if(str == null || "".equals(str))  
            return false;  
        return true;  
    }  
      
    /** 
     * 判断字符串是否为非空(包含null与"","    ") 
     * @param str 
     * @return 
     */  
    public static boolean isNotEmptyIgnoreBlank(String str){  
        if(str == null || "".equals(str) || "".equals(str.trim()))  
            return false;  
        return true;  
    }  
      
    /** 
     * 判断字符串是否为空(包含null与"") 
     * @param str 
     * @return 
     */  
    public static boolean isEmpty(String str){  
        if(str == null || "".equals(str))  
            return true;  
        return false;  
    }  
      
    /** 
     * 判断字符串是否为空(包含null与"","    ") 
     * @param str 
     * @return 
     */  
    public static boolean isEmptyIgnoreBlank(String str){  
        if(str == null || "".equals(str) || "".equals(str.trim()))  
            return true;  
        return false;  
    }  
      
    /**
     * 判断是否包含汉字
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
    
    /**
     * 判断日期为yyyy/MM/dd HH:mm:ss的格式
     * @param str
     * @return
     */
    public static boolean isValidDate(String str) {
       
    	boolean convertSuccess=true;
       
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = null;
        
        if(str.length() > 10)
        {
        	format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        else
        {
        	format = new SimpleDateFormat("yyyy-MM-dd");
		}
        
        try 
        {
        	// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        }
        catch (ParseException e) 
        {
        	 // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
             convertSuccess=false;
        } 
        
        return convertSuccess;
    }
    
    //禁止实例化  
    private ValidatorUtil(){}
}
