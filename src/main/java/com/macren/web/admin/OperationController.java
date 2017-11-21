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

import com.macren.framework.ServiceMgr.OperationServiceMgr;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.logic.PageUtil;
import com.macren.framework.sys.pojo.Opreation;
import com.macren.persistence.BaseConditionVO;
import com.macren.web.BaseController;
 

@Controller("admin.operationController")
@RequestMapping(value="/admin/operation")
public class OperationController extends BaseController{
	@Autowired
	private OperationServiceMgr operationMgr;
	 
	
	@RequestMapping("")
	public String list(BaseConditionVO vo, Model model) {
		Integer totalCount = operationMgr.getOperationNum(vo);
		PageCommon pc =PageUtil.getPageInfo(vo.getPageNum(), vo.getPageSize(), totalCount);
		List<Opreation> operationList = operationMgr.getListOfOperation(vo, pc);
		vo.setTotalCount(totalCount);
		model.addAttribute("vo", vo);
		model.addAttribute("operationList", operationList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageSize", vo.getPageSize());

		return "/admin/operation/list";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
	 
		return "/admin/operation/add";
	}
	 
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert( Opreation operation) {
		operationMgr.saveOpre(operation);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	@RequestMapping("/edit/{operationId}")
	public String edit(@PathVariable("operationId") String operationId, Model model) {
		Opreation op = operationMgr.getOperationByOperId(operationId);
	 
		model.addAttribute("vo", op);

		return "/admin/operation/edit";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(Opreation op) {
		operationMgr.updateOpre(op);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping("/delete/{opreationId}")
	public ModelAndView delete(@PathVariable("opreationId") String opreationId) {

		if (operationMgr.deleteOpre(opreationId)!=0 )
		 {
		 return ajaxDoneSuccess(getMessage("msg.operation.success"));
		 }
		else
		 {
		  return ajaxDoneError(getMessage("msg.operation.success"));
		 }
		
	}
}
