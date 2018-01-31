package com.dlz.framework.ssme.util.string;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import com.dlz.framework.util.DateUtil;
import com.dlz.framework.util.StringUtils;


public class CreateCode{
	private static String LOAN_APPLY = "A";
	private static String LOAN_CODE = "B";
	private static String CONTRAT_CODE = "HT";
	public static final AtomicInteger seed19 = new AtomicInteger(1);
	public static final AtomicInteger seed6 = new AtomicInteger(1);
	
	public static String getLoanCode19(String loanType,Integer Id) {
//		seed19.compareAndSet(100000, 1);
//		return LOAN_CODE+getTimestamp() + StringUtils.leftPad(String.valueOf(seed19.getAndIncrement()), 5, '0');
		String str = null;
		if(Id.toString().length() == 1){
			str = "000"+Id.toString();
		}else if(Id.toString().length() == 2){
			str = "00"+Id.toString();
		}else if(Id.toString().length() == 3){
			str = "0"+Id.toString();
		}else{
			str = Id.toString();
		}
	    return LOAN_CODE + loanType + getTimestamp() + str;
	}
	public static String getApplyCode19(String loanType,Integer Id) {
//		seed19.compareAndSet(100000, 1);
//		return LOAN_APPLY+getTimestamp()+ StringUtils.leftPad(String.valueOf(seed19.getAndIncrement()), 5, '0');
		String str = null;
		if(Id.toString().length() == 1){
			str = "000"+Id.toString();
		}else if(Id.toString().length() == 2){
			str = "00"+Id.toString();
		}else if(Id.toString().length() == 3){
			str = "0"+Id.toString();
		}else{
			str = Id.toString();
		}
	    return LOAN_APPLY + loanType + getTimestamp() + str;
	}
	
	public static String getContractCode6(){
		seed6.compareAndSet(100000, 1);
		return CONTRAT_CODE+getTimestampCont() + StringUtils.leftPad(String.valueOf(seed6.getAndIncrement()), 0, '0');
	}

    private static String getTimestamp(){
       /* Long currentTime = System.currentTimeMillis();
        String str = String.valueOf(currentTime);
    	return str.substring(4, str.length());*/
    	Date today = new Date();
    	return DateUtil.getDateStrCompact(today);
    }
    
    private static String getTimestampCont(){
    	 Long currentTime = System.currentTimeMillis();
         String str = String.valueOf(currentTime);
     	return str.substring(8, str.length());
    }
      
}