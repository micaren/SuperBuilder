package com.macren.web.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.macren.framework.ServiceMgr.FeedbackServiceMgr;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.logic.PageUtil;
import com.macren.framework.sys.pojo.Feedback;
import com.macren.persistence.BaseConditionVO;
import com.macren.web.BaseController;
 

@Controller("admin.feedbackController")
@RequestMapping(value="/admin/feedback")
public class FeedbackController extends BaseController{
	@Autowired
	private FeedbackServiceMgr feedbackMgr;
	 
	
	@RequestMapping("")
	public String list(BaseConditionVO vo, Model model) {
		Integer totalCount = feedbackMgr.searchFeedbackNum(vo);
		PageCommon pc =PageUtil.getPageInfo(vo.getPageNum(), vo.getPageSize(), totalCount);
		List<Feedback> feedbackList = feedbackMgr.getListOfFeedback(vo,pc);
		vo.setTotalCount(totalCount);
		model.addAttribute("vo", vo);
		model.addAttribute("feedbackList", feedbackList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageSize", vo.getPageSize());

		return "/admin/feedback/list";
	}
	
	 
}
