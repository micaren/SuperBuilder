package com.macren.framework.ServiceMgr;

 

import java.util.List;

import com.macren.framework.sys.business.BusinessObjectServiceMgr;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Org;
import com.macren.persistence.BaseConditionVO;
 

public interface OrgServiceMgr extends BusinessObjectServiceMgr {
	String SERVICE_NAME = "orgServiceMgr";
	public List<Org> getListOfOrg(BaseConditionVO vo,PageCommon pc );
	public int searchOrgNum(BaseConditionVO vo );
	public int getOrgNum(BaseConditionVO vo);
	
	public int deleteOrg(String orgId);
	/*
	 * update org information in:Org out:boolean true:OK false:error
	 */
	public int updateOrg(Org org);

	/*
	 * save org information out :Boolean true:Ok false:error
	 */
	public int saveOrg(Org org);
	public Org getOrgName(String orgId);
	public List<Org> getListOrg(); 
   

  
}
