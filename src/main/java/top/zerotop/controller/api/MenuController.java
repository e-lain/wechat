package top.zerotop.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zerotop.global.constrant.URLConstrant;
import top.zerotop.util.RestfulWapper;
import top.zerotop.util.SendUtils;
import top.zerotop.wechat.TokenThread;

import java.io.IOException;

@RestController
@Api(value = "公众号菜单管理", description = "公众号菜单管理")
@RequestMapping(value = "/menu", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class MenuController extends BaseController{
    private final static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @GetMapping("/get")
    @ApiOperation(value = "获取当前公众号菜单")
    public String getMenu() {
        logger.info("--get menu--");

        String url = URLConstrant.URL_MENU_GET + TokenThread.accessToken.getAccessToken();
//        String res = SendUtils.sendGet(url);

        String res = null;
        try {
            res = RestfulWapper.getWapper(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(url);
        System.out.println(res);
        return res;
    }
}
