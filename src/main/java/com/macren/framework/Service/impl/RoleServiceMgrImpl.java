package com.macren.framework.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.macren.framework.ServiceMgr.RoleServiceMgr;
import com.macren.framework.sys.business.AbstractBusinessObjectServiceMgr;
import com.macren.framework.sys.interf.IRoleDao;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Role;
import com.macren.persistence.BaseConditionVO;
 

@Transactional(rollbackFor = Exception.class)
@Service(RoleServiceMgr.SERVICE_NAME)
public class RoleServiceMgrImpl extends AbstractBusinessObjectServiceMgr
		implements RoleServiceMgr {
	
	@Autowired
	private IRoleDao roleDao;

	@Override
	public List<?> getRoleMenuByUserRoleId(String RoleId) {
		// TODO 自动生成的方法存根
		return roleDao.getRoleMenuByUserRoleId(RoleId);
	}

	@Override
	public String getRoleName(String RoleId) {
		// TODO 自动生成的方法存根
		return roleDao.getRoleName(RoleId);
	}

	@Override
	public Role getRoleByroleId(String roleId) {
		// TODO 自动生成的方法存根
		return roleDao.getRoleByroleId(roleId);
	}

	@Override
	public List<Role> getListOfRole(BaseConditionVO vo,PageCommon pc) {
		// TODO 自动生成的方法存根
		return roleDao.getListOfRole(vo,pc);
	}

	@Override
	public int getRoleNum(BaseConditionVO vo) {
		// TODO 自动生成的方法存根
		return roleDao.getRoleNum(vo);
	}

	@Override
	public int deleteRole(String roleId) {
		// TODO 自动生成的方法存根
		return roleDao.deleteRole(roleId);
	}

	@Override
	public int updateRole(Role role) {
		// TODO 自动生成的方法存根
		return roleDao.updateRole(role);
	}

	@Override
	public int saveRole(Role role) {
		// TODO 自动生成的方法存根
		return roleDao.saveRole(role);
	}

	@Override
	public List<Role> getListOfRoleByorgId(String orgId) {
		// TODO 自动生成的方法存根
		return roleDao.getListOfRoleByorgId(orgId);
	}
	
 

	 

 
}
