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
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Named(value = "userManagementController")
@ViewScoped
public class UserManagementController implements Serializable{
    // bind page query criteria
    private String s = "123";
    private String userId;
    private String userEmail;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private List<UserEntity> resultUsers;
    private UserEntity userForModal;
    private String purchaseTime = "No purchase record!";
    private List<ItemEntity> itemsForModal;
    private String userNameForModal;
    private String userAddressForModal;
    private boolean isRenderPurchase;
    @EJB
    private UserDao userDao;
    @EJB
    private TransactionDao transactionDao;
    @EJB
    private SubTransactionDao subTransactionDao;
    @EJB
    private ItemDao itemDao;
    @Inject
    private LoginController lc;
    
    /**
     * Creates a new instance of UserManagementController
     */
    public UserManagementController() {
    }
    
    @PostConstruct
    public void init(){
        this.resultUsers = new ArrayList();
        this.resultUsers = userDao.findBy5Attributes(userId, userEmail, firstName, lastName, phoneNo, this.lc.getMembershipLevel());
    }
    
    public String executeSearch(){
        System.out.println("search!");
        this.resultUsers = new ArrayList();
        this.resultUsers = userDao.findBy5Attributes(userId, userEmail, firstName, lastName, phoneNo, this.lc.getMembershipLevel());
        System.out.println(this.resultUsers.size());
        return "/" + Constants.USER_MANAGE_PAGE  + "?faces-redirect=true";
    }
    
    public void delete(String userId) {
        System.out.println("Delete user: " + userId);
        userDao.deleteById(userId);
    } 
    
    public void downgrade(String userId){
        System.out.println("Downgrade user: " + userId);
        UserEntity user = userDao.findById(userId);
        if (user.getMembershipLevel() > 0) {
            user.setMembershipLevel(user.getMembershipLevel() - 1);
        }
        userDao.update(user);
    }
    
    public void upgrade(String userId){
        System.out.println("Upgrade user: " + userId);
        UserEntity user = userDao.findById(userId);
        if (user.getMembershipLevel() < 2) {
            user.setMembershipLevel(user.getMembershipLevel() + 1);
        }
        userDao.update(user);
    }
    
    public void searchInfo(String userId) {
        s = userId;
        System.out.println("Search user Info: " + userId);
        UserEntity u = userDao.findById(userId);
        if (u!= null) {
            this.userForModal = u;
            this.userAddressForModal = u.getAddress();
            this.userNameForModal= u.getUserName();
            
        }
        System.out.println(u.getUserName());
        TransactionEntity latestTrans = transactionDao.findLatestTransactionByUserId(userId);
        if (latestTrans == null) {
            this.isRenderPurchase = false;
            return;
        }
        
        this.purchaseTime = new SimpleDateFormat(Constants.DATETIME_FORMAT).format(new Date(latestTrans.getTransactionDatetime().getTime()));
        List<SubTransactionEntity> latestSubtrans = subTransactionDao.findSubTransactionByTransactionId(latestTrans.getTransactionId());
        if (latestSubtrans != null && !latestSubtrans.isEmpty()) {
            this.itemsForModal = new ArrayList();
            for (SubTransactionEntity st : latestSubtrans) {
                this.itemsForModal.add(itemDao.findById(st.getItemId()));
            }
        }
    }

    public String getUserNameForModal() {
        return userNameForModal;
    }

    public void setUserNameForModal(String userNameForModal) {
        this.userNameForModal = userNameForModal;
    }

    public String getUserAddressForModal() {
        return userAddressForModal;
    }

    public void setUserAddressForModal(String userAddressForModal) {
        this.userAddressForModal = userAddressForModal;
    }

    public void update(){
        userForModal.setAddress(this.userAddressForModal);
        userForModal.setUserName(this.userNameForModal);
        userDao.update(userForModal);
    }
    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<UserEntity> getResultUsers() {
        return resultUsers;
    }

    public UserEntity getUserForModal() {
        return userForModal;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public List<ItemEntity> getItemsForModal() {
        return itemsForModal;
    }

    public boolean isIsRenderPurchase() {
        return isRenderPurchase;
    }
    
    
    
}
