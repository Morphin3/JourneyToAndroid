package com.taoism.journeytoandroid.http;

import android.accounts.NetworkErrorException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Date: 2017-02-28
 * Time: 18:44
 * Author: cf
 * -----------------------------
 */

public class NetUtils {


    public static String get(String requestUrl) {
        HttpURLConnection conn = null;

        try {
            // 利用string url构建URL对象
            URL url = new URL(requestUrl);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                InputStream is = conn.getInputStream();
                String response = getStringFromInputStream(is);
                return response;
            } else {
                throw new NetworkErrorException("response code is " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return null;
    }

    public static String post(String requestUrl, String content) {

        HttpURLConnection conn = null;

        try {
            URL url = new URL(requestUrl);  //创建一个 URL 对象
            conn = (HttpURLConnection) url.openConnection();  //调用 URL 的openConnection()方法，获取HttpURLConnection对象
            conn.setRequestMethod("POST");// 设置请求方法为 post
            conn.setReadTimeout(5000);   //设置读取超时时间为5秒
            conn.setConnectTimeout(10000); //设置连接网络超时为10秒
            conn.setDoOutput(true);    //允许向服务器输出内容

            OutputStream os = conn.getOutputStream();

            //post 请求的参数
            String date = content;
            os.write(date.getBytes());
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode(); //调用此方法不必再使用conn.connect()方法
            if (responseCode == 200) {
                InputStream is = conn.getInputStream();
                String response = getStringFromInputStream(is);
                return response;
            } else {
                throw new NetworkErrorException("response code is " + responseCode);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();  //关闭连接
            }
        }

        return null;
    }


    public static String getStringFromInputStream(InputStream is)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int length = -1;

        while ((length = is.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }

        is.close();

        String state = baos.toString(); //把流中的数据转换成字符串，采用的编码是 utf-8（模拟器默认编码）
        baos.close();
        return state;
    }

}
