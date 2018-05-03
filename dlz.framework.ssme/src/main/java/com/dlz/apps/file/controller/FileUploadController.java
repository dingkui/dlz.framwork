package com.dlz.apps.file.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dlz.apps.ControllerConst;
import com.dlz.apps.file.db.model.Files;
import com.dlz.apps.file.db.service.FilesService;
import com.dlz.apps.file.enums.FileTypeEnum;
import com.dlz.framework.ssme.base.controller.BaseController;
import com.dlz.framework.ssme.shiro.ShiroUser;
import com.dlz.framework.ssme.util.config.ConfigUtil;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.StringUtils;

/**
 * 单位性质
 * 
 */
@Controller
@RequestMapping(value = ControllerConst.ADMIN+"/fileUpload")
public class FileUploadController extends BaseController {
	@Autowired
	FilesService filesService;
	/*
	 * 页面初始化
	 * 
	 * @return
	 */
	@RequestMapping(value = "/")
	public String init() {
		return "/file/fileUpload";
	}

	/*
	 * 取得logo列表信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toUpload")
	public String toUpload(HttpServletRequest request,Model model) throws Exception {
		String compid = request.getParameter("compid");
		String actUrl = request.getParameter("actUrl");
		model.addAttribute("compid", compid);
		model.addAttribute("actUrl", actUrl);
		return "/file/fileUp";
	}

	@ResponseBody
	@RequestMapping(value = "/doUpload")
	public Files doUpload(HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> fileNames = multipartRequest.getFileNames();
		String dType = request.getParameter("dType");
		String saveDb = request.getParameter("saveDb");
		Files files=null;
		while(fileNames.hasNext()){
			MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
			ShiroUser loginUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			String name = multipartFile.getOriginalFilename();
			String prefix=name.substring(name.lastIndexOf("."));
			
		  Map<String,Object> m=new HashMap<String,Object>();
			m.put("fSurfix", prefix);
			m.put("fName", name);
			m.put("saveFolder", request.getParameter("saveFolder"));
			m.put("dataId", request.getParameter("dataId"));
			m.put("userName", loginUser.getUserName());
			m.put("userId", loginUser.getUserId());
			m.put("fOrd", request.getParameter("fOrd"));
			if(!StringUtils.isEmpty(saveDb)){
				m.put("saveDb", false);
			}
			files = filesService.saveFile(multipartFile.getInputStream(), FileTypeEnum.valueOf(dType),m);
			files.setfName(name);
			files.setfSurfix(prefix);
		}
		return files;
	}
	
	@ResponseBody
	@RequestMapping(value = "/imgUpload")
	public String imgUpload(HttpServletRequest request) throws Exception {
		String files = request.getParameter("images");
		files = JacksonUtil.readValue(files, String.class);
		String[] fileGroup = JacksonUtil.readValue(files, String[].class);
		for(int index = 0 ; index < fileGroup.length ; index ++){
			String picBase64 = fileGroup[index].substring(fileGroup[index].indexOf("base64,")+7);
			byte[] b =  Base64.decode(picBase64.getBytes("UTF-8"));
			String imgFilePath = "d://" + index+1 + ".jpg";//新生成的图片  
		      OutputStream out = new FileOutputStream(imgFilePath);      
		      out.write(b);  
		      out.flush();  
		      out.close();  
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getFiles")
	public List<Files> getFiles(Long dataId,String dType,String fOrd) throws Exception {
		if(dataId == null || dType == null){
			return new ArrayList<Files>();
		}
		return filesService.getFiles(dataId, fOrd, FileTypeEnum.valueOf(dType));
	}
	
	@ResponseBody
	@RequestMapping(value = "/delFile")
	public String delFile(Long id) throws Exception {
		filesService.delFile(id);
		return "ok";
	}
	
	/*
	 * 合同
	 * 
	 * @param model
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editFiles/{dtype}/{id}")
	public String editFiles(HttpServletRequest request,@PathVariable(value = "id") Integer id,@PathVariable(value = "dtype") FileTypeEnum dtype, Model model) {
		try {
			model.addAttribute("dType", dtype.toString());
			model.addAttribute("dataId", id);
			model.addAttribute("fileTypeName", request.getParameter("ftn"));
			model.addAttribute("canAdd", "1".equals(request.getParameter("canAdd")));
			String nginxUrl = ConfigUtil.getConfig("nginxServer");
			model.addAttribute("nginxUrl", nginxUrl);
			return "/file/editFiles";
		} catch (Exception e) {
			return null;
		}
	}
	
	/*
	 * 取得文件地址
	 * 
	 * @param model
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getFileUrls/{dtype}/{id}")
	public Map<String, String> getFileUrls(HttpServletRequest request,@PathVariable(value = "id") Long id,@PathVariable(value = "dtype") FileTypeEnum dtype, Model model) {
		try {
			List<Files> fls=filesService.getFiles(id, null, dtype);
			String url=null;
			if(fls.size()>0){
				url=fls.get(fls.size()-1).getfPath();
			}
			return filesService.getFileUrls(url);
		} catch (Exception e) {
			return null;
		}
	}
}
