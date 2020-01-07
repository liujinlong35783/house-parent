<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0050)http://localhost:8080/HouseRent/manage!list.action -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD>
<TITLE>青鸟租房 - 用户管理</TITLE>
  <script src="../scripts/jquery-1.8.3.js"></script>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR ></HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV>
<DIV class=search><LABEL class="ui-green searchs"><a href="fabu.jsp" title="">发布房屋信息</a></LABEL>
<LABEL class=ui-green><INPUT onclick='document.location="index.jsp"' value="退       出" type=button name=search></LABEL>
  <h2>欢迎您:${users.name}</h2>
</DIV></DIV>
<DIV class="main wrap">
<DIV id=houseArea>
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${pageInfo.list}" var="page">
  <TR>
    <TD class=house-thumb><SPAN><A href="details.htm" target="_blank">${page.path}<img src="http://localhost:80/${page.path}" width="100" height="75" alt=""></A></SPAN></TD>
    <TD>
      <DL>
        <DT><A href="details.htm" target="_blank">${page.title}</A></DT>
        <DD>${page.dname},${page.floorage}<BR>联系方式：${page.contact} </DD></DL></TD>
    <td>
      <c:if test="${page.ispass==0}">未审核</c:if>
      <c:if test="${page.ispass==1}">已审核</c:if>
    </td>
    <TD class=house-type><LABEL class=ui-green><INPUT onclick="location.href='updateHouseUI?id=${page.id}'" value="修    改" type=button name=search></LABEL></TD>
    <TD class=house-price><LABEL class=ui-green><INPUT onclick="location.href='delHouse?id=${page.id}'" value="删    除" type=button name=search></LABEL></TD></TR>
  </c:forEach>
</TBODY></TABLE></DIV>
<DIV class=pager>
<UL>
  <c:forEach begin="1" end="${pageInfo.pages}" var="num">
    <LI class=current><A id=widget_338868862
                         href="getAllHouseDataById?page=${num}"
                         parseContent="true" showError="true" targets="houseArea"
                         ajaxAfterValidation="false" validate="false"
                         dojoType="struts:BindAnchor">${num}</A>
    </LI>
  </c:forEach>
</UL>
 <SPAN class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY>
<script language="JavaScript" type="text/javascript">
  <c:if test="${updata=='success'}">
  alert("更新成功")
  </c:if>
  <c:if test="${updata=='fail'}">
  alert("更新失败")
  </c:if>
</script>
</HTML>
