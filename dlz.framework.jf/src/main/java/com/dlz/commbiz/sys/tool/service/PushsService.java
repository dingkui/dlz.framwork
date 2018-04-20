package com.dlz.commbiz.sys.tool.service;

import java.util.List;
import java.util.Map;

import com.dlz.common.base.service.BaseService;
import com.dlz.commbiz.sys.tool.model.Pushs;

public interface PushsService extends BaseService<Pushs, Long> {

	void addPushs(List<Long> userIds, String title, String text, String type, Map para) throws Exception;

	void push(Pushs pushs) throws Exception;
}