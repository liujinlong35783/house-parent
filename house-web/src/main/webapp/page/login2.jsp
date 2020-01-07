<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0032)http://localhost:8080/HouseRent/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 用户登录</TITLE>
  <script src="../scripts/jquery-1.8.3.js"></script>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script src="../admin/js/jquery-1.8.3.js"></script>
  <script language="JavaScript" type="text/javascript">
    $(function () {
        $("#getCode").click(function(){
            var tel=$("#tel").val()
            $.post("loginUsersByTel",{"tel":tel},function(data){
                if (data.result>0){
                    alert("验证码已发送")
                }else{
                    alert("访问量过大,请稍后再试")
                }
            },"json")
        })
    })
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DIV class=box>
<H4>用户登录</H4>
<FORM id=user method=post name=user action=loginUsers>
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD colSpan=2></TD></TR>
  <TR>
    <TD class=field>电话：</TD>
    <TD><!-- <input type="text" class="text" name="name" /> --><INPUT 
      id=tel class=text type=text name=name> <input type="button" value="获取验证码" id="getCode"></TD>

  </TR>
  <TR>
    <TD class=field>验证码：</TD>
    <TD><!-- <input type="password" class="text" name="password" /> --><INPUT 
      id=user_password class=text type=password name=password> </TD></TR><!--
						<tr>
							<td class="field">验 证 码：</td>
							<td><input type="text" class="text verycode" name="veryCode" /></td>
						</tr>
						--></TBODY></TABLE>
<DIV class=buttons> <INPUT  value=登陆 type=submit> <INPUT onclick='document.location="regs.htm"' value=注册 type=button>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY>
<script>
    <c:if test="${login=='fail'}">
    alert("账号或者密码不正确")
    </c:if>
</script>
</HTML>
