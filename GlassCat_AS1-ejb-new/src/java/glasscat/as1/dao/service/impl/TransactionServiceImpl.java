/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.service.impl;

import glasscat.as1.dao.impl.ItemDao;
import glasscat.as1.dao.impl.SubTransactionDao;
import glasscat.as1.dao.impl.TransactionDao;
import glasscat.as1.entity.ItemEntity;
import glasscat.as1.entity.SubTransactionEntity;
import glasscat.as1.entity.TransactionEntity;
import glasscat.as1.util.IDUtil;
import java.sql.Timestamp;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import glasscat.as1.service.TransactionService;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Stateless
public class TransactionServiceImpl implements TransactionService {
    @EJB
    private TransactionDao transactionDao;
    @EJB
    private SubTransactionDao subTransactionDao;
    @EJB
    private ItemDao itemDao;
    @Override
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public void saveSingleTransaction(String itemId, String userId, Integer count) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(IDUtil.getUUID());
        transactionEntity.setUserId(userId);
        transactionEntity.setTransactionDatetime(new Timestamp(System.currentTimeMillis()));
        transactionDao.save(transactionEntity);
        
        SubTransactionEntity subTransactionEntity = new SubTransactionEntity();
        subTransactionEntity.setSubtransactionId(IDUtil.getUUID());
        subTransactionEntity.setTransactionId(transactionEntity.getTransactionId());
        subTransactionEntity.setItemId(itemId);
        subTransactionEntity.setCount(count);
        subTransactionDao.save(subTransactionEntity);
        
        ItemEntity itemEntity = itemDao.findById(itemId);
        itemEntity.setStock(itemEntity.getStock() - count);
        itemDao.update(itemEntity);
    }

    @Override
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public void saveMultiTransaction(Map<String, Integer> cartMap, String userId) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(IDUtil.getUUID());
        transactionEntity.setUserId(userId);
        transactionEntity.setTransactionDatetime(new Timestamp(System.currentTimeMillis()));
        transactionDao.save(transactionEntity);
        for(Map.Entry<String, Integer> cartEntry : cartMap.entrySet()) {
            SubTransactionEntity subTransactionEntity = new SubTransactionEntity();
            subTransactionEntity.setSubtransactionId(IDUtil.getUUID());
            subTransactionEntity.setTransactionId(transactionEntity.getTransactionId());
            subTransactionEntity.setItemId(cartEntry.getKey());
            subTransactionEntity.setCount(cartEntry.getValue());
            subTransactionDao.save(subTransactionEntity);
            
            ItemEntity itemEntity = itemDao.findById(cartEntry.getKey());
            itemEntity.setStock(itemEntity.getStock() - cartEntry.getValue());
            itemDao.update(itemEntity);
        }
    }
    
}
