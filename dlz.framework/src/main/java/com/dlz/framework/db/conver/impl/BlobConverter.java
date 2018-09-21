package com.dlz.framework.db.conver.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;

import com.dlz.framework.db.conver.AClassConverter;
import com.dlz.framework.db.enums.CharsetNameEnum;
import org.slf4j.Logger;

public class BlobConverter extends AClassConverter<Object,String,CharsetNameEnum> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static String COVERCLASS_BLOB="oracle.sql.BLOB";
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(BlobConverter.class);
	public BlobConverter(CharsetNameEnum para) {
		super(para);
	}
	@Override
	public String getCoverClass() {
		return COVERCLASS_BLOB;
	}
	@Override
	public String conver2Str(Object blob) {
		if(blob==null){
			return null;
		}
		try {
			return new String(((Blob)blob).getBytes((long)1, (int)((Blob)blob).length()),para.getCharsetName());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	@Override
	public byte[] conver2Db(String o) {
		if(o==null){
			return null;
		}
		try {
			return o.getBytes(para.getCharsetName());
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
}
