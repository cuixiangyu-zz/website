package com.cxy.website.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zh
 * @ClassName cn.saytime.Swgger2
 * @Description
 * @date 2017-07-10 22:12:31
 */
@Configuration
@EnableSwagger2
public class Swagger2 {


    @Value("${swagger.show}")
    private boolean swaggerShow;

    /**
     * swagger通过注解表明该接口会生成文档，包括接口名、请求方法、参数、返回信息的等等。
     *
     * @Api：修饰整个类，描述Controller的作用
     * @ApiOperation：描述一个类的一个方法，或者说一个接口
     * @ApiParam：单个参数描述
     * @ApiModel：用对象来接收参数
     * @ApiProperty：用对象接收参数时，描述对象的一个字段
     * @ApiResponse：HTTP响应其中1个描述
     * @ApiResponses：HTTP响应整体描述
     * @ApiIgnore：使用该注解忽略这个API
     * @ApiError ：发生错误返回的信息
     * @ApiImplicitParam：一个请求参数
     * @ApiImplicitParams：多个请求参数
     * @return
     */
    @Bean
    public Docket createRestApi() {
        System.err.println("Swagger2启动");
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.intehel.controller"))
//                .apis(RequestHandlerSelectors.basePackage("com.intehel.admin.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("医院分诊叫号系统")
                .description("新分诊叫号")
                .termsOfServiceUrl("http://127.0.0.1:8080/swagger-ui.html")
                .version("1.0")
                .build();
    }
}