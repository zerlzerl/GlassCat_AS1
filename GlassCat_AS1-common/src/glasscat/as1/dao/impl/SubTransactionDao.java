/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.impl;

import glasscat.as1.dao.Dao;
import glasscat.as1.entity.SubTransactionEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Remote
public interface SubTransactionDao extends Dao<SubTransactionEntity> {

    public List<SubTransactionEntity> findSubTransactionRecordsByUserId(String currentUserId);
    
}
