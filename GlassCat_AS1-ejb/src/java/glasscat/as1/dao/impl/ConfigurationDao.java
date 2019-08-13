/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.impl;

import glasscat.as1.entity.ConfigurationEntity;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Stateless
public class ConfigurationDao  extends BaseDaoImpl<ConfigurationEntity>{

    public ConfigurationDao() {
        super(ConfigurationEntity.class);
    }
    
    //if any entity specified logic
    
    public List<ConfigurationEntity> findByTypeAndName(String Type, String name){
        return this.entityManager.createNamedQuery("findByTypeAndName", ConfigurationEntity.class)
                .setParameter("type", Type)
                .setParameter("name", name)
                .getResultList();
    }
}
