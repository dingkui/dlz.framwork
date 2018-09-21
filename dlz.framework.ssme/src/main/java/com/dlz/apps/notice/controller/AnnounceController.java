package com.dlz.apps.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.apps.notice.db.model.Announce;
import com.dlz.apps.notice.db.model.AnnounceCriteria;
import com.dlz.apps.notice.db.service.AnnounceService;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.service.ICommService;
import org.slf4j.Logger;
import com.dlz.framework.ssme.base.controller.BaseController;
import com.dlz.framework.ssme.util.criterias.Criterias;
import com.dlz.framework.util.JacksonUtil;
/**
 * 通告
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/news/announce")
public class AnnounceController extends BaseController{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(AnnounceController.class);
	@Autowired
	 private AnnounceService  announceServices;
	@Autowired
	ICommService commService;
	/**
	 * 页面初始化
	 * @author penghao
	 * @return
	 */
	@RequestMapping()
	public String init() {
		return "msg/news/announce";
	}

	/**
	 * 取得列表信息
	 * @author penghao
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list")
	public Page list(HttpServletRequest request) throws Exception {
		AnnounceCriteria mic = Criterias.buildCriteria(AnnounceCriteria.class, request);
		return announceServices.pageByExample(mic);
	}
	/**
	 * 保存新增页面
	 * @author penghao
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			String data = request.getParameter("data");
			Announce announce1 = JacksonUtil.readValue(data, Announce.class);
			if(announce1.getId()!=null){
				announceServices.updateByPrimaryKey(announce1);
			}else{
				announce1.setId(commService.getSeq(Announce.class));
				announceServices.insert(announce1);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "保存失败";
		}
	
		return "ok";
	}
	/**
	 * 删除
	 * @author penghao
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/del/{id}")
	public String del(@PathVariable(value = "id") long id) throws Exception {
		try{
			announceServices.deleteByPrimaryKey(id);
		}catch (Exception e) {
      return "操作异常";
		}
		return "OK";
	}

}
