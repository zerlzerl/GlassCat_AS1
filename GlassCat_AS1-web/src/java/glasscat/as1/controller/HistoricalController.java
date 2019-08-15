/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.dao.impl.ItemDao;
import glasscat.as1.dao.impl.RatingDao;
import glasscat.as1.dao.impl.SubTransactionDao;
import glasscat.as1.dao.impl.TransactionDao;
import glasscat.as1.entity.ItemEntity;
import glasscat.as1.entity.RatingEntity;
import glasscat.as1.entity.SubTransactionEntity;
import glasscat.as1.entity.TransactionEntity;
import glasscat.as1.util.Constants;
import glasscat.as1.util.IDUtil;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author 86185
 */
@Named(value = "historicalController")
@RequestScoped
public class HistoricalController {
private List<SubTransactionEntity> subTransactionRecords;
private List<ItemEntity> itemEntities;
private List<TransactionEntity> transactionEntities;
private List<RatingEntity> ratingEntities;
private List<Integer> marks;
private List<String> comments;
private List<Date> ratingDates;
@EJB
private RatingDao ratingDao;
@EJB
private SubTransactionDao subTransactionDao;
@EJB
private TransactionDao transactionDao;
@EJB
private ItemDao itemDao;
@Inject
private LoginController loginController;

    /**
     * Creates a new instance of HistoricalController
     */
    public HistoricalController() {
    }
    
    @PostConstruct
    public void init(){
        System.out.println("history");
        this.subTransactionRecords = subTransactionDao.findSubTransactionRecordsByUserId(loginController.getCurrentUserId());
        
        System.out.println(subTransactionRecords.size());
        // the user has bought some clothes
        if(subTransactionRecords != null && subTransactionRecords.size() > 0) {
            this.itemEntities = new ArrayList<>(subTransactionRecords.size()); // can't be null
            this.transactionEntities = new ArrayList<>(subTransactionRecords.size());// can't be null
            this.ratingEntities = new ArrayList<>(subTransactionRecords.size());// can be null
            this.marks = new ArrayList<>(subTransactionRecords.size());// can be null
            this.comments = new ArrayList<>(subTransactionRecords.size());// can be null
            this.ratingDates = new ArrayList<>(subTransactionRecords.size());// can be null
            
            for(SubTransactionEntity s : subTransactionRecords) {
                ItemEntity itemEntity = itemDao.findById(s.getItemId());
                TransactionEntity transactionEntity = transactionDao.findById(s.getTransactionId());
                RatingEntity ratingEntity = ratingDao.findRatingBySubTransactionId(s.getSubtransactionId());
                this.ratingEntities.add(ratingEntity);
                this.itemEntities.add(itemEntity);
                this.transactionEntities.add(transactionEntity);
                if(ratingEntity != null) {
                    this.marks.add(ratingEntity.getMark());
                    this.comments.add(ratingEntity.getComment());
                    this.ratingDates.add(ratingEntity.getRatingDate());
                } else {
                    this.marks.add(null);
                    this.comments.add(null);
                    this.ratingDates.add(null);
                }
            }
        } else {
            System.err.println("No history!");
        }
    }
    
    public String addRating(Integer index) {
        String subTransactionId = this.subTransactionRecords.get(index).getSubtransactionId();
        String itemId = this.itemEntities.get(index).getId();
//        System.out.println(index);
//        System.out.println(subTransactionId);
//        System.out.println(itemId);
        RatingEntity ratingEntity = new RatingEntity();
//        System.out.println("comment:" + this.comments.get(index));
//        System.out.println("mark:" + this.marks.get(index));
//        System.out.println((this.marks.get(index) instanceof Integer));
//        System.out.println("The type is " + this.comments.get(index).getClass().getName());
//        System.out.println((this.marks.get(index) instanceof String));
        ratingEntity.setComment(this.comments.get(index));
        // The nature of Java generic type information is present during compiletime only and thus completely absent during runtime, 
        // and EL expressions is evaluated during runtime only and thus not during compiletime.
        // Here, the EL infers the mark as a String, but it should be an Integer to be inserted into the database.
        Object tmp = this.marks.get(index);
        String t = (String)tmp;
        ratingEntity.setMark(Integer.parseInt(t));
        ratingEntity.setItemId(itemId);
        ratingEntity.setRatingId(IDUtil.getUUID());
        ratingEntity.setSubTransactionId(subTransactionId);
        ratingEntity.setUserId(loginController.getCurrentUserId());
        ratingEntity.setRatingDate(new java.sql.Date(System.currentTimeMillis()));
        ratingDao.save(ratingEntity);
        return "/" + Constants.HISTORY_PAGE + "?faces-redirect=true";
    }

    public List<SubTransactionEntity> getSubTransactionRecords() {
        return subTransactionRecords;
    }

    public void setSubTransactionRecords(List<SubTransactionEntity> subTransactionRecords) {
        this.subTransactionRecords = subTransactionRecords;
    }

    public List<ItemEntity> getItemEntities() {
        return itemEntities;
    }

    public void setItemEntities(List<ItemEntity> itemEntities) {
        this.itemEntities = itemEntities;
    }

    public List<TransactionEntity> getTransactionEntities() {
        return transactionEntities;
    }

    public void setTransactionEntities(List<TransactionEntity> transactionEntities) {
        this.transactionEntities = transactionEntities;
    }

    public List<RatingEntity> getRatingEntities() {
        return ratingEntities;
    }

    public void setRatingEntities(List<RatingEntity> ratingEntities) {
        this.ratingEntities = ratingEntities;
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public void setMarks(List<Integer> marks) {
        this.marks = marks;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<Date> getRatingDates() {
        return ratingDates;
    }



    
}
