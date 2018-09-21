package com.dlz.apps.sys.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.apps.ControllerConst;
import com.dlz.framework.db.modal.Page;
import org.slf4j.Logger;
import com.dlz.framework.ssme.db.model.SysLog;
import com.dlz.framework.ssme.db.model.SysLogCriteria;
import com.dlz.framework.ssme.db.service.SysLogService;
import com.dlz.framework.ssme.util.criterias.Criterias;
import com.dlz.framework.ssme.util.office.Excels;
import com.dlz.framework.util.StringUtils;
import com.google.common.collect.ImmutableMap;

/**
 * 系统日志控制器类
 * 方法上的注释为页面中Button的标题
 */
@Controller
@RequestMapping(value = ControllerConst.ADMIN+"/rbac/sysLog")
public class SysLogController {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(SysLogController.class);

	@Autowired
	private SysLogService sysLogService;

	@RequestMapping()
	public String init() {
		return "sys/rbac/sysLog";
	}

	/*
	 * 显示-日志列表
	 */
	@ResponseBody
	@RequestMapping(value = "list")
	public Page list(HttpServletRequest request) {
		try {
			SysLogCriteria uc = Criterias.buildCriteria(SysLogCriteria.class,
					request);
			return sysLogService.pageByExample(uc);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	/*
	 * 清空日志-将日志全部删除
	 */
	@ResponseBody
	@RequestMapping(value = "delete")
	public boolean delete() {
		try {
			int count = sysLogService.deleteByExample(null);
			if(count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}
	/*
	 * 导出日志
	 */
	@ResponseBody
	@RequestMapping(value = "exportSysLog")
	public boolean exportSysLog(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			//获取页面列表中显示的数据
			SysLogCriteria criteria = Criterias.buildCriteria(SysLogCriteria.class, request);
			List<SysLog> logList = sysLogService.selectByExample(criteria);
			
			if(StringUtils.isNotEmpty(logList)) {
				//将数据传到Excels类中并以ImmutableMap.of()中的格式进行处理
				byte[] excelContent = Excels.exportExcel("系统日志列表", ImmutableMap.of(
						"userId", "操作者", 
						"operTime", "操作时间", 
						"url", "功能地址",
						"param", "参数"), logList,
						"yyyy/mm/dd hh:mm:ss");
				
				//设置文档标编码、类型、和格式后从网络中导出
				String fileName = "系统日志列表";
				if (request.getHeader("User-Agent").toUpperCase().contains("MSIE")) {
					fileName = URLEncoder.encode(fileName, "UTF-8");
				} else {
					fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
				}
				response.setHeader("Content-Disposition", "attachment;filename="
						+ fileName + ".xls");
				response.setContentType("application/vnd.ms-excel");
				response.getOutputStream().write(excelContent);
				
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}
}
