/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.dao.impl.ItemDao;
import glasscat.as1.entity.ItemEntity;
import glasscat.as1.util.Constants;
import java.util.ArrayList;
import java.util.List;
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
    private List<ItemEntity> sList;
    private List<Double> marks;
    @EJB
    private ItemDao itemDao;
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
        this.sList = queryResult;
    }

    public List<ItemEntity> getSList() {
        return sList;
    }

    public void setSList(ArrayList<ItemEntity> sList) {
        this.sList = sList;
    }
    
    
    
}
