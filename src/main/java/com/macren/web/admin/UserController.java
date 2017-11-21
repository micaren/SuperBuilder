package com.macren.web.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.macren.framework.ServiceMgr.OrgServiceMgr;
import com.macren.framework.ServiceMgr.RoleServiceMgr;
import com.macren.framework.ServiceMgr.StaffServiceMgr;
import com.macren.framework.ServiceMgr.UserServiceMgr;
import com.macren.framework.ServiceMgr.UserStatus;
import com.macren.framework.sys.exception.ServiceException;
import com.macren.framework.sys.pojo.User;
import com.macren.persistence.BaseConditionVO;
import com.macren.web.BaseController;
 

@Controller("admin.userController")
@RequestMapping(value="/admin/user")
public class UserController extends BaseController{
	@Autowired
	private UserServiceMgr userMgr;
	@Autowired
	private OrgServiceMgr orgMgr;
	@Autowired
	private StaffServiceMgr staffMgr;
	@Autowired
	private RoleServiceMgr roleMgr;
	
	@RequestMapping("")
	public String list(BaseConditionVO vo, Model model) {
		List<User> userList = userMgr.searchUser(vo);
		Integer totalCount = userMgr.searchUserNum(vo);
		
		model.addAttribute("userList", userList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("userStatusList", UserStatus.values());
		model.addAttribute("pageSize", vo.getPageSize());

		return "/admin/user/list";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
 
		model.addAttribute("orgList",orgMgr.getListOrg()   );
	 
		return "/admin/user/add";
	}
	//根据orgid找角色list
	@RequestMapping("/roleList")
	public String roleList(@RequestParam("orgid") String orgid, Model model){
		if (orgid != null){
			model.addAttribute("roleList", roleMgr.getListOfRoleByorgId(orgid)  );
		}
		
		return "/admin/user/roleList";
	}
	//根据roleid找职工list
		@RequestMapping("/staffList")
		public String staffList(@RequestParam("roleid") String roleid, Model model){
			if (roleid != null){
				model.addAttribute("roleList", staffMgr.getListOfStaffByroleId(roleid)  );
			}
			
			return "/admin/user/staffList";
		}
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(User user) {
		try {
			userMgr.addUser(user);
		} catch (ServiceException e) {
			return ajaxDoneError(e.getMessage());
		}

		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	@RequestMapping("/edit/{userId}")
	public String edit(@PathVariable("userId") String userId, Model model) {
		User user = userMgr.getUserById(userId);
		model.addAttribute("orgList",orgMgr.getListOrg()   );
		model.addAttribute("roleList", roleMgr.getListOfRoleByorgId(user.getOrgid())  );
		model.addAttribute("staffList", staffMgr.getListOfStaffByroleId(user.getRoleid())  );
		model.addAttribute("vo", user);
	//	model.addAttribute("genderList", Gender.values());

		return "/admin/user/edit";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(User user) {
		userMgr.updUser(user);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping("/delete/{userId}")
	public ModelAndView delete(@PathVariable("userId") String userId) {

		if ( userMgr.delUser(userId)!=0 )
		 {
		 return ajaxDoneSuccess(getMessage("msg.operation.success"));
		 }
		else
		 {
		  return ajaxDoneError(getMessage("msg.operation.success"));
		 }
		
	}
}
