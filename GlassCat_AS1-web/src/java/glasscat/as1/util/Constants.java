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
    String RESULT_PAGE = "result.jsf"; //result page
    String DETAIL_PAGE = "detail.jsf"; //detail page
    String CART_PAGE = "cart.jsf"; //cart page
    
    String ADMIN_PAGE = "admin.jsf";  //back end management page
    String HISTORY_PAGE = "admin/historical_purchase.jsf";
    String TRANS_MANAGE_PAGE = "admin/transaction_management.jsf";
    String ITEM_MANAGE_PAGE = "admin/item_management.jsf";
    String USER_MANAGE_PAGE = "admin/user_management.jsf";
    String EDIT_USER_PAGE = "admin/edit_user.jsf";
    String EDIT_ITEM_PAGE = "admin/edit_item.jsf";
    String ADD_ITEM_PAGE = "admin/add_item.jsf";
    
    String ERROR_PAGE = "500.xhtml";  //500 error
    String ACCESS_DENIED_PAGE = "403.xhtml";  // 403 forbidden page
    String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String DATE_FORMAT = "yyyy-MM-dd";
    String PAGE_FORMAT = "xhtml";
    String EMAIL_VALIDATION_REGEX = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
    String PASSWD_VALIDATION_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{8,20})";
    String PROTOCOL = "http"; //change to https if needed
    
    String VISITOR = "Visitor";
    String CUSTOMER = "Customer";
    String MANAGER = "Manager";
    String ADMIN = "Administrator";
    Boolean IS_ENABLE_LOGIN_FILTER = true; // trigger to enable the login filter
}
