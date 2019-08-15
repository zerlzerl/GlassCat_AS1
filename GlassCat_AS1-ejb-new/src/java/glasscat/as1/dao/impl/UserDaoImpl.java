/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.impl;

import glasscat.as1.entity.UserEntity;
import glasscat.as1.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Stateless
public class UserDaoImpl extends BaseDaoImpl<UserEntity> implements UserDao {

    public UserDaoImpl() {
        super(UserEntity.class);
    }
    
    @Override
    public List<UserEntity> findByEmail(String email) {
        return this.entityManager.createNamedQuery("findByEmail", UserEntity.class)
                .setParameter("email", email)
                .getResultList();
    }

    @Override
    public List<UserEntity> findByUserName(String username) {
        return this.entityManager.createNamedQuery("findByUserName", UserEntity.class)
                .setParameter("userName", username)
                .getResultList();
    }

    @Override
    public List<String> findAllProfessions() {
        return this.entityManager.createQuery("SELECT DISTINCT u.profession FROM UserEntity u ORDER BY u.profession", String.class)
                .getResultList();
    }

    @Override
    public List<UserEntity> findBy5Attributes(String userId, String email, String firstName, String lastName, String phoneNumber, Integer membershipLevel) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        List<Predicate> predicatesList = new ArrayList<>();
        if (!StringUtil.isBlank(userId)) {
            predicatesList.add(criteriaBuilder.like(root.get("id").as(String.class), "%" + userId + "%"));
        }
        if (!StringUtil.isBlank(email)) {
            predicatesList.add(criteriaBuilder.like(root.get("email").as(String.class), "%" + email + "%"));
        }
        if (!StringUtil.isBlank(firstName)) {
            predicatesList.add(criteriaBuilder.like(root.get("firstName").as(String.class), "%" + firstName + "%"));
        }
        if (!StringUtil.isBlank(lastName)) {
            predicatesList.add(criteriaBuilder.like(root.get("lastName").as(String.class), "%" + lastName + "%"));
        }
        if (!StringUtil.isBlank(phoneNumber)) {
            predicatesList.add(criteriaBuilder.like(root.get("phoneNumber").as(String.class), "%" + phoneNumber + "%"));
        }
        predicatesList.add(criteriaBuilder.lt(root.get("membershipLevel").as(Integer.class), membershipLevel));
        System.out.println(predicatesList.size());
        query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
        TypedQuery<UserEntity> q = this.entityManager.createQuery(query);
        
        return q.getResultList();
    }

}
