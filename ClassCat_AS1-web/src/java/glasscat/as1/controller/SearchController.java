/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.util.Constants;
import java.util.ArrayList;
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
    private ArrayList<String> sList;
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
        sList = new ArrayList<>();
        sList.add("123");
        sList.add("456");
    }

    public ArrayList<String> getSList() {
        return sList;
    }

    public void setSList(ArrayList<String> sList) {
        this.sList = sList;
    }
    
    
    
}
