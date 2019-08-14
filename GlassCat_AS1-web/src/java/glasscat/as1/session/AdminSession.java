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
    private String currentUserId;
    private Integer membershipLevel;

    public AdminSession() {
    }

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public Integer getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(Integer membershipLevel) {
        this.membershipLevel = membershipLevel;
    }


}
