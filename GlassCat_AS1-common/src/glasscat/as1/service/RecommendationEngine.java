/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.service;

import glasscat.as1.entity.ItemEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Remote
public interface RecommendationEngine {
    public List<ItemEntity> similarityRecommendation(String userId);
    public List<ItemEntity> CollaborativeFilter(String userId);
}
