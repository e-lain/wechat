package top.zerotop.controller.api;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    @ExceptionHandler(Exception.class)
    public String handlerException() {
        Map<String, String> map = new HashMap<>();
        map.put("error", "catch exception ..");
        return JSON.toJSONString(map);
    }
}
