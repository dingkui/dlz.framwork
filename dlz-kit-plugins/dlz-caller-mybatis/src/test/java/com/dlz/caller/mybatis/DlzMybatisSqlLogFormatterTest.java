package com.dlz.caller.mybatis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DlzMybatisSqlLogFormatterTest {

    @Test
    void rendersParametersWithoutReplacingQuestionMarksInQuotedSqlOrComments() {
        Configuration configuration = new Configuration();
        List<ParameterMapping> mappings = Arrays.asList(
                new ParameterMapping.Builder(configuration, "id", Integer.class).build(),
                new ParameterMapping.Builder(configuration, "name", String.class).build());
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", 7);
        parameters.put("name", "O'Reilly");
        BoundSql boundSql = new BoundSql(configuration,
                "select  *\nfrom user where id = ? and name = ? and marker = 'two  spaces ?' -- ?", mappings, parameters);

        assertEquals("select * from user where id = 7 and name = 'O''Reilly' and marker = 'two  spaces ?'",
                DlzMybatisSqlLogFormatter.toExecutableSql(configuration, boundSql));
    }

    @Test
    void resolvesMybatisAdditionalParametersUsedByForeach() {
        Configuration configuration = new Configuration();
        List<ParameterMapping> mappings = Arrays.asList(
                new ParameterMapping.Builder(configuration, "__frch_item_0", Integer.class).build());
        BoundSql boundSql = new BoundSql(configuration, "select * from user where id = ?", mappings, null);
        boundSql.setAdditionalParameter("__frch_item_0", 42);

        assertEquals("select * from user where id = 42",
                DlzMybatisSqlLogFormatter.toExecutableSql(configuration, boundSql));
    }
    @Test
    void removesLineCommentsToKeepOneLineAndBindsFollowingParameters() {
        Configuration configuration = new Configuration();
        for (String newline : Arrays.asList("\n", "\r\n", "\r")) {
            BoundSql boundSql = new BoundSql(configuration,
                    "select 1 -- leave ? unchanged" + newline + "  where id = ?",
                    Arrays.asList(new ParameterMapping.Builder(configuration, "id", Integer.class).build()), 7);
            assertEquals("select 1 where id = 7",
                    DlzMybatisSqlLogFormatter.toExecutableSql(configuration, boundSql));
        }
    }
}
