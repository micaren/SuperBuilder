<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../_frag/pager/pagerForm.jsp"></c:import>

<form method="post" rel="pagerForm" action="<c:url value='/admin/org'/>" onsubmit="return navTabSearch(this)">
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
			<li><a class="add" target="navTab" rel="staffNav" href="<c:url value='/admin/org/add'/>" title="添加用户"><span>添加</span></a></li>
			<li><a class="edit" target="navTab" rel="staffNav" href="<c:url value='/admin/org/edit/{slt_objId}'/>" title="编辑用户"><span>编辑</span></a></li>
			<li><a class="delete" target="ajaxTodo" href="<c:url value='/admin/org/delete/{slt_objId}'/>" title="你确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50"></th>
				<th width="100" orderField="org_name" class="${param.orderField eq 'org_name' ? param.orderDirection : 'desc'}">机构名称</th>
				<th width="100" orderField="org_pid" class="${param.orderField eq 'org_pid' ? param.orderDirection : 'desc'}">上级机构</th>
				<th width="100" orderField="org_tel" class="${param.orderField eq 'org_tel' ? param.orderDirection : 'desc'}">机构电话</th>
				<th width="100" orderField="org_order" class="${param.orderField eq 'org_order' ? param.orderDirection : 'desc'}">顺序</th>
				<th width="130" orderField="org_address" class="${param.orderField eq 'org_address' ? param.orderDirection : 'desc'}">地址</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${orgList}" varStatus="s">
			<tr target="slt_objId" rel="${item.id }">
				<td>${vo.pageSize*(vo.pageNum-1)+s.index + 1}</td>
				<td>${item.name}</td>
				<td>${item.pid}</td>
				<td>${item.tel}</td>
				<td>${item.order}</td>
				<td>${item.address}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../_frag/pager/panelBar.jsp"></c:import>
</div>