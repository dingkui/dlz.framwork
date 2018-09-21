package com.dlz.framework.ssme.util.criterias;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.dlz.framework.db.modal.Page;
import org.slf4j.Logger;
import com.dlz.framework.ssme.util.web.Servlets;
import com.dlz.framework.util.StringUtils;
import com.dlz.framework.util.system.Reflections;
import com.google.common.base.CaseFormat;

public class Criterias {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(Criterias.class);
	private static final int DEFAULT_PAGE_SIZE = 100;
	
	public static <T> T buildCriteria(Class<T> criteriaClass, HttpServletRequest request, Map<String, Object> extendParam,List<String> conditionLst) {
		return buildCriteria(criteriaClass, request, -1, extendParam);
	}
	
	public static <T> T buildCriteria(Class<T> criteriaClass, HttpServletRequest request, Map<String, Object> extendParam) {
		return buildCriteria(criteriaClass, request, -1, extendParam);
	}

	public static <T> T buildCriteria(Class<T> criteriaClass, HttpServletRequest request) {
		return buildCriteria(criteriaClass, request, null);
	}

	public static <T> T buildCriteria(Class<T> criteriaClass,Map<String, Object> searchParams,Page page, String orderBy) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		if(filters == null) {
			return null;
		}
		return bySearchFilter(criteriaClass, filters.values(), page, orderBy);
	}
	
	public static <T> T buildCriteria(Class<T> criteriaClass, HttpServletRequest request,int pageSize,Map<String, Object> extendParam) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		if(StringUtils.isNotEmpty(extendParam)) {
			searchParams.putAll(extendParam);
		}
		Map<String, Object> attrParams = Servlets.getAttrbuteStartingWith(request, "search_");
		if(StringUtils.isNotEmpty(attrParams)) {
			searchParams.putAll(attrParams);
		}
		Page page = null;
		if(pageSize==-1){
			page=buildPage(request);
		}else{
			page=buildPage(request,pageSize);
		}
		String orderBy = buildOrderBy(request);
		T t = null;
		try {
			t = buildCriteria(criteriaClass, searchParams, page, orderBy);
		} catch(Exception e) {
			logger.error(e.getMessage(),e);
		}
		return t;
	}
	
	public static Page buildPage(ServletRequest request) {
		int pageNum = 1;
		int pagesizeNum = DEFAULT_PAGE_SIZE;
		String page = request.getParameter("pageIndex");
		String pagesize = request.getParameter("pageSize");
		if(StringUtils.isEmpty(page)) {
			return null;
		}
		pageNum = Integer.parseInt(page);
		if (StringUtils.isNotEmpty(pagesize)) {
			pagesizeNum = Integer.parseInt(pagesize);
		}
		return new Page(pageNum, pagesizeNum);
	}
	
	public static Page buildPage(ServletRequest request,int pageSize) {
		int pageNum = 0;
		int pagesizeNum = pageSize;
		String page = request.getParameter("pageIndex");
		if(!StringUtils.isEmpty(page)) {
			pageNum = Integer.parseInt(page);
		}
		return new Page(pageNum, pagesizeNum);
	}
	
	private static <T> T bySearchFilter(Class<T> criteriaClass,Collection<SearchFilter> filters, Page page, String orderBy) {
		T t = Reflections.newInstance(criteriaClass);
		Object criteria = Reflections.invokeMethod(t, "createCriteria", null, null);
		if (StringUtils.isNotEmpty(filters)) {
			try {
				for (SearchFilter filter : filters) {
					invokeAndOperatorMethod(criteria, filter);
				}
			} catch (Exception e) {
				throw Reflections.convertReflectionExceptionToUnchecked(e);
			}
		}
		if (page != null) {
			Reflections.setFieldValue(t, "page", page);
		}
		if (StringUtils.isNotEmpty(orderBy)) {
			Reflections.setFieldValue(t, "orderByClause", orderBy);
		}
		return t;
	}

	private static void invokeAndOperatorMethod(Object criteria,SearchFilter filter) throws IllegalAccessException,InvocationTargetException {
		if("condition".equals(filter.operator.value)){
			Method addOperatorMethod = Reflections.getAccessibleMethodByName(criteria, "addCriterion");
			addOperatorMethod.invoke(criteria, new Object[] { filter.value });
			return;
		}
		Method andOperatorMethod = Reflections.getAccessibleMethodByName(criteria, "and" + StringUtils.capitalize(filter.fieldName)
						+ filter.operator.value);
		Class<?>[] paramClassEs = andOperatorMethod.getParameterTypes();
		//add by dk 2015-3-31 无参数时异常
		if(paramClassEs.length==0){
			andOperatorMethod.invoke(criteria, new Object[]{});
			return;
		}
		Class<?> paramClass = andOperatorMethod.getParameterTypes()[0];
		if(paramClass == null) {
			throw new IllegalArgumentException("param name error");
		}
		if (paramClass == String.class || paramClass == List.class) {
			andOperatorMethod.invoke(criteria, new Object[] { filter.value });
		} else if (paramClass == Date.class) {
			String value = (String) filter.value;
			Date date = null;
			if (value.length() == 10) {
				date = DateTime.parse(value,
						DateTimeFormat.forPattern("yyyy-MM-dd")).toDate();
			} else if(value.length() == 16) {
				date = DateTime.parse(value,
						DateTimeFormat.forPattern("yyyy-MM-dd HH:mm"))
						.toDate();
			} else if (value.length() == 19) {
				date = DateTime.parse(value,
						DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"))
						.toDate();
			} else if (value.length() == 18) {
				date = DateTime.parse(value,
						DateTimeFormat.forPattern("yyyy-MM-dd H:mm:ss"))
						.toDate();
			} else {
				throw new IllegalArgumentException("data format error");
			}
			andOperatorMethod.invoke(criteria, new Object[] { date });
		} else if (paramClass == Short.class || paramClass == Integer.class
				|| paramClass == Long.class || paramClass == Float.class
				|| paramClass == Double.class || paramClass == BigDecimal.class) {
			try {
				Object value=filter.value;
				if(!paramClass.isInstance(filter.value)){
					value = Reflections.newInstance(paramClass,
							new Class<?>[] { String.class },
							new Object[] { filter.value });
				}
				andOperatorMethod.invoke(criteria, new Object[] { value });
			} catch(Exception e) {
				throw new IllegalArgumentException("param type error");
			}
		} else {
			throw new IllegalArgumentException("param type error" + paramClass.getName());
		}
	}

	public static String buildOrderBy(ServletRequest request) {
		String orderBy = null;
		//以下两个字段名应与前台传过来的对应，否则无法实现排序
		String sortname = request.getParameter("sortField");
		String sortorder = request.getParameter("sortOrder");
		if(StringUtils.isEmpty(sortname)) {
			return null;
		}
		orderBy = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, sortname);
		if (StringUtils.isNotEmpty(sortorder)) {
			orderBy += " " + sortorder.toUpperCase();
		}
		return orderBy;
	}

}
