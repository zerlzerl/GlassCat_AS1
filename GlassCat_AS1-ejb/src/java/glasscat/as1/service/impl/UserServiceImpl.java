/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.service.impl;

import glasscat.as1.entity.UserEntity;
import javax.ejb.Stateless;
import glasscat.as1.service.UserService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Stateless
public class UserServiceImpl implements UserService {
    @PersistenceContext(unitName = "GlassCatPU")
    private EntityManager entityManager;
    @Override
    public void addUser() {
        System.out.println("=========调用到addUser方法==========");
        UserEntity user = new UserEntity();
        user.setEmail("zerlzerl@163.com");
        user.setPassword("123456");
        
        entityManager.persist(user);        
        entityManager.flush();
    }
    
    public List<UserEntity> getAllUsers(){
        System.out.println("=========调用到getAllUsers方法==========");
        TypedQuery<UserEntity> query = entityManager.createQuery(
            "SELECT u FROM UserEntity u ORDER BY u.id", UserEntity.class);
        return query.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
