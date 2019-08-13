/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import static com.oracle.jrockit.jfr.ContentType.Timestamp;
import glasscat.as1.dao.impl.CartDao;
import glasscat.as1.dao.impl.ItemDao;
import glasscat.as1.dao.impl.RatingDao;
import glasscat.as1.dao.impl.SubTransactionDao;
import glasscat.as1.dao.impl.TransactionDao;
import glasscat.as1.dao.impl.UserDao;
import glasscat.as1.entity.CartEntity;
import glasscat.as1.entity.ItemEntity;
import glasscat.as1.entity.RatingEntity;
import glasscat.as1.entity.SubTransactionEntity;
import glasscat.as1.entity.TransactionEntity;
import glasscat.as1.entity.UserEntity;
import glasscat.as1.service.TransactionService;
import glasscat.as1.util.IDUtil;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author 86185
 */
@Named(value = "detailController")
@ViewScoped
public class DetailController implements Serializable {
    private String productId;
    private ItemEntity itemEntity;
    private List<RatingEntity> ratings;
    private List<String> usernames;
    private float averageMark;
    private Integer count = 1;
    @EJB
    private CartDao cartDao;
    @EJB
    private ItemDao itemDao;
    @EJB
    private RatingDao ratingDao;
    @EJB
    private UserDao userDao;
    @EJB
    private TransactionService transactionService;
    /**
     * Creates a new instance of DetailController
     */
    public DetailController() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    public void itemSearch() {
        // search for item and ratings
        System.out.println(this.productId);
//        itemDao.setClazz(ItemEntity.class);
        this.itemEntity = itemDao.findById(this.productId);
        this.ratings = ratingDao.findRatingsByItemId(this.productId);
        float sum = 0.0f;
        usernames = new ArrayList();
        for(RatingEntity r : ratings) {
            UserEntity u = userDao.findById(r.getUserId());
            usernames.add(u.getUserName());
            sum += r.getMark();
            
        }
        this.averageMark = sum / ratings.size();
    }
    
    public void buy() {
        try {
            transactionService.saveSingleTransaction(this.productId, "1234123454", this.count);
        } catch (Exception ex) {
            Logger.getLogger(DetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addCart() {
//        System.out.println(this.count + "");
        CartEntity cartEntity = new CartEntity();
        cartEntity.setCartId(IDUtil.getUUID());
        cartEntity.setCount(this.count);
        cartEntity.setUserId("12345678");
        cartEntity.setItemId(this.productId);
        cartDao.save(cartEntity);
        System.out.println("successfully add to cart");
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public List<RatingEntity> getRatings() {
        return ratings;
    }
    
    public float getAverageMark() {
        return averageMark;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
    

    public List<String> getUsernames() {
        return usernames;
    }

    
}
