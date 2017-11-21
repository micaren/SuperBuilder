package com.macren.framework.sys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.id.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.macren.framework.sys.interf.ILogDao;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Log;
import com.macren.persistence.BaseConditionVO;
 
@Repository
public class LogDao implements ILogDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;

	public void saveLog(String userid,String roleid,String orgid,String staffid, String log, String type) {

		String sql = "insert into dbo.gd_sys_log" +
							"(log_id,log_userid,operation," +
							"log_roleid,log_orgid,log_staffid,opertype,log_ctime) "
							+ "values('"
							+ new String(Hex
									.encodeHex(org.apache.commons.id.uuid.UUID
											.randomUUID().getRawBytes()))
							+ "', '"
							+ userid
							+ "','"
							+ log
							+ "','"
							+roleid
							+ "','"
							+orgid
							+ "','"
							+staffid
							+ "','"
							+ type
							+ "',getDate())";

	     jdbcTemplate.update(sql);
	}

	public List<Log> getListOfLog(BaseConditionVO vo,PageCommon pc) {
		final List<Log> listlog = new ArrayList<Log>();
		String orderfild="";
		String where="";
		if ( !(vo.getOrderField()==null))
		{
			if(!vo.getOrderField().equals(""))
			 orderfild=vo.getOrderField()+" "+vo.getOrderDirection();
			else
				orderfild = "log_ctime";
		}
		else
		{
			orderfild = "log_ctime";
		}
		 if (vo.getKeywords()!=null) 
		 { 
			   where = " where  operation like '%"+vo.getKeywords()+"%' or  log_staffid like '%"+vo.getKeywords()+"%'";
		 }
                  String sql="	SELECT	log_id,operation,log_userid,dbo.App_Sys_FpGetRoleNameByRoleid(log_roleid) as log_roleid,     dbo.App_Sys_FpGetOrgNameByOrgid(log_orgid) as log_orgid,     dbo.App_Sys_FpGetStaffNameByStaffid(log_staffid) as log_staffid,     opertype,log_ctime	FROM " +
                  		" (select *,ROW_NUMBER() OVER(order by "+orderfild+" )as rowindex from dbo.gd_sys_log "+where+"  )dba where rowindex between "+pc.getStartrow()+" and "+pc.getStoprow();
       jdbcTemplate.query(sql, new RowCallbackHandler(){
	@Override
	public void processRow(ResultSet rs) throws SQLException {
                     Log log = new Log();
				log.setId(rs.getString("log_id"));
				log.setUserid(rs.getString("log_userid"));
				log.setOperation(rs.getString("operation"));
				log.setOrgid(rs.getString("log_orgid"));
				log.setRoleid(rs.getString("log_roleid"));
				log.setStaffid(rs.getString("log_staffid"));
				log.setType(rs.getString("opertype"));
				log.setTime(rs.getTimestamp("log_ctime"));
				listlog.add(log);
				log = null;
			}});
			 
		return listlog;
	}

	@Override
	public int getLogNum(BaseConditionVO vo) {
		String where = "";
		if (vo.getKeywords()!=null) 
		 { 
			   where = " where  operation like '%"+vo.getKeywords()+"%' or  log_staffid like '%"+vo.getKeywords()+"%'";
		 }
		 String sql="	SELECT	count(*)	FROM dbo.gd_sys_log "+where;
	       return jdbcTemplate.queryForInt(sql);
	}

 

}
