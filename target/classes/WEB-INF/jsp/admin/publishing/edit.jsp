<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>

<div class="pageContent">
<form method="post" action="<c:url value='/management/publishing/update?navTabId=publishingLiNav&callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<input type="hidden" name="id" value="${vo.id}"/>
	
	<div class="pageFormContent" layoutH="57">
		<p>
			<label>名称: </label>
			<input type="text" name="name" value="${vo.name}" class="required" maxlength="30"/>
		</p>
		<p>
			<label>区域: </label>
			<select name="region" class="combox required" ref="agencyCombox" refUrl="/management/publishing/agencyList?region={value}">
				<c:forEach var="item" items="${regionList}">
				<option value="${item}" ${item eq vo.region ? 'selected="selected"' : ''}><fmt:message key="${item.key}" /></option>
				</c:forEach>
			</select>
		</p>
		<p>
			<label>权重: </label>
			<input type="text" name="weight" value="${vo.weight}" class="number"/>
		</p>
		<p>
			<label>出版机构: </label>
			<select id="agencyCombox" name="agency" class="combox required">
				<c:forEach var="item" items="${vo.region.publishingAgencyList}">
				<option value="${item}" ${item eq vo.agency ? 'selected="selected"' : ''}><fmt:message key="${item.key}" /></option>
				</c:forEach>
			</select>
		</p>
		<p class="nowrap">
			<label>摘要: </label>
			<textarea name="summary" rows="2" cols="80" maxlength="1000">${vo.summary}</textarea>
		</p>
		<p class="nowrap">
			<label>介绍: </label>
			<textarea name="description" rows="3" cols="80" maxlength="1000">${vo.description}</textarea>
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
