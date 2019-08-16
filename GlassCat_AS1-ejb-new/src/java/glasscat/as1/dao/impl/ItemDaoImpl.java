/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.impl;

import glasscat.as1.entity.ItemEntity;
import glasscat.as1.entity.UserEntity;
import glasscat.as1.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Stateless
public class ItemDaoImpl extends BaseDaoImpl<ItemEntity> implements ItemDao {

    public ItemDaoImpl() {
        super(ItemEntity.class);
    }

    @Override
    public List<ItemEntity> findByTitleLike(String queryStr) {
        return this.entityManager.createNamedQuery("findByTitleLike", ItemEntity.class)
                .setParameter("title", queryStr)
                .getResultList();
    }

    @Override
    public List<ItemEntity> findByAttributesLike(String queryStr) {
        return this.entityManager.createNamedQuery("findByAttributesLike", ItemEntity.class)
                .setParameter("keyword", queryStr)
                .getResultList();
    }

    @Override
    public List<ItemEntity> findBy3Attributes(String title, String type, Double low, Double high) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemEntity> query = criteriaBuilder.createQuery(ItemEntity.class);
        Root<ItemEntity> root = query.from(ItemEntity.class);
        List<Predicate> predicatesList = new ArrayList<>();
        if (!StringUtil.isBlank(title)) {
            predicatesList.add(criteriaBuilder.like(root.get("title").as(String.class), "%" + title + "%"));
        }
        if (!StringUtil.isBlank(type)) {
            predicatesList.add(criteriaBuilder.like(root.get("category").as(String.class), "%" + type + "%"));
        }
        if (low != null) {
            predicatesList.add(criteriaBuilder.gt(root.get("price").as(Double.class), low));
        }
        if (high != null) {
            predicatesList.add(criteriaBuilder.lt(root.get("price").as(Double.class), high));
        }
        query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
        TypedQuery<ItemEntity> q = this.entityManager.createQuery(query);
        
        return q.getResultList();
    }
    
}
