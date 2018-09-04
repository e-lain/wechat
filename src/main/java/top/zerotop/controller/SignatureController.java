package top.zerotop.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import top.zerotop.domain.AccessToken;
import top.zerotop.util.ExtractJsonString;
import top.zerotop.wechat.util.Decript;
import top.zerotop.wechat.util.SendUtil;

/**
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年5月4日下午9:48:14
 */
@Controller
public class SignatureController {
	
	/**
	 * 项目中jsp使用jssdk签名接口
	 * @param req
	 * @return
	 */
	@RequestMapping("/signature")
	public ModelAndView signature(HttpServletRequest req){
		
		System.out.println("-----------signature---------------");
		
		String url = req.getRequestURL().toString();
		String timestamp = SignatureController.createTimestamp();
		String nonceStr = SignatureController.createNonce();
		String signatureStr = "jsapi_ticket="+AccessToken.getJsapiTicket()
							+ "&noncestr="+nonceStr
							+ "&timestamp="+timestamp
							+ "&url="+url;
		String signature = Decript.SHA1(signatureStr);
		System.out.println("url:"+url);
		System.out.println("timestamp:"+timestamp);
		System.out.println("nonceStr:"+nonceStr);
		System.out.println("signatureStr:"+signatureStr);
		System.out.println("signature:"+signature);
		
		ModelAndView view = new ModelAndView("Signature");
		view.addObject("appId", "appId");
		view.addObject("nonceStr", nonceStr);
		view.addObject("timestamp", timestamp);
		view.addObject("signature", signature);
		System.out.println("-----------end---------------");
		return view;
	}
	
	/**
	 * 微信小程序登录        wx。login
	 * @param req	 HttpServletRequest
	 * @param code   临时登录凭证code只能使用一次
	 * @return
	 */
	@RequestMapping(value = "/mini/login/{code}", produces="application/json;charset=utf-8")
	public @ResponseBody String miniLogin(HttpServletRequest req ,@PathVariable("code")String code){
		
		Map<String, String> map = new HashMap<String, String>();
		
		System.out.println("sessionid:"+req.getSession().getId());
		
		String url = "https://api.weixin.qq.com/sns/jscode2session?"
				+ "appid=yourappid"
				+ "&secret=yoursecret"
				+ "&js_code="+code
				+ "&grant_type=authorization_code";
		System.out.println("rul: "+ url);
		
		String res = SendUtil.sendGet(url, null);	
		
		JSONObject tjson = JSON.parseObject(res);
		String openid = tjson.get("openid").toString();
		String session_key = tjson.get("openid").toString();
		
		//用于验证微信小程序用户身份
		String minisign = Decript.SHA1(openid+session_key);
		req.getSession().setAttribute("minisign", minisign);
		
		map.put("session_id", req.getSession().getId());
		map.put("minisign", minisign);
		
		
		return JSON.toJSONString(map);
	}
		
	/**
	 * 微信公众号本地jssdk验证
	 * @param req
	 * @return
	 */
	@RequestMapping(value= "/signature/vue", produces="application/json;charset=utf-8")
	public @ResponseBody String signatureVue(HttpServletRequest req){
		
		System.out.println("--------------- vue signature -------------------");
		
		String json = ExtractJsonString.extractJson(req);
		System.out.println("json: "+json);
		Gson gson = new Gson();
		JSONObject tjson = JSON.parseObject(json);
		String rurl = tjson.get("url").toString();
		System.out.println("realurl: "+rurl);
		
		Map<String, String> map = new HashMap<String, String>();
		
//		String url = req.getRequestURL().toString();
		String timestamp = SignatureController.createTimestamp();
		String nonceStr = SignatureController.createNonce();
		String signatureStr = "jsapi_ticket="+AccessToken.getJsapiTicket()
							+ "&noncestr="+nonceStr
							+ "&timestamp="+timestamp
							+ "&url="+rurl;
		String signature = Decript.SHA1(signatureStr);

		System.out.println("url:"+rurl);
		System.out.println("timestamp:"+timestamp);
		System.out.println("nonceStr:"+nonceStr);
		System.out.println("signatureStr:"+signatureStr);
		System.out.println("signature:"+signature);
		
		map.put("appId", "appId");
		map.put("nonceStr", nonceStr);
		map.put("timestamp", timestamp);
		map.put("signature", signature);
		
		return gson.toJson(map);
	}

	private static String createTimestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	/**
	 * 生成jssdk需要的nonce
	 * @return
	 */
	private static String createNonce() {
		return UUID.randomUUID().toString();
	}

	public static String sort(String ticket, String timestamp, String nonceStr, String url) {
		String[] strArray = { ticket, timestamp, nonceStr, url};
		Map<String, String> map = new HashMap<String, String>();
		map.put( ticket,"jsapi_ticket");
		map.put(nonceStr,"noncestr");		map.put(timestamp,"timestamp");
		map.put(url,"url");
		Arrays.sort(strArray);

		String str = map.get(strArray[0])+"="+strArray[0]
						+"&"+map.get(strArray[1])+"="+strArray[1]
						+"&"+map.get(strArray[2])+"="+strArray[2]
						+"&"+map.get(strArray[3])+"="+strArray[3]; 
		
		System.out.println("str:"+str);

		return str;
	}
}
