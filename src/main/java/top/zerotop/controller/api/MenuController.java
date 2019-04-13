package top.zerotop.controller.api;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.zerotop.domain.menu.Menu;
import top.zerotop.util.JsonUtils;
import top.zerotop.util.RestfulWapper;
import top.zerotop.util.Result;
import top.zerotop.wechat.TokenThread;

import static top.zerotop.global.constrant.URLConstrant.URL_MENU_CREATE;
import static top.zerotop.global.constrant.URLConstrant.URL_MENU_DELETE;
import static top.zerotop.global.constrant.URLConstrant.URL_MENU_GET;

@RestController
@Api(value = "公众号菜单管理", description = "公众号菜单管理")
@RequestMapping(value = "/menu", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class MenuController extends BaseController{
    @GetMapping("/get")
    @ApiOperation(value = "获取当前公众号菜单")
    public Result<Menu> getMenu() {
        String url = URL_MENU_GET + TokenThread.accessToken.getAccessToken();
        String res = (String)RestfulWapper.getWapper(url).get("result");
        return Result.make(JsonUtils.toSubObject(res, "menu", Menu.class));
    }

    @GetMapping("/delete")
    @ApiOperation(value = "获取当前公众号菜单")
    public Result<String> deleteMenu() {
        String url = URL_MENU_DELETE + TokenThread.accessToken.getAccessToken();
        String res = (String)RestfulWapper.getWapper(url).get("result");
        return Result.make(res);
    }

    @PostMapping("/create")
    @ApiOperation(value = "创建菜单")
    public Result<String> createMenu(@ApiParam(value = "菜单")
                            @RequestBody Menu menu) {
        String url = URL_MENU_CREATE + TokenThread.accessToken.getAccessToken();
        String res = RestfulWapper.postWapper(url, JSON.toJSONString(menu));
        return Result.make(res);
    }


}
