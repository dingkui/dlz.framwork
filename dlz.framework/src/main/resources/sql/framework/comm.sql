<?xml version="1.0" encoding="UTF-8" ?>
<!--=========================================================================-->
<!--  Copyright bj 2015 All Rights Reserved. -->
<!--  @version	1.00												 -->
<!--=========================================================================-->

<sqlList>
	<sql sqlId="key.comm.searchTable">
	<![CDATA[
	  select ${colums} from ${tableName} ${where} ${otherwhere}
    ]]>
 	</sql>
	<sql sqlId="key.comm.updateTable">
	<![CDATA[
	  update ${tableName} set ${sets} ${where} ${otherwhere}
    ]]>
 	</sql>
	<sql sqlId="key.comm.insertTable">
	<![CDATA[
	  insert into ${tableName}(${colums}) values(${values})
    ]]>
 	</sql>
	<sql sqlId="key.comm.deleteTable">
	<![CDATA[
	  delete from ${tableName} ${where} ${otherwhere}
    ]]>
 	</sql>
 	<sql sqlId="key.comm.pageSql">
	<![CDATA[
		[select * from (select a1.*,rownum rownum_ from ( ^#{page.end}]
			[select * from ( ^#{page.orderBy}]
				${_sql}
			[) order by ${page.orderBy}]
		[) a1 where rownum <=#{page.end} ) [where rownum_> #{page.begin}]]
    ]]>
 	</sql>
 	<sql sqlId="key.comm.cntSql">
	<![CDATA[
	  select count(1) from (${_sql}) t
    ]]>
 	</sql>
 	
 	<sql sqlId="key.comm.pageSql.mysql"><![CDATA[
	 ${_sql} [ ORDER BY ${page.orderBy} ] [ LIMIT [#{page.begin},]#{page.end} ]
 	]]>
 	</sql>
 	
</sqlList>
