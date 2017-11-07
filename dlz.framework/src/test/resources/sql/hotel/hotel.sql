<?xml version="1.0" encoding="UTF-8" ?>
<!--=========================================================================-->
<!--  Copyright dlz 2017 All Rights Reserved.			 				 -->
<!--  equipment.sql															 -->
<!--																		 -->
<!--  [概要描述]															 	 -->
<!--  设备相关sql													     -->
<!--																		 -->
<!--																		 -->
<!--  @history	2017-06-23 ver1.00          							     -->
<!--  @author	dingkui											    		 -->
<!--  @version	1.00														 -->
<!--=========================================================================-->

<sqlList>
	<!--
		取得安装员
	-->
	<sql sqlId="key.hotel.getAzyHotels"><![CDATA[
	    select hotel_id
		  from ES_H_EQUIPMENT e
		 where e.id = #{userid}
		   and r.equipment_id = e.id
		   and h.hotel_id = r.hotel_id
 	]]></sql>
	<!--
		取得订单流水
	-->
	<sql sqlId="key.hotel.getOrderFlows"><![CDATA[
	    select * from eh_order where hotel_id=#{hotel_id}
 	]]></sql>
	<!--
		取得授权流水
	-->
	<sql sqlId="key.hotel.getRoomAuthorizeFlows"><![CDATA[
	    select * from eh_room_AUTH_FLOW where hotel_id=#{hotel_id}
 	]]></sql>
 	
</sqlList>










