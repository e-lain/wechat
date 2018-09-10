package top.zerotop.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "测试api")
@RequestMapping(value = "/api", produces = "application/json;charset=utf-8")
public class testController {

    @ApiOperation(value = "测试api请求")
    @GetMapping(value = "/swagger", produces = "application/json;charset=utf-8")
    public @ResponseBody String testRequest() {

        return "hello swagger tester 你好 测试者";
    }

}
