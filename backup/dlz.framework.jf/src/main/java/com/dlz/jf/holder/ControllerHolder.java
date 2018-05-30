package com.dlz.jf.holder;

import com.dlz.framework.logger.MyLogger;
import com.jfinal.core.Controller;


/**
 *  用ThreadLocal来存储request,response
 * @author dk 2017-06-15
 * @version 1.1
 */
public class ControllerHolder  {
	protected static final MyLogger logger = MyLogger.getLogger(ControllerHolder.class);
	
	private static ThreadLocal<Controller> ControllerThreadLocalHolder = new ThreadLocal<Controller>();
	public static void setController(Controller controller){
		ControllerThreadLocalHolder.set(controller);
	}
	public static Controller getController(){
		return  ControllerThreadLocalHolder.get();
	}
}
