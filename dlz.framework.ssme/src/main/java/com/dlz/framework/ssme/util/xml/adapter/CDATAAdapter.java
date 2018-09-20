package com.dlz.framework.ssme.util.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CDATAAdapter extends XmlAdapter<String, String> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	@Override
	public String marshal(String v) throws Exception {
		return "<![CDATA[" + v + "]]>";
	}

	@Override
	public String unmarshal(String v) throws Exception {
		return v;
	}
	
}