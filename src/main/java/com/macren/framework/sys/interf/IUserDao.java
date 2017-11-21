package com.macren.framework.sys.interf;

import java.util.List;

import com.macren.framework.sys.logic.LogicList;
import com.macren.framework.sys.logic.ModelRoleOpreation;
import com.macren.framework.sys.pojo.User;
import com.macren.persistence.BaseConditionVO;

public interface IUserDao {
	public User getUser(String username, String password);

	public List<ModelRoleOpreation> getUserModelRoleOpreation(String Roleid,
			String OrgId);

	public List<User> getListOfUser(BaseConditionVO vo);
	public int searchUserNum(BaseConditionVO vo);
	
	public int deleteUser(String userId);
	/*
	 * update org information in:Org out:boolean true:OK false:error
	 */
	public int updateUser(User user);

	/*
	 * save org information out :Boolean true:Ok false:error
	 */
	public int saveUser(User user);
	
	public User getUserBy(String userId);
	
	public int changePassword(String newPassword ,String userid,String oldpassword);
	
	public List<LogicList> getAllStaffNameByuserId();  
	
	public int	 startUser(String userid);

	public int stopUser(String userid);
}
