/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.dao.impl.CartDao;
import glasscat.as1.dao.impl.ItemDao;
import glasscat.as1.entity.CartEntity;
import glasscat.as1.entity.ItemEntity;
import glasscat.as1.util.Constants;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author 86185
 */
@Named(value = "cartController")
@RequestScoped
public class CartController implements Serializable {
    private List<CartEntity> cartRecords;
    private List<ItemEntity> itemEntities;
    private Double total = 0.00d;
    @EJB
    private CartDao cartDao;
    @EJB
    private ItemDao itemDao;
    @Inject
    private LoginController loginController;
    /**
     * Creates a new instance of CartController
     */
    public CartController() {

    }
    
    @PostConstruct
    public void init(){
        System.out.println("cart");
        this.cartRecords = cartDao.findCartRecordsByUserId(loginController.getCurrentUserId());
        
        System.out.println(cartRecords.size());
        if(cartRecords != null && cartRecords.size() > 0) {
            this.itemEntities = new ArrayList<>(cartRecords.size());
            for(CartEntity c : cartRecords) {
                ItemEntity cartItem = itemDao.findById(c.getItemId());
                this.itemEntities.add(cartItem);
                total += cartItem.getPrice() * c.getCount();
            }
        } else {
            System.err.println("Empty cart!");
        }
    }
    public List<CartEntity> getCartRecords() {
        return cartRecords;
    }

    public void setCartRecords(List<CartEntity> cartRecords) {
        this.cartRecords = cartRecords;
    }

    public List<ItemEntity> getItemEntities() {
        return itemEntities;
    }

    public Double getTotal() {
        return total;
    }

    public String removeItem(Integer index) {
        System.out.println("You remove " + index);
        this.cartDao.delete(cartRecords.get(index));
        return "/" + Constants.CART_PAGE + "?faces-redirect=true";
    }
    
    public void checkout() {
        
    }

}
