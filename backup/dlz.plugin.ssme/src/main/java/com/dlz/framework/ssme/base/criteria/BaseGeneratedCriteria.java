package com.dlz.framework.ssme.base.criteria;

import java.util.ArrayList;
import java.util.List;

public class BaseGeneratedCriteria {
	protected List<Criterion> criteria;

	public BaseGeneratedCriteria() {
		criteria = new ArrayList<Criterion>();
	}

	public void  clear() {
		criteria.clear();
	}
	public boolean isValid() {
		return criteria.size() > 0;
	}

	public List<Criterion> getAllCriteria() {
		return criteria;
	}

	public List<Criterion> getCriteria() {
		return criteria;
	}

	public void addCriterion(String condition) {
		if (condition == null) {
			throw new RuntimeException("Value for condition cannot be null");
		}
		criteria.add(new Criterion(condition));
	}

	public void addCriterion(String condition, Object value, String property) {
		if(condition.endsWith(" like")){
			if(value!=null ){
				String v = String.valueOf(value);
				if(v.indexOf("%")==-1){
					v="%"+v+"%";
					value=v;
				}
			}
		}
		if (value == null) {
			throw new RuntimeException("Value for " + property + " cannot be null");
		}
		criteria.add(new Criterion(condition, value));
	}

	public void addCriterion(String condition, Object value1, Object value2, String property) {
		if (value1 == null || value2 == null) {
			throw new RuntimeException("Between values for " + property + " cannot be null");
		}
		criteria.add(new Criterion(condition, value1, value2));
	}
}
