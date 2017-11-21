package com.macren.framework.sys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.macren.framework.sys.interf.IOrgDao;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Org;
import com.macren.persistence.BaseConditionVO;
@Repository
public class OrgDao implements IOrgDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Override
	public int searchOrgNum(BaseConditionVO vo) {
		String sql ="SELECT count(*) " +
				"	FROM dbo.gd_sys_org ";
		if (vo.getKeywords()!=null) sql =sql + " where org_name like '%"+vo.getKeywords()+"%'";
		return jdbcTemplate.queryForInt(sql);
	}
	public List<Org> getListOfOrg(BaseConditionVO vo,PageCommon pc) {
		final List<Org> listorg = new ArrayList<Org>();
		String sql ="SELECT " +
				"	org_id,org_name,org_tel,org_address,dbo.App_Sys_FpGetOrgNameByOrgid(org_pid) as org_pid,org_order,org_comment" +
				"	FROM dbo.gd_sys_org";
		if (vo.getKeywords()!=null) sql =sql + " where org_name like '%"+vo.getKeywords()+"%'";
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Org org = new Org();
				org.setId(rs.getString("org_id"));
				org.setName(rs.getString("org_name"));
				org.setPid(rs.getString("org_pid"));
				org.setTel(rs.getString("org_tel"));
				org.setOrder(rs.getInt("org_order"));
				org.setAddress(rs.getString("org_address"));
				listorg.add(org);
				org = null;
			}});
		return listorg;
	}

	public int saveOrg(Org org_) {
		String sql = "insert into gd_sys_org (org_id,org_name,org_tel," +
					"org_address,org_pid,org_order,org_comment,org_status)"+
                    " values('"+org_.getId()+"','"+org_.getName()+"','"+org_.getTel()+"'," +
            
                    "'"+org_.getAddress()+"','"+org_.getPid()+"',"+org_.getOrder()+",'"+org_.getComment()+"',1)";
			return jdbcTemplate.update(sql);
	}

	public int updateOrg(Org org) {
		String sql = "update gd_sys_org set " + "org_name='"
					+ org.getName() + "'," + "org_address='" + org.getAddress()
					+ "'," + "org_pid='" + org.getPid() + "'," + "org_order="
					+ org.getOrder() + "," + "org_comment='" + org.getComment()
					+ "', " + "org_status=" + org.getStatus() + " "
					+ "where org_id='" + org.getId() + "'";
		return jdbcTemplate.update(sql);
	}

	public int deleteOrg(String orgId) {
		String sql = "delete gd_sys_org  where gd_sys_org.org_id='"
							+ orgId
							+ "' "
							+ " and   not exists"
							+ "(select gd_sys_role.org_id from gd_sys_role "
							+ "where  gd_sys_role.org_id='"+orgId+"') and   not exists " +
						      "(select gd_sys_staff.org_id from gd_sys_staff where gd_sys_staff.org_id='"+orgId+"') and  not exists " +
						      "(select gd_sys_user.org_id from gd_sys_user where gd_sys_user.org_id='"+orgId+"')   ";
		return jdbcTemplate.update(sql);
	}

 

	public Org getOrgName(String orgId) {
		final Org result =new Org();
		String sql = "select org.org_id, org.org_name,org.org_address,org.org_pid," +
					"org.org_tel,org.org_order,org.org_comment from gd_sys_org as org where org.org_id='"+orgId+"'";
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				result.setId(rs.getString("org_id"));
				result.setName(rs.getString("org_name"));
				result.setAddress(rs.getString("org_address"));
				result.setPid(rs.getString("org_pid"));
				result.setTel(rs.getString("org_tel"));
				result.setOrder(rs.getInt("org_order"));
				result.setComment(rs.getString("org_comment"));
			}});
		 
		return result;
	}
   public List<Org> getListOrg(){
	   final List<Org> orglist=new ArrayList<Org>();
	   String sql= "select org.org_id,org.org_name from gd_sys_org as org ";
	   jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Org org=new Org();
				org.setId(rs.getString("org_id"));
				org.setName(rs.getString("org_name"));
				orglist.add(org);
				org=null;
			}});
		 
	    return orglist;
   }
 
 
}

 
