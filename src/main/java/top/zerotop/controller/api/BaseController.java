package top.zerotop.controller.api;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import top.zerotop.util.Result;

import java.util.HashMap;
import java.util.Map;

@Component
public class BaseController {

    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e) {
        Map<String, String> map = new HashMap<>();
        map.put("error", "catch exception ..");
        return Result.make(map);
    }
}
