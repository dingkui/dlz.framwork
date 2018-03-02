<?xml version="1.0" encoding="UTF-8" ?>
<!--=========================================================================-->
<!--  Copyright bj 2015 All Rights Reserved. -->
<!--  @version	1.00												 -->
<!--=========================================================================-->

<sqlList>
	<!--查询字典配置-->
	<sql sqlId="key.dict.getDict">
	<![CDATA[
	select DICT_ID id,
	       DICT_ID code,
	       DICT_NAME name,
	       DICT_STATUS enable,
	       DICT_SOURCE sqltext
	  from T_B_DICT
	 where 1=1 
	  [and DICT_ID=#{code}]
    ]]>
 	</sql>
 	
 	<!--查询字典明细-->
	<sql sqlId="key.dict.getDictItem">
	<![CDATA[
	select DICT_PARAM_VALUE  id,
	       DICT_PARAM_NAME   text,
	      case when DICT_PARAM_STATUS='0' then 1 else 0 end del
	  from T_B_DICT_DETAIL
	 where DICT_ID = #{p0}
	 order by DICT_ORDER
    ]]>
 	</sql>
 	
 	<!--查询多级字典明细-->
 	<sql sqlId="key.dict.getDictsItem">
	<![CDATA[
	select id, ID value, NAME text, 0 del, is_leaf
	  from T_B_DICTS
	 where 1=1 
	 [and PID = #{p1}]
	 order by ORD
    ]]>
 	</sql>
 	
 	<!--
		更新是否子节点
	-->
	<sql sqlId="key.dicts.upisleaf"><![CDATA[
		update T_B_DICTS set IS_LEAF=#{isLeaf} where id=#{id} [and not exists ( select 1 from T_B_DICTS d where d.pid=#{pid})]
 	]]></sql>
 	
 	<!--
		更新所有层级码
		tableName：表名
		pidClName：父节点ID字段名
		orderClName：排序字段名
		codeClName：cede字段名
		idClName：id字段名
	-->
	<sql sqlId="key.dict.updateCodes"><![CDATA[
	    DECLARE
		  CURSOR V_S IS
		    SELECT * FROM ${tableName} ORDER BY ${pidClName}, ${orderClName};
		  V   NUMBER := 0;
		  VS  VARCHAR2(10) := '';
		  PVS VARCHAR2(10) := '';
		  FID NUMBER := 0;
		BEGIN
		  FOR V_ IN V_S LOOP		  
		    IF V_.${pidClName} = 0 THEN
		      V  := V + 1;
		      VS := LTRIM(TO_CHAR(V, '0X'));		    
		      UPDATE ${tableName} SET ${codeClName} = VS WHERE ${idClName} = ${idClName};
		    ELSE
		      IF FID != V_.${pidClName} THEN
		        SELECT ${codeClName} INTO PVS FROM ${tableName} WHERE ${idClName} = V_.${pidClName};
		        V   := 0;
		        FID := V_.${pidClName};
		      END IF;
		      IF PVS IS NOT NULL THEN
		        V  := V + 1;
		        VS := PVS || LTRIM(TO_CHAR(V, '0X'));		      
		        UPDATE ${tableName} T SET T.${codeClName} = VS WHERE T.${idClName} = V_.${idClName};
		      END IF;		    
		    END IF;
		    UPDATE ${tableName}  SET ${orderClName} = V WHERE ${idClName} = V_.${idClName};
		  END LOOP;
		END;
 	]]></sql>
</sqlList>
