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

import com.macren.framework.sys.interf.IStaffDao;
import com.macren.framework.sys.pojo.Staff;
import com.macren.persistence.BaseConditionVO;
 
@Repository
public class StaffDao implements IStaffDao {
	 
	 @Autowired
     private JdbcTemplate jdbcTemplate;
		 
		public int searchStaffNum(BaseConditionVO vo) {
			 String sql ="SELECT	 count(*) FROM dbo.gd_sys_staff";
				   if (vo.getKeywords()!=null) sql =  sql +" WHERE gd_sys_staff.org_id LIKE '%'"+vo.getKeywords()+"'%'";
			return jdbcTemplate.queryForInt(sql);
		}

	public List<Staff> getListOfStaff(BaseConditionVO vo) {
	 
		final List<Staff> liststaff = new ArrayList<Staff>();
	 
	   String sql ="SELECT	staff_id,staff_name,staff_phone,staff_tel,staff_address,staff_post,staff_sex,staff_workerid,staff_email," +
	   		"    staff_ctime,staff_status,staff_order,dbo.App_Sys_FpGetOrgNameByOrgid(org_id) as org_id,staff_comment,staff_qq	FROM dbo.gd_sys_staff";
	   if (vo.getKeywords()!=null) sql =  sql +" WHERE gd_sys_staff.org_id LIKE '%'"+vo.getKeywords()+"'%'";
	   jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Staff staff = new Staff();
				staff.setId(rs.getString("staff_id"));
				staff.setName(rs.getString("staff_name"));
				staff.setAddress(rs.getString("staff_address"));
				staff.setOrgid(rs.getString("org_id"));
				staff.setPhone(rs.getString("staff_phone"));
				staff.setPost(rs.getString("staff_post"));
				staff.setWorkerid(rs.getString("staff_workerid"));
				staff.setTel(rs.getString("staff_tel"));
				staff.setSex(rs.getString("staff_sex"));
				staff.setTime(rs.getTimestamp("staff_ctime"));
				staff.setOrder(rs.getInt("staff_order"));
				staff.setQq(rs.getString("staff_qq"));
				staff.setStatus(rs.getInt("staff_status"));
				liststaff.add(staff);
				staff =  null ;
			}
			
		});
		return liststaff;
	}

	public int deleteStaff(String staffId) {
		 
	 
		String sql = "delete gd_sys_staff  where gd_sys_staff.staff_id='"
						    + staffId
							+ "' "
							+ "and    not exists"
							+ "(select gd_sys_user.staff_id from gd_sys_user "
							+ "where   gd_sys_user.staff_id='"+staffId+"' )";
		 
		return jdbcTemplate.update(sql);
	}

	public int saveStaff(Staff staff) {
		staff.setId(new String(Hex.encodeHex(org.apache.commons.id.uuid.UUID.randomUUID().getRawBytes())));
		String sql = "insert into gd_sys_staff "+
					"(staff_id ,staff_name,staff_phone,staff_tel,staff_address"+
                   ",staff_post,staff_sex,staff_workerid,staff_email"+
                   " ,staff_ctime,staff_status,staff_order,org_id,staff_comment,staff_qq)"+
                    "values('"+staff.getId()+"','"+staff.getName()+"','"+staff.getPhone()+"','"+staff.getTel()+"','"+staff.getAddress()+"'" +
                    		",'"+staff.getPost()+"','"+staff.getSex()+"','"+staff.getWorkerid()+"','"+staff.getEmail()+"',GETDATE(),1," +
                    				""+staff.getOrder()+",'"+staff.getOrgid()+"','"+staff.getComment()+"','"+staff.getQq()+"')";
		   
		return  jdbcTemplate.update(sql);
	}
    public Staff getStaffByStaffId(String staffId)
    {
    	final Staff staff =new Staff();
    	String sql = "select staff_id,staff_name,staff_phone,staff_tel,staff_address,staff_post,staff_sex,staff_workerid,staff_email,staff_ctime,staff_status,staff_order,org_id,staff_comment,staff_qq" +
					" from gd_sys_staff as staff where staff.staff_id='"+staffId+"' ";
    	 jdbcTemplate.query(sql, new RowCallbackHandler(){
 			@Override
 			public void processRow(ResultSet rs) throws SQLException {
			staff.setId(rs.getString("staff_id"));
			staff.setName(rs.getString("staff_name"));
			staff.setPhone(rs.getString("staff_phone"));
			staff.setTel(rs.getString("staff_tel"));
			staff.setAddress(rs.getString("staff_address"));
			staff.setPost(rs.getString("staff_post"));
			staff.setSex(rs.getString("staff_sex"));
			staff.setWorkerid(rs.getString("staff_workerid"));
			staff.setEmail(rs.getString("staff_email"));
			staff.setTime(rs.getDate("staff_ctime"));
			staff.setStatus(rs.getInt("staff_status"));
			staff.setOrder(rs.getInt("staff_order"));
			staff.setOrgid(rs.getString("org_id"));
			staff.setComment(rs.getString("staff_comment"));
			staff.setQq(rs.getString("staff_qq"));
 			}
			
 		});
    	return staff;
    }
	public int updateStaff(Staff staff) {
		String sql = "update gd_sys_staff  set"+
					" staff_name='"+staff.getName()+"',staff_phone='"+staff.getPhone()+"',staff_tel='"+staff.getTel()+"',staff_address='"+staff.getAddress()+"'"+
                   ",staff_post='"+staff.getPost()+"',staff_sex='"+staff.getSex()+"',staff_workerid='"+staff.getWorkerid()+"',staff_email='"+staff.getEmail()+"'"+
                   " ,staff_order='"+staff.getOrder()+"',org_id='"+staff.getOrgid()+"',staff_comment='"+staff.getComment()+"',staff_qq='"+staff.getQq()+"' where gd_sys_staff.staff_id='"+staff.getId()+"'";
		return jdbcTemplate.update(sql);
	}

	public List<Staff> getListOfStaffByorgId(String orgId) {
		final List <Staff> liststaff=new ArrayList<Staff>();
				String sql = "select staff_id,org_name,staff_name from gd_sys_staff as staff,gd_sys_org as org where org.org_id=staff.org_id " ;
				if (orgId != null) sql =sql +" where org.org_id='"+orgId+"'";
				jdbcTemplate.query(sql, new RowCallbackHandler(){
					@Override
					public void processRow(ResultSet rs1) throws SQLException {	 
				    Staff staff = new Staff();
				    staff.setId(rs1.getString("staff_id"));
				    staff.setName("原部门："+rs1.getString("org_name")+"----"+rs1.getString("staff_name"));
				    liststaff.add(staff); 
				    staff=null;
			  
				}
				
     	});
		return liststaff;
	}

	@Override
	public List<Staff> getListOfStaffByroleId(String roleId) {
		final List <Staff> liststaff=new ArrayList<Staff>();
		String sql = "select  DISTINCT staff.staff_id ,staff.staff_name from gd_sys_staff as staff,gd_sys_user  as auser ,gd_sys_role as role where auser.staff_id=staff.staff_id and role.org_id=staff.org_id  " ;
		if (roleId != null) sql =sql +"and role.role_id='"+roleId+"'";
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs1) throws SQLException {	 
		    Staff staff = new Staff();
		    staff.setId(rs1.getString("staff_id"));
		    staff.setName(rs1.getString("staff_name"));
		    liststaff.add(staff); 
		    staff=null;
	  
		}
		
	});
return liststaff;
	}


 

}
