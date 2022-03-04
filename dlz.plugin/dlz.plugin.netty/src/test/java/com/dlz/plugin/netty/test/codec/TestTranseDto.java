package com.dlz.plugin.netty.test.codec;

import lombok.Data;

@Data
public class TestTranseDto {
    //包头 字符 2 固定为##
    private String head;

    /**
     * 数据段长度 十进制整数 4 数据段的 ASCII 字符数，如：长 255，则写为“0255”
     */
    private String length;

    /**
     * 数据段 字符 0<n<1024 变长的数据，详见 6.5 章节的表 3《数据段结构组成表》
     */
    private String data;
    //	CRC 校验 十六进制整数 4 数据段的校验结果
    private String crc;
    //	包尾 字符 2 固定为<CR><LF>（回车，换行）
    private String end;
}