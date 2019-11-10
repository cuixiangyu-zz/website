package com.cxy.website.common.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.cxy.website.common.constants.ErrorEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Configuration
public class CORSAuthenticationFilter extends FormAuthenticationFilter {


    public CORSAuthenticationFilter() {
        super();
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //Always return true if the request's method is OPTIONSif (request instanceof HttpServletRequest) {
        if ("OPTIONS".equals(((HttpServletRequest) request).getMethod().toUpperCase())) {
            return true;
        }
        return super.isAccessAllowed(request, response, mappedValue);

}

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ErrorEnum.E_4001.getErrorCode());
        jsonObject.put("msg", ErrorEnum.E_4001.getErrorMsg());
        jsonObject.put("msgType",ErrorEnum.E_4001.getMsgType() );
        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        res.setHeader("Access-Control-Allow-Origin", "*");
        // 不使用*，自动适配跨域域名，避免携带Cookie时失效
        String origin = req.getHeader("Origin");
        if(StringUtils.isNotBlank(origin)) {
            res.setHeader("Access-Control-Allow-Origin", origin);
        }
        // 自适应所有自定义头
        String headers = req.getHeader("Access-Control-Request-Headers");
        if(StringUtils.isNotBlank(headers)) {
            res.setHeader("Access-Control-Allow-Headers", headers);
            res.setHeader("Access-Control-Expose-Headers", headers);
        }
        // 允许跨域的请求方法类型
        res.setHeader("Access-Control-Allow-Methods", "*");
        // 明确许可客户端发送Cookie，不允许删除字段即可
        res.setHeader("Access-Control-Allow-Credentials", "true");

        try {
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            out = response.getWriter();
            out.println(jsonObject);
        } catch (Exception e) {
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
        return false;
    }

    @Bean
    public FilterRegistrationBean registration(CORSAuthenticationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}
