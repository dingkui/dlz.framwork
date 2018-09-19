//package com.dlz.framework.ssme.tag;
//
//import java.util.Collection;
//
//import javax.servlet.jsp.jstl.core.ConditionalTagSupport;
//
//import org.apache.shiro.SecurityUtils;
//
//import com.dlz.framework.util.JacksonUtil;
//import com.dlz.framework.util.StringUtils;
//import com.dlz.framework.util.ValUtil;
//
///**
// * <p>用户认证信息判断</p>
// *
// * @author dk
// */
//
//public class UserIfTag extends ConditionalTagSupport {
//	private static final long serialVersionUID = 5468604723660375731L;
//	String property=null;
//	String vals=null;
//    public String getProperty() {
//		return property;
//	}
//	public void setProperty(String property) {
//		this.property = property;
//	}
//	public String getVals() {
//		return vals;
//	}
//	public void setVals(String vals) {
//		this.vals = vals;
//	}
//	protected boolean condition() {
//		Object o=JacksonUtil.at(SecurityUtils.getSubject().getPrincipal(),property);
//		if(o==null){
//			return false;
//		}
//		if(vals==null){
//			return true;
//		}
//		
//		if(o instanceof Object[] || o instanceof Collection){
//			o=StringUtils.join(ValUtil.getArray(o),",");
//		}
//		String strs=ValUtil.getStr(o);
//		String[] checks=vals.split(",");
//		
//		for(String v:strs.split(",")){
//			if(checkIn(v, checks)){
//				return true;
//			}
//		}
//        return false;
//    }
//	private boolean checkIn(String check,String[] group){
//		for(String v:group){
//			if(v.equals(check)){
//				return true;
//			}
//		}
//		return false;
//	}
//}
