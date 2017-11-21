package com.macren.framework.ServiceMgr;

import java.util.List;

import com.macren.framework.sys.business.BusinessObjectServiceMgr;
import com.macren.framework.sys.exception.ServiceException;
import com.macren.framework.sys.pojo.User;
import com.macren.persistence.BaseConditionVO;

public interface UserServiceMgr extends BusinessObjectServiceMgr {
	String SERVICE_NAME = "userServiceMgr";
	 

	/**
	 * 后台添加用户
	 * 
	 * @param user
	 */
	int addUser(User user) throws ServiceException;

	int updUser(User user);
 

	User getUserByUsername(String username,String password);
	User getUserById(String userid);

	int delUser(String id);

	List<User> searchUser(BaseConditionVO vo);

	Integer searchUserNum(BaseConditionVO vo);

  
}
