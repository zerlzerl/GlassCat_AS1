/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.util.Constants;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Named(value = "registerController")
@SessionScoped
public class RegisterController implements Serializable {
    private String email;
    private String password;
    private String passwordRe;
    /**
     * Creates a new instance of RegisterController
     */
    public RegisterController() {
    }
    
    public String signUp() {
        System.out.println("user Sign Up");
        return Constants.LOGIN_PAGE + "?faces-redirect=true";
    }

    public String loginPage() {
        return Constants.LOGIN_PAGE + "?faces-redirect=true";
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRe() {
        return passwordRe;
    }

    public void setPasswordRe(String passwordRe) {
        this.passwordRe = passwordRe;
    }
    
}
