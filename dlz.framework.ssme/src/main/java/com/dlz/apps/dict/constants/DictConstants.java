package com.dlz.apps.dict.constants;

public class DictConstants {
	/*
	public static final String DICT_EDUCATION = "COMMON_EDUCATION";  //学历
	public static final String DICT_INVESTMENT_FORM = "INVESTMENT_FORM";  //出资形式
	public static final String DICT_INVESTMENT_TYPE = "INVESTMENT_TYPE";  //投资方式
	public static final String DICT_SHAREHOLDER_TYPE = "SHAREHOLDER_TYPE";  //股东类型
	public static final String DICT_COMMON_MARRIAGE = "COMMON_MARRIAGE";  //婚姻状态
	public static final String DICT_COMMON_PUB = "COMMON_PUB";  //发布状态
	public static final String DICT_LOAN_STATUS = "LOAN_STATUS";  //贷款状态
	public static final String DICT_COMMON_HAS = "COMMON_HAS";  //有无
	public static final String DICT_LOAN_CHANNEL = "LOAN_CHANNEL";  //贷款信息渠道
	public static final String DICT_REPAY_TYPE = "REPAY_TYPE";  //贷款还款方式
	public static final String DICT_STRUCTURE_TYPE = "STRUCTURE_TYPE";  //企业规模结构
*/	
	/*
	 * 是否
	 */
	public static class YesOrNo{
		/*
		 * 是
		 */
		public static final String YES ="1";
		/*
		 * 否
		 */
		public static final String NO ="2";
		
	}
	
	/*
	 * 图片审核状态
	 */
	public static class SHSTATUS {
		/*
		 * 草稿
		 */
		public static final String DRAFT = "0";
	
		/*
		 * 审核通过
		 */
		public static final String PASS = "1";
		
		/*
		 * 归档
		 */
		public static final String ARCHIVE = "2";
	}
	
	/**
	 * 利润分配审核状态
	 *
	 */
	public static class PROFITSTATUS {
		/*
		 * 草稿
		 */
		public static final String DRAFT = "0";
	
		/*
		 * 审核通过
		 */
		public static final String PASS = "1";
		
		/*
		 * 拒绝
		 */
		public static final String DECLINE = "-1";
	}
	
	/*
	 * 合伙人审核状态
	 */
	public static class STATUS {
		/*
		 * 审核完善资料
		 */
		public static final String DRAFT = "0";
	
		/*
		 * 财务审核
		 */
		public static final String FINANCE = "1";
		
		/*
		 * 审核通过
		 */
		public static final String PASS = "3";
		
		/*
		 * 拒绝
		 */
		public static final String REJECT = "4";
	}
	
	
	public static class APPLYSTATUS{
		/*
		 * 申请中
		 */
		public static final String DRAFT = "0";
		
		/*
		 * 付款完成
		 */
		public static final String PAYED = "0";
	
	
	}
	
	/**
	 *项目状态
	 */
	public static class ProjectStatus {
		public static final String TJ = "1"; // 土建
		public static final String NZSJZB = "2";// 内装设计招标
		public static final String NZSJ = "3";// 内装设计
		public static final String YST = "4";// 已上图
		public static final String NZZB = "5";// 内装招标
		public static final String NZJC = "6";// 内装进场
		public static final String ZBXJ = "7";// 总包询价
		public static final String YBJSQ = "8";// 样板间申请
		public static final String SWTP = "9";// 商务谈判
		public static final String HTQM = "10";// 合同签订
		public static final String ZBCG = "11";// 总包采购
		public static final String SFGH = "12";// 是否供货
		public static final String SGAZ = "13";// 施工安装
		public static final String ZJHK = "14";// 资金回款
	}
	
	public static class TaskType{
		/*
		 *  
		 */
		public static final String PRO_UPDATE = "1";
		/*
		 *  
		 */
		public static final String PRO_CONFIRM = "2";
		
	}
	
	public static class SubType{
		
		public static final String HELP_DESIGN = "1";
		public static final String HELP_OWNER = "2";
		public static final String HELP_CONS = "3";
	}
	
	
	/*
	 * 有效状态
	 */
	public static class EffectStatus{
		/*
		 * 有效
		 */
		public static final String ENABLE ="1";
		/*
		 * 无效
		 */
		public static final String DISABLE ="0";
		/*
		 * 删除
		 */
		public static final String DELETE ="-1";
	}
	
	/*
	 * 会员类型
	 */
	public static class MemberType{
		/*
		 * 普通用户
		 */
		public static final String COMMON ="10";
		/*
		 * 商户
		 */
		public static final String MERCHANT ="20";
	}
	
	/*
	 * 会员类型
	 */
	public static class HelpType{
		/*
		 * 设计方
		 */
		public static final String DESIGN ="1";
		/*
		 * 业主方
		 */
		public static final String OWNER ="2";
		/*
		 * 施工方
		 */
		public static final String CONS ="3";
	}
	
	/*
	 * 性别
	 */
	public static class Sex{
		/*
		 * 男
		 */
		public static final String MAN ="1";
		/*
		 * 女
		 */
		public static final String WOMAN ="2";
	}
	
	/*
	 * 学历
	 */
	public static class COMMON_EDUCATION{
		/*
		 * 高中
		 */
		public static final String SENIOR_HIGH_SCHOOL ="1";
		/*
		 * 专科
		 */
		public static final String COLLEGE ="2";
		/*
		 * 本科
		 */
		public static final String UNDERGRADUATE_COLLEGE ="3";
		/*
		 * 研究生
		 */
		public static final String POSTGRADUATE ="4";
		/*
		 * 博士
		 */
		public static final String DOCTOR ="5";
		/*
		 * 其他
		 */
		public static final String OTHER ="6";
	}
	
	/*
	 * 用户组类型
	 */
	public static class GroupType{
		/*
		 * 系统管理员
		 */
		public static final String SYS_MANAGER = "1";
		/*
		 * 总经理
		 */
		public static final String MANAGER = "2";
		/*
		 * 风控主管
		 */
		public static final String RISK_MANAGER = "3";
		/*
		 * 业务主管
		 */
		public static final String BUSSINESS_MANAGER = "4";
		/*
		 * 风控员
		 */
		public static final String RISK_MAN = "5";
		/*
		 * 业务员
		 */
		public static final String BUSINESS_MAN = "6";
		/*
		 * 客服主管
		 */
		public static final String SERVICE_MANAGER = "7";
		/*
		 * 客服
		 */
		public static final String SERVICE_MAN = "8";
		/*
		 * 财务
		 */
		public static final String FINANCE = "9";
	}
	/*
	 * 订单状态
	 */
	public static class OrdStatus{
		/*
		 * 申请
		 */
		public static final String APPLY ="1";
		/*
		 * 现场勘查 
		 */
		public static final String XCKC ="2";
		/*
		 * 平面图
		 */
		public static final String PMT ="3";
		/*
		 * 上传楼书，概况图
		 */
		public static final String LS="4";
		/*
		 * 工程现场勘察表
		 */
		public static final String XCKCB ="5";
		/*
		 * 上传合同
		 */
		public static final String SCHT ="6";
		 
		
	} 
	
	/**
	 * 文件类型
	 * @author Administrator
	 *
	 */
	public static class FileType{
		//楼书
		public static final String ls ="1";
		//合同
		public static final String ht ="2";
		//施工图纸
		public static final String sgtz ="3";
		//打款凭证
		public static final String dkpz ="4"; 
		//排期表
		public static final String pqb ="5"; 
	}
	 
	
	/*
	 * 信用等级
	 */
	public static class CreditLevel{
		/*
		 * 一级  AA
		 */
		public static final int ONE =1;
		/*
		 * 二级 A 
		 */
		public static final int TWO =2;
		/*
		 * 三级 B
		 */
		public static final int THERE =3;
		/*
		 * 四级 C
		 */
		public static final int FOUR =4;
		/**
		 * 五级 D
		 */
		public static final int FIVE =5;
		
		/**
		 * E
		 */
		public static final String SIX ="6";
		
		/**
		 * HR
		 */
		public static final String SEVEN ="7";
	}
	
	/*
	 * 用户等级
	 */
	public static class MemberLevel{
		/*
		 * 一级
		 */
		public static final String ONE ="01";
		/*
		 * 二级
		 */
		public static final String TWO ="02";
	}
	
	//合同模板类型
	public static class ContractType{
		/*
		 * 借款合同
		 */
		public static final String loan_apply ="1";
		/*
		 * 投资合同
		 */
		public static final String bid_loan ="2";
		/*
		 * 授信合同
		 */
		public static final String bank_crdit ="3";
	}
	
	/*
	 * 标的审核状态
	 */
	public static class CheckStatus{
		/*
		 * 通过
		 */
		public static final String CHECK_PASS ="1";
		/*
		 * 不通过
		 */
		public static final String NOT_PASS ="2";
		/*
		 * 退回
		 */
		public static final String BACK_TO_PREV ="3";
	}
	/*
	 * 公司类型
	 */
	public static class CompanyType{
		
		/*
		 * 外资(欧美)
		 */
		public static final String  FOREIGN_EUROPE_AMERICA ="1";
		/*
		 * 外资(非欧美)
		 */
		public static final String FOREIGN_NO_EUROPE_AMERICA ="2";
		/*
		 * 合资(欧美)
		 */
		public static final String JOINT_VENTURES_EUROPE_AMERICA ="3";
		/*
		 * 合资(非欧美)
		 */
		public static final String JOINT_VENTURES_NO_EUROPE_AMERICA ="4";
		/*
		 * 国企
		 */
		public static final String ENTERPRISES ="5";
		/*
		 * 民营公司
		 */
		public static final String PRIVATE_SECTOR ="6";
		/*
		 * 外企代表处
		 */
		public static final String FOREIGN_REPRESENTATIVE_OFFICE ="7";
		/*
		 * 事业单位
		 */
		public static final String PUBLIC_INSTITUTION ="8";
		/*
		 * 非盈利机构
		 */
		public static final String NONPROFIT_ORG ="9";
		/*
		 * 其他性质
		 */
		public static final String OTHER_PROPERTIES ="10";
	}
	
	/*
	 * 启用状态
	 */
	public static class EnableStatus{
		/*
		 * 启用
		 */
		public static final String  ABLE_STATUS ="1";
		/*
		 * 禁用
		 */
		public static final String  DISABLE_STATUS ="2";
	}
	 
	/*
	 * 图片类型
	 */
	public static class ImageType{
		/*
		 * 清晰
		 */
		public static final String  IMAGE_CLEAR ="1";
		/*
		 * 马赛克
		 */
		public static final String  IMAGE_MOSAIC ="2";
		/*
		 * 水印
		 */
		public static final String  IMAGE_WATERWET ="3";
	}
	
	/*
	 * 认证类型
	 */
	public static class ApproveType{
		/*
		 * 身份证认证
		 */
		public static final String  ID_CARD_APPR ="01";
		/*
		 * 工作认证
		 */
		public static final String  WORK_APPR ="02";
		/*
		 * 信用报告认证
		 */
		public static final String  BELEVE_APPR ="03";
		/*
		 * 收入认证
		 */
		public static final String  INCOME_APPR ="04";
		/*
		 * 房产认证
		 */
		public static final String  HOUSE_APPR ="05";
		/*
		 * 审计报告
		 */
		public static final String  WEED_CERT ="06";
		/*
		 * 学历认证
		 */
		public static final String  GRADE_APPR ="07";
		/*
		 * 居住地认证
		 */
		public static final String  PLACE_APPR ="08";
		/*
		 * 实地认证
		 */
		public static final String  FACT_AREA_APPR ="09";
		/*
		 * 车辆认证
		 */
		public static final String  CAR_APPR ="10";
		/*
		 * 其他认证
		 */
		public static final String  OTHER_APPR ="11";
	}
	
	/*
	 * 认证审核状态
	 */
	public static class ApproveStatus{
		/*
		 * 未审核
		 */
		public static final String WAIT_CHECK = "0";
		/*
		 * 审核通过
		 */
		public static final String CHECK_PASS = "1";
		/*
		 * 审核不通过
		 */
		public static final String CHECK_NO_PASS = "2";
	}
	
	/*
	 * 认证方式
	 */
	public static class ApproveMethod{
		/*
		 * 人工认证
		 */
		public static final String HAND = "1";
		/*
		 * 自动认证
		 */
		public static final String AUTO = "2";
	}
	
	//以下为未入库的数据字典
	public static class LoanProfitStaStatus{
		/*
		 * 收益中
		 */
		public static final String  IN_PROFIT ="1";
		/*
		 * 收益已完成
		 */
		public static final String  END_PROFIT ="2";
		/*
		 * 逾期
		 */
		public static final String  NOT_PROFIT ="3";
		/*
		 * 坏账
		 */
		public static final String  BAD_PROFIT ="4";
	}
	public static class SuccessOrFail{
		/*
		 * 成功
		 */
		public static final String  SUCCESS ="1";
		/*
		 * 失败
		 */
		public static final String  FAIL ="2";
		
	}
	
	//扣款类型
	public static class DeductionType{
		/*
		 * 自动扣款
		 */
		public static final String  AOTU_DEDUCTION ="1";
		/*
		 * 人工扣款
		 */
		public static final String  HAND_DEDUCTION ="2";
		
	}
	
	//消息类型
	public static class MessageType{
		/*
		 * 系统通知
		 */
		public static final String  MES_SYSTEM ="1";
		/*
		 * 魔方活动
		 */
		public static final String  MES_CUBE ="2";
		/*
		 * 投标完成
		 */
		public static final String  MES_BIDFINISH ="3";
		/*
		 * 流标通知
		 */
		public static final String  MES_FLOW  ="4";
		/*
		 * 项目回款
		 */
		public static final String  MES_PROFIT="5";
		/*
		 * 提现通知
		 */
		public static final String  MES_WITHDRAW="6";
		
		
		public static final String SYSTEM = "01"; // 系统消息
		public static final String INOUT = "02"; // 充值提现
		public static final String PROJECT = "03"; // 项目消息
		public static final String PROMOTION = "04"; // 活动推广
	}
	
	//流标类型
	public static class FlowType{
		/*
		 * 过期自动流标
		 */
		public static final String  AOTU_FLOW ="1";
		/*
		 * 人工流标
		 */
		public static final String  HAND_FLOW ="2";
		
	}
	
	//消息已读未读状态
	public static class MessageStatus{
		/*
		 * 过期自动流标
		 */
		public static final String  HAS_READ ="1";
		/*
		 * 人工流标
		 */
		public static final String  NOT_READ ="2";
		
	}
	
	/*
	 * 消息读取状态
	 */
	public static class MsgReadStatus{
		/*
		 * 未读
		 */
		public static final String  NOT_READ ="1";
		/*
		 * 已读
		 */
		public static final String  HAD_READ="2";
		/*
		 * 删除
		 */
		public static final String  DELETE ="3";
	}
	
	//还款类型
	public static class UserRepayType{
		/*
		 * 当期还款
		 */
		public static final String  CURRENT_REPAY ="1";
		/*
		 * 全额还款
		 */
		public static final String  ALL_REPAY ="2";
		
	}
	
	
	public static class PlanType{
		
		/*
		 * 计划未执行
		 */
		public static final String  INIT_PLAN ="1";
		/*
		 * 计划已执行
		 */
		public static final String  FINISH_PLAN ="2";
		/*
		 * 计划已删除
		 */
		public static final String  DELE_PLAN ="3";
	}
	
	
	public static class RateType{
		
		/*
		 * 利息管理费
		 */
		public static final String  TYPE_PROFIT ="1";
		/*
		 * 提现管理费
		 */
		public static final String  TYPE_CASHOUT ="2";
	}
	
   public static class NewsType{
		
		/*
		 * 公告
		 */
		public static final String  NOTICE ="1";
		
		public static final String  PUBLISH ="2";

		public static final String  FINANCE ="3"; 
		
		public static final String  LOANNOTICE ="4"; 
		
	}
	
   //新闻发布状态
	public static class COMMON_PUB{
		
		/*
		 * 未发布状态DD
		 */
		public static final String  PUB_OFF ="1";
		
		/*
		 * 发布状态
		 */
		public static final String  PUB_ON ="2";
		
		
	}
	
	/*
	 * 年收入范围
	 */
	public static class IncomeRange{
		/*
		 * 五千元以下
		 */
		public static final String FLOWFIVE="5000元以下";
		/*
		 * 五千至一万元
		 */
		public static final String FIVETOW="5000~10000元";
		/*
		 * 一万元以上
		 */
		public static final String HIGHTOW="10000元以上";
	}
	
	/*
	 * 费率类型
	 */
	public static class FeeRateType{
		/*
		 * 收益费率
		 */
		public static final String INCOME_FEE_RATE="1";
		/*
		 * 提现费率
		 */
		public static final String REFLECT_FEE_RATE="2";
	}
	
	/*
	 * 申请来源
	 */
	public static class ApplySource{
		/*
		 * 门户申请
		 */
		public static final String PORTAL="1";
		/*
		 * 后台申请
		 */
		public static final String SYS_BACK="2";
	}
	
	/*
	 * 游客申请
	 */
	public static class CustomApplyStatus{
		/*
		 * 未处理
		 */
		public static final String UNTREATED = "1";
		/*
		 * 已受理
		 */
		public static final String DEAL_WITH="2";
		/*
		 * 拒绝
		 */
		public static final String REFUSED="3";
		/*
		 *  待申请借款
		 */
		public static final String WAIT_LAON = "4";
		
		/*
		 *  已申请借款
		 */
		public static final String APPLY_LAON = "5";
		
	}
	
	/*
	 * 充值订单状态
	 */
	public static class DepositOrderStatus{
		/*
		 * 未处理
		 */
		public static final String NO_DOING="1";
		/*
		 * 充值成功
		 */
		public static final String SUCCESS = "2";
		/*
		 * 充值失败
		 */
		public static final String FAIL="3";
	}
	
	/*
	 * 提现订单审核状态
	 */
	public static class CheckWithDrawOrd{
		/**
		 * 乾多多申请-处理中
		 */
		public static final String M3_APPLY_DEALING = "m1";
		
		/*
		 * 处理中
		 */
		public static final String DEALING = "1";
		
		/**
		 * 乾多多订单审核-失败.
		 */
		public static final String M3_AUDIT_FAIL = "m2";
		
		/*
		 * 提现成功
		 */
		public static final String SUCCESS="2";
		
		/*
		 * 审核通过
		 */
		public static final String PASS="4"; 
		 
		/*
		 * 提现退票
		 */
		public static final String ROLLBACK="5";
		
		/*
		 * 批次处理中
		 */
		public static final String BATCHING ="6";
		
		/*
		 *提现失败 
		 */
		public static final String FAIL ="7";
		
	}
	
	/*
	 * 提现对账状态
	 */
	public static class WithAcctPreStatus{
		/*
		 * 未对账
		 */
		public static final String NO_VEROF_ACCT="0"; 
		 
		/*
		 * 已对账
		 */
		public static final String HAD_VEROF_ACCT="1";
	}
	
	/*
	 * 提现对账明细状态
	 */
	public static class WithAcctDetailStatus{
		/*
		 * 未对账
		 */
		public static final String NO_VEROF_ACCT="0"; 
		/*
		 * 对账成功
		 */
		public static final String VEROF_ACCT_SUCCESS="1";
		/*
		 * 交易状态不正确
		 */
		public static final String DEAL_STATUS_ERROR="2"; 
		/*
		 * 交易金额不正确
		 */
		public static final String DEAL_AMOUNT_ERROR="3";
		/*
		 * 对方账户不正确
		 */
		public static final String TOS_ACCT_ERROR="4"; 
		/*
		 * 订单不存在
		 */
		public static final String NO_EXITS_ORDER="5";
	}
	
	/*
	 * 对账结果
	 */
	public static class TrxDetailResult{
		
		/*
		 * 未对账
		 */
		public static final String NO_DOING ="0";
		/*
		 * 对账成功
		 */
		public static final String SUCCESS="1";
		/*
		 * 对账失败
		 */
		public static final String FAIL="2";
	}
	
	/**
	 * 批次提现状态
	 * @author Administrator
	 *
	 */
	public static class BatchWithdrawStatus {
		/**
		 * 10：待发送 初始状态
		 */
		public static final String BATCH_WITHDRAW_STATUS_NO_SEND = "10";
		
		/**
		 * 11:发送中   中间状态  表示是否放入队列   
		 */
		public static final String BATCH_WITHDRAW_STATUS_SENDING ="11";
	
		/**
		 * 12:发送失败  提现订单可以重新勾选 重建批次
		 */
		public static final String BATCH_WITHDRAW_STATUS_SEND_FAIL = "12";
		
		/**
		 * 20：待查询批次结果
		 */
		public static final String BATCH_WITHDRAW_STATUS_QUERY = "20";
		
		/**
		 *:21：接受通联通知   url方式   待查询批次结果立即处理   处理失败->变为20状态
		 */
		public static final String BATCH_WITHDRAW_STATUS_QUERY_NOTICE ="21";
		
		/**
		 * 22:取得交易通知结果中 中间状态    明细全部取得结果状态变为30 否则变为20;
		 */
		public static final String BATCH_WITHDRAW_STATUS_QUERYING ="22";
		
		/**
		 * :30：全部完成
		 */
		public static final String BATCH_WITHDRAW_STATUS_SUCC = "30";
		
		/**
		 * 31：全部失败
		 */
		public static final String BATCH_WITHDRAW_STATUS_FAIL = "31";
		
		
	
	}
	
	/**
	 * 批次明细状态
	 * @author Administrator
	 *
	 */
	public static class BatchWithdrawDetlStatus{
		/**
		 * 0：发送 初始状态
		 */
		public static final String BATCH_WITHDRAW_DETL_STATUS_SEND = "0";
		/**
		 * 1：成功
		 */
		public static final String BATCH_WITHDRAW_DETL_STATUS_SUCC = "1";
		/**
		 * 2：失败
		 */
		public static final String BATCH_WITHDRAW_DETL_STATUS_FAILURE = "2";
	}
	
	/**
	 * 提现明细 算费标记
	 * @author Administrator
	 *
	 */
	public static class IPSStatus{
		/**
		 * 0：已经发送
		 */
		public static final String send = "0";
		/**
		 * 成功
		*/
		public static final String sucess = "1";
		/**
		 * 重复
		 */
		public static final String error = "2";
	}
	/**
	 * 提现明细 算费标记
	 * @author Administrator
	 *
	 */
	public static class BatchWithdrawDetlCaluateFeeStatus{
		/**
		 * 0：未算费
		 */
		public static final String BATCH_WITHDRAW_DETL_CALCULATE_FEE_NO = "0";
		/**
		 * 1：已算费
		 */
		public static final String BATCH_WITHDRAW_DETL_CALCULATE_FEE_YES = "1";
	}
	
	/**
	 * 身份验证状态
	 * @author Administrator
	 *
	 */
	public static class IdValidationStatus {
		public static final String PROCESSED = "1";// 1：已认证
		public static final String UN_PROCESSED = "2";// 2:未认证
		public static final String IN_PROCESS = "3";// 3:认证处理中
	}
	
	public static class Certification{
		
		public static final String ENTERPRISE = "enterprise";
		public static final String PERSON = "person";
	}
	
	public static class TransferStatus {
		/*
		 * 处理中
		 */
		public static final String PROCESSING = "0";

		/*
		 * 转账成功
		 */
		public static final String SUCC = "1";

		/*
		 * 转账失败
		 */
		public static final String FAIL = "2";
	}

	public static class TransferCheckStatus {
		/*
		 * 未对账
		 */
		public static final String NO = "0";
		/*
		 * 已对帐
		 */
		public static final String YES = "1";
	}
 
	public static class BankCardType {
		public static final String DEBIT_CARD = "0"; // 0#借记卡 1#信用卡
		public static final String CREDIT_CARD = "1";
	}
	
	/*
	 * 优惠券状态
	 */
	public static class CouponState{
		/*
		 * 未激活
		 */
		public static final String UNACTIVE ="1";
		/*
		 * 已激活
		 */
		public static final String ACTIVE ="2";
		/*
		 * 兑换中
		 */
		public static final String USING ="3";
		/*
		 * 已兑换
		 */
		public static final String USED ="4";
		/*
		 * 已过期
		 */
		public static final String EXPIRE ="5";
	}
	
	/*
	 * 优惠券类型
	 */
	public static class CouponType{
		/*
		 * 自动发放
		 */
		public static final String AUTO_PROVIDE ="1";
		/*
		 * 人工发放
		 */
		public static final String MANUAL_PROVIDE ="2";
		/*
		 * 积分兑换
		 */
		public static final String POINT_EXCHANGE ="3";
		/*
		 * 奖励
		 */
		public static final String BONUS ="4";
	}
	
	/**
	 * 优惠券来源
	 * @author zhuwb
	 *
	 */
	public static class CouponSrc {
		public static final String REGISTER = "1";// 1.注册
		public static final String BACK_MANUAL = "2";// 2.人工后台发放
		public static final String POINT_EXCHANGE = "3";// 3.积分兑换
	}
	
	public static class PayResult{
		public static final String NOTPAY = "0";// 0.尚未支付
		public static final String PAYSUCCESS = "1";// 1.支付成功
		public static final String PAYFAILED = "2";// 2.支付失败
	}
	
	public static class MemberTypeId{
		public static final String PERSON = "1";// 1.个人会员
		public static final String COMPANY = "2";// 2.招聘会员
	}
	
	/**
	 * 文档模板类型
	 */
	public static class TmplDocType{
		private TmplDocType(){
		}
		public static final String DESIGN_DOC = "2";//2#设计现场勘察表
		public static final String PROJECT_DOC = "3";//3#工程现场勘察表
	}
	
	/**
	 *图片类型 
	 */
	public static class PicType {
		private PicType() {
		}
		public static final String GOODS = "1";// 1#商品
		public static final String MODULE = "2";// 2#模块
		public static final String ROOM = "3";// 3#Room
		public static final String STYLE = "4";// 4#风格
		public static final String DOCTMPL = "5";// #现场勘查表
		public static final String PAY = "6";// 支付凭证
		public static final String BJPMT = "7";// 布局平面图
	}
	
	/**
	 *图片类型 子分类
	 */
	public static class PicSubType {
		private PicSubType() {
		}
		// 天1#SKY_01,天2#SKY_02,地1#LAND_01,墙1#WALL_01,强弱电#SWE_01,水#WATER_01,其它#OTHER_01
		public static final String SKY_01 = "SKY_01";
		public static final String SKY_02 = "SKY_02";
		public static final String SKY_03 = "SKY_03";
		public static final String WALL_01 = "WALL_01";
		public static final String WALL_02 = "WALL_02";
		public static final String WALL_03 = "WALL_03";
		public static final String WALL_04 = "WALL_04";
		public static final String WALL_05 = "WALL_05";
		public static final String SWE_01 = "SWE_01";
		public static final String SWE_02 = "SWE_02";
		public static final String SWE_03 = "SWE_03";
		public static final String SWE_04 = "SWE_04";
		public static final String WATER_01 = "WATER_01";
	}

	public static class ContractCheckStatus {
		private ContractCheckStatus() {
		}

		// 审核 0#未通过 1#通过
		public static final String NOT_PASS = "0";
		public static final String PASS = "1";
	}
	
	public static class PtnUserLevel {
		private PtnUserLevel() {
		}

		// 0#普通会员 4#合伙体验 5#创客 3#项目合伙人 2#销售合伙人 1#体验厅合伙人
		public static final String COMMON = "0";
		public static final String TYTHHR = "1";
		public static final String XSHHR = "2";
		public static final String XMHHR = "3";
		public static final String HHTY = "4";
		public static final String CK = "5";

	}

	public static class MallUserAddType {
		// 0新注册 1同步同时登录
		private MallUserAddType() {
		}

		public static final String REGISTER = "0";
		public static final String SYNC_UPDATE = "1";
	}

	public static class MallRetState {
		// 调用成功返回值"status" : "0"
		// 失败返回值 status " : "1"
		private MallRetState() {
		}

		public static final String SUCC = "0";
		public static final String FAIL = "1";
	}
	
}
