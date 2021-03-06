/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.impl;

import glasscat.as1.entity.CartEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author 86185
 */
@Stateless
public class CartDao extends BaseDaoImpl<CartEntity>{
    
    public CartDao() {
        super(CartEntity.class);
    }
    
    //if any entity specified logic
    public List<CartEntity> findCartRecordsByUserId(String userId) {
        return this.entityManager.createNamedQuery("findCartRecordsByUserId", CartEntity.class)
                .setParameter("userId", userId)
                .getResultList();
    }
    
    public CartEntity findCartByItemIdAndUserId(String ItemId, String userId) {
        try {
            List<CartEntity> found =  this.entityManager.createQuery("SELECT c from CartEntity c where c.userId = :userId AND c.itemId = :itemId", CartEntity.class)
                .setParameter("userId", userId)
                .setParameter("itemId", ItemId)
                .getResultList();
            return found.isEmpty() ? null : found.get(0);
        } catch (NoResultException e) {
            return null;
        }
        
//        return this.entityManager.createNamedQuery("findCartByItemIdAndUserId", CartEntity.class)
//                .setParameter("userId", userId)
//                .setParameter("itemId", ItemId)
//                .getSingleResult();
    }
    public Integer cartCombine (String ItemId, String userId) {
        CartEntity cart = this.findCartByItemIdAndUserId(ItemId, userId);
        if (cart == null) {
            return 0;
        } else {
            return cart.getCount();
        }
    }
    
    @Override
    public void save(CartEntity inputCart) {
        CartEntity cart = this.findCartByItemIdAndUserId(inputCart.getItemId(), inputCart.getUserId());
        if (cart != null) { 
            cart.setCount(cart.getCount() + inputCart.getCount());
            super.update(cart);
        } else {
            super.save(inputCart);
        }
    }
    
    
}
