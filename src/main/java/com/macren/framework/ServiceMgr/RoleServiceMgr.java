package com.macren.framework.ServiceMgr;

import java.util.List;

import com.macren.framework.sys.business.BusinessObjectServiceMgr;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Role;
import com.macren.persistence.BaseConditionVO;

public interface RoleServiceMgr extends BusinessObjectServiceMgr {
	String SERVICE_NAME = "roleServiceMgr";
	 
	public List<?>  getRoleMenuByUserRoleId(String RoleId);

	public String getRoleName(String RoleId);
	public Role getRoleByroleId(String roleId);

	public List<Role> getListOfRole(BaseConditionVO vo,PageCommon pc);
	public int getRoleNum(BaseConditionVO vo);
	public int deleteRole(String roleId);
	/*
	 * update org information in:Org out:boolean true:OK false:error
	 */
	public int updateRole(Role role);

	/*
	 * save org information out :Boolean true:Ok false:error
	 */
	public int saveRole(Role role);
	public List<Role> getListOfRoleByorgId(String orgId);
  
}
