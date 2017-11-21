package com.macren.framework.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.macren.framework.ServiceMgr.ModelServiceMgr;
import com.macren.framework.sys.business.AbstractBusinessObjectServiceMgr;
import com.macren.framework.sys.interf.IModelDao;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Model;
import com.macren.persistence.BaseConditionVO;
 

@Transactional(rollbackFor = Exception.class)
@Service(ModelServiceMgr.SERVICE_NAME)
public class ModelServiceMgrImpl extends AbstractBusinessObjectServiceMgr
		implements ModelServiceMgr {
	
	@Autowired
	private IModelDao modeldao;

	@Override
	public List<Model> getListOfModel(BaseConditionVO vo,PageCommon pc) {
		// TODO 自动生成的方法存根
		return modeldao.getListOfModel(vo,pc);
	}

	@Override
	public int searchModelNum(BaseConditionVO vo) {
		// TODO 自动生成的方法存根
		return modeldao.searchModelNum(vo);
	}

	@Override
	public List<Model> getRootOfModel() {
		// TODO 自动生成的方法存根
		return  modeldao.getRootOfModel();
	}

	@Override
	public Model getModelByModelId(String modelId) {
		// TODO 自动生成的方法存根
		return modeldao.getModelByModelId(modelId);
	}

	@Override
	public int delModelByModelId(String modelId) {
		// TODO 自动生成的方法存根
		return modeldao.delModelByModelId(modelId);
	}

	@Override
	public int saveModel(Model model) {
		// TODO 自动生成的方法存根
		return modeldao.saveModel(model);
	}

	@Override
	public int updateModel(Model model) {
		// TODO 自动生成的方法存根
		return modeldao.updateModel(model);
	}

	@Override
	public List<Model> getTotalListOfModel() {
		// TODO 自动生成的方法存根
		return modeldao.getTotalListOfModel();
	}
 
 
 
}
