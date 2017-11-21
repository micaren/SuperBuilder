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

import com.macren.framework.ServiceMgr.ModelServiceMgr;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.logic.PageUtil;
import com.macren.persistence.BaseConditionVO;
import com.macren.web.BaseController;
 

@Controller("admin.modelController")
@RequestMapping(value="/admin/model")
public class ModelController extends BaseController{
	@Autowired
	private ModelServiceMgr modelMgr;
	 
	 
	
	@RequestMapping("")
	public String list(BaseConditionVO vo, Model model) {
		Integer totalCount = modelMgr.searchModelNum(vo);
		PageCommon pc =PageUtil.getPageInfo(vo.getPageNum(), vo.getPageSize(), totalCount);
		List<com.macren.framework.sys.pojo.Model> modelList = modelMgr.getListOfModel(vo,pc);
		model.addAttribute("modelList", modelList);
		vo.setTotalCount(totalCount);
		model.addAttribute("vo", vo);
		model.addAttribute("pageSize", vo.getPageSize());

		return "/admin/model/list";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {
 
		model.addAttribute("modelList",modelMgr.getRootOfModel()  );
	 
		return "/admin/model/add";
	}
	 
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(com.macren.framework.sys.pojo.Model model) {
		 
			modelMgr.saveModel(model);
		 
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	@RequestMapping("/edit/{modelId}")
	public String edit(@PathVariable("modelId") String modelId, Model model) {
		com.macren.framework.sys.pojo.Model model1 = modelMgr.getModelByModelId(modelId);
		model.addAttribute("modelList", modelMgr.getRootOfModel()   );
		model.addAttribute("vo", model1);
		return "/admin/model/edit";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(com.macren.framework.sys.pojo.Model m) {
		modelMgr.saveModel(m);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping("/delete/{modelId}")
	public ModelAndView delete(@PathVariable("modelId") String modelId) {

		if ( modelMgr.delModelByModelId(modelId)!=0 )
		 {
		 return ajaxDoneSuccess(getMessage("msg.operation.success"));
		 }
		else
		 {
		  return ajaxDoneError(getMessage("msg.operation.success"));
		 }
		
	}
}
