package com.dlz.test.comm.json;

import com.dlz.comm.json.JSONList;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class JSONListTest {
	@Test
	public void test0(){
		List<LinkedHashMap<String, Object>> index = Arrays.stream(new String[]{"1", "2", "3"}).map(item -> {
			LinkedHashMap<String, Object> p = new LinkedHashMap<>();
			p.put("index", item);
			return p;
		}).collect(Collectors.toList());

		JSONList objects = new JSONList(index);
		objects.asList().forEach(item->{
			System.out.println(item.getInt("index"));
		});

	}

}

