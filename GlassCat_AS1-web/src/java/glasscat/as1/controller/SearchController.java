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
import glasscat.as1.util.StringUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Named(value = "searchController")
@ViewScoped
public class SearchController implements Serializable{
    // url params
    private String searchStr;
    private String category;
    private String style;
    private String color;
    private String season;
    
    // select arrtibutes
    private Set<String> categorylList;
    private Set<String> styleList;
    private Set<String> colorList;
    private Set<String> seasonList;
    
    // show results
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
//        System.out.println("s:" + searchStr);
        System.out.println("category:" + category);
//        System.out.println("color:" + color);
        return "/" + Constants.RESULT_PAGE + "?faces-redirect=true&s=" + searchStr
                + (StringUtil.isBlank(category) ? "" : "&category=" + category)
                + (StringUtil.isBlank(color) ? "" : "&color=" + color)
                + (StringUtil.isBlank(style) ? "" : "&style=" + style)
                + (StringUtil.isBlank(season) ? "" : "&season=" + season)
                ;
    }
    
    public void excuteSearch(){
        // search criteria
        String queryStr = this.searchStr;
        String [] categoris = null;
        String [] colors = null;
        String [] styles = null;
        String [] seasons = null;
        
        if (!StringUtil.isBlank(category)) categoris = this.category.split(",");
        if (!StringUtil.isBlank(color)) colors = this.color.split(",");
        if (!StringUtil.isBlank(style)) styles = this.style.split(",");
        if (!StringUtil.isBlank(season)) seasons = this.season.split(",");
        
        //search items which satisfy our criteria
        List<ItemEntity> queryResult = itemDao.findByAttributesLikeWithCritiria(queryStr, categoris, colors, styles, seasons);
        
        
        // select item category attributes
        categorylList = new HashSet();
        styleList = new HashSet();
        colorList = new HashSet();
        seasonList = new HashSet();
        for (ItemEntity item : queryResult) {
            categorylList.add(item.getCategory());
            styleList.add(item.getStyle());
            colorList.add(item.getColor());
            seasonList.add(item.getSeason());
        }
        
        // calculate population and resort
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Set<String> getCategorylList() {
        return categorylList;
    }

    public void setCategorylList(Set<String> categorylList) {
        this.categorylList = categorylList;
    }

    public Set<String> getStyleList() {
        return styleList;
    }

    public void setStyleList(Set<String> styleList) {
        this.styleList = styleList;
    }

    public Set<String> getColorList() {
        return colorList;
    }

    public void setColorList(Set<String> colorList) {
        this.colorList = colorList;
    }

    public Set<String> getSeasonList() {
        return seasonList;
    }

    public void setSeasonList(Set<String> seasonList) {
        this.seasonList = seasonList;
    }
    
    
    
    

    
}
