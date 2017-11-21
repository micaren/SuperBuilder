<%@ page contentType="text/html;charset=UTF-8"  import="java.util.*" pageEncoding="UTF-8"%><%@ include
	file="/include.inc.jsp"%>
	<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>业务管理平台</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="<%=basePath%>/styles/loginresource/css/admin.css" type="text/css"
	rel="stylesheet">
<script src="<%=basePath%>/styles/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=basePath%>/styles/dwz/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script  type="text/javascript">
  $(function(){
   $("#randomCode").click(function(){
	   $("#randomCode").attr("src","<%=basePath%>macrencheck");
   }) ;

   
   $("#login").click(function(){
 
	   var user = $("#user").val();
	   var pass = $("#pass").val();
	   var checkword = $("#checkword").val();
	   if (user=="")
	   {
	       $("#confirm").text("请输入登录用户名");
	       $("#user").focus();
	       return false;
	   }
	   if(pass=="")
	   {
	       $("#confirm").text("请输入登录密码");
	       $("#pass").focus();
	       return false;
	   }
	   if(checkword=="")
	   {
	       $("#confirm").text("请输入校验码");
	       $("#checkword").focus();
	       return false;
	   }
	   alert(checkword);
	   $("#form1").submit();
     });
  }) ;  

  </script>
</HEAD>
<BODY onload=document.form1.name.focus();>
<form method="post" id="form1" action="<c:url value='/passport
/login'/>">
 <input type="hidden" name="backToUrl" value="<%=basePath%>admin"/>
	<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%"
		bgColor=#002779 border=0>
		<TR>
			<TD align="center">
				<TABLE cellSpacing=0 cellPadding=0 width=468 border=0>
					<TR>
						<TD><IMG height=23 src="<%=basePath%>/styles/loginresource/images/login_1.jpg"
							width=468></TD>
					</TR>
					<TR>
						<TD><IMG height=147 src="<%=basePath%>/styles/loginresource/images/login_2.jpg"
							width=468></TD>
					</TR>
				</TABLE>
				<TABLE cellSpacing=0 cellPadding=0 width=468 bgColor=#ffffff
					border=0>
					<TR >
						<TD width=16><IMG height=122
							src="<%=basePath%>/styles/loginresource/images/login_3.jpg" width=16></TD>
						<TD align="center">
							
								<TABLE cellSpacing=0 cellPadding=0 width=230 border=0>

									<TR height=5>
										<TD width=5></TD>
										<TD width=56></TD>
										<TD></TD>
									</TR>
									<TR height=25>
										<TD></TD>
										<TD>帐 户</TD>
										<TD><INPUT
											style="BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-BOTTOM: #000000 1px solid"
											maxLength=30 size=24   name="username" id="user"></TD>
									</TR>
									<TR height=25>
										<TD></TD>
										<TD>密 码</TD>
										<TD><INPUT
											style="BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-BOTTOM: #000000 1px solid"
											type=password maxLength=30 size=24   name="password" id="pass"></TD>
									</TR>
									<TR height=25>
										<TD></TD>
										<TD>验证码</TD>
										<TD><INPUT
											style="margin-bottom:4px; BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-BOTTOM: #000000 1px solid"
											type=password maxLength=34 size=10   name="checkword" id="checkword" >
											<a   > <img
												border="0" id="randomCode" height="20" src="<%=basePath%>macrencheck"
												alt="看不清，点击换一张"></a> </td>
										<td></TD>
									</TR>
									<TR>
										<TD colSpan=3></TD>
									</TR>
									<tr height="16">

										<TD colspan=3 width=156><div id="msgId"
												style="font: 8pt 微软雅黑; color: red">

												<span id="confirm"></span>
												<c:if test="${! empty message}">
													<p style="color: red">${message}</p>
												</c:if>
											</div></TD>
									</tr>
									<TR>
										<TD></TD>
										<TD></TD>
										<TD><a   ><img id="login"
												border="0"  height=18 width=70 
											src="<%=basePath%>/styles/loginresource/images/bt_login.gif"
											 ></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<INPUT
											onclick="javascript:reset1();" type=image height=18 width=70
											src="<%=basePath%>/styles/loginresource/images/reset.gif"></TD>
									</TR>

								</TABLE>
							
						</TD>
						<TD width=16><IMG height=122
							src="<%=basePath%>/styles/loginresource/images/login_4.jpg" width=16></TD>
					</TR>
				</TABLE>
				<TABLE cellSpacing=0 cellPadding=0 width=468 border=0>
					<TR>
						<TD><IMG height=16 src="<%=basePath%>/styles/loginresource/images/login_5.jpg"
							width=468></TD>
					</TR>
				</TABLE>
				<TABLE cellSpacing=0 cellPadding=0 width=468 border=0>
					<TR>
						<TD align=right><IMG height=26
							src="<%=basePath%>/styles/loginresource/images/login_6.gif" width=165 border=0>
						</TD>
					</TR>
				</TABLE>
			</TD>
		</TR>
	</TABLE>
 </form>
</BODY>
</HTML>
