/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.dao.impl.ItemDao;
import glasscat.as1.entity.ItemEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Named(value = "itemManageController")
@ViewScoped
public class ItemManageController implements Serializable{
    private List<ItemEntity> allItems;
    private boolean [] editableList;
    @EJB private ItemDao itemDao;
    /**
     * Creates a new instance of ItemManageController
     */
    public ItemManageController() {
    }
    
    @PostConstruct
    public void init(){
        this.allItems = itemDao.findAll();
        this.editableList = new boolean[this.allItems.size()];
        for (int i = 0 ; i < this.allItems.size(); i ++) {
            this.editableList[i] = false;
        }
    }
    
    public List<ItemEntity> getAllItems() {
        return allItems;
    }

    public boolean[] getEditableList() {
        return editableList;
    }
    
}
