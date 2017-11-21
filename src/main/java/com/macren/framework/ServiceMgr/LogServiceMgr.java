package com.macren.framework.ServiceMgr;

import java.util.List;

import com.macren.framework.sys.business.BusinessObjectServiceMgr;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Feedback;
import com.macren.framework.sys.pojo.Log;
import com.macren.persistence.BaseConditionVO;

public interface LogServiceMgr extends BusinessObjectServiceMgr {
	String SERVICE_NAME = "logServiceMgr";
	public void saveLog(String userid,String roleid,String orgid,String staffid, String log, String type);

	public List<Log> getListOfLog(BaseConditionVO vo,PageCommon pc);
	
	public int getLogNum(BaseConditionVO vo);
	
}
