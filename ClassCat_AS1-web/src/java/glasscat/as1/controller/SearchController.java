/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.dao.impl.ItemDao;
import glasscat.as1.dao.impl.RatingDao;
import glasscat.as1.entity.ItemEntity;
import glasscat.as1.util.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Named(value = "searchController")
@RequestScoped
public class SearchController {
    private String searchStr;
    private List<ItemEntity> itemList;
    private List<Double> itemMarks;
    private Map<ItemEntity, Double> item2Marks;
    private Map<ItemEntity, Double> sortedMap;
    @EJB
    private ItemDao itemDao;
    @EJB
    private RatingDao ratingDao;
    /**
     * Creates a new instance of SearchController
     */
    public SearchController() {
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }
    
    public String resultPage(){
        return "/" + Constants.RESULT_PAGE + "?faces-redirect=true&s=" + searchStr;
    }
    
    public void excuteSearch(){
        String queryStr = this.searchStr;
        List<ItemEntity> queryResult = itemDao.findByAttributesLike(queryStr);
        item2Marks = new HashMap<>((int) (queryResult.size() / 0.75));
        
        for (ItemEntity result : queryResult) {
            //get the average mark of item
            item2Marks.put(result, ratingDao.findAverageMarkByItemId(result.getId()));
        }
        
        // sort hashmap by value
        sortedMap = new LinkedHashMap<>();
        item2Marks.entrySet().stream()
                .sorted(Map.Entry.<ItemEntity, Double>comparingByValue().reversed())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        
        this.itemList = sortedMap.keySet().stream().collect(Collectors.toList());  
        this.itemMarks = sortedMap.values().stream().collect(Collectors.toList()); 
        
    }

    public List<ItemEntity> getItemList() {
        return itemList;
    }

    public List<Double> getItemMarks() {
        return itemMarks;
    }
    

    public Map<ItemEntity, Double> getSortedMap() {
        return sortedMap;
    }

    
    

    
}
