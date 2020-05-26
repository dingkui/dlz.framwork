package com.dlz.framework.db.conver.impl;

import com.dlz.framework.db.conver.AClassConverter;
import com.dlz.framework.db.enums.CharsetNameEnum;
import org.slf4j.Logger;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;

public class BlobConverter extends AClassConverter<Object,String,CharsetNameEnum> {

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
			Blob b = (Blob) blob;
			byte[] bytes = b.getBytes(1l, (int) b.length());
			return new String(bytes,para.getCharsetName());
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
