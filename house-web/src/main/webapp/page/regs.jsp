<%@page language="java" pageEncoding="utf-8" contentType="text/html; utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/
TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0049)http://localhost:8080/HouseRent/page/register.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 用户注册</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<script language="JavaScript" type="text/javascript" src="../scripts/jquery-1.8.3.js"></script>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新用户注册</DT>
  <DD class=past>填写个人信息</DD></DL>
<DIV class=box>
<FORM action="addUsers" method="post" id="myform" onsubmit="check()">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>用 户 名：</TD>
    <TD><INPUT class=text type=text name=name id="username"><span id="sp"></span>
      <%--<input type="button" value="检验是否存在" id="btn111">--%>
    </TD></TR>
  <TR>
    <TD class=field>密　　码：</TD>
    <TD><INPUT class=text type=password name=password id="password"><span id="word1"></span></TD></TR>
  <TR>
    <TD class=field>确认密码：</TD>
    <TD><INPUT class=text type=password name=repassword id="repassword"><span id="word2"></span> </TD></TR>
  <TR>
    <TD class=field>电　　话：</TD>
    <TD><INPUT class=text type=text name=telephone> </TD></TR>
  <TR>
    <TD class=field>年龄：</TD>
    <TD><INPUT class=text type=text name=age> </TD></TR></TBODY></TABLE>
<DIV class=buttons>
<INPUT  value=立即注册 type="submit">
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY>
<script language="JavaScript" type="text/javascript">
  $(function () {
      $("#username").blur(function () {
          var name=$("#username").val();
          $.post("checkName","name="+name,function(data){
              if(data.result){
                  $("#sp").html("用户名可以使用").css("color","green")
              }else{
                  $("#sp").html("用户名已存在").css("color","red")
              }
          },"json")
      })
  })
  $("#repassword").blur(function () {
      var opassword=$("#password").val();
      var orepassword=$("#repassword").val();
      if(opassword!=orepassword&&opassword!=""){
          $("#word1").html("两次输入不一致").css("color","red");
          $("#word2").html("两次输入不一致").css("color","red");
      }else{
          $("#word1").html("OK").css("color","green");
          $("#word2").html("OK").css("color","green");
      }
  })
</script>
</HTML>
