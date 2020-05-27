package com.dlz.framework.db.convertor.result.impl;

import com.dlz.framework.db.convertor.result.AClassConverter;
import com.dlz.framework.db.enums.CharsetNameEnum;
import org.slf4j.Logger;

import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Clob;

public class ClobConverter  extends AClassConverter<Object,String,CharsetNameEnum> {

	private static String COVERCLASS_BLOB="oracle.sql.CLOB";
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(ClobConverter.class);
	public ClobConverter(CharsetNameEnum para) {
		super(para);
	}
	@Override
	public String getCoverClass() {
		return COVERCLASS_BLOB;
	}
	@Override
	public String conver2Str(Object o) {
		if(o==null){
			return null;
		}
		Clob clob=(Clob)o;
//		BufferedReader reader = null;
		try {
//			reader = new BufferedReader(new InputStreamReader(clob.getAsciiStream(),para));
//	        String line = null;
//	        StringBuffer req = new StringBuffer();
//	        while ((line = reader.readLine()) != null) {
//	            req.append(line);
//	        }
//			return req.toString();
			Reader reader = clob.getCharacterStream();
	        if (reader == null) {
	            return null;
	        }
	        StringBuffer sb = new StringBuffer();
	        char[] charbuf = new char[4096];
	        for (int i = reader.read(charbuf); i > 0; i = reader.read(charbuf)) {
	            sb.append(charbuf, 0, i);
	        }
	        return new String(sb.toString().getBytes(para.getCharsetName()));
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
	
//	@Override
//	public Object conver2Str(Object o,Object para) {
//		if(o==null){
//			return o;
//		}
//		Clob blob=(Clob)o;
//		try {
//			return blob.getAsciiStream().read();
//		} catch (Exception e) {
//			logger.error(e.getMessage(),e);
//		}
//		return o;
//	}
//	@Override
//	public String getCoverClass() {
//		return "oracle.sql.CLOB";
//	}
//	@Override
//	public Object conver2Db(Object o, Object para) {
//		if(o==null){
//			return o;
//		}
//		try {
//			return String.valueOf(o).getBytes(charsetName);
//		} catch (UnsupportedEncodingException e) {
//			logger.error(e.getMessage(),e);
//		}
//		return null;
//	}
}
