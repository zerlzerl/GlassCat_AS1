/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.dao.impl.UserDao;
import glasscat.as1.entity.UserEntity;
import glasscat.as1.util.Constants;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Named(value = "userManagementController")
@RequestScoped
public class UserManagementController {
    // bind page query criteria
    private String userId;
    private String userEmail;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private List<UserEntity> resultUsers;
    @EJB
    private UserDao userDao;
    /**
     * Creates a new instance of UserManagementController
     */
    public UserManagementController() {
    }
    
    @PostConstruct
    public void init(){
        this.resultUsers = userDao.findAll();
    }
    
    public String executeSearch(){
        System.out.println("search!");
        this.resultUsers = new ArrayList();
        this.resultUsers = userDao.findBy5Attributes(userId, userEmail, firstName, lastName, phoneNo);
        return "/" + Constants.USER_MANAGE_PAGE  + "?faces-redirect=true";
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<UserEntity> getResultUsers() {
        return resultUsers;
    }

    
    
}
