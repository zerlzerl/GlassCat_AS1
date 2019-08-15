/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.util;

import java.util.regex.Pattern;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
public class StringUtil {
    private static final String EMAIL_PATTERN = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{8,20})";
    private static final String PHONENO_PATTERN = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";
    private static final String NAME_PATTERN = "^[A-Z][a-zA-Z]+$";
    public static boolean isValidEmail(String str) {
        return Pattern.matches(EMAIL_PATTERN, str);
    }
    
    public static boolean isValidPassword(String str) {
        return Pattern.matches(PASSWORD_PATTERN, str);
    }
    
    public static boolean isValidPhoneNumber(String str) {
        return Pattern.matches(PHONENO_PATTERN, str);
    }
    
    public static boolean isValidName(String str) {
        return Pattern.matches(NAME_PATTERN, str);
    }
    
    public static boolean isBlank(Object object){
        if (null == object) {
            return true;
        }
        if ((object instanceof String)){
            return "".equals(((String)object).trim());
        }
        return false;
    }

}
