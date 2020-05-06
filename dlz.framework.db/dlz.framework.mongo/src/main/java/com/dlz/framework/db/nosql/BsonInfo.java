package com.dlz.framework.db.nosql;

import com.dlz.comm.util.JacksonUtil;

public class BsonInfo{

		String bson;
		String name;
		String filter;
		String clumns;
		
		public String getBson() {
			return bson;
		}
		public void setBson(String bson) {
			this.bson = bson;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getFilter() {
			return filter;
		}
		public void setFilter(String filter) {
			this.filter = filter;
		}
		public String getClumns() {
			return clumns;
		}
		public void setClumns(String clumns) {
			this.clumns = clumns;
		}
		public String toString(){
			return JacksonUtil.getJson(this);
		}
	}