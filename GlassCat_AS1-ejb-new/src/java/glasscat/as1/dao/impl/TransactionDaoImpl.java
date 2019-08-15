/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.impl;

import glasscat.as1.entity.TransactionEntity;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Stateless
public class TransactionDaoImpl extends BaseDaoImpl<TransactionEntity> implements TransactionDao{
    
    public TransactionDaoImpl() {
        super(TransactionEntity.class);
    }

    @Override
    public TransactionEntity findLatestTransactionByUserId(String userId) {
        List<TransactionEntity> found = this.entityManager.createQuery("SELECT t FROM TransactionEntity t WHERE t.userId=:userId ORDER BY T.transactionDatetime DESC", TransactionEntity.class)
                .setParameter("userId", userId)
                .getResultList();
        if (found != null && !found.isEmpty()) {
            return found.get(0);
        } else {
            return null;
        }
    }
    
}
