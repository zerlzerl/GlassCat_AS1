/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import glasscat.as1.dao.impl.CartDao;
import glasscat.as1.dao.impl.ConfigurationDao;
import glasscat.as1.dao.impl.ItemDao;
import glasscat.as1.dao.impl.RatingDao;
import glasscat.as1.dao.impl.SubTransactionDao;
import glasscat.as1.dao.impl.TransactionDao;
import glasscat.as1.dao.impl.UserDao;
import glasscat.as1.entity.ConfigurationEntity;
import glasscat.as1.entity.ItemEntity;
import glasscat.as1.entity.RatingEntity;
import glasscat.as1.entity.SubTransactionEntity;
import glasscat.as1.entity.TransactionEntity;
import glasscat.as1.entity.UserEntity;
import glasscat.as1.util.IDUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * This bean is used to insert the initial data into database
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Startup
@Singleton
public class DataInitialization {
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    // dao instances
    @EJB
    private UserDao userDao;
    @EJB
    private ItemDao itemDao;    
    @EJB
    private TransactionDao transactionDao;
    @EJB
    private SubTransactionDao subTransactionDao;
    @EJB
    private RatingDao ratingDao;
    @EJB
    private ConfigurationDao configurationDao;
    @EJB
    private CartDao cartDao;
    
    public DataInitialization() {
        
    }
    
    @PostConstruct
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void init(){
        if (isInitialized()) return;
        
        System.out.println("############ database initialization");
        String dataDir = this.getClass().getClassLoader().getResource("META-INF/dataset").toString();
        String usersPath = (dataDir + File.separator + "users.json").substring(5);
        String itemsPath = (dataDir + File.separator + "items.json").substring(5);
        String transactionsPath = (dataDir + File.separator + "transactions.json").substring(5);
        String subtransactionsPath = (dataDir + File.separator + "subtransactions.json").substring(5);
        String ratingsPath = (dataDir + File.separator + "ratings.json").substring(5);
//        userPath = userPath.substring(5);
        try {
            BufferedReader in = null;
            in = new BufferedReader(new FileReader(usersPath));
            String usersJsonStr = in.readLine();
            in = new BufferedReader(new FileReader(itemsPath));
            String itemsJsonStr = in.readLine();
            in = new BufferedReader(new FileReader(transactionsPath));
            String transactionsJsonStr = in.readLine();
            in = new BufferedReader(new FileReader(subtransactionsPath));
            String subtransactionsJsonStr = in.readLine();
            in = new BufferedReader(new FileReader(ratingsPath));
            String ratingsJsonStr = in.readLine();
            
            System.out.println("############ init user information");
            JSONArray users = JSONObject.parseObject(usersJsonStr).getJSONArray("users");
            for (Object u : users){
                JSONObject user = (JSONObject)u;
                UserEntity userEntity = new UserEntity();
                userEntity.setId(user.getString("user_id"));
                userEntity.setAddress(user.getString("address"));
                userEntity.setBirthday(new java.sql.Date(new SimpleDateFormat(DATE_FORMAT).parse(user.getString("birthday")).getTime()));
                userEntity.setEmail(user.getString("email"));
                userEntity.setGender(user.getString("gender"));
                userEntity.setFirstName(user.getString("first_name"));
                userEntity.setLastName(user.getString("last_name"));
                userEntity.setMembershipLevel(user.getInteger("membership_level"));
                userEntity.setPassword(user.getString("password"));
                userEntity.setPhoneNumber(user.getString("phone_number"));
                userEntity.setUserName(user.getString("user_name"));
                userEntity.setProfession(user.getString("profession"));
                userDao.save(userEntity);
            }
            System.out.println("############ init item information");
            JSONArray items = JSONObject.parseObject(itemsJsonStr).getJSONArray("items");
            for (Object i : items) {
                JSONObject item = (JSONObject)i;
                ItemEntity itemEntity = new ItemEntity();
                itemEntity.setTitle(item.getString("title"));
                itemEntity.setId(item.getString("cloth_id"));
                itemEntity.setCategory(item.getString("category"));
                itemEntity.setColor(item.getString("color"));
                itemEntity.setImageUrl(item.getString("img_url"));
                itemEntity.setImageUrlThumb(item.getString("img_url_thumb"));
                itemEntity.setPrice(Double.valueOf(item.getString("price")));
                itemEntity.setSeason(item.getString("season"));
                itemEntity.setStock(item.getInteger("stock"));
                itemEntity.setStyle(item.getString("style"));
                itemDao.save(itemEntity);
            }
            System.out.println("############ init transaction information");
            JSONArray transactions = JSONObject.parseObject(transactionsJsonStr).getJSONArray("transactions");
            for(Object trans : transactions) {
                JSONObject transaction = (JSONObject)trans;
                TransactionEntity transactionEntity = new TransactionEntity();
                transactionEntity.setTransactionId(transaction.getString("transaction_id"));
                transactionEntity.setUserId(transaction.getString("user_id"));
                transactionEntity.setTransactionDatetime(new java.sql.Timestamp(new SimpleDateFormat(DATETIME_FORMAT)
                        .parse(transaction.getString("transaction_datetime")).getTime()));
                transactionDao.save(transactionEntity);
            }
            JSONArray subtransactions = JSONObject.parseObject(subtransactionsJsonStr).getJSONArray("subtransactions");
            JSONArray ratings = JSONObject.parseObject(ratingsJsonStr).getJSONArray("ratings");
            for (int i = 0 ; i < subtransactions.size() ; i ++) {
                JSONObject subtransaction = subtransactions.getJSONObject(i);
                JSONObject rating = ratings.getJSONObject(i);
                SubTransactionEntity subTransactionEntity = new SubTransactionEntity();
                subTransactionEntity.setSubtransactionId(subtransaction.getString("subtransaction_id"));
                subTransactionEntity.setTransactionId(subtransaction.getString("transaction_id"));
                subTransactionEntity.setItemId(subtransaction.getString("item_id"));
                subTransactionEntity.setCount(subtransaction.getInteger("count"));
                subTransactionDao.save(subTransactionEntity);
                RatingEntity ratingEntity = new RatingEntity();
                ratingEntity.setRatingId(rating.getString("rating_id"));
                ratingEntity.setMark(rating.getInteger("mark"));
                ratingEntity.setComment(rating.getString("comment"));
                ratingEntity.setItemId(rating.getString("item_id"));
                ratingEntity.setUserId(rating.getString("user_id"));
                ratingEntity.setSubTransactionId(subtransaction.getString("subtransaction_id"));
                ratingEntity.setRatingDate(new java.sql.Date(new SimpleDateFormat(DATE_FORMAT).parse(rating.getString("rating_date")).getTime()));
                ratingDao.save(ratingEntity);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(DataInitialization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        cartDao.findAll();
        
        System.out.println("######## init initialized flag ########");
        ConfigurationEntity initializedFlag = new ConfigurationEntity();
        initializedFlag.setId(IDUtil.getUUID());
        initializedFlag.setConfigType("SYSTEM");
        initializedFlag.setConfigName("isInitialized");
        initializedFlag.setValue("TRUE");
        configurationDao.save(initializedFlag);
    }
    
    public boolean isInitialized(){
        List<ConfigurationEntity> configs = configurationDao.findByTypeAndName("SYSTEM", "isInitialized");
        if (configs != null && !configs.isEmpty()) {
            String initializedFlag = configs.get(0).getValue();
            if (initializedFlag.equals("TRUE")) {
                return true;
            }
        }
        return false;
    }
    
}
