package top.zerotop.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class RestfulWapper {
    private static Logger logger = LoggerFactory.getLogger(RestfulWapper.class);

    private static CloseableHttpClient httpclient = HttpClients.createDefault();

    private static  String result = "";

    public static String getWapper(String url) throws IOException {
        System.out.println(url);
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse res = null;
        HttpEntity entity1 = null;
        try {
            res = httpclient.execute(httpGet);
            if(HttpStatus.SC_OK == res.getStatusLine().getStatusCode()) {
                entity1 = res.getEntity();
                result = EntityUtils.toString(entity1, "UTF-8");
            }
            System.out.println(result);
        } catch (IOException e1) {
            logger.info(String.format("url:[%s], get fail, exception:[IOException]", url));
        } finally {
            if (res != null) {
                res.close();
            }
        }
        return result;
    }

    public static String postWapper(String url, String data) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(data, "UTF-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse res = httpclient.execute(httpPost);
        try {
            logger.info(res.getStatusLine().getStatusCode()+"");
            HttpEntity entity1 = res.getEntity();
            result = EntityUtils.toString(entity1, "UTF-8");
            logger.info(entity1.toString());
        } finally {
            res.close();
        }
        return result;
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
