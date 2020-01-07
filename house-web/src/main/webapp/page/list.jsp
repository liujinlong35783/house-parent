<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; utf-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
  <script src="/admin/js/jquery-1.8.3.js"></script>
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<script>
  $(function(){
      //户型
      $.post("getTypeData",null,function(data){
          for(var i=0;i<data.length;i++){
              var optionValue=$("<option value="+data[i].id+">"+data[i].name+"</option>");
              $("#type_id").append(optionValue);
          }
          //回显类型
          $("#type_id").val(${searchSome.tid});
      },"json");
      //区域
      $.post("getDistrictData",null,function(data){
          for (var i=0;i<data.length;i++){
              var optionValue=$("<option value="+data[i].id+">"+data[i].name+"</option>");
              $("#district_id").append(optionValue);
          }
          //设置区域的选中项
          $("#district_id").val(${searchSome.did});
          loadStreet();//此处的作用主要查询对应区域下的街道,
          //因为预加载异步请求的顺序是从上到下的,当进入区域的异步请求时(不会等待)
          // ,会继续街道的异步请求(参数为空,所以查询不到)
          //分别使用异步请求和改变事件加载街道
      },"json");
      //发布时,预加载区域异步请求,改变事件之后,调用函数加载街道
      $("#district_id").change(function(){
          loadStreet()
      })
      //函数加载街道(修改和发布两次调用)
      function loadStreet(){
          var did=$("#district_id").val();
          $("#street_id>option:gt(0)").remove();//清除street_id>option标签下的除首个的子元素
          $.post("getStreetDataByDid",{"did":did},function(data){
              for (var i=0;i<data.length;i++){
                  var optionValue=$("<option value="+data[i].id+">"+data[i].name+"</option>")
                  $("#street_id").append(optionValue);
              }
              $("#street_id").val(${searchSome.sid})
          },"json")
      }
  })
  function goPage(pageNum){
      //设置页码
      $("#savePage").val(pageNum);
      //提交表单  跳转  传条件
      $("#myform").submit();

  }
</script>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  <FORM id=myform method=post action=getAllWithList>
    <!--隐藏保存当前页码-->
    <input type="hidden" value="1" name="page" id="savePage">
    标题：<INPUT class=text type=text name=title value="${searchSome.title}">
    价格区间: <input type="text" name="startPrice" id="" size="10" value="${searchSome.startPrice}">-
      <input type="text" name="endPrice" id="" size="10" value="${searchSome.endPrice}">
    区
    <SELECT id=district_id name=did>
      <option value="">--请选择--</option>
    </SELECT>
    街道
    <SELECT id=street_id name=sid>
      <option value="">--请选择--</option>
    </SELECT>
    <TD class=field>户　　型：</TD>
    <TD><SELECT class=text name=tid id="type_id">
      <option value="">--请选择--</option>
    </SELECT></TD>
    <input type="submit" value="搜索"></FORM></DL></DIV>
<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${pageInfo.list}" var="page">
  <tr>
    <td class="house-thumb"><span><a href="details.htm" target="_blank"><img src="http://localhost:80/${page.path}" alt="" width="100" height="75"></a></span></td>
    <td>
      <dl>
        <dt><a href="details.htm" target="_blank">${page.title}</a></dt>
        <dd>${page.dname},${page.sname},${page.floorage}平米<br>联系方式：${page.contact} </dd></dl></td>
    <td class="house-type">${page.tname}</td>
    <td class="house-price"><span>${page.price}</span>元/月</td></tr>
  </c:forEach>
  </TBODY></TABLE>
<DIV class=pager>
  <UL>
    <LI class=current><A href="javascript:goPage(1)">首页</A></LI>
    <LI><A href="javascript:goPage(${pageInfo.pageNum-1})">上一页</A></LI>
    <LI><A href="javascript:goPage(${pageInfo.pageNum+1})">下一页</A></LI>
    <LI><A href="javascript:goPage(${pageInfo.pages})">末页</A></LI></UL><SPAN
        class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DIV></BODY></HTML>
