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

import com.macren.framework.sys.interf.IOperationDao;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Opreation;
import com.macren.persistence.BaseConditionVO;
 
@Repository
public class OpreationDao implements IOperationDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Override
	public int getOperationNum(BaseConditionVO vo) {
		String where =  ""; 
		if (vo.getKeywords()!=null) 
		 { 
			   where = " where  comment like '%"+vo.getKeywords()+"%'  ";
		 }
		String sql ="SELECT " +
				"count(*) " +
				"	FROM   dbo.gd_sys_opre "+where;
		return jdbcTemplate.queryForInt(sql);
	}
	
	public List<Opreation> getListOfOperation(BaseConditionVO vo,PageCommon pc) {
		final List<Opreation> listoperation = new ArrayList<Opreation>();
		String orderfild="";
		String where="";
		if ( !(vo.getOrderField()==null))
		{
			if(!vo.getOrderField().equals(""))
			 orderfild=vo.getOrderField()+" "+vo.getOrderDirection();
			else
				orderfild = "opre_code";
		}
		else
		{
			orderfild = "opre_code";
		}
		 if (vo.getKeywords()!=null) 
		 { 
			   where = " where  comment like '%"+vo.getKeywords()+"%'  ";
		 }
		String sql ="SELECT " +
				"opre_id,opre_code,dbo.App_Sys_FpGetOrgNameByOrgid(org_id) as org_id,opre_num,comment" +
				"	FROM ( select *,ROW_NUMBER() over(order by "+orderfild+")as rowindex from dbo.gd_sys_opre  "+where+" )dba where rowindex between "+pc.getStartrow()+" and "+pc.getStoprow();
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Opreation operation = new Opreation();
				operation.setId(rs.getString("opre_id"));
				operation.setCode(rs.getString("opre_code"));
				operation.setNum(rs.getInt("opre_num"));
				operation.setOrgid(rs.getString("org_id"));
				operation.setComment(rs.getString("comment"));
				listoperation.add(operation);
				operation = null;
			}});
			 
		return listoperation;
	}

	public int deleteOpre(String opreationId) {
		String sql  = "delete gd_sys_opre   where opre_id='"+opreationId+"'";
		return jdbcTemplate.update(sql);
	}

	public int saveOpre(Opreation opreation) {
		opreation.setId(new String(Hex.encodeHex(org.apache.commons.id.uuid.UUID.randomUUID().getRawBytes())));
		String sql ="insert into gd_sys_opre (opre_id,opre_code ,org_id,opre_num,comment) VALUES  "
					+ "('"+opreation.getId()+"','"+opreation.getCode()+"','"+opreation.getOrgid()+"',"+opreation.getNum()+",'"+opreation.getComment()+"')";
		return jdbcTemplate.update(sql);
	}

	public int updateOpre(Opreation opreation) {
		String sql ="update gd_sys_opre set opre_id="
					+ "'"+opreation.getId()+"',opre_code='"+opreation.getCode()+"'," +
							"org_id='"+opreation.getOrgid()+"'," +
									"opre_num="+opreation.getNum()+",comment='"+opreation.getComment()+"' " +
											" where opre_id='"+opreation.getId()+"'";
			 
		return jdbcTemplate.update(sql);
	}

	public Opreation getOperationByOperId(String operId) {
		final Opreation op =new Opreation();
		String sql = "select opre_id,opre_code,org_id,opre_num,comment from gd_sys_opre where opre_id='"+operId+"'";
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				op.setId(rs.getString("opre_id"));
				op.setCode(rs.getString("opre_code"));
				op.setNum(rs.getInt("opre_num"));
				op.setOrgid(rs.getString("org_id"));
				op.setComment(rs.getString("comment"));
				
			}});
		return op;
	}

	public List<Opreation> getListOpreationByOrgNoInRoleModel(String roleId,String modelId) {
		final List<Opreation> listop =new ArrayList<Opreation>();
		
		String sql = "select op.opre_id ,op.comment FROM gd_sys_opre as op, " +
					"gd_sys_role as role where op.org_id=role.org_id and op.org_id=role.org_id and " +
					"role.role_id='"+roleId+"' " +
					"and op.opre_id not in(select rm.opre_id from gd_sys_rolemodel as rm where rm.role_id='"+roleId+"'  and " +
					"rm.model_id='"+modelId+"' )";
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Opreation op =new Opreation();
				op.setId(rs.getString("opre_id"));
				op.setComment(rs.getString("comment"));
				listop.add(op);
				op=null;
				
			}});
		return listop;
	}

 
	

}
