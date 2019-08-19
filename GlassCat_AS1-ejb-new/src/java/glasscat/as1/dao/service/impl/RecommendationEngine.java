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
import glasscat.as1.entity.RatingEntity;
import glasscat.as1.entity.SubTransactionEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
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
    
    // item label weight
    private static final double colorWeight = 1.00001;
    private static final double styleWeight = 1.00002;
    private static final double categoryWeight = 1.00003;
    private static final double seasonWeight = 1.00004;
    
    // item label pool
    List<String> colorLabels;
    List<String> styleLabels;
    List<String> categoryLabels;
    List<String> seasonLabels;
    // all items
    private static List<ItemEntity> itemEntityPool;
    
    @PostConstruct
    public void init(){
        colorLabels = itemDao.findAllColors();
        styleLabels = itemDao.findAllStyles();
        categoryLabels = itemDao.findAllCategories();
        seasonLabels = itemDao.findAllSeasons();
        itemEntityPool = itemDao.findAll();
    } 
    
    @Override
    public List<ItemEntity> similarityRecommendation(String userId) {   
        // search the historical purchase of the user
        List<SubTransactionEntity> historyPurchase = subTransactionDao.findSubTransactionRecordsByUserId(userId);
//        System.out.println("history purchase count: " + historyPurchase.size());
        if (historyPurchase == null) {
            return itemDao.findX(RECOMMEND_NUMBER);
        }
        // search the historical rating of the user
        // seperate commented purchase and uncommented
        List<RatingEntity> historyRating = new ArrayList<>();
        List<SubTransactionEntity> historyPurchaseWithoutRating = new ArrayList<>();
        for (SubTransactionEntity sb : historyPurchase) {
            RatingEntity rating = ratingDao.findRatingBySubTransactionId(sb.getSubtransactionId());
            if (rating == null) {
                historyPurchaseWithoutRating.add(sb);
            } else {
                historyRating.add(rating);
            }
        }
        System.out.println("colorLabels:" + colorLabels.size());
        System.out.println("styleLabels:" + styleLabels.size());
        System.out.println("categoryLabels:" + categoryLabels.size());
        System.out.println("seasonLabels:" + seasonLabels.size());
        
        
        // vote scores
        HashMap<String, Double> colorPool = new HashMap<>();
        for (String c : colorLabels) { colorPool.put(c, 0.0); }
        HashMap<String, Double> stylePool = new HashMap<>();
        for (String c : styleLabels) { stylePool.put(c, 0.0); }
        HashMap<String, Double> categoryPool = new HashMap<>();
        for (String c : categoryLabels) { categoryPool.put(c, 0.0); }
        HashMap<String, Double> seasonPool = new HashMap<>();
        for (String c : seasonLabels) { seasonPool.put(c, 0.0); }   
        
        // calculate rating vote
        if (!historyRating.isEmpty()) {
            for (RatingEntity re : historyRating) {
                ItemEntity item = itemDao.findById(re.getItemId());
                double colorScore = colorPool.get(item.getColor());
                double styleScore = stylePool.get(item.getStyle());
                double categoryScore = categoryPool.get(item.getCategory());
                double seasonScore = seasonPool.get(item.getSeason());

                colorPool.put(item.getColor(), colorScore + colorWeight * re.getMark());
                stylePool.put(item.getStyle(), styleScore + styleWeight * re.getMark());
                categoryPool.put(item.getCategory(), categoryScore + categoryWeight * re.getMark());
                seasonPool.put(item.getSeason(), seasonScore + seasonWeight * re.getMark());
            }
        }
        // calculate un rating vote
        if(!historyPurchaseWithoutRating.isEmpty()){
            for (SubTransactionEntity sb : historyPurchaseWithoutRating) {
                ItemEntity item = itemDao.findById(sb.getItemId());
                double colorScore = colorPool.get(item.getColor());
                double styleScore = stylePool.get(item.getStyle());
                double categoryScore = categoryPool.get(item.getCategory());
                double seasonScore = seasonPool.get(item.getSeason());

                colorPool.put(item.getColor(), colorScore + colorWeight * 4);
                stylePool.put(item.getStyle(), styleScore + styleWeight * 4);
                categoryPool.put(item.getCategory(), categoryScore + categoryWeight * 4);
                seasonPool.put(item.getSeason(), seasonScore + seasonWeight * 4);
            }
        }
        
        Map<ItemEntity, Double> item2Score = new HashMap();
        for (ItemEntity item : itemEntityPool) {
            Double itemScore = colorPool.get(item.getColor())
                    + stylePool.get(item.getStyle())
                    + categoryPool.get(item.getCategory())
                    + seasonPool.get(item.getSeason());
            item2Score.put(item, itemScore);
        }
        
        // sort map by value
        LinkedHashMap<ItemEntity, Double> sortedMap = new LinkedHashMap<>();
        item2Score.entrySet().stream()
                .sorted(Map.Entry.<ItemEntity, Double>comparingByValue().reversed())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        
        List<ItemEntity> itemList = sortedMap.keySet().stream().collect(Collectors.toList());  
        
        List<ItemEntity> recommendations = new ArrayList<>();
        for (int i = 0; i < RECOMMEND_NUMBER; i++) {
            recommendations.add(itemList.get(i));
        }
        // search the 
         return recommendations;
    }

    @Override
    public List<ItemEntity> CollaborativeFilter(String userId) {
        return itemDao.findX(RECOMMEND_NUMBER);
    }
    
}
