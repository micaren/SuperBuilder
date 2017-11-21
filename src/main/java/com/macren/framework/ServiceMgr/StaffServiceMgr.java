package com.macren.framework.ServiceMgr;

import java.util.List;

import com.macren.framework.sys.business.BusinessObjectServiceMgr;
import com.macren.framework.sys.exception.ServiceException;
import com.macren.framework.sys.pojo.Staff;
import com.macren.persistence.BaseConditionVO;

public interface StaffServiceMgr extends BusinessObjectServiceMgr {
	String SERVICE_NAME = "staffServiceMgr";
	 
	public List<Staff> getListOfStaff(BaseConditionVO vo);
	public int searchStaffNum(BaseConditionVO vo);
	public int deleteStaff(String staffId);
	/*
	 * update org information in:Org out:boolean true:OK false:error
	 */
	public int updateStaff(Staff staff);

	/*
	 * save org information out :Boolean true:Ok false:error
	 */
	public int saveStaff(Staff staff);
	public Staff getStaffByStaffId(String staffId);
	public List<Staff> getListOfStaffByorgId(String orgId);
	public List<Staff> getListOfStaffByroleId(String roleId);
}
