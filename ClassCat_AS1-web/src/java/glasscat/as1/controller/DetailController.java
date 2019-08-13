/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.dao.impl.ItemDao;
import glasscat.as1.dao.impl.RatingDao;
import glasscat.as1.dao.impl.UserDao;
import glasscat.as1.entity.ItemEntity;
import glasscat.as1.entity.RatingEntity;
import glasscat.as1.entity.UserEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
//    private List<String> ratingUserNames;
    
    @EJB
    private ItemDao itemDao;
    @EJB
    private RatingDao ratingDao;
    @EJB
    private UserDao userDao;
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

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public List<RatingEntity> getRatings() {
        return ratings;
    }
    
    public float getAverageMark() {
        return averageMark;
    }
    
//    public String getRatingUserName() {
//        return ratingUserName;
//    }

    public List<String> getUsernames() {
        return usernames;
    }

    
}
