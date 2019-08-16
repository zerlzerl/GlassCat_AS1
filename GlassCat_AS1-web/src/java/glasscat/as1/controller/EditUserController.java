/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.dao.impl.UserDao;
import glasscat.as1.entity.UserEntity;
import glasscat.as1.util.Constants;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Named(value = "editUserController")
@ViewScoped
public class EditUserController implements Serializable{
    private String userName;
    private UserEntity user;
    
    private String userfn;
    private String userln;
    private String address;
    @EJB
    private UserDao userDao;
    /**
     * Creates a new instance of TransManageController
     */
    public EditUserController() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public void excuteSearch(){
        List<UserEntity> users = userDao.findByUserName(userName);
        if (users != null && !users.isEmpty()) {
            user = users.get(0);
            this.userfn = user.getFirstName();
            this.userln = user.getLastName();
            this.address = user.getAddress();
        }
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
    
    public String save(){
        user.setFirstName(userfn);
        user.setLastName(userln);
        user.setAddress(address);
        System.out.println(this.user.getFirstName());
        System.out.println(this.user.getLastName());
        System.out.println(this.user.getAddress());
        userDao.update(user);
        return "/" + Constants.USER_MANAGE_PAGE + "?faces-redirect=true";
    }

    public String getUserfn() {
        return userfn;
    }

    public void setUserfn(String userfn) {
        this.userfn = userfn;
    }

    public String getUserln() {
        return userln;
    }

    public void setUserln(String userln) {
        this.userln = userln;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
