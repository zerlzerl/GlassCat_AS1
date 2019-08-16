/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.dao.impl.ItemDao;
import glasscat.as1.dao.impl.SubTransactionDao;
import glasscat.as1.dao.impl.UserDao;
import glasscat.as1.entity.ItemEntity;
import glasscat.as1.entity.SubTransactionEntity;
import glasscat.as1.entity.UserEntity;
import glasscat.as1.service.RecommendationEngine;
import glasscat.as1.util.Constants;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author Sun Yeqing
 */
@Named(value = "indexController")
@SessionScoped
public class IndexController implements Serializable {
    private List<ItemEntity> similarityRecomandation;
    private List<ItemEntity> collaborativeFilter;
    @Inject
    private LoginController loginController;
    @EJB
    private RecommendationEngine recommendationEngine;
    /**
     * Creates a new instance of IndexController
     */
    public IndexController() {
    }
    
    @PostConstruct
    public void init() {
        similarityRecomandation = recommendationEngine.similarityRecommendation(loginController.getCurrentUserId());
        collaborativeFilter = recommendationEngine.CollaborativeFilter(loginController.getCurrentUserId());
    }
    
    public String cart(){
        return "/" + Constants.CART_PAGE + "?faces-redirect=true";
    }
    
    public String personalCenter() {
        return "/" + Constants.ADMIN_PAGE + "?faces-redirect=true";
    }

    public List<ItemEntity> getSimilarityRecomandation() {
        return similarityRecomandation;
    }

    public List<ItemEntity> getCollaborativeFilter() {
        return collaborativeFilter;
    }
    

}
