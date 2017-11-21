<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>

<c:import url="../_frag/pager/pagerForm.jsp"></c:import>

<form method="post" rel="pagerForm" action="<c:url value='/management/publishing?popSelect=1'/>" onsubmit="return dialogSearch(this)">
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

	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40"></th>
				<th width="180">名称</th>
				<th width="60">区域</th>
				<th>摘要</th>
				<th width="70">查找带回</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${publishingList}" varStatus="s">
			<tr target="slt_objId" rel="${item.id }">
				<td>${s.index + 1}</td>
				<td>${item.name}</td>
				<td><fmt:message key="${item.region.key}" /></td>
				<td>${item.summary}</td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({publishingId:'${item.id}', publishingName:'${item.name}'})" title="查找带回">选择</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../_frag/pager/panelBar.jsp?targetType=dialog"></c:import>
</div>
