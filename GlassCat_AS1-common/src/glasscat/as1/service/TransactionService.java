/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.service;

import java.util.Map;
import javax.ejb.Remote;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Remote
public interface TransactionService {
    public void saveSingleTransaction (String itemId, String userId, Integer count);
    public void saveMultiTransaction (Map<String, Integer> cartMap, String userId);
}
