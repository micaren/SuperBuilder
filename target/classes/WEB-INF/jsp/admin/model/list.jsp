<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../_frag/pager/pagerForm.jsp"></c:import>

<form method="post" rel="pagerForm" action="<c:url value='/admin/model'/>" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>关键词：</label>
				<input type="text" name="keywords" value="${param.keywords}"/>
			</li>
			 
		</ul>
		<div class="subBar">
			<ul>						
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
			</ul>
		</div>
	</div>
</div>
</form>

<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" target="navTab" rel="staffNav" href="<c:url value='/admin/model/add'/>" title="添加用户"><span>添加</span></a></li>
			<li><a class="edit" target="navTab" rel="staffNav" href="<c:url value='/admin/model/edit/{slt_objId}'/>" title="编辑用户"><span>编辑</span></a></li>
			<li><a class="delete" target="ajaxTodo" href="<c:url value='/admin/model/delete/{slt_objId}'/>" title="你确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50"></th>
				<th width="100" orderField="model_name" class="${param.orderField eq 'staff_name' ? param.orderDirection : 'desc'}">功能名称</th>
				<th width="100" orderField="model_href" class="${param.orderField eq 'staff_address' ? param.orderDirection : 'desc'}">链接地址</th>
				<th width="100" orderField="model_pid" class="${param.orderField eq 'staff_tel' ? param.orderDirection : 'desc'}">父节点</th>
				<th width="100" orderField="model_status" class="${param.orderField eq 'org_id' ? param.orderDirection : 'desc'}">状态</th>
				<th width="130" orderField="model_issys" class="${param.orderField eq 'staff_sex' ? param.orderDirection : 'desc'}">是否系统配置</th>
				<th width="100" orderField="model_comment" class="${param.orderField eq 'staff_ctime' ? param.orderDirection : 'desc'}">备注内容</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${modelList}" varStatus="s">
			<tr target="slt_objId" rel="${item.id }">
				<td>${vo.pageSize*(vo.pageNum-1)+s.index + 1}</td>
				<td>${item.name}</td>
				<td>${item.href}</td>
				<td>${item.pid}</td>
				<td>${item.status}</td>
				<td>${item.issys}</td>
				<td>${item.comment}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../_frag/pager/panelBar.jsp"></c:import>
</div>