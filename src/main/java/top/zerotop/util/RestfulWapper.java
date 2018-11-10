package top.zerotop.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestfulWapper {
    private static Logger logger = LoggerFactory.getLogger(RestfulWapper.class);

    private static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static void getWapper(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse res = null;
        try {
            res = httpclient.execute(httpGet);
            HttpEntity entity1 = res.getEntity();
            EntityUtils.consume(entity1);
            System.out.println(EntityUtils.toString(entity1, "UTF-8"));
        } catch (IOException e1) {
            logger.info(String.format("url:[%s], get fail, exception:[IOException]", url));
        } finally {
            if (res != null) {
                res.close();
            }
        }
    }

    public static void postWapper(String url, String data) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(data, "UTF-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse res = httpclient.execute(httpPost);
        try {
            logger.info(res.getStatusLine().getStatusCode()+"");
            HttpEntity entity2 = res.getEntity();
            System.out.println(entity2.toString());
        } finally {
            res.close();
        }
    }

    public static void formPostWapper(String url, List<NameValuePair> data) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(data));
        CloseableHttpResponse response2 = httpclient.execute(httpPost);
        try {
            logger.info(response2.getStatusLine().getStatusCode()+"");
            HttpEntity entity2 = response2.getEntity();
            EntityUtils.consume(entity2);
        } finally {
            response2.close();
        }
    }
}
