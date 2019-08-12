/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.session.AdminSession;
import glasscat.as1.util.Constants;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Named(value = "loginController")
@SessionScoped
@Specializes
public class LoginController extends AdminSession implements Serializable {
    private String currentUser; // current login user id
    private String loginProof; //login proof which user entered
    private String password;
    private String email; // login proof - email
    private String userName; // alternative login proof - username
    
    
    public String login() throws IOException {
        System.out.println("login method is invoked.");
        System.out.println("proof: " + this.loginProof);
        System.out.println("password: " + this.password);
        currentUser = email;
        setIsLoggedIn(true);
        return Constants.INDEX_PAGE;
        
    }
    
    public String logout() {
        System.out.println("Logout");
        setIsLoggedIn(false);
        return Constants.LOGIN_PAGE;
    }
    
    public String getTestPage(){
        return "admin.jsg";
    }
    public String getLoginProof() {
        return loginProof;
    }

    public void setLoginProof(String loginProof) {
        this.loginProof = loginProof;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
