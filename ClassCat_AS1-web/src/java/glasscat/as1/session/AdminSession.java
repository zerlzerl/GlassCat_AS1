/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.session;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
/**
 * this managed bean is used to save the login state of user
 * @author Li Xuekai<zerlzerl@163.com>
 */
@SessionScoped
public class AdminSession implements Serializable {
    
    private boolean isLoggedIn = false;
    private boolean userRedirected = false;

    public AdminSession() {
    }

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public boolean isUserRedirected() {
        return this.userRedirected;
    }

    public void setUserRedirected(boolean userRedirected) {
        this.userRedirected = userRedirected;
    }
}
