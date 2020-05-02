package top.zerotop.global.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import top.zerotop.util.Result;


/**
 * Created by:zerotop  date:2019/4/22
 */
@ControllerAdvice
public class GlobalException {
    private final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(Exception.class)
    public Result<String> handlerException(Exception e) {
        logger.warn("catch global exception: ", e);
        return Result.make("some wrong happened, please try against later..");
    }
}
