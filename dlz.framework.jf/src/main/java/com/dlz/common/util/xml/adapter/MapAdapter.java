package com.dlz.common.util.xml.adapter;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.dlz.common.util.xml.mapper.JaxbMapper;
import com.dlz.common.util.xml.wrapper.CollectionWrapper;

public class MapAdapter extends XmlAdapter<Element, Map<?, ?>> {
	@Override
	public Map<?, ?> unmarshal(Element element) throws Exception {
		return null;
	}

	@Override
	public Element marshal(Map<?, ?> map) throws Exception {
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element result = doc.createElement("result");
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			Element keyValuePair = doc.createElement(entry.getKey().toString());
			Object value = entry.getValue();
			if (value == null) {
				value = new String("");
			}
			if (value instanceof String || value instanceof Number || value instanceof Date) {
				keyValuePair.appendChild(doc.createTextNode(value.toString()));
			} else if (value instanceof Collection) {
				CollectionWrapper wrapper = new CollectionWrapper((Collection<?>) value);
				Class<?> targetClass = ((List<?>) value).get(0).getClass();
				JaxbMapper.createMarshaller(targetClass).marshal(wrapper, keyValuePair);
			} else if (value instanceof Map) {
				keyValuePair.appendChild(marshal((Map<?, ?>) value));
			} else {
				JaxbMapper.createMarshaller(value.getClass()).marshal(value, keyValuePair);
			}
			result.appendChild(keyValuePair);
		}
		return result;
	}
}