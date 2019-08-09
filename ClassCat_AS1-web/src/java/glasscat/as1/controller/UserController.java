/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.entity.UserEntity;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import glasscat.as1.service.UserService;
import java.util.List;
/**
 *
 * @author zerlz
 */
@Named(value = "userController")
@RequestScoped
public class UserController {
//    @EJB
//    private UserService userService;
    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }
    
//    public String getMessage(){
//        System.out.println("=========调用到getMessage方法==========");
//        
//        userService.addUser();
//        List<UserEntity> users = userService.getAllUsers();
//        
//        return "" + users.size();
//    }
    
}
