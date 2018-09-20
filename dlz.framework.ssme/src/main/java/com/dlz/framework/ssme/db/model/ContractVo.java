package com.dlz.framework.ssme.db.model;

import java.util.List;
@SuppressWarnings({"rawtypes"})
public class ContractVo {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	
	//合同编号
	private String contractNum;
	//借款人姓名
	private String borrower;
	//借款人平台用户名
	private String plateformName;
	//借款人身份证号
	private String idcard;
	//出借人
	private String lender;
	//出借人平台用户名
	private String lenderNames;
	//借出金额
	private String lendAmount;
	//出借人预期收益
	private String profitInAd;
	//投资收益列表
	private List profitList; 
	//担保方
	private String guarantee;
	//担保方地址
	private String guaranteeAddr;
	//项目编号
	private String projectNum;
	//借款用途
	private String loanFor;
	//借款金额（小写）
	private String loanAmount;
	//借款金额(大写)
	private String capital;
	//借款利率
	private String loanFee;
	//借款期限
	private String loanTerm;
	//还款方式
	private String repayType;
	//生效时间
	private String effectiveDay;
	//还款计划
	private List repayList;
	//合同模版路径
	private String url;
	//合同起还日
	private String startDate;
	//还款终止日
	private String endDate;
	//还款日
	private String date;
	//借款手续费
	private String fee;
	//借款手续费率
	private String feeRate;
	//借款管理费
	private String manageFee;
	//借款管理费率
	private String manageFeeRate;
	//借款管理费总额
	private String totalManageFee;
	//合同章
	private String stamp;
	//平台logo
	private String logo;
	/**  
	 * 获取contractNum
	 * @return contractNum contractNum  
	 */
	public String getContractNum() {
		return contractNum;
	}
	/** 
	 * 设置contractNum 
	 * @param contractNum contractNum 
	 */
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	/**  
	 * 获取borrower
	 * @return borrower borrower  
	 */
	public String getBorrower() {
		return borrower;
	}
	/** 
	 * 设置borrower 
	 * @param borrower borrower 
	 */
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	/**  
	 * 获取plateformName
	 * @return plateformName plateformName  
	 */
	public String getPlateformName() {
		return plateformName;
	}
	/** 
	 * 设置plateformName 
	 * @param plateformName plateformName 
	 */
	public void setPlateformName(String plateformName) {
		this.plateformName = plateformName;
	}
	/**  
	 * 获取idcard
	 * @return idcard idcard  
	 */
	public String getIdcard() {
		return idcard;
	}
	/** 
	 * 设置idcard 
	 * @param idcard idcard 
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	/**  
	 * 获取projectNum
	 * @return projectNum projectNum  
	 */
	public String getProjectNum() {
		return projectNum;
	}
	/** 
	 * 设置projectNum 
	 * @param projectNum projectNum 
	 */
	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}
	/**  
	 * 获取loanFor
	 * @return loanFor loanFor  
	 */
	public String getLoanFor() {
		return loanFor;
	}
	/** 
	 * 设置loanFor 
	 * @param loanFor loanFor 
	 */
	public void setLoanFor(String loanFor) {
		this.loanFor = loanFor;
	}
	/**  
	 * 获取loanAmount
	 * @return loanAmount loanAmount  
	 */
	public String getLoanAmount() {
		return loanAmount;
	}
	/** 
	 * 设置loanAmount 
	 * @param loanAmount loanAmount 
	 */
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	/**  
	 * 获取capital
	 * @return capital capital  
	 */
	public String getCapital() {
		return capital;
	}
	/** 
	 * 设置capital 
	 * @param capital capital 
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}
	/**  
	 * 获取loanFee
	 * @return loanFee loanFee  
	 */
	public String getLoanFee() {
		return loanFee;
	}
	/** 
	 * 设置loanFee 
	 * @param loanFee loanFee 
	 */
	public void setLoanFee(String loanFee) {
		this.loanFee = loanFee;
	}
	/**  
	 * 获取loanTerm
	 * @return loanTerm loanTerm  
	 */
	public String getLoanTerm() {
		return loanTerm;
	}
	/** 
	 * 设置loanTerm 
	 * @param loanTerm loanTerm 
	 */
	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}
	/**  
	 * 获取effectiveDay
	 * @return effectiveDay effectiveDay  
	 */
	public String getEffectiveDay() {
		return effectiveDay;
	}
	/** 
	 * 设置effectiveDay 
	 * @param effectiveDay effectiveDay 
	 */
	public void setEffectiveDay(String effectiveDay) {
		this.effectiveDay = effectiveDay;
	}
	/**  
	 * 获取repayList
	 * @return repayList repayList  
	 */
	public List getRepayList() {
		return repayList;
	}
	/** 
	 * 设置repayList 
	 * @param repayList repayList 
	 */
	public void setRepayList(List repayList) {
		this.repayList = repayList;
	}
	/**  
	 * 获取guarantee
	 * @return guarantee guarantee  
	 */
	public String getGuarantee() {
		return guarantee;
	}
	/** 
	 * 设置guarantee 
	 * @param guarantee guarantee 
	 */
	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}
	/**  
	 * 获取guaranteeAddr
	 * @return guaranteeAddr guaranteeAddr  
	 */
	public String getGuaranteeAddr() {
		return guaranteeAddr;
	}
	/** 
	 * 设置guaranteeAddr 
	 * @param guaranteeAddr guaranteeAddr 
	 */
	public void setGuaranteeAddr(String guaranteeAddr) {
		this.guaranteeAddr = guaranteeAddr;
	}
	/**  
	 * 获取url
	 * @return url url  
	 */
	public String getUrl() {
		return url;
	}
	/** 
	 * 设置url 
	 * @param url url 
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**  
	 * 获取lender
	 * @return lender lender  
	 */
	public String getLender() {
		return lender;
	}
	/** 
	 * 设置lender 
	 * @param lender lender 
	 */
	public void setLender(String lender) {
		this.lender = lender;
	}
	/**  
	 * 获取lenderNames
	 * @return lenderNames lenderNames  
	 */
	public String getLenderNames() {
		return lenderNames;
	}
	/** 
	 * 设置lenderNames 
	 * @param lenderNames lenderNames 
	 */
	public void setLenderNames(String lenderNames) {
		this.lenderNames = lenderNames;
	}
	/**  
	 * 获取profitList
	 * @return profitList profitList  
	 */
	public List getProfitList() {
		return profitList;
	}
	/** 
	 * 设置profitList 
	 * @param profitList profitList 
	 */
	public void setProfitList(List profitList) {
		this.profitList = profitList;
	}
	/**  
	 * 获取lendAmount
	 * @return lendAmount lendAmount  
	 */
	public String getLendAmount() {
		return lendAmount;
	}
	/** 
	 * 设置lendAmount 
	 * @param lendAmount lendAmount 
	 */
	public void setLendAmount(String lendAmount) {
		this.lendAmount = lendAmount;
	}
	/**  
	 * 获取repayType
	 * @return repayType repayType  
	 */
	public String getRepayType() {
		return repayType;
	}
	/** 
	 * 设置repayType 
	 * @param repayType repayType 
	 */
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}
	/**  
	 * 获取profitInAd
	 * @return profitInAd profitInAd  
	 */
	public String getProfitInAd() {
		return profitInAd;
	}
	/** 
	 * 设置profitInAd 
	 * @param profitInAd profitInAd 
	 */
	public void setProfitInAd(String profitInAd) {
		this.profitInAd = profitInAd;
	}
	/**  
	 * 获取startDate
	 * @return startDate startDate  
	 */
	public String getStartDate() {
		return startDate;
	}
	/** 
	 * 设置startDate 
	 * @param startDate startDate 
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**  
	 * 获取endDate
	 * @return endDate endDate  
	 */
	public String getEndDate() {
		return endDate;
	}
	/** 
	 * 设置endDate 
	 * @param endDate endDate 
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**  
	 * 获取date
	 * @return date date  
	 */
	public String getDate() {
		return date;
	}
	/** 
	 * 设置date 
	 * @param date date 
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**  
	 * 获取fee
	 * @return fee fee  
	 */
	public String getFee() {
		return fee;
	}
	/** 
	 * 设置fee 
	 * @param fee fee 
	 */
	public void setFee(String fee) {
		this.fee = fee;
	}
	/**  
	 * 获取feeRate
	 * @return feeRate feeRate  
	 */
	public String getFeeRate() {
		return feeRate;
	}
	/** 
	 * 设置feeRate 
	 * @param feeRate feeRate 
	 */
	public void setFeeRate(String feeRate) {
		this.feeRate = feeRate;
	}
	/**  
	 * 获取manageFee
	 * @return manageFee manageFee  
	 */
	public String getManageFee() {
		return manageFee;
	}
	/** 
	 * 设置manageFee 
	 * @param manageFee manageFee 
	 */
	public void setManageFee(String manageFee) {
		this.manageFee = manageFee;
	}
	/**  
	 * 获取manageFeeRate
	 * @return manageFeeRate manageFeeRate  
	 */
	public String getManageFeeRate() {
		return manageFeeRate;
	}
	/** 
	 * 设置manageFeeRate 
	 * @param manageFeeRate manageFeeRate 
	 */
	public void setManageFeeRate(String manageFeeRate) {
		this.manageFeeRate = manageFeeRate;
	}
	/**  
	 * 获取totalManageFee
	 * @return totalManageFee totalManageFee  
	 */
	public String getTotalManageFee() {
		return totalManageFee;
	}
	/** 
	 * 设置totalManageFee 
	 * @param totalManageFee totalManageFee 
	 */
	public void setTotalManageFee(String totalManageFee) {
		this.totalManageFee = totalManageFee;
	}
	/**  
	 * 获取stamp
	 * @return stamp stamp  
	 */
	public String getStamp() {
		return stamp;
	}
	/** 
	 * 设置stamp 
	 * @param stamp stamp 
	 */
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	/**  
	 * 获取logo
	 * @return logo logo  
	 */
	public String getLogo() {
		return logo;
	}
	/** 
	 * 设置logo 
	 * @param logo logo 
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
}