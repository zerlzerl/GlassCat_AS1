/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.service;

import com.sun.xml.ws.api.tx.at.Transactional;
import glasscat.as1.dao.impl.SubTransactionDao;
import glasscat.as1.dao.impl.TransactionDao;
import glasscat.as1.entity.SubTransactionEntity;
import glasscat.as1.entity.TransactionEntity;
import glasscat.as1.util.IDUtil;
import java.sql.Timestamp;
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
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public void saveSingleTransaction (String itemId, String userId, Integer count) throws Exception {
        SubTransactionEntity subTransactionEntity = new SubTransactionEntity();
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(IDUtil.getUUID());
        transactionEntity.setUserId(userId);
        transactionEntity.setTransactionDatetime(new Timestamp(System.currentTimeMillis()));
        transactionDao.save(transactionEntity);
        
        subTransactionEntity.setSubtransactionId(IDUtil.getUUID());
        subTransactionEntity.setTransactionId(transactionEntity.getTransactionId());
        subTransactionEntity.setItemId(itemId);
        subTransactionEntity.setCount(count);
        subTransactionDao.save(subTransactionEntity);
        throw new Exception("dou ni waner");
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
