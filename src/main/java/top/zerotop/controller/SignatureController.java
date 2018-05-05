package top.zerotop.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import top.zerotop.domain.AccessToken;
import top.zerotop.wechat.util.Decript;

/**
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年5月4日下午9:48:14
 */
@Controller
public class SignatureController {

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
		map.put(nonceStr,"noncestr");
		map.put(timestamp,"timestamp");
		map.put(url,"url");
		Arrays.sort(strArray);
		

//		StringBuilder sbuilder = new StringBuilder();
//		for (String str : strArray) {
//			sbuilder.append(str);
//		}
		String str = map.get(strArray[0])+"="+strArray[0]
						+"&"+map.get(strArray[1])+"="+strArray[1]
						+"&"+map.get(strArray[2])+"="+strArray[2]
						+"&"+map.get(strArray[3])+"="+strArray[3]; 
		
		System.out.println("str:"+str);

		return str;
	}
}
