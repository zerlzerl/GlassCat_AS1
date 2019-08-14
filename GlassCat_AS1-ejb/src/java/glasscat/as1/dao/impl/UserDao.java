/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.impl;

import glasscat.as1.entity.UserEntity;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Stateless
public class UserDao extends BaseDaoImpl<UserEntity>{

    public UserDao() {
        super(UserEntity.class);
    }
    
    //if any entity specified logic
    public List<UserEntity> findByEmail(String email) {
        return this.entityManager.createNamedQuery("findByEmail", UserEntity.class)
                .setParameter("email", email)
                .getResultList();
    }

    public List<UserEntity> findByUserName(String username) {
        return this.entityManager.createNamedQuery("findByUserName", UserEntity.class)
                .setParameter("userName", username)
                .getResultList();
    }
    

}
