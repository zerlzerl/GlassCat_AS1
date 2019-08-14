/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.service;

import com.sun.xml.ws.api.tx.at.Transactional;
import glasscat.as1.dao.impl.CartDao;
import glasscat.as1.dao.impl.ItemDao;
import glasscat.as1.dao.impl.SubTransactionDao;
import glasscat.as1.dao.impl.TransactionDao;
import glasscat.as1.entity.CartEntity;
import glasscat.as1.entity.ItemEntity;
import glasscat.as1.entity.SubTransactionEntity;
import glasscat.as1.entity.TransactionEntity;
import glasscat.as1.util.IDUtil;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


/**
 *
 * @author 86185
 */
@Stateless
@LocalBean
@Transactional
public class TransactionService {
    @EJB
    private TransactionDao transactionDao;
    @EJB
    private SubTransactionDao subTransactionDao;
    @EJB
    private ItemDao itemDao;
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public void saveSingleTransaction (String itemId, String userId, Integer count) {
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
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public void saveMultiTransaction (Map<String, Integer> cartMap, String userId) {
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
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
