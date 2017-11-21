package com.macren.framework.sys.interf;

import java.util.List;

import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Opreation;
import com.macren.persistence.BaseConditionVO;

public interface IOperationDao {
	public List<Opreation> getListOfOperation(BaseConditionVO vo,PageCommon pc);
	public int getOperationNum(BaseConditionVO vo);
	
	public int deleteOpre(String opreationId);
	/*
	 * update Opreation information in:Org out:boolean true:OK false:error
	 */
	public int updateOpre(Opreation opreation);

	/*
	 * save Opreation information out :Boolean true:Ok false:error
	 */
	public int saveOpre(Opreation opreation);
	
	public Opreation getOperationByOperId(String operId);
	
	/*
	 * getListofopreation by org_id & no exist in RoleModel table
	 */
	public List<Opreation> getListOpreationByOrgNoInRoleModel(String roleId,String modelId);
}
