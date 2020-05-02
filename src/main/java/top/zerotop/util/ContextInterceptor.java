package top.zerotop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContextInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(ContextInterceptor.class);

    private boolean preHandler(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info(" =====> pre handler");
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {}

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}
}
