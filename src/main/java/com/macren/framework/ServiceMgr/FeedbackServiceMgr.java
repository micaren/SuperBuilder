package com.macren.framework.ServiceMgr;

import java.util.List;

import com.macren.framework.sys.business.BusinessObjectServiceMgr;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Feedback;
import com.macren.persistence.BaseConditionVO;

public interface FeedbackServiceMgr extends BusinessObjectServiceMgr {
	String SERVICE_NAME = "feedbackServiceMgr";
	 
	public List<Feedback> getListOfFeedback(BaseConditionVO vo,PageCommon pc);
	public int searchFeedbackNum(BaseConditionVO vo);
 
}
