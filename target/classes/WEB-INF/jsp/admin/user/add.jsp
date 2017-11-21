<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>

<div class="pageContent">
<form method="post" action="<c:url value='/admin/user/insert?navTabId=userLiNav&callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<div class="pageFormContent" layoutH="57">

		<p>
			<label>用户名: </label>
			<input type="text" name="username" class="required alphanumeric" minlength="6" maxlength="20"/>
		</p>
		<p>
			<label>密码: </label>
			<input type="text" name="password" class="required alphanumeric" minlength="6" maxlength="20"/>
		</p>
		<p>
			<label>机构: </label>
			<select   name="orgid" ref="role" id="org"    refUrl="admin/user/roleList?orgid={value}" class="combox required">
			    <option value="">请选择机构</option>
			    <c:forEach var="item" items="${orgList}">
			    <option value="${item.id}"> ${item.name}</option>
			    </c:forEach>
			</select>
		</p>
		<p>
			<label>角色: </label>
			<select  width="20px"    name="roleid"  id="role" ref="staff" refUrl="admin/user/staffList?roleid={value}" class="combox required">
		     <option value="">请选择角色</option>
			</select>
		</p>
		 
		<p>
			<label>职工: </label>
			<select  width="20px"   name="staffid"   id="staff" class="combox required" >
			 <option value="">请选择职工</option>
			 </select>
		</p>
	 
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>
