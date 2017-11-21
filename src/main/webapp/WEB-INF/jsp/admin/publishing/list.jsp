<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../_frag/pager/pagerForm.jsp"></c:import>

<form method="post" rel="pagerForm" action="<c:url value='/management/publishing'/>" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>关键词：</label>
				<input type="text" name="keywords" value="${param.keywords}"/>
			</li>
		</ul>
		<div class="subBar">
			<span style="padding:5px; line-height: 20px;">点击表头‘名称’，‘区域’ 可排序</span>
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
			<li><a class="add" target="navTab" rel="publishingNav" href="<c:url value='/management/publishing/add'/>" title="添加出版社"><span>添加</span></a></li>
			<li><a class="edit" target="navTab" rel="publishingNav" href="<c:url value='/management/publishing/edit/{slt_objId}'/>" title="编辑出版社"><span>编辑</span></a></li>
			<li><a class="delete" target="ajaxTodo" href="<c:url value='/management/publishing/delete/{slt_objId}'/>" title="你确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40"></th>
				<th width="180" orderField="NAME" class="${param.orderField eq 'NAME' ? param.orderDirection : ''}">名称</th>
				<th width="60" orderField="REGION" class="${param.orderField eq 'REGION' ? param.orderDirection : ''}">区域</th>
				<th width="100">权重</th>
				<th width="100">出版机构</th>
				<th>摘要</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${publishingList}" varStatus="s">
			<tr target="slt_objId" rel="${item.id }">
				<td>${s.index + 1}</td>
				<td>${item.name}</td>
				<td><fmt:message key="${item.region.key}" /></td>
				<td>${item.weight}</td>
				<td><fmt:message key="${item.agency.key}" /></td>
				<td>${item.summary}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../_frag/pager/panelBar.jsp"></c:import>
</div>