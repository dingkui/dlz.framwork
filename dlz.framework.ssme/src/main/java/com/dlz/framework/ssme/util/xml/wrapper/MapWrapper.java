package com.dlz.framework.ssme.util.xml.wrapper;

import java.util.Map;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.dlz.framework.ssme.util.xml.adapter.MapAdapter;

/**
 * 封装Root Element 是 Map的情况.
 * 
 * @author vincent
 */
@XmlRootElement(name = "map")
public class MapWrapper {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	
	@XmlAnyElement
	@XmlJavaTypeAdapter(MapAdapter.class)
	protected Map<?, ?> map;

	public MapWrapper() {
	}

	public MapWrapper(Map<?, ?> map) {
		this.map = map;
	}
}