package top.zerotop.util;

import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestfulWapper {
    private static Logger logger = LoggerFactory.getLogger(RestfulWapper.class);

    private static CloseableHttpClient httpclient = HttpClients.createDefault();

    private static  String result = "";

    private static Map<String, Object> resultMap = new HashMap<>();

    public static Map<String, Object> getWapper(String url) throws IOException {
        System.out.println(url);
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse res = null;
        HttpEntity entity1 = null;
        try {
            res = httpclient.execute(httpGet);
            if(HttpStatus.SC_OK == res.getStatusLine().getStatusCode()) {
                entity1 = res.getEntity();
                System.out.println(entity1.getContentType());
                if (null != entity1 && entity1.getContentType().getValue().contains("image")) {
                    String name = "D://"+System.currentTimeMillis()+".jpg";
                    FileOutputStream outputStream = new FileOutputStream(name);
                    entity1.writeTo(outputStream);
                    resultMap.put("img", name);
                    outputStream.close();
                } else {
                    result = EntityUtils.toString(entity1, "UTF-8");
                    resultMap.put("result", result);
                }
            }
//            System.out.println(result);
        } catch (IOException e1) {
            logger.info(String.format("url:[%s], get fail, exception:[IOException]", url));
        } finally {
            if (res != null) {
                res.close();
            }
        }
        return resultMap;
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

    public static String formPostWapper(String url, List<NameValuePair> data, MultipartFile file) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        File file1 = new File(file.getOriginalFilename());
        FileUtils.copyInputStreamToFile(file.getInputStream(), file1);
        FileBody fileBody = new FileBody(file1, ContentType.create("image/*"));

        String boundary = "----------" + System.currentTimeMillis();
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Content-Type", "multipart/form-data;boundary=" + boundary);
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.0) ");

        MultipartEntityBuilder mbuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
        mbuilder.setBoundary("--"+boundary).setCharset(Charset.forName("utf-8"));
//        mbuilder.addBinaryBody("file", file1, ContentType.create("image/*"), file1.getName());
        mbuilder.addPart("file", fileBody).setContentType(ContentType.APPLICATION_OCTET_STREAM);

        HttpEntity  httpEntity = mbuilder.build();

        httpPost.setEntity(httpEntity);
        CloseableHttpResponse response2 = httpclient.execute(httpPost);
        try {
            logger.info(response2.getStatusLine().getStatusCode()+"");
            HttpEntity entity = response2.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);
        } finally {
            response2.close();
        }
        return result;
    }
}
