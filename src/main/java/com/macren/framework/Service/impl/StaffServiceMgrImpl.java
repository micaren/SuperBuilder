package com.macren.framework.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.macren.framework.ServiceMgr.StaffServiceMgr;
import com.macren.framework.sys.business.AbstractBusinessObjectServiceMgr;
import com.macren.framework.sys.exception.ServiceException;
import com.macren.framework.sys.interf.IStaffDao;
import com.macren.framework.sys.pojo.Staff;
import com.macren.framework.sys.pojo.User;
import com.macren.persistence.BaseConditionVO;
 

@Transactional(rollbackFor = Exception.class)
@Service(StaffServiceMgr.SERVICE_NAME)
public class StaffServiceMgrImpl extends AbstractBusinessObjectServiceMgr
		implements StaffServiceMgr {
	
	@Autowired
	private IStaffDao staffdao;

	@Override
	public List<Staff> getListOfStaff(BaseConditionVO vo) {
		// TODO 自动生成的方法存根
		return staffdao.getListOfStaff(vo);
	}

	@Override
	public int searchStaffNum(BaseConditionVO vo) {
		// TODO 自动生成的方法存根
		return staffdao.searchStaffNum(vo);
	}

	@Override
	public int deleteStaff(String staffId) {
		// TODO 自动生成的方法存根
		return staffdao.deleteStaff(staffId);
	}

	@Override
	public int updateStaff(Staff staff) {
		// TODO 自动生成的方法存根
		return staffdao.updateStaff(staff);
	}

	@Override
	public int saveStaff(Staff staff) {
		// TODO 自动生成的方法存根
		return staffdao.saveStaff(staff);
	}

	@Override
	public Staff getStaffByStaffId(String staffId) {
		// TODO 自动生成的方法存根
		return staffdao.getStaffByStaffId(staffId);
	}

	@Override
	public List<Staff> getListOfStaffByorgId(String orgId) {
		// TODO 自动生成的方法存根
		return staffdao.getListOfStaffByorgId(orgId);
	}

	@Override
	public List<Staff> getListOfStaffByroleId(String roleId) {
		// TODO 自动生成的方法存根
		return staffdao.getListOfStaffByroleId(roleId);
	}
 
 
}
