<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>

<div class="pageContent">
<form method="post" action="<c:url value='/admin/staff/insert?navTabId=userLiNav&callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<div class="pageFormContent" layoutH="57">

		<p>
			<label>用户名: </label>
			<input type="text" name="name" class="required alpha" minlength="2" maxlength="5"/>
		</p>
		<p>
			<label>手机号码: </label>
			<input type="text" name="tel" class="required  numeric" minlength="11" maxlength="11"/>
		</p>
		<p>
			<label>机构: </label>
			<select   name="orgid"  id="org" class="required"   >
			    <option value="">请选择机构</option>
			    <c:forEach var="item" items="${orgList}">
			    <option value="${item.id}"> ${item.name}</option>
			    </c:forEach>
			</select>
		</p>
		<p>
			<label>性别: </label>
			<select  width="20px"  class="required"  name="sex"  id="role" ref="staff" >
			 <option value="">请选择</option>
				<c:forEach var="item" items="${genderList}">
				<option value="${item.name}">${item.name}</option>
				</c:forEach>
			</select>
		</p>
		 
		<p>
			<label>地址: </label>
			<input type="text" name="address" class="required alpha" minlength="1" maxlength="30"/>
		</p>
		<p>
			<label>qq号码： </label>
			<input type="text" name="qq" class="required  numeric" minlength="5" maxlength="15"/>
		</p>
	 <p>
			<label>Email地址： </label>
			<input type="text" name="email" class="required email" minlength="1" maxlength="50"/>
		</p>
		 <p>
			<label>员工编号： </label>
			<input type="text" name="workid" class="required  numeric" minlength="1" maxlength="11"/>
		</p>
	     <p>
			<label>备注： </label>
			<input type="text" name="comment" class="required alpha" minlength="5" maxlength="100"/>
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
