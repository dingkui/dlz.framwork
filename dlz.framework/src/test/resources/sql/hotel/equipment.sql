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
		取得设备信息
	-->
	<sql sqlId="key.equipment.getEquipment"><![CDATA[
	    select e.puname, e.puid, r.room_name, r.is_auth, h.hotel_name, h.address
		  from dH_EQUIPMENT e, dh_room r, dh_hotel h
		 where e.id = #{equipment_id}
		   and r.equipment_id = e.id
		   and h.hotel_id = r.hotel_id
 	]]></sql>
 	
</sqlList>










