/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.Headers;
import glasscat.as1.dao.impl.RatingDao;
import glasscat.as1.dao.impl.UserDao;
import glasscat.as1.util.HttpUtil;
import glasscat.as1.entity.ItemEntity;
import glasscat.as1.entity.RatingEntity;
import glasscat.as1.entity.UserEntity;
import glasscat.as1.service.ItemService;
import glasscat.as1.util.IDUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Stateless
public class ItemServiceImpl implements ItemService{
    private static Pattern r = Pattern.compile("\\d{6}");
    private String basePath = "https://us.shein.com/";
    private String detailPath = "product/details?access_level=1&ids=";
//    private String pricePath = "product/getPrice?goods_id=";
    private String ratingPath = "product/comment?goods_id=";
    
    @EJB
    private UserDao userDao;
    @EJB
    private RatingDao ratingDao;
    @Override
    public ItemEntity generateNewItemFromURL(String url) {
        // https://us.shein.com/Letter-And-Tropical-Print-Tee-p-737242-cat-1738.html
        Matcher m = r.matcher(url);
        if (!m.find()) {
            return null;
        }
        String itemId = m.group(0);
        System.out.println("itemId: " + itemId);
        String baseJson = null;
        String detailJson = null;
//        String priceJson = null;
//        String ratingJson = null;
        try {
            baseJson = HttpUtil.get(basePath + "product-item-" + itemId + ".html", headers);
            detailJson = HttpUtil.get(basePath + detailPath + itemId, headers);
//            priceJson = HttpUtil.get(basePath + pricePath + itemId, headers);
//            ratingJson = HttpUtil.get(basePath + ratingPath + itemId, headers);            
            System.out.println("capture data successful");
            System.out.println(baseJson);
            System.out.println(detailJson);
//            System.out.println(priceJson);
//            System.out.println(ratingJson);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Catch data from url fail.");
            return null;
        }
        
        JSONObject product = JSONObject.parseObject(baseJson);
        JSONObject detail = JSONObject.parseObject(detailJson);
//        JSONObject price = JSONObject.parseObject(priceJson);
//        JSONObject rating = JSONObject.parseObject(ratingJson);
        
        if (product != null && detail != null) {
            System.out.println("parse json data successful");
            ItemEntity item = new ItemEntity();
            item.setId(itemId);
            item.setTitle(product.getJSONObject("info").getJSONObject("goods").getJSONObject("detail").getString("goods_url_name"));
            item.setImageUrl(product.getJSONObject("info").getJSONObject("goods").getJSONObject("goods_imgs").getJSONObject("main_image").getString("medium_image"));
            item.setImageUrlThumb(product.getJSONObject("info").getJSONObject("goods").getJSONObject("goods_imgs").getJSONObject("main_image").getString("thumbnail"));
            item.setCategory(product.getJSONObject("info").getJSONObject("goods").getJSONObject("localSize").getString("category_name"));
            item.setPrice(product.getJSONObject("info").getJSONObject("goods").getJSONObject("detail").getJSONObject("retailPrice").getDouble("amount"));
            item.setStock(product.getJSONObject("info").getJSONObject("goods").getJSONObject("detail").getInteger("stock"));
            JSONArray detailList = detail.getJSONObject("info").getJSONObject("products").getJSONObject(itemId).getJSONArray("productDetails");
            String color = "Multicolor";
            String style = "Casual";
            String season = "All";
            for (Object d : detailList){
                JSONObject det = (JSONObject)d;
                if (det.getString("attr_name").equals("Color")) {
                    color = det.getString("attr_value");
                }
                if (det.getString("attr_name").equals("Style")) {
                    style = det.getString("attr_value");
                }
                if (det.getString("attr_name").equals("Season")) {
                    season = det.getString("attr_value");
                }
            }
            item.setColor(color);
            item.setStyle(style);
            item.setSeason(season);
            return item;
        } else {
            return null;
        }
    }
    
    private Map<String, String> headers;
    @PostConstruct
    public void init(){
        headers = new HashMap<String, String>();
        
        headers.put("Accept","*/*");
        headers.put("Accept-Charset","UTF-8");
        headers.put("Accept-Language", "en,zh-CN;q=0.9,zh;q=0.8");
        headers.put("Connection", "keep-alive");
        headers.put("Host","us.shein.com");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " + 
                "Chrome/75.0.3770.142 Safari/537.36");
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("Cookie", "_ga=GA1.2.1868428510.1565315108; default_currency=USD; cookieId=9E1A3CD1_A8DB_91A4_6197_2514D4E145EA; _fbp=fb.1.1565315119287.2001438239; countryId=; cto_lwid=50a3bdab-5990-4cf3-8c2d-06f33ce9a2a3; scarab.visitor=%2269A9EC3701BF95E%22; G_ENABLED_IDPS=google; _aimtellSubscriberID=c6ebc8e7-5068-f770-4e3e-cbdc06cbf96d; scarab.profile=%22768693%7C1565315120%22; fita.sid.shein=zP9ECcFyorn7LK5dCc4YfcCfu8DuNx8q; _aimtellSessionPageViews=7; cate_active_name=0; cate_channel_type=2; sessionID_shein=s%3AooTbfZeyO32NBsGI6Qf0HVI_54fQPsM7.ezCt%2BQ%2Bpi9bMzV67JjjE0Ai6%2BPGEX9jZQpcUEuXZnAI; _gid=GA1.2.266561139.1565949170; hideCoupon=1; have_show=1; showInch=0; scarab.mayViewed=%5B%5D; scarab.mayAdd=%5B%7B%22i%22%3A%22768686%22%7D%2C%7B%22i%22%3A%22761713%22%2C%22t%22%3A%22RELATED%22%2C%22c%22%3A%22AAAA%22%7D%2C%7B%22i%22%3A%22771608%22%2C%22t%22%3A%22RELATED%22%2C%22c%22%3A%22AAAA%22%7D%2C%7B%22i%22%3A%22736011%22%2C%22t%22%3A%22RELATED%22%2C%22c%22%3A%22AAAA%22%7D%2C%7B%22i%22%3A%22775345%22%2C%22t%22%3A%22RELATED%22%2C%22c%22%3A%22AAAA%22%7D%2C%7B%22i%22%3A%22613259%22%2C%22t%22%3A%22RELATED%22%2C%22c%22%3A%22AAAA%22%7D%2C%7B%22i%22%3A%22745201%22%7D%2C%7B%22i%22%3A%22753747%22%7D%2C%7B%22i%22%3A%22790700%22%7D%2C%7B%22i%22%3A%22737242%22%2C%22t%22%3A%22RELATED%22%2C%22c%22%3A%22AAAA%22%7D%5D; __atuvc=11%7C32%2C7%7C33; _gat_shein=1; crowds_id=");
        
    }

    @Override
    public String captureRatingsAndSave(String itemId) {
        System.out.println("itemId： " + itemId);
        String ratingJson = null;
        try {
            ratingJson = HttpUtil.get(basePath + ratingPath + itemId, headers);   
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Catch data from url fail.");
            return null;
        }
        JSONObject rating = JSONObject.parseObject(ratingJson);
        if (rating != null) {
            System.out.println(rating);
            JSONArray comments = rating.getJSONObject("info").getJSONArray("commentInfo");
            List<UserEntity> users = userDao.findAll();
            for (int i = 0; i < comments.size(); i ++) {
                int num = 0 + (int)(Math.random() * (users.size()));
                RatingEntity r = new RatingEntity();
                r.setUserId(users.get(num).getId());
                r.setSubTransactionId(IDUtil.getUUID());
                r.setRatingId(comments.getJSONObject(i).getString("comment_id"));
                r.setComment(comments.getJSONObject(i).getString("content"));
                r.setItemId(itemId);
                r.setMark(comments.getJSONObject(i).getInteger("comment_rank"));
                try {
                    r.setRatingDate(new java.sql.Date(new SimpleDateFormat("dd MMM,yyyy").parse(comments.getJSONObject(i).getString("comment_time")).getTime()));
                } catch (ParseException ex) {
                    Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }          
                ratingDao.save(r);
            }
            return "success";
        } else {
            return null;
        }
    }
}
