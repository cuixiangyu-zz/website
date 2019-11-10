package com.cxy.website.common.config.interceptors;/*
package com.intehel.common.config.interceptors;

import com.alibaba.fastjson.JSONObject;
import com.intehel.common.util.web.JsonData;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*/
/**
 * @program: hospcall
 * @description: 统一封装返回数据
 * @author: CuiXiangYu
 * @create: 2019-10-17 10:13
 **//*

@ControllerAdvice
public class DateIntercepter implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return false;
    }

    */
/**
     * 如果返回参数不是JsonData或JSONObject ，封装成JsonData
     * @param o     返回参数
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     *//*

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//        if(o instanceof JsonData||o instanceof JSONObject){
//            return o;
//        }
//        return JsonData.buildSuccess(o);
        return o;
    }
}
*/
