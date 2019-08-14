/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.impl;

import glasscat.as1.entity.RatingEntity;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Stateless
public class RatingDao  extends BaseDaoImpl<RatingEntity>{
    
    public RatingDao(){
        super(RatingEntity.class);
    }
    //if any entity specified logic
    public List<RatingEntity> findRatingsByItemId(String itemId) {
        return this.entityManager.createNamedQuery("findRatingsByItemId", RatingEntity.class)
                .setParameter("itemId", itemId)
                .getResultList();
    }
    
    public double findAverageMarkByItemId(String itemId) {
        List<BigDecimal> found = this.entityManager.createNamedQuery("findAverageMarkByItemId", BigDecimal.class)
                .setParameter("itemId", itemId)
                .getResultList();
        if (found.isEmpty()) {
            return 0.0;
        } else {
            return found.get(0).doubleValue();
        }
    }
}
