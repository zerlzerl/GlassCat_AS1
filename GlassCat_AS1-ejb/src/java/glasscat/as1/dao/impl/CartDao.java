/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.impl;

import glasscat.as1.entity.CartEntity;
import javax.ejb.Stateless;

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
    
}
