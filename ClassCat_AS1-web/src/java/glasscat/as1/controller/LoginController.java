/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.dao.impl.UserDao;
import glasscat.as1.entity.UserEntity;
import glasscat.as1.session.AdminSession;
import glasscat.as1.util.Constants;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Named(value = "loginController")
@SessionScoped
@Specializes
public class LoginController extends AdminSession implements Serializable {
    private String loginProof; //login proof which user entered
    private String email; // login proof - email
    private String userName; // alternative login proof - username
    private String password;
    @EJB
    private UserDao userDao;
    
    public String login() throws IOException {
        return Constants.INDEX_PAGE + "?faces-redirect=true";
        
    }
    
    public void loginValidator(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UIInput loginProofComponent = (UIInput) component.getAttributes().get("loginProofComponent");
        String proof = (String) loginProofComponent.getValue();
        String passwd = (String) value;
        System.out.println("loginProof: " + proof);
        System.out.println("password: " + passwd);
        
        if (proof == null || passwd == null) {
            return; // Just ignore and let required="true" do its job.
        }
        
        // user database table check
        List<UserEntity> users;
        if (Pattern.matches(Constants.EMAIL_VALIDATION_REGEX, proof)) {
            // user use email to login
            users = userDao.findByEmail(proof);            
        } else {
            // user use username to login
            users = userDao.findByUserName(proof);            
        }
        if (users != null && users.size() > 0) {
            // search a existing user
            UserEntity u = users.get(0);
            if (u.getPassword().equals(passwd)) {
                // success
                this.email = u.getEmail() == null ? null : u.getEmail();
                this.userName = u.getUserName() == null ? null : u.getUserName();
                this.setIsLoggedIn(true);
                this.setCurrentUserId(u.getId());
                this.setMembershipLevel(u.getMembershipLevel());
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login fail.", "Please ensure your email/username and password.");
                throw new ValidatorException(msg);
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No user found.", "Please check your email or username.");
            throw new ValidatorException(msg); 
        }
    }
    
    public String logout() {
        System.out.println("Logout");
        setIsLoggedIn(false);
        return "/" + Constants.LOGIN_PAGE + "?faces-redirect=true";
    }
    
    public String registerPage(){
        return "/" + Constants.REGISTER_PAGE + "?faces-redirect=true";
    }
    public String getLoginProof() {
        return loginProof;
    }

    public void setLoginProof(String loginProof) {
        this.loginProof = loginProof;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    
    
}
