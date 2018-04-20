package com.dlz.common.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.http.HttpStatus;

public class ShiroUrlFilter extends AuthorizationFilter {

    @Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        Subject subject = getSubject(request, response);

        boolean isPermitted = true;
        String requestURI = getPathWithinApplication(request);
        
        if (!subject.isPermitted(requestURI)) {
	        	HttpServletResponse httpResponse = (HttpServletResponse)response;
	        	httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
				return false;
			}

        return isPermitted;
    }
}