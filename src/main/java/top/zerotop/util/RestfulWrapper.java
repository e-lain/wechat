package top.zerotop.util;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
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

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestfulWrapper {
    private static Logger logger = LoggerFactory.getLogger(RestfulWrapper.class);

    private static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static Map<String, Object> getWrapper(String url) {
        HttpGet httpGet = new HttpGet(url);
        Map<String, Object> resultMap = new HashMap<>();
        try (CloseableHttpResponse res = httpclient.execute(httpGet)) {
            if (HttpStatus.SC_OK == res.getStatusLine().getStatusCode()) {
                HttpEntity entity1 = res.getEntity();
                if (null != entity1 && entity1.getContentType().getValue() != null && entity1.getContentType().getValue().contains("image")) {
                    String name = "D://" + System.currentTimeMillis() + ".jpg";
                    FileOutputStream outputStream = new FileOutputStream(name);
                    entity1.writeTo(outputStream);
                    resultMap.put("img", name);
                    outputStream.close();
                } else if (entity1 != null) {
                    String result = EntityUtils.toString(entity1, StandardCharsets.UTF_8);
                    resultMap.put("result", result);
                }
            }
        } catch (IOException e) {
            logger.info(String.format("===> url:[%s], get fail, exception:[IOException]", url), e);
        }
        return resultMap;
    }

    public static String postWrapper(String url, String data) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(data, StandardCharsets.UTF_8);
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);

        try(CloseableHttpResponse res = httpclient.execute(httpPost)) {
            HttpEntity entity1 = res.getEntity();
            return EntityUtils.toString(entity1, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("post wrapper wrong. {}", e.getMessage());
        }
        return "";
    }

    public static String formPostWrapper(String url, List<NameValuePair> data, MultipartFile file) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        File tempFile = new File(file.getOriginalFilename());
        FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
        FileBody fileBody = new FileBody(tempFile, ContentType.create("image/*"));

        String boundary = "----------" + System.currentTimeMillis();
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Content-Type", "multipart/form-data;boundary=" + boundary);
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.0) ");

        MultipartEntityBuilder mbuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
        mbuilder.setBoundary("--" + boundary).setCharset(StandardCharsets.UTF_8);
//        mbuilder.addBinaryBody("file", file1, ContentType.create("image/*"), tempFile.getName());
        mbuilder.addPart("file", fileBody).setContentType(ContentType.APPLICATION_OCTET_STREAM);

        HttpEntity httpEntity = mbuilder.build();

        httpPost.setEntity(httpEntity);
        String result = "";
        try (CloseableHttpResponse response = httpclient.execute(httpPost);) {
            logger.info(response.getStatusLine().getStatusCode() + "");
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            EntityUtils.consume(entity);
        } catch (Exception e) {
            logger.warn("form post wrapper failed. cause: ", e);
        }
        return result;
    }
}
