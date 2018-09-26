package com.dlz.apps.notice.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlz.apps.notice.db.model.Mailtemp;
import com.dlz.apps.notice.db.model.MailtempCriteria;
import com.dlz.apps.notice.db.model.MailtempCriteria.GeneratedCriteria;
import com.dlz.apps.notice.db.service.MailtempService;
import com.dlz.framework.cache.AbstractCache;
import org.slf4j.Logger;
/**
 * 用级别信息缓存
 * @author dingkui
 */
@Component
public class MailTemplateCache extends AbstractCache<String, String> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	/**
	 * 日志logger
	 */
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(MailTemplateCache.class);
	public MailTemplateCache() {
		super(MailTemplateCache.class.getSimpleName());
		dbOperator=new DbOperator() {
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
