/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.impl;

import glasscat.as1.entity.UserEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        this.entityManager.flush();
        return this.entityManager.createNamedQuery("findByUserName", UserEntity.class)
                .setParameter("userName", username)
                .getResultList();
    }
    
    public List<String> findAllProfessions(){
//        List<String> originalProfessions = 
                return this.entityManager.createQuery("SELECT DISTINCT u.profession FROM UserEntity u ORDER BY u.profession", String.class)
                .getResultList();
//        if (originalProfessions != null && originalProfessions.size() > 0) {
//            Set<String> professions = new HashSet<>();
//            for (String op : originalProfessions) {
//                String[] opList = op.split(",");
//                for (String p : opList) {
//                    professions.add(p);
//                }
//            }
//            return new ArrayList<>(professions);
//        } else {
//            return null;
//        }
        
    }
}
