package top.zerotop.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.zerotop.domain.menu.Menu;
import top.zerotop.service.MenuService;
import top.zerotop.util.Result;

@RestController
@Api(value = "公众号菜单管理", description = "公众号菜单管理")
@RequestMapping(value = "/menu", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class MenuController extends BaseController {
    @Autowired
    protected MenuService menuService;

    @GetMapping("/get")
    @ApiOperation(value = "获取当前公众号菜单")
    public Result<Menu> getMenu() {
        return Result.make(menuService.getMenu());
    }

    @GetMapping("/delete")
    @ApiOperation(value = "获取当前公众号菜单")
    public Result<String> deleteMenu() {
        return Result.make(menuService.deleteMenu());
    }

    @PostMapping("/create")
    @ApiOperation(value = "创建菜单")
    public Result createMenu(@ApiParam(value = "菜单")
                             @RequestBody Menu menu) {
        return Result.make(menuService.createMenu(url, menu));
    }
}
