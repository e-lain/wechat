package top.zerotop.controller.api;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Component
public class BaseController {

    @ExceptionHandler(Exception.class)
    public String handlerException(Exception e) {
        Map<String, String> map = new HashMap<>();
        map.put("error", "catch exception ..");
        return JSON.toJSONString(map);
    }
}
