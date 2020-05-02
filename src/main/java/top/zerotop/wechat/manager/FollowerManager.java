package top.zerotop.wechat.manager;

import com.google.gson.Gson;

import top.zerotop.domain.Follower;
import top.zerotop.global.constrant.URLConstrant;
import top.zerotop.util.SendUtils;
import top.zerotop.util.URLUtils;

public class FollowerManager {
    private static Gson gson = new Gson();

    public static void main(String args[]) {
        FollowerManager followermanager = new FollowerManager();
        followermanager.UserInfo("ACCESS_TOKEN", "openid");
    }

    /**
     * 列出所有用户
     *
     * @param access_token
     * @return
     */
    public String ListFollower(String access_token) {
        String url = URLConstrant.URL_FOLLOWER_GET + access_token;
        String param = SendUtils.sendGet(url);
        Follower follow = gson.fromJson(param, Follower.class);
        System.out.println(follow);

        return url;
    }

    /**
     * 获取用户信息
     *
     * @param access_token
     * @param openid
     * @return
     */
    public String UserInfo(String access_token, String openid) {
        String url = URLUtils.getUrl(URLConstrant.URE_GET_USER_INFO, access_token).replace("{openid}", openid);

        String param = SendUtils.sendGet(url);
        System.out.println(param);

        return url;
    }
}
