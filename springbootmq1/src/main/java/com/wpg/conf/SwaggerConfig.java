package com.wpg.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.wpg.controller")).build();
    }
    private ApiInfo apiInfo(){
        Contact contact = new Contact("DSL","http:/www.admin.cn/","1336866384@qq.com");
        return new ApiInfoBuilder().title("MQ项目API接口")
                .description("再小的帆")
                .contact(contact)
                .version("0.1")
                .build();
    }
}
