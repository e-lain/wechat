package top.zerotop.wechat.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.zerotop.controller.SignatureController;
import top.zerotop.util.SignatureUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by:zerotop  date:2019/4/7
 */
@Controller
@RequestMapping(value = "/page")
public class IndexController {

    @RequestMapping(value = "/share")
    public ModelAndView jsIndexPage(HttpServletRequest request) {
        Map<String, String> map = SignatureUtils.signatureJSSDK(request);

        ModelAndView model = new ModelAndView("page/share");
        model.addObject("appId", map.get("appId"));
        model.addObject("timestamp", map.get("timestamp"));
        model.addObject("nonceStr", map.get("nonceStr"));
        model.addObject("signature", map.get("signature"));
        return model;
    }
}
