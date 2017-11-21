package com.macren.web.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.macren.framework.ServiceMgr.OrgServiceMgr;
import com.macren.framework.ServiceMgr.RoleServiceMgr;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.logic.PageUtil;
import com.macren.framework.sys.pojo.Role;
import com.macren.persistence.BaseConditionVO;
import com.macren.web.BaseController;
 

@Controller("admin.roleController")
@RequestMapping(value="/admin/role")
public class RoleController extends BaseController{
	@Autowired
	private RoleServiceMgr roleMgr;
	@Autowired
	private OrgServiceMgr orgMgr; 
	
	@RequestMapping("")
	public String list(BaseConditionVO vo, Model model) {
		Integer totalCount = roleMgr.getRoleNum(vo);
		PageCommon pc =PageUtil.getPageInfo(vo.getPageNum(), vo.getPageSize(), totalCount);
		List<Role> roleList = roleMgr.getListOfRole(vo, pc);
		vo.setTotalCount(totalCount);
		model.addAttribute("vo", vo);
		model.addAttribute("roleList", roleList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageSize", vo.getPageSize());

		return "/admin/role/list";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
	 
		return "/admin/role/add";
	}
	 
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(Role role) {
		 
		 roleMgr.saveRole(role);
		 

		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	@RequestMapping("/edit/{roleId}")
	public String edit(@PathVariable("roleId") String roleId, Model model) {
		Role role = roleMgr.getRoleByroleId(roleId);
		model.addAttribute("orgList",orgMgr.getListOrg()   );
	 
		model.addAttribute("vo", role);
		return "/admin/role/edit";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(Role role) {
		roleMgr.updateRole(role);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping("/delete/{roleId}")
	public ModelAndView delete(@PathVariable("roleId") String roleId) {

		if ( roleMgr.deleteRole(roleId)!=0 )
		 {
		 return ajaxDoneError(getMessage("msg.operation.success"));
		 }
		else
		 {
		  return ajaxDoneSuccess(getMessage("msg.operation.success"));
		 }
		
	}
}
