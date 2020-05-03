package top.zerotop.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.zerotop.domain.AccessToken;
import top.zerotop.global.constrant.LoginURLConstrant;
import top.zerotop.util.RestfulWrapper;
import top.zerotop.util.SendUtils;
import top.zerotop.util.URLUtils;

import java.time.LocalDateTime;

public class TokenThread implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(TokenThread.class);

    private static String appId = "";
    private static String appSecret = "";

    /**
     * 全局只有一个
     */
    public static AccessToken accessToken = null;

    static void initAppInfo(String appId, String appSecret) {
        TokenThread.appId = appId;
        TokenThread.appSecret = appSecret;
    }

    public static String getAppId() {
        return appId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public void run() {
        while (true) {
            try {
                accessToken = this.getAccessToken();
                if (accessToken != null) {
                    logger.info(String.format("get token ===> date:{ %s }  token:{ %s }", LocalDateTime.now(), AccessToken.getAccessToken()));
                    Thread.sleep(7000 * 1000); // 获取到access_token 休眠7000秒
                } else {
                    Thread.sleep(1000 * 3); // 获取的access_token为空 休眠3秒
                }
            } catch (InterruptedException e) {
                logger.error("发生异常：" + e.getMessage());
                try {
                    Thread.sleep(1000 * 10); // 发生异常休眠1秒
                } catch (Exception e1) {
                    logger.error("发生异常：" + e1.getMessage());
                }
            }
        }
    }

    /**
     * 获取access_token
     *
     * @return
     */
    private AccessToken getAccessToken() {
        String Url = LoginURLConstrant.URL_GET_TOKEN.replace("{appid}", TokenThread.appId).replace("{app_secret}", TokenThread.appSecret);
        String result = (String) RestfulWrapper.getWrapper(Url).getOrDefault("result", "");
        logger.info("=====> get Token: {}", result);
        JSONObject tokenJson = JSON.parseObject(result);
        AccessToken token = new AccessToken();
        AccessToken.setAccessToken(tokenJson.getString("access_token"));
        token.setExpiresin(tokenJson.getInteger("expires_in"));

        String url = URLUtils.getUrl(LoginURLConstrant.URL_GET_TICKET);
        String ticket = SendUtils.sendGet(url);
        JSONObject ticketJson = JSON.parseObject(ticket);
        AccessToken.setJsapiTicket(ticketJson.getString("ticket"));
        logger.info("ticket: {}", ticketJson.getString("ticket"));
        return token;
    }
}