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

import com.macren.framework.sys.interf.IModelDao;
import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Model;
import com.macren.persistence.BaseConditionVO;
 
@Repository
public class ModelDao implements IModelDao {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	public List<Model> getListOfModel( BaseConditionVO vo,PageCommon pc) {
		final List<Model> listlog = new ArrayList<Model>();
		String orderfild="";
		String where="";
		if ( !(vo.getOrderField()==null))
		{
			if(!vo.getOrderField().equals(""))
			 orderfild=vo.getOrderField()+" "+vo.getOrderDirection();
			else
				orderfild = "model_order";
		}
		else
		{
			orderfild = "model_order";
		}
		 if (vo.getKeywords()!=null) 
		 { 
			   where = " where  model_name like '%"+vo.getKeywords()+"%'";
		 }
		 String sql =" with query as   (select model_id,model_name,model_href,model_target,dbo.App_Sys_FpGetModelNameByModelid(model_pid) as model_pid,model_status,model_order,model_issys,model_comment,	ROW_NUMBER() over(order by "+orderfild+") as   rowindex from gd_sys_model  "+where+")"+
		  "  SELECT *  FROM query  WHERE rowindex BETWEEN "+pc.getStartrow()+" and  " +	 pc.getStoprow();
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Model model = new Model();
				model.setId(rs.getString("model_id"));
				model.setName(rs.getString("model_name"));
				model.setHref(rs.getString("model_href"));
				model.setTarget(rs.getString("model_target"));
				model.setStatus(rs.getInt("model_status"));
				model.setOrder(rs.getInt("model_order"));
				model.setPid(rs.getString("model_pid"));
				model.setIssys(rs.getInt("model_issys"));
				model.setComment(rs.getString("model_comment"));
				listlog.add(model);
				model = null;
			}});
			 
		return listlog;
	}
	public int searchModelNum( BaseConditionVO vo)
	{
		String sql ="SELECT count(*) FROM dbo.gd_sys_model";
		return jdbcTemplate.queryForInt(sql);
		}
	
	
	public List<Model> getRootOfModel() {
		final List<Model> listmodel = new ArrayList<Model>();
		 
		Model model = new Model();
		model.setId("0");
		model.setName("-根模块");
		listmodel.add(model);
		String sql ="select model.model_id,model.model_name from "
							+ "gd_sys_model as model where model.model_pid='0' ORDER BY model.model_order";
			 jdbcTemplate.query(sql, new RowCallbackHandler(){
								@Override
			 public void processRow(ResultSet rs) throws SQLException {
				Model model = new Model();
				model.setId(rs.getString("model_id"));
				model.setName("+" + rs.getString("model_name"));
				listmodel.add(model);
				model = null;
			}});
		return listmodel;
	}

	public Model getModelByModelId(String modelId) {
		final Model model = new Model();
		String sql = "select * from gd_sys_model as model where model.model_id='"
							+ modelId + "'";
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
                    public void processRow(ResultSet rs) throws SQLException {
				model.setId(rs.getString("model_id"));
				model.setName(rs.getString("model_name"));
				model.setHref(rs.getString("model_href"));
				model.setTarget(rs.getString("model_target"));
				model.setPid(rs.getString("model_pid"));
				model.setOrder(rs.getInt("model_order"));
				model.setStatus(rs.getInt("model_status"));
				model.setIssys(rs.getInt("model_issys"));
				model.setComment(rs.getString("model_comment"));
			}});
		 
		return model;
	}

	public int delModelByModelId(String modelId) {
		String sql = "delete gd_sys_model  where gd_sys_model.model_id='"
						+ modelId
						+ "' "
						+ "and gd_sys_model.model_issys!=1 and  not exists"
						+ "(select gd_sys_rolemodel.model_id from gd_sys_rolemodel "
						+ "where  gd_sys_model.model_id=gd_sys_rolemodel.model_id )";
		       
		return jdbcTemplate.update(sql);
	}

	public int saveModel(Model model) {
		 
		String modelid = new String(Hex
				.encodeHex(org.apache.commons.id.uuid.UUID.randomUUID()
						.getRawBytes()));
	 
			if (model.getPid().equals("0")) {
				model.setHref("#");
				model.setTarget(null);
			}
			String sql = "insert into gd_sys_model(model_id,"
					+ "model_name," + "model_href," + "model_target,"
					+ "model_status," + "model_pid" + ",model_order,"
					+ "model_issys, " + "model_comment) " + "values('" + modelid
					+ "'," + "'" + model.getName() + "'," + "'"
					+ model.getHref() + "'," + "'main'," + "1," + "'"
					+ model.getPid() + "'," + "0," + "0, " + "'"
					+ model.getComment() + "'" + ")";
			return jdbcTemplate.update(sql);
	}

	public int updateModel(Model model) {
		 
			if (model.getPid().equals("0")) {
				model.setHref("#");
				model.setTarget(null);
			}
			String sql ="update gd_sys_model set " + "model_name='"
					+ model.getName() + "'," + "model_href='" + model.getHref()
					+ "'," + "model_target='" + model.getTarget() + "',"
					+ "model_pid='" + model.getPid() + "'," + "model_order="
					+ model.getOrder() + " " + "where model_id='"
					+ model.getId() + "' ";
					return jdbcTemplate.update(sql);
	}

	public List<Model> getTotalListOfModel() {
		 final List<Model> listmodel =new ArrayList<Model>() ;
	     String sql = "select model.model_id,model.model_name from gd_sys_model as model where model.model_pid='0' order by model.model_order";
	     jdbcTemplate.query(sql, new RowCallbackHandler(){
				@Override
	                    public void processRow(ResultSet rs) throws SQLException {
				   /*
				    * 读取
				    */
				    Model rootofmodel =new Model();
				      rootofmodel.setId(rs.getString("model_id"));
				      rootofmodel.setName("+++"+rs.getString("model_name"));
				     listmodel.add(rootofmodel);
				       String sql1 = "select model.model_id,model.model_name from gd_sys_model as model where  model.model_pid='"+rootofmodel.getId()+"' order by model.model_order";
				       jdbcTemplate.query(sql1, new RowCallbackHandler(){
							@Override
				                    public void processRow(ResultSet rs1) throws SQLException {
				    	   Model childrenofmodel =new Model();
				    	   childrenofmodel.setId(rs1.getString("model_id"));
				    	   childrenofmodel.setName("------------"+rs1.getString("model_name"));
				    	   listmodel.add(childrenofmodel);
				    	   childrenofmodel=null;
							}});
				}});
		return listmodel;
	}
}
