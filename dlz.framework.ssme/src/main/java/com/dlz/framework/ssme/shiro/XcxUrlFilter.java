package com.dlz.framework.ssme.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import com.dlz.web.holder.ThreadHolder;

public class XcxUrlFilter extends AuthorizationFilter {

    @Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
    	HttpServletResponse httpResponse = (HttpServletResponse)response;
    	HttpServletRequest httpRequest = (HttpServletRequest)request;
    	ThreadHolder.setHttpRequest(httpRequest);
		ThreadHolder.setHttpResponse(httpResponse);
        return true;
    }
}
