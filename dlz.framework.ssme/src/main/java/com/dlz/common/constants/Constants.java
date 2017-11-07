package com.dlz.common.constants;

public class Constants {

	//后台操作员默认密码
	public static final String USER_DEFAULT_PASSWORD = "123456";
	
	// 有效 无效状态
	public static final String EFFECTIVE_STATUS_ACTIVE = "1";
	public static final String EFFECTIVE_STATUS_UNACTIVE = "2";
	// 发布状态
	public static final String PUB_STATUS_UNACTIVE = "1";
	public static final String PUB_STATUS_ACTIVE = "2";
	// 审核状态
	public static final String APPROVE_STATUS_UNACTIVE = "1"; // 审核失败
	public static final String APPROVE_STATUS_ACTIVE = "2"; // 审核成功
	// 上架状态
	public static final String SHELF_STATUS_UNACTIVE = "1"; // 未上架
	public static final String SHELF_STATUS_ACTIVE = "2"; // 上架

	// 用户说明
	public static final String USER_TYPE_INDEX = "1"; // 未上架
	public static final String USER_TYPE_SYSTEM = "2"; // 上架
	public static final String USER_TYPE_INDEX_NAME = "门户用户"; // 未上架
	public static final String USER_TYPE_SYSTEM_NAME = "后台用户"; // 上架

	// 通联交互日志类型
	public static final String TLT_LOG_TYPE_ACCT_VERIFY = "1";// 身份验证
	public static final String TLT_LOG_TYPE_BATCH_AGENT_PAY = "2";// 批量代付
	public static final String TLT_LOG_TYPE_QUERY_RESULT = "3"; // 交易结果查询
	public static final String TLT_LOG_TYPE_DOWNLOAD = "4"; // 对账文件下载
	public static final String TLT_LOG_TYPE_SINGEL = "5"; // 单笔代付

	// 通联返回CODE
	public static final String TLT_RET_CODE_SUCC = "0000";

	public static final String QUERY_TLT_SUCC = "0";// 查询成功
	public static final String QUERY_TLT_AGAIN_YES = "1";// 需要重新查询
	public static final String QUERY_TLT_AGAIN_NO = "2"; // 不需要重新查询
	public static final String QUERY_TLT_EXCEPTION = "3"; // 内部异常
	
	
	/**
	 * 投标方式
	 * @author zhuwb
	 * 
	 */
	public static class BidWay {
		public static final String MANUAL = "1";// 1#手动
		public static final String AUTO = "2";// 2#自动
	}

}
