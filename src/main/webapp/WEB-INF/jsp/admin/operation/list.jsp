<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../_frag/pager/pagerForm.jsp"></c:import>

<form method="post" rel="pagerForm" action="<c:url value='/admin/operation'/>" onsubmit="return navTabSearch(this)">
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
			<li><a class="add" target="navTab" rel="staffNav" href="<c:url value='/admin/operation/add'/>" title="添加用户"><span>添加</span></a></li>
			<li><a class="edit" target="navTab" rel="staffNav" href="<c:url value='/admin/operation/edit/{slt_objId}'/>" title="编辑用户"><span>编辑</span></a></li>
			<li><a class="delete" target="ajaxTodo" href="<c:url value='/admin/operation/delete/{slt_objId}'/>" title="你确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50"></th>
				<th width="100" orderField="opre_code" class="${param.orderField eq 'opre_code' ? param.orderDirection : 'desc'}">操作名称</th>
				<th width="100" orderField="opre_num" class="${param.orderField eq 'opre_num' ? param.orderDirection : 'desc'}">操作数</th>
				<th width="100" orderField="org_id" class="${param.orderField eq 'org_id' ? param.orderDirection : 'desc'}">所属机构</th>
				<th width="100" orderField="comment" class="${param.orderField eq 'comment' ? param.orderDirection : 'desc'}">备注</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${operationList}" varStatus="s">
			<tr target="slt_objId" rel="${item.id }">
				<td>${vo.pageSize*(vo.pageNum-1)+s.index + 1}</td>
				<td>${item.code}</td>
				<td>${item.num}</td>
				<td>${item.orgid}</td>
				<td>${item.comment}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../_frag/pager/panelBar.jsp"></c:import>
</div>