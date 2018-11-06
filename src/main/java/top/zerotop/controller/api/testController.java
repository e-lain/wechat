package top.zerotop.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "测试api")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class testController {

    @ApiOperation(value = "测试api请求")
    @GetMapping(value = "/swagger/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String testRequest() {
        String str = "hello swagger tester 你好 测试者";
        return str;
    }

}
