package top.zerotop.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by:zerotop  date:2019/4/7
 */
@Controller
@RequestMapping(value = "/jssdk")
public class IndexController {
    @RequestMapping(value = "/testpage")
    public ModelAndView jsIndexPage() {
        System.out.println("get js index page req...");
        ModelAndView model = new ModelAndView("jsindex");
        return model;
    }
}
