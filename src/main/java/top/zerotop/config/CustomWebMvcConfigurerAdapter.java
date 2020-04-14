package top.zerotop.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by:zerotop  date:2019/5/19
 */
@Component
public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    /**
     *
     * 配置拦截器
     *
     * @param registry
     * @author lance
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SocketInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(new ContextInterceptor(userService, Arrays.asList(new String[]{"^/swagger", "/v2/api-docs"}))).addPathPatterns("/**");
    }

    /**
     * 配置MessageConverter中的ObjectMapper：允许将空对象转成Json字符串。
     *
     * @param converters
     */
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        for (HttpMessageConverter converter : converters) {
//            if (converter instanceof MappingJackson2HttpMessageConverter) {
//                MappingJackson2HttpMessageConverter mappingJsonConverter = (MappingJackson2HttpMessageConverter) converter;
//                mappingJsonConverter.getObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//            }
//        }
//    }
}