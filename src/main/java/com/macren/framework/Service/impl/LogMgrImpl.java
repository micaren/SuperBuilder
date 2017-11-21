package com.macren.framework.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.macren.framework.ServiceMgr.LogServiceMgr;
import com.macren.framework.sys.business.AbstractBusinessObjectServiceMgr;
import com.macren.framework.sys.interf.ILogDao;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Log;
import com.macren.persistence.BaseConditionVO;
 

@Transactional(rollbackFor = Exception.class)
@Service(LogServiceMgr.SERVICE_NAME)
public class LogMgrImpl extends AbstractBusinessObjectServiceMgr
		implements LogServiceMgr {
	@Autowired
	private ILogDao logdao;
	@Override
	public void saveLog(String userid, String roleid, String orgid,
			String staffid, String log, String type) {
		 
		logdao.saveLog(userid, roleid, orgid, staffid, log, type);
	}

	@Override
	public List<Log> getListOfLog(BaseConditionVO vo, PageCommon pc) {
		// TODO 自动生成的方法存根
		return logdao.getListOfLog(vo, pc);
	}

	@Override
	public int getLogNum(BaseConditionVO vo) {
		// TODO 自动生成的方法存根
		return logdao.getLogNum(vo);
	}
	
	 

	 
 
}
