package com.dlz.caller.mybatis;

import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;

final class DlzMybatisSqlLogFormatter {

    private DlzMybatisSqlLogFormatter() {
    }

    static String toExecutableSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        MetaObject parameterMetaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
        List<ParameterMapping> mappings = boundSql.getParameterMappings();

        String rawSql = compactWhitespace(boundSql.getSql());
        StringBuilder sql = new StringBuilder(rawSql.length() + mappings.size() * 16);
        int parameterIndex = 0;
        SqlLexState state = SqlLexState.NORMAL;
        for (int index = 0; index < rawSql.length(); index++) {
            char current = rawSql.charAt(index);
            state = state.next(rawSql, index);
            if (current == '?' && state == SqlLexState.NORMAL && parameterIndex < mappings.size()) {
                Object value = resolveParameterValue(boundSql, parameterObject, parameterMetaObject,
                        typeHandlerRegistry, mappings.get(parameterIndex++));
                sql.append(formatValue(value));
            } else {
                sql.append(current);
            }
        }
        return sql.toString().trim();
    }

    static String getMapper(MetaObject metaObject) {
        String statementId = String.valueOf(metaObject.getValue("delegate.mappedStatement.id"));
        int mapperPackageIndex = statementId.lastIndexOf(".mapper.");
        return mapperPackageIndex >= 0 ? statementId.substring(mapperPackageIndex + 8) + " " : statementId + " ";
    }

    private static Object resolveParameterValue(BoundSql boundSql, Object parameterObject,
                                                MetaObject parameterMetaObject,
                                                TypeHandlerRegistry typeHandlerRegistry,
                                                ParameterMapping parameterMapping) {
        String property = parameterMapping.getProperty();
        if (boundSql.hasAdditionalParameter(property)) {
            return boundSql.getAdditionalParameter(property);
        }
        if (parameterObject == null) {
            return null;
        }
        if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
            return parameterObject;
        }
        return parameterMetaObject == null ? null : parameterMetaObject.getValue(property);
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "NULL";
        }
        if (value instanceof Number || value instanceof Boolean) {
            return value.toString();
        }
        if (value instanceof byte[]) {
            StringBuilder hex = new StringBuilder("X'");
            for (byte item : (byte[]) value) {
                hex.append(String.format("%02X", item));
            }
            return hex.append('\'').toString();
        }
        if (value instanceof Date || value instanceof TemporalAccessor || value instanceof Character
                || value instanceof Enum || value instanceof CharSequence) {
            return quote(value.toString());
        }
        return quote(value.toString());
    }

    private static String quote(String value) {
        return "'" + value.replace("'", "''") + "'";
    }

    private static String compactWhitespace(String sql) {
        StringBuilder compactSql = new StringBuilder(sql.length());
        SqlLexState state = SqlLexState.NORMAL;
        boolean previousWhitespace = false;
        for (int index = 0; index < sql.length(); index++) {
            char current = sql.charAt(index);
            state = state.next(sql, index);
            if (state == SqlLexState.NORMAL && Character.isWhitespace(current)) {
                if (!previousWhitespace) {
                    compactSql.append(' ');
                    previousWhitespace = true;
                }
            } else {
                compactSql.append(current);
                previousWhitespace = false;
            }
        }
        return compactSql.toString();
    }

    private enum SqlLexState {
        NORMAL {
            @Override
            SqlLexState next(String sql, int index) {
                char current = sql.charAt(index);
                if (current == '\'') {
                    return SINGLE_QUOTE;
                }
                if (current == '\"') {
                    return DOUBLE_QUOTE;
                }
                if (current == '`') {
                    return BACKTICK;
                }
                if (current == '-' && hasNext(sql, index, '-')) {
                    return LINE_COMMENT;
                }
                if (current == '/' && hasNext(sql, index, '*')) {
                    return BLOCK_COMMENT;
                }
                return this;
            }
        },
        SINGLE_QUOTE {
            @Override
            SqlLexState next(String sql, int index) {
                return sql.charAt(index) == '\'' && !isEscaped(sql, index) ? NORMAL : this;
            }
        },
        DOUBLE_QUOTE {
            @Override
            SqlLexState next(String sql, int index) {
                return sql.charAt(index) == '\"' && !isEscaped(sql, index) ? NORMAL : this;
            }
        },
        BACKTICK {
            @Override
            SqlLexState next(String sql, int index) {
                return sql.charAt(index) == '`' ? NORMAL : this;
            }
        },
        LINE_COMMENT {
            @Override
            SqlLexState next(String sql, int index) {
                char current = sql.charAt(index);
                return current == '\n' || current == '\r' ? NORMAL : this;
            }
        },
        BLOCK_COMMENT {
            @Override
            SqlLexState next(String sql, int index) {
                return sql.charAt(index) == '/' && index > 0 && sql.charAt(index - 1) == '*' ? NORMAL : this;
            }
        };

        abstract SqlLexState next(String sql, int index);

        private static boolean hasNext(String sql, int index, char expected) {
            return index + 1 < sql.length() && sql.charAt(index + 1) == expected;
        }

        private static boolean isEscaped(String sql, int index) {
            return index > 0 && sql.charAt(index - 1) == '\\';
        }
    }
}
