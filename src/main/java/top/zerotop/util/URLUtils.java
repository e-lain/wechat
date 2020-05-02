package top.zerotop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import top.zerotop.wechat.TokenThread;

/**
 * Created by:zerotop  date:2019/4/25
 */
public class URLUtils {
    private static final Logger logger = LoggerFactory.getLogger(URLUtils.class);

    public static String getUrl(String url) {
        if (StringUtils.hasText(url)) {
            return url.replace("{access_token}", TokenThread.accessToken.getAccessToken());
        }
        return url;
    }

    public static String getUrl(String url, String accessToken) {
        if (StringUtils.hasText(url)) {
            return url.replace("{access_token}", accessToken);
        }
        return url;
    }
}
