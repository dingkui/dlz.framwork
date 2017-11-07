<?xml version="1.0" encoding="UTF-8" ?>
<!--=========================================================================-->
<!--  Copyright dlz 2017 All Rights Reserved.			 				 -->
<!--  auth.sql															 -->
<!--																		 -->
<!--  [概要描述]															 	 -->
<!--  认证相关sql													     -->
<!--																		 -->
<!--																		 -->
<!--  @history	2017-06-23 ver1.00          							     -->
<!--  @author	dingkui											    		 -->
<!--  @version	1.00														 -->
<!--=========================================================================-->

<sqlList>
	<!--
		登录
	-->
	<sql sqlId="key.auth.loginByName"><![CDATA[
	    select * from DS_MEMBER where l_id=#{loginName} or mobile like #{loginName}
 	]]></sql>
 	
 	<!--
		取得用户角色
	-->
	<sql sqlId="key.auth.getUserRoles"><![CDATA[
	    select * from DS_R_MEB_ROLE where MEMBER_ID =#{id}
 	]]></sql>
 	
 	
 	<!--
		取得代理商信息
	-->
	<sql sqlId="key.auth.getDlsinfo"><![CDATA[
	    select * from DH_MR_AGENCY where ID =#{id}
 	]]></sql>
 	
 	<!--
		取得用户酒店信息
	-->
	<sql sqlId="key.auth.getHotelInfo"><![CDATA[
	    select h.* from DH_hotel h,DH_R_HOTEL_MER m where m.MEMBER_ID =#{id} and m.hotel_id=h.hotel_id
 	]]></sql>
</sqlList>










