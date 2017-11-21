<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
[
<c:forEach var="item" items="${agencyList}" varStatus="s">
	["${item}", "<fmt:message key='${item.key}' />"]<c:if test="${!s.last}">,</c:if>
</c:forEach>
]