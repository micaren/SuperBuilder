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

import com.macren.framework.sys.interf.IRoleModelDao;
import com.macren.framework.sys.logic.LogicRoleModelOpreation;
import com.macren.framework.sys.logic.LogicServiceRoleModel;
import com.macren.framework.sys.pojo.RoleModel;
import com.macren.persistence.BaseConditionVO;

 
@Repository
public class RoleModelDaoImpl implements IRoleModelDao {
	 @Autowired
     private JdbcTemplate jdbcTemplate;
	 
	public int deleteRoleModelOpreationByRoleModleId(String rolemodelId) {
		String sql = "delete gd_sys_rolemodel   where role_model_id='"+rolemodelId+"'";
		return jdbcTemplate.update(sql);
	}

	public List<LogicRoleModelOpreation> getRoleModelOpreationByroleId(
			BaseConditionVO vo) {
		final List<LogicRoleModelOpreation> listLogicRoleModelOpreation = new ArrayList<LogicRoleModelOpreation>();
       String sql ="SELECT role_model_id,org_name,role_name,model_name,dbo.App_Sys_FpGetModelNameByModelid(model_pid) AS pidname,comment " +
		"  from  gd_sys_model as m ,gd_sys_opre as op,gd_sys_rolemodel as rm, gd_sys_role as role,gd_sys_org as org where" +
		"  m.model_id=rm.model_id and role.role_id=rm.role_id and role.org_id =rm.org_id " +
		"  and rm.opre_id=op.opre_id and op.org_id=rm.org_id and op.org_id=org.org_id  and rm.role_id="+vo.getKeywords(); 
       jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				LogicRoleModelOpreation lr = new LogicRoleModelOpreation();
				 lr.setRmid(rs.getString("role_model_id"));
				 lr.setRmorgname(rs.getString("org_name"));
				 lr.setRmrolename(rs.getString("role_name"));
				 lr.setRmmodelname(rs.getString("model_name"));
				 lr.setRmmodelpidname(rs.getString("pidname"));
				 lr.setRmoprename(rs.getString("comment"));
				listLogicRoleModelOpreation.add(lr);
				lr = null;
			}
			
		});
			 
		return listLogicRoleModelOpreation;
	}

	public int save(RoleModel rm) {
	 
		rm.setRmid(new String(Hex.encodeHex(org.apache.commons.id.uuid.UUID.randomUUID().getRawBytes())));
		 
		String sql = "insert into gd_sys_rolemodel (role_model_id,role_id,model_id,opre_id,org_id,comment)" +
					"values (" +
					"'"+rm.getId()+"','"+rm.getRoleid()+"','"+rm.getModelid()+"'" +
							",'"+rm.getOpid()+"','"+rm.getOrgid()+"','"+rm.getComment()+"')";
	 
		return jdbcTemplate.update(sql);
	}

	public LogicServiceRoleModel getRoleMoleByroleId(String roleId) {
		final LogicServiceRoleModel result = new LogicServiceRoleModel();
		String sql = "select org.org_id,role.role_id,org.org_name ,role.role_name " +
					"from gd_sys_role as role,gd_sys_org as org " +
					"where role.org_id=org.org_id and role.role_id='"+roleId+"'";
	 jdbcTemplate.query(sql, new RowCallbackHandler(){
		 @Override
		 public void processRow(ResultSet rs) throws SQLException {
	        	result.setOrg_id(rs.getString("org_id"));
	        	result.setRole_id(rs.getString("role_id"));
	        	result.setOrg_name(rs.getString("org_name"));
	        	result.setRole_name(rs.getString("role_name"));
		    }
			
		});
		return result;
	}

	public LogicServiceRoleModel getRoleMoleByRoleModelId(String rolemodelId) {
		final LogicServiceRoleModel result = new LogicServiceRoleModel();
		String sql  ="select rm.role_model_id,org.org_id,org.org_name,role.role_id,role.role_name,rm.model_id,rm.opre_id,rm.comment from gd_sys_role as role,gd_sys_org as org ," +
					"gd_sys_opre as opre,gd_sys_rolemodel as rm where role.org_id=org.org_id and role.role_id=rm.role_id and org.org_id=rm.org_id and  opre.opre_id=rm.opre_id AND " +
					"opre.org_id=rm.org_id and rm.role_model_id='"+rolemodelId+"'";
		 jdbcTemplate.query(sql, new RowCallbackHandler(){
			 @Override
			 public void processRow(ResultSet rs) throws SQLException {
	        	result.setRm_id(rs.getString("role_model_id"));
	        	result.setOrg_id(rs.getString("org_id"));
	        	result.setOrg_name(rs.getString("org_name"));
	        	result.setRole_id(rs.getString("role_id"));
	        	result.setRole_name(rs.getString("role_name"));
	        	result.setModel_id(rs.getString("model_id"));
	        	result.setOpre_id(rs.getString("opre_id"));
	        	result.setComment(rs.getString("comment"));
			  }
				
			});
		return result;
	}

 

 
}
