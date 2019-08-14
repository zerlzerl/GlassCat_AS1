/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.dao.impl.UserDao;
import glasscat.as1.entity.UserEntity;
import glasscat.as1.util.Constants;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Named(value = "adminController")
@RequestScoped
public class AdminController implements Serializable {
    private String email;
    private Integer memberShipLevel; // membership level used to control the render of the admin nav
    private String memberShip;
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNo;    
    private String gender;
    private String birthday;
    private String profession;
    @Inject
    private LoginController loginController;
    @EJB
    private UserDao userDao;
    /**
     * Creates a new instance of AdminController
     */
    public AdminController() {
    }
    
    @PostConstruct
    public void init(){
        String userId = loginController.getCurrentUserId();
        UserEntity user = userDao.findById(userId);
        if (user != null) {
            this.email = user.getEmail();
            this.memberShipLevel = user.getMembershipLevel();
            switch(this.memberShipLevel){
                case 0:
                    this.memberShip = Constants.VISITOR; break;
                case 1:
                    this.memberShip = Constants.CUSTOMER; break;
                case 2:
                    this.memberShip = Constants.MANAGER; break;
                case 3:
                    this.memberShip = Constants.ADMIN; break;
                default:
                    this.memberShip = null;
            }
            this.username = user.getUserName();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.address = user.getAddress();
            this.phoneNo = user.getPhoneNumber();
            this.gender = user.getGender();
            this.birthday = new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthday());
            this.profession = user.getProfession();
        }
        
    }
    public String getCurrentPage() {
        HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String requestURI = origRequest.getRequestURI();
        System.out.println(requestURI);
        return requestURI;
    }

    public String getEmail() {
        return email;
    }

    public String getMemberShip() {
        return memberShip;
    }

    public Integer getMsl() {
        return this.memberShipLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    
    
    
}
