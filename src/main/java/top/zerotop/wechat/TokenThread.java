package top.zerotop.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.zerotop.Provider.NetWorkHelper;
import top.zerotop.domain.AccessToken;
import top.zerotop.global.constrant.URLConstrant;
import top.zerotop.util.RestfulWapper;
import top.zerotop.util.SendUtils;

import java.io.IOException;
import java.time.LocalDateTime;

public class TokenThread implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(TokenThread.class);

    public static String appId = "";

    public static String appSecret = "";

    /**
     * 全局只有一个
     */
    public static AccessToken accessToken = null;

    public void run() {
        while (true) {
            try {
                accessToken = this.getAccessToken();
                if (null != accessToken) {
                    System.out.println(String.format("==date:{%s}  token:{%s}", LocalDateTime.now(), AccessToken.getAccessToken()));
                    Thread.sleep(7000 * 1000); // 获取到access_token 休眠7000秒
                } else {
                    Thread.sleep(1000 * 3); // 获取的access_token为空 休眠3秒
                }
            } catch (Exception e) {
                logger.info("发生异常：" + e.getMessage());
                e.printStackTrace();
                try {
                    Thread.sleep(1000 * 10); // 发生异常休眠1秒
                } catch (Exception e1) {
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
//		NetWorkHelper netHelper = new NetWorkHelper();
        String Url = String.format(
                URLConstrant.BASE_URL + "/token?grant_type=client_credential&appid=%s&secret=%s",
                TokenThread.appId, TokenThread.appSecret);

//		String result = netHelper.getHttpsResponse(Url, "");
        String result = "";
        try {
            result = (String) RestfulWapper.getWapper(Url).getOrDefault("result", "");
        } catch (IOException ioE) {
            ioE.printStackTrace();
        }
        logger.info(result);
        JSONObject tokenJson = JSON.parseObject(result);
        AccessToken token = new AccessToken();
        AccessToken.setAccessToken(tokenJson.getString("access_token"));
        token.setExpiresin(tokenJson.getInteger("expires_in"));

        String url = URLConstrant.BASE_URL + "/ticket/getticket?access_token=" + AccessToken.getAccessToken() + "&type=jsapi";

        String ticket = SendUtils.sendGet(url);
        JSONObject ticketJson = JSON.parseObject(ticket);
        AccessToken.setJsapiTicket(ticketJson.getString("ticket"));
        logger.info("ticket:{%s}", ticketJson.getString("ticket"));

        return token;
    }
}