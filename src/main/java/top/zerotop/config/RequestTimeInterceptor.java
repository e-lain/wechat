package top.zerotop.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestTimeInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(RequestTimeInterceptor.class);

    private NamedThreadLocal<Long> startTime = new NamedThreadLocal<Long>("requestStartTime");

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        startTime.set(System.currentTimeMillis());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startTime.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long spendTime = System.currentTimeMillis() - startTime.get();

        String timingMsg = String.format("[%s] [%d ms] [%s] %s %s", request.getRequestURI(), spendTime, getRemoteIP(request), request.getMethod(), request.getRequestURI());
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
