package com.dlz.apps.notice.db.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.apps.notice.db.service.MsgService;

@Service
@Transactional
public class MsgServiceImpl implements MsgService {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	
}
