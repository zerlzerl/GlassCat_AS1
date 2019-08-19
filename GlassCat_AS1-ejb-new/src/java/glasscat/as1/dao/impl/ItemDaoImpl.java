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

    @Override
    public List<ItemEntity> findX(Integer x) {
        return this.entityManager.createQuery("SELECT i FROM ItemEntity i", ItemEntity.class)
                .setMaxResults(x).getResultList();
    }

    @Override
    public List<ItemEntity> findByAttributesLikeWithCritiria(String queryStr, String[] categories, String[] colors, String[] styles, String[] seasons) {
        StringBuffer queryJPQL = new StringBuffer("select i from ItemEntity i where (CONCAT(UPPER(i.title),UPPER(i.category),UPPER(i.color),UPPER(i.style),UPPER(i.season)) LIKE CONCAT('%',UPPER('" + queryStr + "'),'%'))");
        if (categories != null) {
            queryJPQL.append(" AND (");
            for (int i = 0; i < categories.length; i++) {
                queryJPQL.append(" i.category='");
                queryJPQL.append(categories[i]);
                queryJPQL.append("'");
                if (i != categories.length -1) queryJPQL.append(" OR ");                
            }
            queryJPQL.append(" ) ");
        }
        if (colors != null) {
            queryJPQL.append(" AND (");
            for (int i = 0; i < colors.length; i++) {
                queryJPQL.append(" i.color='");
                queryJPQL.append(colors[i]);
                queryJPQL.append("'");
                if (i != colors.length -1) queryJPQL.append(" OR ");                
            }
            queryJPQL.append(" ) ");
        }
        if (styles != null) {
            queryJPQL.append(" AND (");
            for (int i = 0; i < styles.length; i++) {
                queryJPQL.append(" i.style='");
                queryJPQL.append(styles[i]);
                queryJPQL.append("'");
                if (i != styles.length -1) queryJPQL.append(" OR ");                
            }
            queryJPQL.append(" ) ");
        }
        if (seasons != null) {
            queryJPQL.append(" AND (");
            for (int i = 0; i < seasons.length; i++) {
                queryJPQL.append(" i.season='");
                queryJPQL.append(seasons[i]);
                queryJPQL.append("'");
                if (i != seasons.length -1) queryJPQL.append(" OR ");                
            }
            queryJPQL.append(" ) ");
        }
        
        String qeuryString = queryJPQL.toString();
        System.out.println(qeuryString);
        return this.entityManager.createQuery(qeuryString, ItemEntity.class).getResultList();
    }

    @Override
    public List<String> findAllStyles() {
        return this.entityManager.createQuery("SELECT DISTINCT(i.style) from ItemEntity i", String.class).getResultList();
    }

    @Override
    public List<String> findAllColors() {
        return this.entityManager.createQuery("SELECT DISTINCT(i.color) from ItemEntity i", String.class).getResultList();
    }

    @Override
    public List<String> findAllCategories() {
        return this.entityManager.createQuery("SELECT DISTINCT(i.category) from ItemEntity i", String.class).getResultList();
    }

    @Override
    public List<String> findAllSeasons() {
        return this.entityManager.createQuery("SELECT DISTINCT(i.season) from ItemEntity i", String.class).getResultList();
    }
    
}
