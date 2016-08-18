package com.cxstock.utils.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.utils.system.Constants;


public class SecurityFilter implements Filter {
	@SuppressWarnings("unused")
	private FilterConfig filterCon = null;
	
	public void init(FilterConfig config) throws ServletException {
		filterCon = config;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String path = ((HttpServletRequest) request).getServletPath();
		if(path.startsWith("/front")||path.startsWith("/extensible-1.0.2")||path.startsWith("/mobile")){
			filterChain .doFilter(request, response);
			return;
		}
		UserDTO userInfo = (UserDTO) httpRequest.getSession().getAttribute(Constants.USERINFO);
		String url=httpRequest.getRequestURL().toString();
		if(userInfo==null){
			/*2016-05-25修改/login.jsp为/index.jsp*/
			if(url.indexOf("/index.jsp")==-1){
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/index.jsp");
			}else{
				filterChain .doFilter(request, response);
			}
		}else{
			filterChain .doFilter(request, response);		
		}
	}

	public void destroy() {
		filterCon = null;
	}
}
