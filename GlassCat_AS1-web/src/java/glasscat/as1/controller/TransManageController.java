/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.dao.impl.ItemDao;
import glasscat.as1.dao.impl.SubTransactionDao;
import glasscat.as1.dao.impl.TransactionDao;
import glasscat.as1.dao.impl.UserDao;
import glasscat.as1.entity.ItemEntity;
import glasscat.as1.entity.SubTransactionEntity;
import glasscat.as1.entity.TransactionEntity;
import glasscat.as1.entity.UserEntity;
import glasscat.as1.util.Constants;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Named(value = "transManageController")
@ViewScoped
public class TransManageController implements Serializable{
    private List<TransTable> transList;
    @EJB
    private TransactionDao transactionDao;
    @EJB
    private SubTransactionDao subTransactionDao;
    @EJB 
    private UserDao userDao;
    @EJB
    private ItemDao itemDao;
    /**
     * Creates a new instance of TransManageController
     */
    public TransManageController() {
    }
    
    @PostConstruct
    public void init(){
        transList = new ArrayList<>();
        List<TransactionEntity> trans = transactionDao.findLatestXTransactions(100);
        if (trans == null || trans.isEmpty()) {
            return;
        }
        for (TransactionEntity tran : trans) {
            TransTable transTable = new TransTable();
            transTable.setTransId(tran.getTransactionId());
            transTable.setTransDateTime(new SimpleDateFormat(Constants.DATETIME_FORMAT).format(new Date(tran.getTransactionDatetime().getTime())));
            
            UserEntity user = userDao.findById(tran.getUserId());
            if(user != null){
                transTable.setUserEmail(user.getEmail());
                transTable.setUserName(user.getUserName());
            }
            
            List<SubTransactionEntity> subtrans = subTransactionDao.findSubTransactionByTransactionId(tran.getTransactionId());
            if (subtrans != null && !subtrans.isEmpty()) {
                for (SubTransactionEntity subtran : subtrans) {
                    transTable.setSubtransId(subtran.getSubtransactionId());
                    ItemEntity item = itemDao.findById(subtran.getItemId());
                    transTable.setItemId(item.getId());
                    transTable.setItemTitle(item.getTitle());
                }
            }
            transList.add(transTable);
        }
    }

    public List<TransTable> getTransList() {
        return transList;
    }

    public void setTransList(List<TransTable> transList) {
        this.transList = transList;
    }
    
    public static class TransTable {
        private String transId;
        private String transDateTime;
        private String subtransId;
        private String userName;
        private String userEmail;
        private String itemId;
        private String itemTitle;

        public String getTransId() {
            return transId;
        }

        public void setTransId(String transId) {
            this.transId = transId;
        }

        public String getSubtransId() {
            return subtransId;
        }

        public String getTransDateTime() {
            return transDateTime;
        }

        public void setTransDateTime(String transDateTime) {
            this.transDateTime = transDateTime;
        }

        public void setSubtransId(String subtransId) {
            this.subtransId = subtransId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getItemTitle() {
            return itemTitle;
        }

        public void setItemTitle(String itemTitle) {
            this.itemTitle = itemTitle;
        }
        
    }
}
