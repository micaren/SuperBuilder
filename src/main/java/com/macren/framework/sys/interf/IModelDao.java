package com.macren.framework.sys.interf;

import java.util.List;

import com.macren.framework.sys.logic.PageCommon;
import com.macren.framework.sys.pojo.Model;
import com.macren.persistence.BaseConditionVO;

public interface IModelDao {
	public List<Model> getListOfModel(BaseConditionVO vo,PageCommon pc);
	public int searchModelNum( BaseConditionVO vo);

	public List<Model> getRootOfModel();

	public Model getModelByModelId(String modelId);

	/* delmodelbyid */
	public int delModelByModelId(String modelId);

	/* insert model */
	public int saveModel(Model model);

	/* update model */
	public int updateModel(Model model);
	/*
	 * get total list of model orderby root children 
	 * example:
	 *    +系统管理
	 *      -用户管理
	 *      -权限管理
	 *    +监控中心
	 *      -实时控制
	 *      ......
	 */
	public List<Model> getTotalListOfModel();
}
