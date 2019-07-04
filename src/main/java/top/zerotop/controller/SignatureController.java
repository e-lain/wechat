package top.zerotop.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import org.springframework.web.servlet.ModelAndView;
import top.zerotop.domain.AccessToken;
import top.zerotop.util.*;
import top.zerotop.wechat.TokenThread;

import static top.zerotop.wechat.TokenThread.accessToken;

/**
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年5月4日下午9:48:14
 */
@Api(value = "WeChat接口")
@RestController
@RequestMapping(value = "/jssdk", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class SignatureController {
    private static Logger logger = LoggerFactory.getLogger(SignatureController.class);

//    @GetMapping(value = "/token")
//    public @ResponseBody String getToken() {
//        return TokenThread.accessToken.getAccessToken();
//    }
    @ApiOperation(value = "微信接口验证")
    @GetMapping(value = "/token")
    public Result<String> getToken() {
        logger.info("get token reqest");
        return Result.make(accessToken.getAccessToken());
    }


    /**
     * 项目中jsp使用jssdk签名接口
     */
    @ApiOperation(value = "微信接口验证")
    @GetMapping("/signature")
    public Map<String, String> signature(HttpServletRequest req) {
        Map<String, String> result = SignatureUtils.signatureJSSDK(req);
        return result;
    }

    /**
     * 微信小程序登录        wx.login
     *
     * @param req  HttpServletRequest
     * @param code 临时登录凭证code只能使用一次
     * @return
     */
    @ApiOperation(value = "微信小程序登录接口")
    @GetMapping(value = "/mini/login/{code}")
    public String miniLogin(HttpServletRequest req, @PathVariable("code") String code) {
        Map<String, String> map = new HashMap<>();

        logger.info("sessionid:" + req.getSession().getId());

        String url = "https://api.weixin.qq.com/sns/jscode2session?"
                + "appid=yourappid"
                + "&secret=yoursecret"
                + "&js_code=" + code
                + "&grant_type=authorization_code";
        logger.info("splice is url: " + url);

        String res = SendUtils.sendGet(url);

        JSONObject json = JSON.parseObject(res);
        String openid = json.get("openid").toString();
        String session_key = json.get("openid").toString();

        //用于验证微信小程序用户身份
        String minisign = DecriptUtils.SHA1(openid + session_key);
        req.getSession().setAttribute("minisign", minisign);

        map.put("session_id", req.getSession().getId());
        map.put("minisign", minisign);

        return JSON.toJSONString(map);
    }

    /**
     * 微信公众号本地jssdk验证
     */
    @ApiOperation(value = "微信公众号本地jssdk验证")
    @PostMapping(value = "/signature/vue")
    public Result signatureVue(HttpServletRequest req) {
        System.out.println("--------------- vue signature -------------------");
        String json = JsonUtils.toJsonString(req);
        JSONObject tjson = JSON.parseObject(json);
        String rurl = tjson.get("url").toString();

        Map<String, String> map = SignatureUtils.signatureJSSDK(req);
        return Result.make(map);
    }

}
