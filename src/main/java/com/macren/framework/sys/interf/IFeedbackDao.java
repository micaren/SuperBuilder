package com.macren.framework.sys.interf;

import java.util.List;

import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Feedback;
import com.macren.persistence.BaseConditionVO;

public interface IFeedbackDao {
	public List<Feedback> getListOfFeedback(BaseConditionVO vo,PageCommon pc);
	public int searchFeedbackNum(BaseConditionVO vo) ;
}
