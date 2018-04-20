package com.dlz.framework.ssme.util.xml.wrapper;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 封装Root Element 是 Collection的情况.
 * 
 * @author vincent
 */
@XmlRootElement(name = "collection")
public class CollectionWrapper {
	@XmlAnyElement
	public Collection<?> collection;

	public CollectionWrapper() {
	}

	public CollectionWrapper(Collection<?> collection) {
		this.collection = collection;
	}
}