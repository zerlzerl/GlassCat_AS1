/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.util;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
public interface Constants {
    String INDEX_PAGE = "index.jsf";  //front end main page
    String LOGIN_PAGE = "login.jsf";  //login page
    String REGISTER_PAGE = "register.jsf"; //register page
    String ADMIN_PAGE = "admin.jsf";  //back end management page
    String RESULT_PAGE = "result.jsf"; //result page
    String DETAIL_PAGE = "detail.jsf"; //detail page
    String CART_PAGE = "cart.jsf"; //cart page
    String ERROR_PAGE = "500.xhtml";  //500 error
    String ACCESS_DENIED_PAGE = "403.xhtml";  // 403 forbidden page
    String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String DATE_FORMAT = "yyyy-MM-dd";
    String PAGE_FORMAT = "xhtml";
    
    String PROTOCOL = "http"; //change to https if needed
    
    Boolean IS_ENABLE_LOGIN_FILTER = false; // trigger to enable the login filter
}
