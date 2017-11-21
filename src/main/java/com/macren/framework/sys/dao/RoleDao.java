package com.macren.framework.sys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.id.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.macren.framework.sys.interf.IRoleDao;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Model;
import com.macren.framework.sys.pojo.Role;
import com.macren.persistence.BaseConditionVO;
@Repository
public class RoleDao implements IRoleDao {
	 
	@Autowired
     private JdbcTemplate jdbcTemplate;

	public List getRoleMenuByUserRoleId(final String RoleId) {
		final List returnList = new ArrayList();
 
		int i = 0, j = 0;
			 String sql = "select mo.model_id,mo.model_name,mo.model_href,"
					+ "mo.model_target,mo.model_pid  from gd_sys_opre as op,gd_sys_model as mo,"
					+ "gd_sys_rolemodel as rm ,gd_sys_role as role "
					+ "where mo.model_pid='0' and op.opre_id=rm.opre_id and op.opre_code='look'  and mo.model_id=rm.model_id "
					+ "and rm.role_id='"
					+ RoleId
					+ "' and mo.model_status=1 and rm.role_id=role.role_id and rm.org_id=role.org_id "
					+ "ORDER BY mo.model_order ASC;";
			jdbcTemplate.query(sql, new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet rs) throws SQLException {
			 
				 final Hashtable userHash = new Hashtable();
				userHash.put("TfunId", rs.getString("model_id"));
				userHash.put("TfunName", rs.getString("model_name"));

				String sql1 = "select mo.model_id,mo.model_name,mo.model_href,mo.model_target,mo.model_pid "
						+ "from gd_sys_opre as op,gd_sys_model as mo,gd_sys_rolemodel as rm,gd_sys_role as role where mo.model_pid='"
						+ userHash.get("TfunId")
						+ "' "
						+ "and op.opre_id=rm.opre_id and op.opre_code='look'  and mo.model_id=rm.model_id and rm.role_id='"
						+ RoleId
						+ "' "
						+ "and mo.model_status=1  and rm.role_id=role.role_id and rm.org_id=role.org_id "
						+ "and rm.role_id=role.role_id and rm.org_id=role.org_id ORDER BY mo.model_order asc;";
				//子项目
				final List usersubList = new ArrayList();
				jdbcTemplate.query(sql1, new RowCallbackHandler(){
					@Override
					public void processRow(ResultSet rs1) throws SQLException {
					 final Hashtable usersubHash = new Hashtable();
					usersubHash.put("id", rs1.getString("model_id"));
					usersubHash.put("text", rs1.getString("model_name"));
					usersubHash.put("href", rs1.getString("model_href")  );
					usersubHash.put("hrefTarget",rs1.getString("model_target"));
					usersubList.add(usersubHash);
					 }
					});
				 
				userHash.put("RoleSub", usersubList);
				returnList.add(userHash);
			}});
			 
		return returnList;
	}

	/*
	 * 据roleid获取角色的名称
	 */
	public String getRoleName(String RoleId) {
		final String result ="";
			String sql = "select role.role_name from gd_sys_role as role where role.role_id="
					+ RoleId;
			return jdbcTemplate.queryForObject(sql, String.class);
		 
	 
	}

	@Override
	public int getRoleNum(BaseConditionVO vo) {
		String where="";
		if (vo.getKeywords()!=null) 
		 { 
			   where = " where  role_name like '%"+vo.getKeywords()+"%'";
		 }
		String sql="SELECT		count(*)	FROM dbo.gd_sys_role "+where;
		return jdbcTemplate.queryForInt(sql);
	}
	public List<Role> getListOfRole(BaseConditionVO vo,PageCommon pc) {
		final List<Role> listrole = new ArrayList<Role>();
		String orderfild="";
		String where="";
		if ( !(vo.getOrderField()==null))
		{
			if(!vo.getOrderField().equals(""))
			 orderfild=vo.getOrderField()+" "+vo.getOrderDirection();
			else
				orderfild = "role_name";
		}
		else
		{
			orderfild = "role_name";
		}
		 if (vo.getKeywords()!=null) 
		 { 
			   where = " where  role_name like '%"+vo.getKeywords()+"%'";
		 }
		String sql="SELECT		role_id,role_name,role_enable,dbo.App_Sys_FpGetOrgNameByOrgid(org_id) as org_id,role_comment	FROM " +
				"(select *,ROW_NUMBER() OVER(order by "+orderfild+")as rowindex from dbo.gd_sys_role "+where+" )dba where rowindex between "+pc.getStartrow()+" and "+pc.getStoprow();
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Role role = new Role();
				role.setId(rs.getString("role_id"));
				role.setName(rs.getString("role_name"));
				role.setOrgid(rs.getString("org_id"));
				role.setEnable(rs.getInt("role_enable"));
				listrole.add(role);
				role = null;
			}
		});
	 
		return listrole;
	}

	public int deleteRole(String roleId) {
		String sql ="delete gd_sys_role  where gd_sys_role.role_id='"
						    + roleId
							+ "' "
							+ "and  not exists"
							+ "(select gd_sys_rolemodel.role_id from gd_sys_rolemodel ";
			 return jdbcTemplate.update(sql);
	}

	public int saveRole(Role role) {
		role.setId(new String(Hex.encodeHex(org.apache.commons.id.uuid.UUID.randomUUID().getRawBytes())));
		String sql = "insert into gd_sys_role (role_id," +
					                                      "role_name," +
					                                      "role_enable," +
					                                      "org_id," +
					                                      "role_comment)" +
					                                      "values('"+role.getId()+
					                                      "','"+role.getName()+
					                                      "',1,'"+role.getOrgid()+
					                                      "','"+role.getComment()+
					                                      "')";
		return jdbcTemplate.update(sql);
	}

	public int updateRole(Role role) {
		String sql ="update gd_sys_role set  role_name='"+role.getName()+"'," +
					                                      "role_enable="+1+"," +
					                                      "org_id='"+role.getOrgid()+"'," +
					                                      "role_comment='"+role.getComment()+"' " +
					                                      "where role_id='"+role.getId()+"'";
		return jdbcTemplate.update(sql);
	}

	public Role getRoleByroleId(String roleId) {
		final Role role =new Role();
		 
			String sql= "select role_id,role_name,role_enable,org_id,role_comment from gd_sys_role as role where role.role_id='"
					+ roleId+"'";
			jdbcTemplate.query(sql, new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet rs1) throws SQLException {
				role.setId(rs1.getString("role_id"));
				role.setName(rs1.getString("role_name"));
				role.setEnable(rs1.getInt("role_enable"));
				role.setOrgid(rs1.getString("org_id"));
				role.setComment(rs1.getString("role_comment"));
				}});
		return role;
	}

	public List<Role> getListOfRoleByorgId(String orgId) {
		final List <Role> listrole=new ArrayList<Role>();
	 
			String sql="";
			if ((orgId!=null))
			 sql = "select role.role_id,org.org_name,role.role_name from gd_sys_role as role,gd_sys_org as org where org.org_id=role.org_id and role.org_id='"
					+ orgId+"'";
			else
				sql = "select role.role_id,org.org_name,role.role_name from gd_sys_role as role,gd_sys_org as org  where org.org_id=role.org_id";
			jdbcTemplate.query(sql, new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet rs1) throws SQLException {
				Role role = new Role();
				role.setId(rs1.getString("role_id"));
				role.setName("机构："+rs1.getString("org_name")+"-----"+rs1.getString("role_name"));
				listrole.add(role); 
				role=null;
				}});
		return listrole;
	}


}
