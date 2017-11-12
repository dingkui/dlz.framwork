package com.dlz.framework.ssme.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class RoleAuthorizationFilter extends AuthorizationFilter {  
  
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)  
            throws IOException {  
  
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();  
        String[] rolesArray = (String[]) mappedValue;  
  
        if (rolesArray == null || rolesArray.length == 0) {  
            // no roles specified, so nothing to check - allow access.  
            return true;  
        }  
  
        for (String role : rolesArray) {  
            if (subject.hasRole(role)) {  
                return true;  
            }  
        }  
        return false;  
    }  
  
} 