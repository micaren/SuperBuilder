package com.macren.framework.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.macren.framework.ServiceMgr.FeedbackServiceMgr;
import com.macren.framework.sys.business.AbstractBusinessObjectServiceMgr;
import com.macren.framework.sys.interf.IFeedbackDao;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Feedback;
import com.macren.persistence.BaseConditionVO;
 

@Transactional(rollbackFor = Exception.class)
@Service(FeedbackServiceMgr.SERVICE_NAME)
public class FeedbackMgrImpl extends AbstractBusinessObjectServiceMgr
		implements FeedbackServiceMgr {
	
	@Autowired
	private IFeedbackDao feedbackdao;

	@Override
	public List<Feedback> getListOfFeedback(BaseConditionVO vo,PageCommon pc) {
		// TODO 自动生成的方法存根
		return feedbackdao.getListOfFeedback(vo,pc);
	}

	@Override
	public int searchFeedbackNum(BaseConditionVO vo) {
		// TODO 自动生成的方法存根
		return feedbackdao.searchFeedbackNum(vo);
	}

	 
 
}
