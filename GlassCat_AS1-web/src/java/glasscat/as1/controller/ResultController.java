/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Sun Yeqing
 */
@Named(value = "resultController")
@RequestScoped
public class ResultController {
    private String queryStr;
    
    /**
     * Creates a new instance of ResultController
     */
    public ResultController() {
    }

    public String getQueryStr() {
        return queryStr;
    }

    public void setQueryStr(String queryStr) {
        this.queryStr = queryStr;
    }
    
    
}
