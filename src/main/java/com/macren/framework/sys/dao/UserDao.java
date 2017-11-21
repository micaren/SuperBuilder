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
import com.macren.framework.sys.interf.IUserDao;
import com.macren.framework.sys.logic.LogicList;
import com.macren.framework.sys.logic.ModelRoleOpreation;
import com.macren.framework.sys.pojo.Opreation;
import com.macren.framework.sys.pojo.User;
import com.macren.persistence.BaseConditionVO;
@Repository
public class UserDao implements IUserDao {
     @Autowired
     private JdbcTemplate jdbcTemplate;
     
	@Override
	public User getUser(String username, String password) {
		final User user = new User();
		String sql="select user_id,username,password,role_id,"
				+ "org_id,staff_id,createtime,status "
				+ "from  gd_sys_user as u where " + "u.username='"
				+ username + "' and u.password='" + password
				+ "'  ";
		 
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet result) throws SQLException {
				user.setId(result.getString("user_id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setRoleid(result.getString("role_id"));
				user.setOrgid(result.getString("org_id"));
				user.setStaffid(result.getString("staff_id"));
				user.setCreatetime(result.getTimestamp("createtime"));
				user.setStatus(result.getInt("status"));
			}
			
		});
		return user;
	}

	@Override
	public List<ModelRoleOpreation> getUserModelRoleOpreation(String Roleid,
			String OrgId) {
		final List<ModelRoleOpreation> listmro = new ArrayList<ModelRoleOpreation>();
        String sql="select DISTINCT rm.model_id ,"
				+ "rm.role_id,rm.org_id from gd_sys_rolemodel as rm where rm.org_id='"
				+ OrgId + "' and rm.role_id='" + Roleid + "'";
		 
        jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet result) throws SQLException {
				ModelRoleOpreation mro = new ModelRoleOpreation();
				mro.setModelid(result.getString("model_id"));
				mro.setRoleid(result.getString("role_id"));
				mro.setOrgid(result.getString("org_id"));
				String sql1="select op.opre_id,op.opre_code,op.org_id,op.opre_num "
								+ "from gd_sys_opre as op,gd_sys_rolemodel as rm where op.opre_id=rm.opre_id "
								+ "and rm.org_id=rm.org_id and rm.model_id='"
								+ mro.getModelid()
								+ "' and rm.org_id='"
								+ mro.getOrgid()
								+ "' and rm.role_id='"
								+ mro.getRoleid() + "'";
				final List<Opreation> opre = new ArrayList<Opreation>();
				jdbcTemplate.query(sql1, new RowCallbackHandler(){
					@Override
					public void processRow(ResultSet result1) throws SQLException {
					Opreation op = new Opreation();
					op.setId(result1.getString("opre_id"));
					op.setCode(result1.getString("opre_code"));
					op.setOrgid(result1.getString("org_id"));
					op.setNum(result1.getInt("opre_num"));
					opre.add(op);
					op = null;
					}
					});
				mro.setOpre(opre);
				listmro.add(mro);
				mro = null;
			   }
			});
	 
		return listmro;
	}
	@Override
	public int searchUserNum(BaseConditionVO vo) {
		 
		String sql="select count(*) "
				+ "from  gd_sys_user as u  ";
		 
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public List<User> getListOfUser(BaseConditionVO vo) {
		final List<User> listuser = new ArrayList<User>();
		String sql="SELECT	user_id,username,password," +
				"dbo.App_Sys_FpGetRoleNameByRoleid(role_id) as role_id," +
				"    dbo.App_Sys_FpGetOrgNameByOrgid(org_id) as org_id," +
				"    dbo.App_Sys_FpGetStaffNameByStaffid(staff_id) as staff_id,status,createtime,comment" +
				"	FROM  dbo.gd_sys_user";
		 
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet result) throws SQLException {
				User user =  new User();
				user.setId(result.getString("user_id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setRoleid(result.getString("role_id"));
				user.setOrgid(result.getString("org_id"));
				user.setStaffid(result.getString("staff_id"));
				user.setCreatetime(result.getTimestamp("createtime"));
				user.setStatus(result.getInt("status"));
				listuser.add(user);
			}
			
		});
		return listuser;
	}

	@Override
	public int deleteUser(String userId) {
		String sql="delete gd_sys_user where user_id='"+userId+"' "; 
		return jdbcTemplate.update(sql);
	 
	}

	@Override
	public int updateUser(User user) {
		String sql = "update gd_sys_user set " +
		 		"username='"+user.getUsername()+"'," +
		 		"password='"+user.getPassword()+"'," +
		 		"role_id='"+user.getRoleid()+"'," +
		 		"org_id='"+user.getOrgid()+"'," +
		 		"staff_id='"+user.getStaffid()+"'," +
		 		"status="+1+"," +
		 		"comment='"+user.getComment()+"' " +
		 		" where gd_sys_user.user_id='"+user.getId()+"'";
		
		return jdbcTemplate.update(sql);
	}

	@Override
	public int saveUser(User user) {
		user.setId(new String(Hex.encodeHex(org.apache.commons.id.uuid.UUID.randomUUID().getRawBytes())));
		String sql ="insert into gd_sys_user (user_id," +
		 		"username," +
		 		"password," +
		 		"role_id," +
		 		"org_id," +
		 		"staff_id," +
		 		"status," +
		 		"createtime," +
		 		"comment)" +
		 		"values ('"+user.getId()+"','" +
		 				""+user.getUsername()+"','"+user.getPassword()+"','" +
		 						""+user.getRoleid()+"','"+user.getOrgid()+"','"
		 						+user.getStaffid()+
		 						"',1,getdate(), '"+user.getComment()+"')";
		return jdbcTemplate.update(sql);
	}

	@Override
	public User getUserBy(String userId) {
		String sql ="select user_id,username,password,role_id,org_id,staff_id,status,createtime,comment from gd_sys_user  where user_id='"+userId+"'";
		final User user = new User();
		jdbcTemplate.query(sql, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(rs.getString("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRoleid(rs.getString("role_id"));
				user.setOrgid(rs.getString("org_id"));
				user.setStaffid(rs.getString("staff_id"));
				user.setStatus(rs.getInt("status"));
				user.setCreatetime(rs.getDate("createtime"));
				user.setComment(rs.getString("comment"));
			}
			
		});
		return user;
	}

	@Override
	public int changePassword(String newPassword, String userid,
			String oldpassword) {
		String sql = "update gd_sys_user set " +
		 		 
			 		"password='"+newPassword+"' " +
			 		 
			 		" where gd_sys_user.user_id='"+userid+"' and gd_sys_user.password='"+oldpassword+"'";
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<LogicList> getAllStaffNameByuserId() {
		String sql ="select user_id,dbo.App_Sys_FpGetStaffNameByUserId(user_id) as username  "
				+ "from  gd_sys_user as u  ";
		final List<LogicList> list =new ArrayList<LogicList>();
		jdbcTemplate.query(sql, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet result) throws SQLException {
				LogicList user = new LogicList();
				user.setId(result.getString("user_id"));
				user.setName(result.getString("username"));
				 list.add(user);
				user=null;
			}
			
		});
		return list;
	}

	@Override
	public int startUser(String userid) {
		String sql ="update gd_sys_user set status=1  where user_id='"+userid+"'";
		return jdbcTemplate.update(sql);
	}

	@Override
	public int stopUser(String userid) {
		String sql ="update gd_sys_user set status=0  where user_id='"+userid+"'";
				return jdbcTemplate.update(sql);
	}


 
}
