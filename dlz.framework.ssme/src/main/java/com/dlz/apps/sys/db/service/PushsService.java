package com.dlz.apps.sys.db.service;

import java.util.List;
import java.util.Map;

import com.dlz.framework.ssme.base.service.BaseService;
import com.dlz.apps.sys.db.model.Pushs;

public interface PushsService extends BaseService<Pushs, Long> {

	void addPushs(List<Long> userIds, String title, String text, String type, Map para) throws Exception;

	void push(Pushs pushs) throws Exception;
}