/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.dao.impl.ItemDao;
import glasscat.as1.entity.ItemEntity;
import glasscat.as1.util.Constants;
import glasscat.as1.util.StringUtil;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Named(value = "addItemController")
@RequestScoped
public class AddItemController {
@EJB
    private ItemDao itemDao;
    
    // page resources
    private String itemURL;
    private String title;
    private String imageUrl;
    private String imageUrlThumb;
    private String category;
    private Double price;
    private Integer stock;
    private String color;
    private String style;
    private String season;
    private String extend1;
    private String extend2;
    private String extend3;
    /**
     * Creates a new instance of EditItemController
     */
    public AddItemController() {
    }
    
    
    
    public String save(){
        ItemEntity item = new ItemEntity();
        item.setId("");
        item.setTitle(title);
        item.setImageUrl(imageUrl);
        item.setImageUrlThumb(imageUrlThumb);
        item.setCategory(category);
        item.setPrice(price);
        item.setStock(stock);
        item.setColor(color);
        item.setStyle(style);
        item.setSeason(season);
        item.setExtend1(extend1);
        item.setExtend2(extend2);
        item.setExtend3(extend3);
        itemDao.update(item);
        System.out.println("save");
        return "/" + Constants.DETAIL_PAGE + "?faces-redirect=true";
    }

    public String getItemURL() {
        return itemURL;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrlThumb() {
        return imageUrlThumb;
    }

    public void setImageUrlThumb(String imageUrlThumb) {
        this.imageUrlThumb = imageUrlThumb;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }
    
    
}
