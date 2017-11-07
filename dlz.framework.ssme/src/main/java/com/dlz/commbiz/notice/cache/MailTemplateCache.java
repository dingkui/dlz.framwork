package com.dlz.commbiz.notice.cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlz.commbiz.notice.model.Mailtemp;
import com.dlz.commbiz.notice.model.MailtempCriteria;
import com.dlz.commbiz.notice.model.MailtempCriteria.GeneratedCriteria;
import com.dlz.commbiz.notice.service.MailtempService;
import com.dlz.framework.cache.AbstractCache;
/**
 * 用级别信息缓存
 * @author dingkui
 */
@Component
public class MailTemplateCache extends AbstractCache<String, String> {
	/**
	 * 日志logger
	 */
	private static Logger logger = LoggerFactory.getLogger(MailTemplateCache.class);
	public MailTemplateCache() {
		super(MailTemplateCache.class.getSimpleName());
		dbOperator=new DbOperator<String, String>() {
			@Override
			public String getFromDb(String member_id) {
				GeneratedCriteria cartList=new MailtempCriteria().createCriteria().andMSignEqualTo(member_id);
				List<Mailtemp> templateList;
				try {
					templateList = mailtempService.selectByExample(cartList);
					if(!templateList.isEmpty())
						return templateList.get(0).getmCon();
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
				return null;
			}
		};
	}
	/**
	 */
	@Autowired
	private MailtempService mailtempService;

}
