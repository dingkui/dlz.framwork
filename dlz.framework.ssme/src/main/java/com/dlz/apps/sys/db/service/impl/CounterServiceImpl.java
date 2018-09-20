package com.dlz.apps.sys.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.apps.sys.db.dao.CounterMapper;
import com.dlz.apps.sys.db.model.Counter;
import com.dlz.apps.sys.db.service.CounterService;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.modal.SearchParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.util.StringUtils;

@Service
@Transactional(rollbackFor=Exception.class)
public class CounterServiceImpl extends BaseServiceImpl<Counter, Long> implements CounterService {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	  @Autowired
	  ICommService commService;
    @Autowired
    public void setMapper(CounterMapper mapper) {
        this.mapper=mapper;
    }

		public synchronized String getNum(String str, int len) throws Exception {
			SearchParaMap pm = new SearchParaMap("S_counter");
			pm.addCondition("str", "=", str);
			ResultMap n = commService.getMap(pm);
			Long id=n==null?-1l:n.getLong("id");
			Long num=n==null?1l:n.getLong("num");
			
			if(id==-1){
				Counter c = new Counter();
				c.setId(commService.getSeq(c.getClass()));
				c.setNum(num);
				c.setStr(str);
				insert(c);
			}else{
				num++;
				Counter c = new Counter();
				c.setId(id);
				c.setNum(num);
				c.setStr(str);
				updateByPrimaryKey(c);
			}
			return StringUtils.leftPad(String.valueOf(num), len, '0');
		}
}