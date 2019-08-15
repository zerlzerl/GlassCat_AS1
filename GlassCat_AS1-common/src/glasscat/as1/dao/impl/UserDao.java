/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.impl;

import glasscat.as1.dao.Dao;
import glasscat.as1.entity.UserEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Remote
public interface UserDao extends Dao<UserEntity> {
    public List<UserEntity> findByEmail(String email);
    public List<UserEntity> findByUserName(String username);
    public List<String> findAllProfessions();
    public List<UserEntity> findBy5Attributes(String userId, String email, String firstName, String lastName, String phoneNo);
}
