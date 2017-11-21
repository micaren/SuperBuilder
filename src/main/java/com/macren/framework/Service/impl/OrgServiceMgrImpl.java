package com.macren.framework.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.macren.framework.ServiceMgr.OrgServiceMgr;
import com.macren.framework.sys.business.AbstractBusinessObjectServiceMgr;
import com.macren.framework.sys.interf.IOrgDao;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Org;
import com.macren.persistence.BaseConditionVO;
 

@Transactional(rollbackFor = Exception.class)
@Service(OrgServiceMgr.SERVICE_NAME)
public class OrgServiceMgrImpl extends AbstractBusinessObjectServiceMgr
		implements OrgServiceMgr {
	
	@Autowired
	private IOrgDao orgdao;

	@Override
	public List<Org> getListOfOrg(BaseConditionVO vo,PageCommon pc) {
		// TODO 自动生成的方法存根
		return orgdao.getListOfOrg(vo,pc);
	}
	@Override
	public int searchOrgNum(BaseConditionVO vo) {
		// TODO 自动生成的方法存根
		return orgdao.searchOrgNum(vo);
	}
	
	@Override
	public int getOrgNum(BaseConditionVO vo) {
		// TODO 自动生成的方法存根
		return orgdao.searchOrgNum(vo);
	}

	@Override
	public int deleteOrg(String orgId) {
		// TODO 自动生成的方法存根
		return orgdao.deleteOrg(orgId);
	}

	@Override
	public int updateOrg(Org org) {
		// TODO 自动生成的方法存根
		return orgdao.updateOrg(org);
	}

	@Override
	public int saveOrg(Org org) {
		// TODO 自动生成的方法存根
		return orgdao.saveOrg(org);
	}

	@Override
	public Org getOrgName(String orgId) {
		// TODO 自动生成的方法存根
		return orgdao.getOrgName(orgId);
	}

	@Override
	public List<Org> getListOrg() {
		// TODO 自动生成的方法存根
		return orgdao.getListOrg();
	}


	 
 
}
