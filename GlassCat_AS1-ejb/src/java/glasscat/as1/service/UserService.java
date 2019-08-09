/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.service;

import glasscat.as1.entity.UserEntity;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
public interface UserService {
    public void addUser();
    public List<UserEntity> getAllUsers();
}
