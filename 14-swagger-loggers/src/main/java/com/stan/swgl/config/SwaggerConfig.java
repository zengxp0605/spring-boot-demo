package com.stan.swgl.config;

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

/**
 * @Author：stanzeng
 * @Description：swagger2配置类
 * @Date ：Created in 2020/3/19 3:58 下午
 * @Modified By：
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stan"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("基于Swagger构建的Rest API文档")
                .description("Document demo")
                .contact(new Contact("Stan", "http://www.jasonzeng.top", "jasonzeng0605@gmail.com"))
                .termsOfServiceUrl("http://www.jasonzeng.top")
                .version("1.0")
                .build();
    }
}