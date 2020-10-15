//package com.dlz.framework.springframework;
//
//import com.dlz.framework.holder.SpringHolder;
//import com.dlz.framework.spring.scaner.DlzSpringScaner;
//import com.dlz.framework.spring.scaner.IScaner;
//import com.dlz.framework.springframework.scaner.IScaner;
//import com.dlz.framework.springframework.scaner.MySpringScaner;
//import org.slf4j.Logger;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.context.support.AbstractXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//import java.util.Map.Entry;
///**
// * spring 启动完成后执行
// * @author dingkui
// *
// */
//@Component
//public class TracingAfterProcessor implements ApplicationListener<ContextRefreshedEvent> {
//	private static Logger logger=org.slf4j.LoggerFactory.getLogger(TracingAfterProcessor.class);
//	private boolean complete(ApplicationContext applicationContext){
//		try{
//			if(applicationContext instanceof AbstractXmlApplicationContext){
//				return true;
//			}else{
//				//web启动
//				return applicationContext.getParent() != null;
//			}
//		}finally{
//			logger.debug("ContextRefreshedEvent done。。。"+applicationContext);
//		}
//	}
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent event) {
//		ApplicationContext applicationContext = event.getApplicationContext();
//		if(complete(applicationContext)){
//			logger.debug("Spring 初始化完成。。。"+applicationContext);
//
//			Map<String, IScaner> beans = SpringHolder.getBeans(IScaner.class);
//			DlzSpringScaner mySpringScaner = new DlzSpringScaner();
//			if (!beans.isEmpty()) {
//				for (Entry<String, IScaner> entry : beans.entrySet()) {
//					mySpringScaner.doComponents(entry.getValue());
////					SpringHolder.unregisterBean(entry.getKey());
//				}
//				SpringHolder.getBeanFactory().preInstantiateSingletons();
//			}
//		}
//	}
//}