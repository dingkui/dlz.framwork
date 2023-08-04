package com.dlz.framework.db.enums;

public enum DbTypeEnum {
    MYSQL(".mysql"),
    POSTGRESQL(".postgresql"),
    ORACLE(""),
    SQLITE(".sqlite"),
    MSSQL(".sqlserver");
    private String end;

    DbTypeEnum(String end) {
        this.end = end;
    }

    public String getEnd() {
        return end;
    }
}