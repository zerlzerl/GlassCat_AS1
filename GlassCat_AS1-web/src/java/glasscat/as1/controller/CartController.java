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
import glasscat.as1.service.TransactionService;
import glasscat.as1.util.Constants;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Sun Yeqing
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
    @EJB
    private TransactionService transactionService;
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
    
    public String checkout() {
        if(this.getCartRecords().size() > 0) {
            try {
                Map<String, Integer> cartMap = new HashMap<String, Integer>(this.getCartRecords().size());
                for(CartEntity cartEntity : this.cartRecords) {
                    cartMap.put(cartEntity.getItemId(), cartEntity.getCount());
                }
                transactionService.saveMultiTransaction(cartMap, loginController.getCurrentUserId());
                for(CartEntity cartEntity : this.cartRecords) {
                    this.cartDao.delete(cartEntity);
                }
            } catch (Exception ex) {
                Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "/" + Constants.CART_PAGE + "?faces-redirect=true";
    }

}
