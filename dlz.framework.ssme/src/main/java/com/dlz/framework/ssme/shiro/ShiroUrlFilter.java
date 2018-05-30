package com.dlz.framework.ssme.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.http.HttpStatus;

import com.dlz.web.holder.ThreadHolder;

public class ShiroUrlFilter extends AuthorizationFilter {

    @Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
    	ThreadHolder.setHttpRequest((HttpServletRequest)request);
    	ThreadHolder.setHttpResponse((HttpServletResponse)response);
    	Subject subject = getSubject(request, response);

        boolean isPermitted = true;
        String requestURI = getPathWithinApplication(request);
        
    	if(requestURI.startsWith("/api_xcx")){
    		return isPermitted;
    	}
        
        if (!subject.isPermitted(requestURI)) {
	        	HttpServletResponse httpResponse = (HttpServletResponse)response;
	        	httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
				return false;
			}

        return isPermitted;
    }
}