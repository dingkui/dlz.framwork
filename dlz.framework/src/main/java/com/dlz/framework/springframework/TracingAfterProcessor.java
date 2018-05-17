package com.dlz.framework.springframework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.springframework.scaner.MySpringScaner;
/**
 * spring 启动完成后执行
 * @author dingkui
 *
 */
@Component
public class TracingAfterProcessor implements ApplicationListener<ContextRefreshedEvent> {
	private static MyLogger logger=MyLogger.getLogger(TracingAfterProcessor.class);
	private boolean complete(ApplicationContext applicationContext){
		try{
			if(applicationContext instanceof AbstractXmlApplicationContext){
				return true;
			}else{
				//web启动
				return applicationContext.getParent() != null;
			}
		}finally{
			logger.debug("ContextRefreshedEvent done。。。"+applicationContext);
		}
	}
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		if(complete(applicationContext)){
			logger.debug("Spring 初始化完成。。。"+applicationContext);
			MySpringScaner.init();
		}
	}
}