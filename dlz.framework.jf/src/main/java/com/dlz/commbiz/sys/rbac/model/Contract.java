package com.dlz.commbiz.sys.rbac.model;

import java.util.Date;
import java.util.List;
@SuppressWarnings({"rawtypes"})
public class Contract {
	
	//合同ID
    private Integer contractId; 
   
    private String contractCode;
    
    private String contractName;

    private String contractUrl;

    private Date createDate;

    private Integer createUser;

    private String createUserName;

    private String contractType;

    private String contractInfo;
    //合同编号
    
		private String contract_num;
    //合同生效日期
    private String sign_date;
    //甲方（出借人）
    private String lenders;
    //金魔方用户名
    private String auserName;
    //总计
    private String amountCount;
    //借出金额
    private String amountLend;
    //借款期限
    private String lendMonth;
    //还款计划
		private List repayLst;
    //乙方真实姓名
    private String realName;
    //乙方身份证号
    private String idNunber;
    //乙方金魔方用户名
    private String buserName;
    //公章图片
    private String imagePath;
    
    
    
    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode == null ? null : contractCode.trim();
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl == null ? null : contractUrl.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType == null ? null : contractType.trim();
    }

    public String getContractInfo() {
        return contractInfo;
    }

    public void setContractInfo(String contractInfo) {
        this.contractInfo = contractInfo == null ? null : contractInfo.trim();
    }

		/**  
		 * 获取contract_num
		 * @return contract_num contract_num  
		 */
		public String getContract_num() {
			return contract_num;
		}

		/** 
		 * 设置contract_num 
		 * @param contract_num contract_num 
		 */
		public void setContract_num(String contract_num) {
			this.contract_num = contract_num;
		}

		/**  
		 * 获取sign_date
		 * @return sign_date sign_date  
		 */
		public String getSign_date() {
			return sign_date;
		}

		/** 
		 * 设置sign_date 
		 * @param sign_date sign_date 
		 */
		public void setSign_date(String sign_date) {
			this.sign_date = sign_date;
		}

		/**  
		 * 获取lenders
		 * @return lenders lenders  
		 */
		public String getLenders() {
			return lenders;
		}

		/** 
		 * 设置lenders 
		 * @param lenders lenders 
		 */
		public void setLenders(String lenders) {
			this.lenders = lenders;
		}

		/**  
		 * 获取auserName
		 * @return auserName auserName  
		 */
		public String getAuserName() {
			return auserName;
		}

		/** 
		 * 设置auserName 
		 * @param auserName auserName 
		 */
		public void setAuserName(String auserName) {
			this.auserName = auserName;
		}

		/**  
		 * 获取amountCount
		 * @return amountCount amountCount  
		 */
		public String getAmountCount() {
			return amountCount;
		}

		/** 
		 * 设置amountCount 
		 * @param amountCount amountCount 
		 */
		public void setAmountCount(String amountCount) {
			this.amountCount = amountCount;
		}

		/**  
		 * 获取amountLend
		 * @return amountLend amountLend  
		 */
		public String getAmountLend() {
			return amountLend;
		}

		/** 
		 * 设置amountLend 
		 * @param amountLend amountLend 
		 */
		public void setAmountLend(String amountLend) {
			this.amountLend = amountLend;
		}

		/**  
		 * 获取lendMonth
		 * @return lendMonth lendMonth  
		 */
		public String getLendMonth() {
			return lendMonth;
		}

		/** 
		 * 设置lendMonth 
		 * @param lendMonth lendMonth 
		 */
		public void setLendMonth(String lendMonth) {
			this.lendMonth = lendMonth;
		}

		/**  
		 * 获取repayLst
		 * @return repayLst repayLst  
		 */
		public List getRepayLst() {
			return repayLst;
		}

		/** 
		 * 设置repayLst 
		 * @param repayLst repayLst 
		 */
		public void setRepayLst(List repayLst) {
			this.repayLst = repayLst;
		}

		/**  
		 * 获取realName
		 * @return realName realName  
		 */
		public String getRealName() {
			return realName;
		}

		/** 
		 * 设置realName 
		 * @param realName realName 
		 */
		public void setRealName(String realName) {
			this.realName = realName;
		}

		/**  
		 * 获取idNunber
		 * @return idNunber idNunber  
		 */
		public String getIdNunber() {
			return idNunber;
		}

		/** 
		 * 设置idNunber 
		 * @param idNunber idNunber 
		 */
		public void setIdNunber(String idNunber) {
			this.idNunber = idNunber;
		}

		/**  
		 * 获取buserName
		 * @return buserName buserName  
		 */
		public String getBuserName() {
			return buserName;
		}

		/** 
		 * 设置buserName 
		 * @param buserName buserName 
		 */
		public void setBuserName(String buserName) {
			this.buserName = buserName;
		}

		/**  
		 * 获取imagePath
		 * @return imagePath imagePath  
		 */
		public String getImagePath() {
			return imagePath;
		}

		/** 
		 * 设置imagePath 
		 * @param imagePath imagePath 
		 */
		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}
    
}