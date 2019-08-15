/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.impl;

import glasscat.as1.entity.ItemEntity;
import java.util.List;
import javax.ejb.Stateless;

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
    
}
