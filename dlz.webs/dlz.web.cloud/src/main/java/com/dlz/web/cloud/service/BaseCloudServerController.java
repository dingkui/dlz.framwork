package com.dlz.web.cloud.service;

import com.dlz.app.uim.holder.UserHolder;
import com.dlz.comm.exception.LogicException;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.util.system.Reflections;
import com.dlz.web.holder.ThreadHolder;
import com.dlz.web.util.AnnoAuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Slf4j
public class BaseCloudServerController {

	protected Object doCloud(HttpServletRequest request, HttpServletResponse response, Object Iservice, String methodName) {
		try {
			Method method = Reflections.getMethodByName(Iservice, methodName);
			if (method == null) {
				throw new NoSuchMethodException("方法不存在");
			}
			
			// 判断访问权限
			int doneAnnoResult = AnnoAuthUtil.doneAnno(Iservice.getClass(),method, UserHolder.getAuthInfo());
			if (doneAnnoResult<0) {
				renderErr(response, HttpStatus.SC_FORBIDDEN,Iservice,methodName, "没有访问权限："+doneAnnoResult);
				return null;
			}
			
			Parameter[] parameters = method.getParameters();
			Object[] paras = new Object[parameters.length];
			for (int i = 0; i < parameters.length; i++) {
				paras[i] = ValUtil.getObj(request.getParameter(parameters[i].getName()), parameters[i].getType());
			}
			return method.invoke(Iservice, ThreadHolder.getHttpRequest(), paras);
		} catch (NoSuchMethodException e) {
			renderErr(response, HttpStatus.SC_NOT_FOUND,Iservice,methodName, e.getMessage());
		} catch (IllegalArgumentException e) {
			renderErr(response, HttpStatus.SC_METHOD_NOT_ALLOWED,Iservice,methodName, "参数有误:"+e.getMessage());
		} catch (IllegalAccessException e) {
			renderErr(response, HttpStatus.SC_NOT_ACCEPTABLE,Iservice,methodName, "参数作用域有误！");
		} catch (InvocationTargetException e) {
			Throwable te = e.getTargetException();
			if (te instanceof LogicException) {
				renderErr(response, HttpStatus.SC_BAD_REQUEST,Iservice,methodName, ((LogicException) te).getInfo());
			} else {
				renderErr(response, HttpStatus.SC_INTERNAL_SERVER_ERROR,Iservice,methodName, te.getMessage());
			}
		} catch (Exception e) {
			renderErr(response, HttpStatus.SC_INTERNAL_SERVER_ERROR,Iservice,methodName, e.getMessage());
		} finally {
//			if (isErr) {
//				logger.info(getInfo(logger, doType + " " + aType, member.getId(), member.getL_id(), datas, m, ui));
//			} else {
//				logger.info(getInfo(logger, doType + " " + aType, datas, m, ui));
//			}
		}
		return null;
	}

	protected static final String contentType = "text/html; charset=UTF-8";
	private void renderErr(HttpServletResponse response,int statu,Object Iservice, String methodName, String msg) {
		msg=Iservice.getClass() + "." + methodName+msg;
		log.error("访问异常：statu="+statu+" msg="+msg);
		response.setStatus(HttpStatus.SC_NOT_FOUND);
		PrintWriter writer = null;
		try {
			response.setContentType(contentType);
			writer = response.getWriter();
			writer.write(msg);
			writer.flush();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			if (writer != null)
				writer.close();
		}
	}
}
