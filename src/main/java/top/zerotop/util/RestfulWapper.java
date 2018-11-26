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
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.charset.Charset;
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

    public static String formPostWapper(String url, List<NameValuePair> data, MultipartFile file) throws IOException {
        HttpPost httpPost = new HttpPost(url);
//        httpPost.setEntity(new UrlEncodedFormEntity(data));
        File file1 = new File(file.getName());
        FileUtils.copyInputStreamToFile(file.getInputStream(), file1);
        FileBody fileBody = new FileBody(file1);


        String boundary = "----------" + System.currentTimeMillis();
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Content-Type", "multipart/form-data;boundary=" + boundary);
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.0) ");

        MultipartEntityBuilder mbuilder = MultipartEntityBuilder.create().addPart("file", fileBody);
        mbuilder.setBoundary(boundary).setCharset(Charset.forName("utf-8")).setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        mbuilder.addBinaryBody("media", file1, ContentType.MULTIPART_FORM_DATA, file.getName());

        HttpEntity  httpEntity = mbuilder.build();

        System.out.println(file.getName());
        System.out.println(httpEntity.getContentLength());
        System.out.println(httpEntity.getContentType());

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
