package top.zerotop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.zerotop.domain.AccessToken;
import top.zerotop.wechat.TokenThread;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by:zerotop  date:2019/4/27
 */
public class SignatureUtils {
    private static final Logger logger = LoggerFactory.getLogger(SignatureUtils.class);


    public static Map<String, String> signatureJSSDK(HttpServletRequest req) {
        String url = req.getRequestURL().toString();
        String timestamp = createTimestamp();
        String nonceStr = createNonce();
        String signatureStr = "jsapi_ticket=" + AccessToken.getJsapiTicket()
                + "&noncestr=" + nonceStr
                + "&timestamp=" + timestamp
                + "&url=" + url;
        String signature = DecriptUtils.SHA1(signatureStr);
        logger.info("===============>>>post signature");
        logger.info("url: {}, timestamp: {}, nonceStr: {}", url, timestamp, nonceStr);
        logger.info("signatureStr: {}, signature: {}", signatureStr, signature);
        logger.info("===============>>>signature done");

        Map<String, String> result = new HashMap<>();
        result.put("appId", TokenThread.getAppId());
        result.put("nonceStr", nonceStr);
        result.put("timestamp", timestamp);
        result.put("signature", signature);
        return result;
    }

    /**
     * 生成时间戳
     */
    private static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /**
     * 生成jssdk需要的nonce
     */
    private static String createNonce() {
        return UUID.randomUUID().toString();
    }

    public static String sort(String ticket, String timestamp, String nonceStr, String url) {
        String[] strArray = {ticket, timestamp, nonceStr, url};
        Map<String, String> map = new HashMap<>();
        map.put(ticket, "jsapi_ticket");
        map.put(nonceStr, "noncestr");
        map.put(timestamp, "timestamp");
        map.put(url, "url");
        Arrays.sort(strArray);

        String str = map.get(strArray[0]) + "=" + strArray[0]
                + "&" + map.get(strArray[1]) + "=" + strArray[1]
                + "&" + map.get(strArray[2]) + "=" + strArray[2]
                + "&" + map.get(strArray[3]) + "=" + strArray[3];

        logger.info("str:" + str);

        return str;
    }
}
