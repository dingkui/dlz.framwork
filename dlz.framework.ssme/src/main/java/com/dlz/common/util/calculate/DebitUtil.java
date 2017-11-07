//package com.dlz.common.util.calculate;
//
//import java.math.BigDecimal;
//import java.math.MathContext;
//import java.math.RoundingMode;
//import java.util.HashMap;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.dlz.common.constants.DictConstants;
///**
// * 借款方费用计算工具类
// * @author Administrator
// *
// */
//public class DebitUtil {
//	private static Logger logger = LoggerFactory.getLogger(DebitUtil.class);
//	/*等额本息还款法的利息计算：
//	等额本息还贷，先算每月还贷本息：BX=a*i(1+i)^N/[(1+i)^N-1]
//	等额本息还贷第n个月还贷本金：
//	B=a*i(1+i)^(n-1)/[(1+i)^N-1]
//	等额本息还贷第n个月还贷利息：
//	X=BX-B= a*i(1+i)^N/[(1+i)^N-1]- a*i(1+i)^(n-1)/[(1+i)^N-1]
//	（注：BX=等额本息还贷每月所还本金和利息总额，
//	B=等额本息还贷每月所还本金，
//	a=贷款总金额
//	i=贷款月利率，
//	N=还贷总月数，
//	n=第n期还贷数
//	X=等额本息还贷每月所还的利息）
//	/**
//	 * 	等额本息
//	 * @param interest   年利率	 需要除以100
//	 * @param loanAmount  贷款金额    单位：分
//	 * @param loanTerm    贷款期限 
//	 * @param loanType    贷款类型
//	 * @param term		  当前期数
//	 * @return
//	 */
// 	@SuppressWarnings("unused")
//	private static HashMap<String,Long> getAverageInterest(Long interest,Long loanAmount,int loanTerm,String loanType,int term){
// 		BigDecimal principal;  			//本金
//		BigDecimal interestAmount;     	//利息
//		BigDecimal repayAmount;			//每月还款额
//		BigDecimal restPrincipal;		//剩余本金
//		
//		BigDecimal bInterest ;      //利率
//		BigDecimal bLoanAmount = new BigDecimal(loanAmount);
//		BigDecimal base = new BigDecimal(1);
//		//BigDecimal bLoanTerm = new BigDecimal(loanAmount);
//		//BigDecimal bTerm = new BigDecimal(term);
//		
//		
//		MathContext interestMc = new MathContext(16, RoundingMode.HALF_DOWN);  
//		MathContext mc = new MathContext(16, RoundingMode.HALF_DOWN);  
////		if(DictConstants.LoanType.THE_DAY_LOAN.equals(loanType)){
////			//天利息
////			bInterest = new BigDecimal(interest).divide(new BigDecimal(3650000),interestMc);
////		}else{
////			//月利息
////			bInterest = new BigDecimal(interest).divide(new BigDecimal(120000),interestMc);
////		}
//		
//		//计算每月/日  还款金额
//		BigDecimal facter = bInterest.add(base).pow(loanTerm);
//		repayAmount = bLoanAmount.multiply(bInterest).multiply(facter).divide(facter.subtract(base),mc);
//		
//		//本金计算
//		BigDecimal Bfacter = bInterest.add(base).pow(term-1);
//		BigDecimal top = bLoanAmount.multiply(bInterest).multiply(Bfacter);
//		BigDecimal bottom =bInterest.add(base).pow(loanTerm).subtract(base);
//		principal = top.divide(bottom,mc);
//		
//		//利息
//		interestAmount = repayAmount.subtract(principal);
//		//剩余本金
//		restPrincipal = bLoanAmount.subtract(principal);
//		HashMap<String,Long> map = new HashMap<String,Long>();
//		map.put("principal", principal.setScale(0,  BigDecimal.ROUND_DOWN).longValue());
//		map.put("interest", interestAmount.setScale(0,  BigDecimal.ROUND_UP).longValue());
//		map.put("repayAmount", repayAmount.setScale(0,  BigDecimal.ROUND_DOWN).longValue());
//		map.put("restPrincipal", restPrincipal.setScale(0,  BigDecimal.ROUND_UP).longValue());
//		logger.debug(map.toString());
//		return map;
//	}
//	
//	/**
//	 * 等额本金
//	 * @param interest
//	 * @param loanAmount
//	 * @param loanTerm  第几期的收益
//	 * @param loanType
//	 * @return
//	 */
//	
//	/*当月应还本金： 本金/贷款总月数
//	当月应还利息: 本金×[1—上个期数/贷款总期数]×月利*/
//	@SuppressWarnings("unused")
//	private static HashMap<String,Long> getAverageCapital(Long interest,Long loanAmount,int loanTerm,String loanType,int term){
//		BigDecimal bLoanAmount = new BigDecimal(loanAmount);
//		BigDecimal bLoanTerm = new BigDecimal(loanTerm);
//		BigDecimal base = BigDecimal.ONE;
//		
//		BigDecimal principal;  			//本金
//		BigDecimal interestAmount;     	//利息
//		BigDecimal repayAmount;			//还款额
//		BigDecimal bInterest ;      	//利率
//		BigDecimal restPrincipal ;		//剩余本金
//		
//		
//		MathContext interestMc = new MathContext(16, RoundingMode.HALF_DOWN);  
//		MathContext mc = new MathContext(16, RoundingMode.HALF_DOWN);  
//		if(DictConstants.LoanType.THE_DAY_LOAN.equals(loanType)){
//			//天利息
//			bInterest = new BigDecimal(interest).divide(new BigDecimal(3650000),interestMc);
//		}else{
//			//月利息
//			bInterest = new BigDecimal(interest).divide(new BigDecimal(120000),interestMc);
//		}
//		
//		//本金
//		principal = bLoanAmount.divide(bLoanTerm,mc);
//		
//		BigDecimal facter =  new BigDecimal(term-1).divide(bLoanTerm,mc);
//		BigDecimal facter1 =  base.subtract(facter);
//		//利息
//		interestAmount = bLoanAmount.multiply(facter1).multiply(bInterest);
//		//每月/日   还款
//		repayAmount = principal.add(interestAmount);
//		//剩余本金
//		BigDecimal facter3 = new BigDecimal(loanTerm-term).divide(bLoanTerm,mc);
//		//BigDecimal facter4 = base.subtract(facter3);
//		restPrincipal = bLoanAmount.multiply(facter3);
//		
//		HashMap<String,Long> map = new HashMap<String,Long>();
//		map.put("principal", principal.setScale(0,  BigDecimal.ROUND_DOWN).longValue());
//		map.put("interest", interestAmount.setScale(0,  BigDecimal.ROUND_UP).longValue());
//		map.put("repayAmount", repayAmount.setScale(0,  BigDecimal.ROUND_DOWN).longValue());
//		map.put("restPrincipal", restPrincipal.setScale(0,  BigDecimal.ROUND_DOWN).longValue());
//		logger.debug(map.toString());
//		return map;
//	}
//	
//	
//	/**
//	 * 到期还本
//	 * @param interest
//	 * @param loanAmount
//	 * @param loanTerm
//	 * @param loanType
//	 * @param term
//	 * @return
//	 */
//	private static HashMap<String,Long> getExpireCapital(Long interest,Long loanAmount,int loanTerm,String loanType,int term){
//		BigDecimal bLoanAmount = new BigDecimal(loanAmount);
//		//BigDecimal bLoanTerm = new BigDecimal(loanTerm);
//		//BigDecimal base = BigDecimal.ONE;
//		
//		BigDecimal principal;  			//本金
//		BigDecimal interestAmount;     	//利息
//		BigDecimal repayAmount;			//还款额
//		BigDecimal bInterest ;      	//利率
//		BigDecimal restPrincipal ;		//剩余本金
//		
//		
//		MathContext interestMc = new MathContext(16, RoundingMode.HALF_DOWN);  
//	//	MathContext mc = new MathContext(16, RoundingMode.HALF_DOWN);  
//		if(DictConstants.LoanType.THE_DAY_LOAN.equals(loanType)){
//			//天利息
//			bInterest = new BigDecimal(interest).divide(new BigDecimal(3650000),interestMc);
//		}else{
//			//月利息
//			bInterest = new BigDecimal(interest).divide(new BigDecimal(120000),interestMc);
//		}
//		
//		interestAmount = bLoanAmount.multiply(bInterest);
//		
//		if(term == loanTerm){
//			principal = bLoanAmount;
//			restPrincipal = BigDecimal.ZERO;
//		}else{
//			principal = BigDecimal.ZERO;
//			restPrincipal = bLoanAmount;
//		}
//		repayAmount = interestAmount.add(principal);
//		
//		HashMap<String,Long> map = new HashMap<String,Long>();
//		map.put("principal", principal.setScale(0,  BigDecimal.ROUND_DOWN).longValue());
//		map.put("interest", interestAmount.setScale(0,  BigDecimal.ROUND_UP).longValue());
//		map.put("repayAmount", repayAmount.setScale(0,  BigDecimal.ROUND_UP).longValue());
//		map.put("restPrincipal", restPrincipal.setScale(0,  BigDecimal.ROUND_DOWN).longValue());
//		logger.debug(map.toString());
//		return map;
//	}
//	public static void main(String[] args) {
//		DebitUtil.getExpireCapital(655L, 20000000L, 120, "2",4);
//	}
//}
