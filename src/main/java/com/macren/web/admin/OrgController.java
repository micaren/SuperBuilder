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
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.logic.PageUtil;
import com.macren.framework.sys.pojo.Org;
import com.macren.persistence.BaseConditionVO;
import com.macren.web.BaseController;
 

@Controller("admin.orgController")
@RequestMapping(value="/admin/org")
public class OrgController extends BaseController{
	@Autowired
	private OrgServiceMgr orgMgr;
	 
	
	@RequestMapping("")
	public String list(BaseConditionVO vo, Model model) {
		Integer totalCount = orgMgr.getOrgNum(vo);
		PageCommon pc =PageUtil.getPageInfo(vo.getPageNum(), vo.getPageSize(), totalCount);
		List<Org> userList = orgMgr.getListOfOrg(vo, pc);
		vo.setTotalCount(totalCount);
		model.addAttribute("vo", vo);
		model.addAttribute("orgList", userList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageSize", vo.getPageSize());

		return "/admin/org/list";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
 
		model.addAttribute("orgList",orgMgr.getListOrg()   );
	 
		return "/admin/org/add";
	}
	 
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(Org org) {
		 
			orgMgr.saveOrg(org);
		 

		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	@RequestMapping("/edit/{orgId}")
	public String edit(@PathVariable("orgId") String orgId, Model model) {
		Org org = orgMgr.getOrgName(orgId);
		model.addAttribute("orgList",orgMgr.getListOrg()   );
	 
		model.addAttribute("vo", org);
	//	model.addAttribute("genderList", Gender.values());

		return "/admin/user/edit";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(Org org) {
		orgMgr.updateOrg(org);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping("/delete/{orgId}")
	public ModelAndView delete(@PathVariable("orgId") String orgId) {

		if ( orgMgr.deleteOrg(orgId)!=0 )
		 {
		 return ajaxDoneSuccess(getMessage("msg.operation.success"));
		 }
		else
		 {
		  return ajaxDoneError(getMessage("msg.operation.success"));
		 }
		
	}
}
