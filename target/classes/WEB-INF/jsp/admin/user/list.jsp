<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../_frag/pager/pagerForm.jsp"></c:import>

<form method="post" rel="pagerForm" action="<c:url value='/admin/user'/>" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>关键词：</label>
				<input type="text" name="keywords" value="${param.keywords}"/>
			</li>
			<li>
				<label>状态：</label>
				<select name="status">
					<option value="">全部</option>
					<c:forEach var="item" items="${userStatusList}">
					<c:if test="${item ne 'DELETED'}">
					<option value="${item}" ${item eq param.status ? 'selected="selected"' : ''}>${item}</option>
					</c:if>
					</c:forEach>
				</select>
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
			<li><a class="add" target="navTab" rel="userNav" href="<c:url value='/admin/user/add'/>" title="添加用户"><span>添加</span></a></li>
			<li><a class="edit" target="navTab" rel="userNav" href="<c:url value='/admin/user/edit/{slt_objId}'/>" title="编辑用户"><span>编辑</span></a></li>
			<li><a class="delete" target="ajaxTodo" href="<c:url value='/admin/user/delete/{slt_objId}'/>" title="你确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50"></th>
				<th width="100" orderField="username" class="${param.orderField eq 'username' ? param.orderDirection : ''}">用户登录名</th>
				<th width="100" orderField="staff_id" class="${param.orderField eq 'staff_id' ? param.orderDirection : ''}">用户姓名</th>
				<th width="100" orderField="role_id" class="${param.orderField eq 'role_id' ? param.orderDirection : ''}">用户角色</th>
				<th width="100" orderField="org_id" class="${param.orderField eq 'org_id' ? param.orderDirection : ''}">用户机构</th>
				<th width="130" orderField="createtime" class="${param.orderField eq 'createtime' ? param.orderDirection : ''}">创建时间</th>
				<th width="100" orderField="status" class="${param.orderField eq 'status' ? param.orderDirection : ''}">状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${userList}" varStatus="s">
			<tr target="slt_objId" rel="${item.id }">
				<td>${s.index + 1}</td>
				<td>${item.username}</td>
				<td>${item.staffid}</td>
				<td>${item.roleid}</td>
				<td>${item.orgid}</td>
				<td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><c:choose>
   <c:when test="${item.status eq '1'}">生效
   </c:when>
   <c:otherwise>失效
   </c:otherwise>
  
</c:choose></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../_frag/pager/panelBar.jsp"></c:import>
</div>