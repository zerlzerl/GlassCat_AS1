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
import javax.inject.Inject;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Named(value = "indexController")
@SessionScoped
public class IndexController implements Serializable {
    @Inject
    private LoginController loginController;
    /**
     * Creates a new instance of IndexController
     */
    public IndexController() {
    }
    
    public String cart(){
        return "/" + Constants.CART_PAGE + "?faces-redirect=true";
    }
    
    public String personalCenter() {
        return "/" + Constants.ADMIN_PAGE + "?faces-redirect=true";
    }

}
