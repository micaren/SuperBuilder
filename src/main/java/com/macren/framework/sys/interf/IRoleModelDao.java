package com.macren.framework.sys.interf;

import java.util.List;

import com.macren.framework.sys.logic.LogicRoleModelOpreation;
import com.macren.framework.sys.logic.LogicServiceRoleModel;
import com.macren.framework.sys.pojo.RoleModel;
import com.macren.persistence.BaseConditionVO;

public interface IRoleModelDao {
	/*
	 * save modelrole
	 */
   public int save(RoleModel rm);
   
   /*
    * delete modelrole
    */
   public int deleteRoleModelOpreationByRoleModleId(String rolemodelId);
   
   /*
    * get list modelrole opreation
    * 
    */
   public List<LogicRoleModelOpreation> getRoleModelOpreationByroleId(BaseConditionVO vo);
   
   public LogicServiceRoleModel getRoleMoleByroleId(String roleId);
   
   public LogicServiceRoleModel getRoleMoleByRoleModelId(String rolemodelId);
}
