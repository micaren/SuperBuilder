<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../_frag/pager/pagerForm.jsp"></c:import>

<form method="post" rel="pagerForm" action="<c:url value='/admin/staff'/>" onsubmit="return navTabSearch(this)">
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
			<li><a class="add" target="navTab" rel="staffNav" href="<c:url value='/admin/staff/add'/>" title="添加用户"><span>添加</span></a></li>
			<li><a class="edit" target="navTab" rel="staffNav" href="<c:url value='/admin/staff/edit/{slt_objId}'/>" title="编辑用户"><span>编辑</span></a></li>
			<li><a class="delete" target="ajaxTodo" href="<c:url value='/admin/staff/delete/{slt_objId}'/>" title="你确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50"></th>
				<th width="100" orderField="staff_name" class="${param.orderField eq 'staff_name' ? param.orderDirection : ''}">姓名</th>
				<th width="100" orderField="staff_address" class="${param.orderField eq 'staff_address' ? param.orderDirection : ''}">地址</th>
				<th width="100" orderField="staff_tel" class="${param.orderField eq 'staff_tel' ? param.orderDirection : ''}">手机号码</th>
				<th width="100" orderField="org_id" class="${param.orderField eq 'org_id' ? param.orderDirection : ''}">用户机构</th>
				<th width="130" orderField="staff_sex" class="${param.orderField eq 'staff_sex' ? param.orderDirection : ''}">性别</th>
				<th width="100" orderField="staff_ctime" class="${param.orderField eq 'staff_ctime' ? param.orderDirection : ''}">创建时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${staffList}" varStatus="s">
			<tr target="slt_objId" rel="${item.id }">
				<td>${s.index + 1}</td>
				<td>${item.name}</td>
				<td>${item.address}</td>
				<td>${item.tel}</td>
				<td>${item.orgid}</td>
				<td>${item.sex}</td>
				<td><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../_frag/pager/panelBar.jsp"></c:import>
</div>