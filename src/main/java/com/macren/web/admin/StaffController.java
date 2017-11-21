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
import com.macren.framework.ServiceMgr.StaffServiceMgr;
import com.macren.framework.enums.Gender;
import com.macren.framework.sys.pojo.Staff;
import com.macren.persistence.BaseConditionVO;
import com.macren.web.BaseController;
 

@Controller("admin.staffController")
@RequestMapping(value="/admin/staff")
public class StaffController extends BaseController{
 
	@Autowired
	private StaffServiceMgr staffMgr;
	@Autowired
	private OrgServiceMgr orgMgr;
	
	@RequestMapping("")
	public String list(BaseConditionVO vo, Model model) {
		List<Staff> staffList = staffMgr.getListOfStaff(vo);
		Integer totalCount = staffMgr.searchStaffNum(vo);
		
		model.addAttribute("staffList", staffList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageSize", vo.getPageSize());

		return "/admin/staff/list";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
 
		model.addAttribute("orgList",orgMgr.getListOrg()   );
		model.addAttribute("genderList", Gender.values());
		return "/admin/staff/add";
	}
	 
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(Staff staff) {
		 
			staffMgr.saveStaff(staff);
	 

		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	@RequestMapping("/edit/{staffId}")
	public String edit(@PathVariable("staffId") String staffId, Model model) {
		Staff staff = staffMgr.getStaffByStaffId(staffId);
		model.addAttribute("orgList",orgMgr.getListOrg()   );
		model.addAttribute("genderList", Gender.values());
		model.addAttribute("vo", staff);

		return "/admin/staff/edit";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(Staff staff) {
		staffMgr.updateStaff(staff);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping("/delete/{staffId}")
	public ModelAndView delete(@PathVariable("staffId") String staffId) {

		if ( staffMgr.deleteStaff(staffId)==0 )
		 {
		 return ajaxDoneError(getMessage("msg.operation.failure"));
		 }
		else
		 {
		  return ajaxDoneSuccess(getMessage("msg.operation.success"));
		 }
		
	}
}
