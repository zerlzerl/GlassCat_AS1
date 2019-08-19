/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.impl;

import glasscat.as1.dao.Dao;
import glasscat.as1.entity.ItemEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Remote
public interface ItemDao extends Dao<ItemEntity> { 
    public List<ItemEntity> findByTitleLike(String queryStr);
    public List<ItemEntity> findByAttributesLike(String queryStr);
    public List<ItemEntity> findBy3Attributes(String title, String type, Double low, Double high);
    public List<ItemEntity> findX(Integer x);
    public List<ItemEntity> findByAttributesLikeWithCritiria(String queryStr, String[] categories, String[] colors, String[] styles, String[] seasons);
    public List<String> findAllStyles();
    public List<String> findAllColors();
    public List<String> findAllCategories();
    public List<String> findAllSeasons();
    
}
