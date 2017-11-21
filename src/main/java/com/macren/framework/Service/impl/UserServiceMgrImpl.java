package com.macren.framework.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.macren.framework.ServiceMgr.UserServiceMgr;
import com.macren.framework.sys.business.AbstractBusinessObjectServiceMgr;
import com.macren.framework.sys.exception.ServiceException;
import com.macren.framework.sys.interf.IUserDao;
import com.macren.framework.sys.pojo.User;
import com.macren.persistence.BaseConditionVO;
 

@Transactional(rollbackFor = Exception.class)
@Service(UserServiceMgr.SERVICE_NAME)
public class UserServiceMgrImpl extends AbstractBusinessObjectServiceMgr
		implements UserServiceMgr {
	
	@Autowired
	private IUserDao userdao;
	
	 
	
	public int addUser(User user) throws ServiceException{
	  
		return userdao.saveUser(user);
	}

	public int updUser(User user) {
		return userdao.updateUser(user);
	}

	public int delUser(String id) {
		return userdao.deleteUser(id);
	}

	public List<User> searchUser(BaseConditionVO vo) {
	    return userdao.getListOfUser(vo);
	}

	public Integer searchUserNum(BaseConditionVO vo) {
		Integer count = userdao.searchUserNum(vo);

		return count;
	}

	public void activeUser(String id) {
		userdao.startUser(id);
	}

	public void inActiveUsesr(String id) {
		userdao.stopUser(id);
	}

	 

	public User getUserByUsername(String username,String password){
	 
		
		return  userdao.getUser(username, password);
	}

	@Override
	public User getUserById(String userid) {
		// TODO 自动生成的方法存根
		return userdao.getUserBy(userid);
	}

 

	 

 
}
