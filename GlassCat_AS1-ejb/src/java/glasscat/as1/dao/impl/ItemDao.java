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
public class ItemDao extends BaseDaoImpl<ItemEntity>{
    //if any entity specified logic
    
    /**
     * This method is used to fuzzy query database item table by user's input
     * @param queryStr
     * @return 
     */
    public List<ItemEntity> findByTitleLike(String queryStr){
        return this.entityManager.createNamedQuery("findByTitleLike", ItemEntity.class)
                .setParameter("title", queryStr)
                .getResultList();
    }
}
