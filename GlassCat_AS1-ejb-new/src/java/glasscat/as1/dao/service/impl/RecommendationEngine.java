/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.service.impl;

import glasscat.as1.dao.impl.CartDao;
import glasscat.as1.dao.impl.ConfigurationDao;
import glasscat.as1.dao.impl.ItemDao;
import glasscat.as1.dao.impl.RatingDao;
import glasscat.as1.dao.impl.SubTransactionDao;
import glasscat.as1.dao.impl.TransactionDao;
import glasscat.as1.dao.impl.UserDao;
import glasscat.as1.entity.ItemEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Singleton
@Startup
public class RecommendationEngine implements glasscat.as1.service.RecommendationEngine {
    private static final int RECOMMEND_NUMBER = 8;
    @EJB
    private UserDao userDao;
    @EJB
    private ItemDao itemDao;    
    @EJB
    private TransactionDao transactionDao;
    @EJB
    private SubTransactionDao subTransactionDao;
    @EJB
    private RatingDao ratingDao;
    @EJB
    private CartDao cartDao;
    
    @Override
    public List<ItemEntity> similarityRecommendation(String userId) {
         return itemDao.findX(RECOMMEND_NUMBER);
    }

    @Override
    public List<ItemEntity> CollaborativeFilter(String userId) {
        return itemDao.findX(RECOMMEND_NUMBER);
    }
    
}
