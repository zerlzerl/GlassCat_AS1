/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.dao.impl.UserDao;
import glasscat.as1.entity.UserEntity;
import glasscat.as1.util.Constants;
import glasscat.as1.util.IDUtil;
import glasscat.as1.util.SHAUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

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
    @EJB
    private UserDao userDao;
    @Inject
    private LoginController loginController;
    /**
     * Creates a new instance of RegisterController
     */
    public RegisterController() {
    }
    
    public String signUp() throws NoSuchAlgorithmException {
        // validation complete
        UserEntity newUser = new UserEntity();
        String newUserId = IDUtil.getUUID();
        String salt = IDUtil.getUUID();
        String hashedPasswd = SHAUtil.toHexString(SHAUtil.getSHA(password + salt));
        newUser.setSalt(salt);
        newUser.setId(newUserId);
        newUser.setEmail(email);
        newUser.setPassword(hashedPasswd);
        newUser.setMembershipLevel(0);
        userDao.save(newUser);
        System.out.println("New user register! email: " + email + ".");
        // login Automatically
        loginController.setIsLoggedIn(true);
        loginController.setCurrentUserId(newUserId);
        
        return Constants.INDEX_PAGE + "?faces-redirect=true";
    }

    public String loginPage() {
        return Constants.LOGIN_PAGE + "?faces-redirect=true";
    }
    
    public void validateEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String em = (String) value;
        System.out.println("email: " + em);
        if (em == null || em == null) {
            return; // Just ignore and let required="true" do its job.
        }
        
        if (!Pattern.matches(Constants.EMAIL_VALIDATION_REGEX, em)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not a valid email.", "Please check your email");
            throw new ValidatorException(msg);
        }
        List<UserEntity> users = userDao.findByEmail(em);
        if (users != null && users.size() > 0) {
            throw new ValidatorException(new FacesMessage("Email is existing."));
        }

    }
    
    public void validatePasswordComfirmation(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UIInput passwordComponent = (UIInput) component.getAttributes().get("passwordComponent");
        String password = (String) passwordComponent.getValue();
        String confirm = (String) value;
        System.out.println("password: " + password);
        System.out.println("confirm: " + confirm);
        if (password == null || confirm == null) {
            return; // Just ignore and let required="true" do its job.
        }

        if (!password.equals(confirm)) {
            throw new ValidatorException(new FacesMessage("Passwords are not equal."));
        }
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
