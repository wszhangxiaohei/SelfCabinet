package com.selfcabinet.config.SwaggerConfig;

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

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.selfcabinet.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("自提柜 API文档")
                //创建人
                .contact(new Contact("wszhangxiaohei", "https://github.com/wszhangxiaohei/SelfCabinet", "1012710130@qq.com"))
                //版本号
                .version("1.0")
                //描述
                .description("后台接口")
                .build();
    }
}

