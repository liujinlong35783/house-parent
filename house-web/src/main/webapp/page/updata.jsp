<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" language="java" contentType="text/html; utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
  <script src="../scripts/jquery-1.8.3.js"></script>
  <script type="text/javascript" language="JavaScript">
    $(function () {
        //户型
        $.post("getTypeData",null,function(data){
            for(var i=0;i<data.length;i++){
                var optionValue=$("<option value="+data[i].id+">"+data[i].name+"</option>");
                $("#type_id").append(optionValue);
                }
                $("#type_id").val(${house.typeId})//将查询到的类型id放到 下拉框的value中
            },"json");
        //区域
        $.post("getDistrictData",null,function(data){
            for (var i=0;i<data.length;i++){
                var optionValue=$("<option value="+data[i].id+">"+data[i].name+"</option>");
                $("#district_id").append(optionValue);
            }
            $("#district_id").val(${house.districtId})//将查询到的区域id放到 下拉框的value中
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
                $("#street_id").val(${house.streetId})
            },"json")
        }
    })
    <c:if test="${addHouse=='success'}">
    alert("发布成功")
    </c:if>
    <c:if test="${addHouse=='fail'}">
    alert("发布失败")
    </c:if>
  </script>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新房屋信息修改</DT>
  <DD class=past>修改房屋信息</DD></DL>
<DIV class=box>
<FORM id=add_action method=post name=add.action action="/page/updateHouse" enctype="multipart/form-data">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <input type="hidden" name="id" value="${house.id}">
    <input type="hidden" name="oldPicPath" value="${house.path}">
    <TD class=field>标　　题：</TD>
    <TD><INPUT id=add_action_title class=text type=text name=title value="${house.title}"> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD><SELECT class=text name=typeId id="type_id">
      <option value="">--请选择--</option>
    </SELECT></TD>
  </TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=add_action_floorage class=text type=text value="${house.floorage}"
name=floorage></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=add_action_price class=text type=text name=price value="${house.price}"> </TD></TR>
  <TR>
    <TD class=field>房产证日期：</TD>
    <TD><INPUT class=text type=date name=pubdate value="<f:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd"></f:formatDate>"></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<SELECT class=text name=district_id id="district_id"><OPTION selected
        value="">--请选择--</OPTION></SELECT> 街：<SELECT class=text
        name=streetId id="street_id"><OPTION selected value="">--请选择--</OPTION></SELECT> </TD></TR><!--
						<tr>
							<td class="field">坐  标：</td>
							<td><input type="text" class="text" name="point" />
							</td>
						</tr>
						--><!--  <tr>
							<td class="field">Y 坐  标：</td>
							<td><input type="text" class="text" name="point.y" /></td>
						</tr>-->
  <TR>
    <TD class=field>联系方式：</TD>
    <TD><INPUT id=add_action_contact class=text type=text name=contact value="${house.contact}"> </TD></TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description>${house.description}</TEXTAREA></TD></TR>
  <TR>
    <TD class=field>图片：</TD>
    <TD><img src="http://localhost:80/${house.path}" alt="" height="100" width="100">
      <input type="file" name="pfile"></TD></TR>
  </TBODY></TABLE>

<DIV class=buttons><INPUT  value=立即修改 type=submit>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
