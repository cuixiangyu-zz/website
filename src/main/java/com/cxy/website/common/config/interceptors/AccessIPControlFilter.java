package com.cxy.website.common.config.interceptors;

import com.cxy.website.common.util.IpControlUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 对ip进行控制
 * @author o
 *
 */
@Component
public class AccessIPControlFilter implements Filter {
	public  static String ip;
	private Logger logger = LoggerFactory.getLogger(AccessIPControlFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String ipAddr = IpControlUtil.getIpAddr(req);
		ip=ipAddr;
		chain.doFilter(request, response);
		return;
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}




}
