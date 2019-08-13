/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.bean;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This ejb beans is used to download images from url and store it in the local server
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Stateless
@LocalBean
public class ImageDownloader {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String downloadImage(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Failed to download file: " + response);
        }
        FileOutputStream fos = new FileOutputStream("d:/tmp.txt");
        fos.write(response.body().bytes());
        fos.close();
        String localRelativePath = "";
        return localRelativePath;
    }
}
