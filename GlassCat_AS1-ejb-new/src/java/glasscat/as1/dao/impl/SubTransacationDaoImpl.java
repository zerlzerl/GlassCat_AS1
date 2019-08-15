/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.impl;

import glasscat.as1.entity.SubTransactionEntity;
import glasscat.as1.entity.TransactionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Stateless
public class SubTransacationDaoImpl extends BaseDaoImpl<SubTransactionEntity> implements SubTransactionDao {

    public SubTransacationDaoImpl() {
        super(SubTransactionEntity.class);
    }

    @Override
    public List<SubTransactionEntity> findSubTransactionRecordsByUserId(String userId) {
        List<SubTransactionEntity> subTransactionEntities = new ArrayList<>();
        List<TransactionEntity> transactionEntities = this.entityManager.createNamedQuery("findTransactionRecordsByUserId", TransactionEntity.class)
                .setParameter("userId", userId)
                .getResultList();
        if(transactionEntities != null && transactionEntities.size() != 0) {
            for(TransactionEntity t : transactionEntities) {
                List<SubTransactionEntity> subTransactionsFound = this.entityManager.createNamedQuery("findSubTransactionRecordsByTransactionId", SubTransactionEntity.class)
                    .setParameter("transactionId", t.getTransactionId())
                    .getResultList();
                for(SubTransactionEntity s : subTransactionsFound) {
                    subTransactionEntities.add(s);
                }
            }
            return subTransactionEntities;
        }
        return null;
    }

    @Override
    public List<SubTransactionEntity> findSubTransactionByTransactionId(String transactionId) {
        return this.entityManager.createQuery("SELECT s from SubTransactionEntity s WHERE s.transactionId=:transactionId", SubTransactionEntity.class)
                .setParameter("transactionId", transactionId)
                .getResultList();
    }
}
