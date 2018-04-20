package com.dlz.commbiz.dict.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.common.util.string.Collections3;
import com.dlz.commbiz.dict.dao.DictDetailMapper;
import com.dlz.commbiz.dict.model.ComboBoxModel;
import com.dlz.commbiz.dict.model.DictDetail;
import com.dlz.commbiz.dict.model.DictDetailCriteria;
import com.dlz.commbiz.dict.service.DictServiceExt;

@Service
@Transactional(rollbackFor=Exception.class)
public class DictServiceExtImpl implements DictServiceExt {

	@Autowired
	private DictDetailMapper detailMapper = null;

	@SuppressWarnings("unchecked")
	public Map<String, String> getDictMap(Class<?> clazz) {
		DictDetailCriteria detailCriteria = new DictDetailCriteria();
		detailCriteria.createCriteria().andDictIdEqualTo(getDictId(clazz));
		List<DictDetail> detailList = detailMapper.selectByExample(detailCriteria);
		return Collections3.extractToMap(detailList, "dictParamValue", "dictParamName");
	}
	
	private static String getDictId(Class<?> clazz) {
		String className = clazz.getName();
		return className.substring(className.lastIndexOf(".") + 1, className.length()).replace("$", ".");
	}
	
	public List<ComboBoxModel> getDictDetails(String dictCode){
		DictDetailCriteria ddc = new DictDetailCriteria();
		ddc.createCriteria().andDictIdEqualTo(dictCode);
		List<DictDetail> dictDetailList = detailMapper.selectByExample(ddc);
		List<ComboBoxModel> comboxModelList = new ArrayList<ComboBoxModel>();
		for (DictDetail dictDetail : dictDetailList) {
			ComboBoxModel comboxModel = new ComboBoxModel();
			comboxModel.setId(dictDetail.getDictParamValue());
			comboxModel.setText(dictDetail.getDictParamName());
			comboxModelList.add(comboxModel);
		}
		return comboxModelList;
	}
	
	public String getNameDesc(String dictCode,String dictValue){
		DictDetailCriteria ddc = new DictDetailCriteria();
		ddc.createCriteria().andDictIdEqualTo(dictCode)
		.andDictParamValueEqualTo(dictValue);
		List<DictDetail> list = detailMapper.selectByExample(ddc);
		return list.get(0).getDictParamName();
	}
}