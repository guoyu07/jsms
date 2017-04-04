// 短信发送工具类
package com.rongle.jsms;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


public final class SendMsg {
    private String username;
    private String password;
    private String epid;
    private String subcode;
    private final static String fmt = "http://114.255.71.158:8061/"
            + "?username=%s&password=%s&phone=%s&message=%s&epid=%s&subcode=%s&linkid=";


    public SendMsg(String username, String password, String epid, String subcode) {
        this.username = username;
        this.password = password;
        this.epid = epid;
        this.subcode = subcode;
    }


    public boolean send(String phone, String msg) {
        String gbkMsg = null;

        // 编码转换
        try {
            gbkMsg = new String(msg.getBytes("UTF-8"), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }

        // 构造url
        String url = String.format(
                fmt, this.username, this.password, phone, gbkMsg, this.epid, this.subcode
        );

        try (CloseableHttpClient cli = HttpClients.createDefault()) {
            HttpGet req = new HttpGet(url);
            try (CloseableHttpResponse rsp = cli.execute(req)) {
                int status = rsp.getStatusLine().getStatusCode();
                String body = EntityUtils.toString(rsp.getEntity());

                if ((200 == status) && body.equals("00")) {
                    return true;
                }

                System.out.println(status);
                if (! StringUtils.isBlank(body)) {
                    System.out.println(body);
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}