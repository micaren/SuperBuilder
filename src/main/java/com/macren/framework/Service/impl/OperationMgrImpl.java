package com.macren.framework.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.macren.framework.ServiceMgr.LogServiceMgr;
import com.macren.framework.ServiceMgr.OperationServiceMgr;
import com.macren.framework.sys.business.AbstractBusinessObjectServiceMgr;
import com.macren.framework.sys.interf.IOperationDao;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Opreation;
import com.macren.persistence.BaseConditionVO;
 

@Transactional(rollbackFor = Exception.class)
@Service(OperationServiceMgr.SERVICE_NAME)
public class OperationMgrImpl extends AbstractBusinessObjectServiceMgr
		implements OperationServiceMgr {
	@Autowired
	private IOperationDao operationdao;

	@Override
	public List<Opreation> getListOfOperation(BaseConditionVO vo, PageCommon pc) {
		// TODO 自动生成的方法存根
		return operationdao.getListOfOperation(vo, pc);
	}

	@Override
	public int getOperationNum(BaseConditionVO vo) {
		// TODO 自动生成的方法存根
		return operationdao.getOperationNum(vo);
	}

	@Override
	public int deleteOpre(String opreationId) {
		// TODO 自动生成的方法存根
		return operationdao.deleteOpre(opreationId);
	}

	@Override
	public int updateOpre(Opreation opreation) {
		// TODO 自动生成的方法存根
		return operationdao.updateOpre(opreation);
	}

	@Override
	public int saveOpre(Opreation opreation) {
		// TODO 自动生成的方法存根
		return operationdao.saveOpre(opreation);
	}

	@Override
	public Opreation getOperationByOperId(String operId) {
		// TODO 自动生成的方法存根
		return operationdao.getOperationByOperId(operId);
	}

	@Override
	public List<Opreation> getListOpreationByOrgNoInRoleModel(String roleId,
			String modelId) {
		// TODO 自动生成的方法存根
		return operationdao.getListOpreationByOrgNoInRoleModel(roleId, modelId);
	}
 
 
	
	 

	 
 
}
