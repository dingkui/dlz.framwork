package com.dlz.framework.ssme.base.criteria;

import java.util.List;

import com.dlz.framework.db.modal.Page;

public abstract class BaseCriteria<T> {
    protected String orderByClause;
    protected boolean distinct;
    protected List<T> oredCriteria;
    protected T currentCriteria;
    protected Page page;

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }
    public boolean isDistinct() {
        return distinct;
    }

    public List<T> getOredCriteria() {
        return oredCriteria;
    }

    public void or(T criteria) {
        oredCriteria.add(criteria);
        currentCriteria=criteria;
    }

    public T or() {
        T criteria = createCriteriaInternal1();
        oredCriteria.add(criteria);
        currentCriteria=criteria;
        return criteria;
    }
    
    protected abstract T createCriteriaInternal1();

    public T createCriteria() {
        T criteria = createCriteriaInternal1();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        currentCriteria=criteria;
        return criteria;
    }
    public T getCurrentCriteria() {
    	if (currentCriteria==null) {
    		T criteria = createCriteriaInternal1();
    		oredCriteria.add(criteria);
    		currentCriteria=criteria;
    	}
    	return currentCriteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
        currentCriteria=null;
    }

    public void setPage(Page page) {
        this.page=page;
    }

    public Page getPage() {
        return page;
    }
}
