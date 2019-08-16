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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sun Yeqing
 */
@Named(value = "adminController")
@RequestScoped
public class AdminController implements Serializable {
    private String email;
    private String memberShip;
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNo;    
    private String gender;
    private String birthday;
    private String profession;
    private List<String> professionList;
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
            switch(user.getMembershipLevel()){
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
            if (user.getBirthday() != null) {
                this.birthday = new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthday());
            }            
            this.profession = user.getProfession();
            this.professionList = userDao.findAllProfessions();
        }
        
    }
    public String getCurrentPage() {
        HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String requestURI = origRequest.getRequestURI();
        System.out.println(requestURI);
        return requestURI;
    }
    
    public void usernameValidator(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String uname = (String) value;
        if (uname != null) {
            List<UserEntity> users = userDao.findByUserName(uname);
            if (users == null || users.isEmpty()) {
                // do nothing
                return;
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Username is existing!", 
                    "Please try anothor username!");
                throw new ValidatorException(msg);
            }
        }
    }

    public String saveProfile() {
        String userId = loginController.getCurrentUserId();
        UserEntity user = userDao.findById(userId);
        user.setUserName(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setPhoneNumber(phoneNo);
        user.setGender(gender);
        try {
            System.out.println(birthday);
            user.setBirthday(new java.sql.Date(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(birthday).getTime()));
        } catch (ParseException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            user.setBirthday(null);
        }
        user.setProfession(profession);
        userDao.update(user);
        return "/" + Constants.ADMIN_PAGE + "?faces-redirect=true";
    }
    public String getEmail() {
        return email;
    }

    public String getMemberShip() {
        return memberShip;
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

    public List<String> getProfessionList() {
        return professionList;
    }
    
}
