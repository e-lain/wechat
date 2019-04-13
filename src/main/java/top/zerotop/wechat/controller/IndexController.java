package top.zerotop.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by:zerotop  date:2019/4/7
 */
@Controller
@RequestMapping(value = "/page")
public class IndexController {
    @RequestMapping(value = "/share")
    public ModelAndView jsIndexPage() {
        ModelAndView model = new ModelAndView("page/share");
        return model;
    }
}
