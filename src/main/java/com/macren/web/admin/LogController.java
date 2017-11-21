package com.macren.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macren.framework.ServiceMgr.LogServiceMgr;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.logic.PageUtil;
import com.macren.framework.sys.pojo.Log;
import com.macren.persistence.BaseConditionVO;
import com.macren.web.BaseController;
 

@Controller("admin.logController")
@RequestMapping(value="/admin/log")
public class LogController extends BaseController{
	@Autowired
	private LogServiceMgr logMgr;
	 
	
	@RequestMapping("")
	public String list(BaseConditionVO vo, Model model) {
		Integer totalCount = logMgr.getLogNum(vo);
		PageCommon pc =PageUtil.getPageInfo(vo.getPageNum(), vo.getPageSize(), totalCount);
		List<Log> logList = logMgr.getListOfLog(vo, pc);
		vo.setTotalCount(totalCount);
		model.addAttribute("vo", vo);
		model.addAttribute("logList", logList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageSize", vo.getPageSize());

		return "/admin/log/list";
	}
	 
}
