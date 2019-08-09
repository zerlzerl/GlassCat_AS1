/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.util;

import java.util.UUID;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
public class IDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
    
    public static void main(String[] args) {
        System.out.println(getUUID());
    }
}
