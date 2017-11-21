package com.macren.framework.sys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.macren.framework.sys.interf.IFeedbackDao;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Feedback;
import com.macren.persistence.BaseConditionVO;
 
@Repository
public class FeedbackDao implements IFeedbackDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	public List<Feedback> getListOfFeedback(BaseConditionVO vo,PageCommon pc) {
		final List<Feedback> listfeedback = new ArrayList<Feedback>();
		String orderfild="";
		String where="";
		if ( !(vo.getOrderField()==null))
		{
			if(!vo.getOrderField().equals(""))
			 orderfild=vo.getOrderField()+" "+vo.getOrderDirection();
			else
				orderfild = "fb_ctime";
		}
		else
		{
			orderfild = "fb_ctime";
		}
		 if (vo.getKeywords()!=null) 
		 { 
			   where = " where  fb_title like '%"+vo.getKeywords()+"%' or  fb_context like '%"+vo.getKeywords()+"%'";
		 }
      String sql ="SELECT	fb_id,fb_title,fb_context,dbo.App_Sys_FpGetStaffNameByUserId(fb_userid) as fb_userid,fb_ctime	" +
      		"FROM (select *,ROW_NUMBER() OVER(order by "+  orderfild+")AS INDEXRUM  from  dbo.gd_sys_feedback "+
    		  where +" )dba where INDEXRUM between "+pc.getStartrow()+" and "+pc.getStoprow();
   jdbcTemplate.query(sql, new RowCallbackHandler(){
	@Override
	public void processRow(ResultSet rs) throws SQLException {
				Feedback feedback = new Feedback();
                feedback.setId(rs.getString("fb_id"));
                feedback.setTitle(rs.getString("fb_title"));
                feedback.setContext(rs.getString("fb_context"));
                feedback.setUserid(rs.getString("fb_userid"));
                feedback.setTime(rs.getTimestamp("fb_ctime"));
				listfeedback.add(feedback);
				feedback = null;
			}});
		 sql =null;
		 where = null;
		 orderfild = null;
		return listfeedback;
	}
	public int searchFeedbackNum(BaseConditionVO vo)
	{
		 String where = "";
		 if (vo.getKeywords()!=null) 
		 { 
			   where = " where  fb_title like '%"+vo.getKeywords()+"%' or  fb_context like '%"+vo.getKeywords()+"%'";
		 }
      String sql ="SELECT	count(*)	FROM dbo.gd_sys_feedback "+where ;
      return  jdbcTemplate.queryForInt(sql);
	}
}
