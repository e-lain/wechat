package top.zerotop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig{

    @Bean
    public Docket ProductApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(productApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.zerotop.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo productApiInfo() {
        return new ApiInfoBuilder()
                .title("wechat接口测试")
                .description("接口测试")
                .termsOfServiceUrl("http://localhost:8088/wechat" )
                .contact(new Contact("zerotop", "https://0top.github.io", ""))
                .version("2.0")
                .build();
    }
}