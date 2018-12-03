package top.zerotop.controller.api;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.zerotop.domain.menu.Menu;
import top.zerotop.global.constrant.URLConstrant;
import top.zerotop.util.RestfulWapper;
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
        String res = null;
        try {
            res = (String)RestfulWapper.getWapper(url).get("result");
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(url);
        System.out.println(res);
        return res;
    }

    @PostMapping("/create")
    @ApiOperation(value = "创建菜单")
    public String createMenu(@ApiParam(value = "菜单")
                            @RequestBody Menu menu) {
        System.out.println("--create menu--");

        String url = URLConstrant.URL_MENU_CREATE + TokenThread.accessToken.getAccessToken();
        String res = null;
        try {
            res = RestfulWapper.postWapper(url, JSON.toJSONString(menu));
            System.out.println(res);

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(url);
        return res;
    }

}
