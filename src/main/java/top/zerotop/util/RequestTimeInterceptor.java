package top.zerotop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestTimeInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(RequestTimeInterceptor.class);

    private NamedThreadLocal<Long> startTime = new NamedThreadLocal<Long>("requestStartTime");

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("-----++++++++++++++++-------");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("-----++++++++++++++++-------");

        long beginTime = System.nanoTime();
        startTime.set(beginTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.nanoTime();
        long beginTime = startTime.get();
        long spendTime = endTime - beginTime;

//        String timingMsg = String.format("[%d ms] [%d] [%s] %s %s", spendTime, response.getStatus(), getRemoteIP(request), request.getMethod(), request.getRequestURI());
        String timingMsg = String.format("[%d ms] [%s] %s %s", spendTime, getRemoteIP(request), request.getMethod(), request.getRequestURI());
        logger.info(timingMsg);
    }

    private String getRemoteIP(HttpServletRequest request) {
        String remoteIP = request.getHeader("x-forwarded-for");
        if (remoteIP != null) {
            return remoteIP;
        }
        return request.getRemoteAddr();
    }
}
