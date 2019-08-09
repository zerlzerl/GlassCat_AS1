/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.service;

import glasscat.as1.dao.impl.ConfigurationDao;
import glasscat.as1.dao.impl.ItemDao;
import glasscat.as1.dao.impl.RatingDao;
import glasscat.as1.dao.impl.SubTransactionDao;
import glasscat.as1.dao.impl.TransactionDao;
import glasscat.as1.dao.impl.UserDao;
import glasscat.as1.entity.ConfigurationEntity;
import glasscat.as1.entity.ItemEntity;
import glasscat.as1.entity.UserEntity;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * This bean is used to insert the initial data into database
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Startup
@Singleton
public class DataInitialization {
    // dao instances
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
    private ConfigurationDao configurationDao;
    
    public DataInitialization() {
        
    }
    
    @PostConstruct
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void init(){
        if (isInitialized()) return;
        System.out.println("############ database initialization");
        
        System.out.println("############ init administrator user information");
        System.out.println("############ init 10 test item");
        System.out.println("############ init 5 single transactions");
        System.out.println("############ init 5 multi transactions");
        System.out.println("############ init 10 test rating");
        System.out.println("############ init labels and interests config");
        
        System.out.println("######## init initialized flag ########");
        ConfigurationEntity initializedFlag = new ConfigurationEntity();
        initializedFlag.setConfigType("SYSTEM");
        initializedFlag.setConfigName("isInitialized");
        initializedFlag.setValue("TRUE");
        configurationDao.save(initializedFlag);
    }
    
    public boolean isInitialized(){
        List<ConfigurationEntity> configs = configurationDao.findByTypeAndName("SYSTEM", "isInitialized");
        if (configs != null && !configs.isEmpty()) {
            String initializedFlag = configs.get(0).getValue();
            if (initializedFlag.equals("TRUE")) {
                return true;
            }
        }
        return false;
    }
    
}
