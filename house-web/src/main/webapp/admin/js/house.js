/**
 * Created by HP on 2019/12/24.
 */
    function SaveUpDialog(){
        var name=$("#upDialogForm").serialize();
        $.post("updataUsers",name,function(data){
            if(data.result>0){
                $("#dg").datagrid("reload");
                $("#upDialog").dialog("close");
            }else{
                console.log(data.result);
                $.messager.alert("提示","更新失败");
            }
        },"json")
    }
//删除
function DeleteByFruitName(){
    var selobj=$("#dg").datagrid("getSelections")
    if(confirm("是否删除")) {
        if (selobj.length == 1) {
            $.post("delUsers", {"id": selobj[0].id}, function (data) {
                if (data.result > 0) {
                    $("#dg").datagrid("reload");
                } else {
                    $.messager.alert("提示", "删除失败")
                }
            }, "json")
        }
    }
}
//批量删除
function DeleteMoreUsers(){
    var selobjs=$("#dg").datagrid("getSelections");
    if(selobjs.length>0){
        if(confirm("提示","是否删除")){
            var str="";
            for(var i=0;i<selobjs.length;i++){
                str=str+selobjs[i].id+",";
//                      str="ids="+selobjs[i].id+"&";
            }
            str=str.substring(0,str.length-1);
        }
        $.post("delMoreUsers",{"ids":str},function(data){
            if(data.result>0){
                $("#dg").datagrid("reload")
            }else{
                $.messager.alert("提示","删除失败")
            }
        },"json")
    }
}
    function goUpdate(){
        var selobj=$("#dg").datagrid("getSelections");
        if(selobj.length==1){
            $("#upDialog").dialog("open").dialog("setTitle","编辑区域");
            $.post("getUsersDataById",{"id":selobj[0].id},function(data){
                $("#upDialogForm").form('load',data);
            },"json")
        }else{
            $.messager.alert('提示',"选多了,没选");
        }
    }
    function SaveAddDialog(){
        var name=$("#addDialogForm").serialize();
        $.post(
            "addUsersData",
            name,
            function(data){
                if(data.result>0){
                    $("#dg").datagrid("reload");
                    $("#AddDialog").dialog("close");
                } else{
                    $.messager.alert("添加失败");
                }
            },"json")
    }
    $(function(){
        //1.使用datagrid组件绑定数据
        $('#dg').datagrid({
            url:'getHouseData',
            pagination:true,  //开启分页
            pageSize:5,  //初始化页大小
            pageList:[3,5,8,10,20],  //页大小选项
            toolbar:'#ToolBar', //指定工具栏
            columns:[[
                {checkbox:true,width:100,align:'right'},
                {field:'id',title:'编号',width:100,align:'right'},
                {field:'tname',title:'户型',width:100,align:'right'},
                {field:'title',title:'标题',width:100,align:'right'},
                {field:'description',title:'描述',width:100,align:'right'},
                {field:'price',title:'价格',width:100,align:'right'},
                {field:'pubdate',title:'日期',width:100,align:'right'},
                {field:'floorage',title:'面积',width:100,align:'right'},
                {field:'dname',title:'区域',width:100,align:'right'},
                {field:'sname',title:'街道',width:100,align:'right'},
                {field: 'ispass', title: '状态', width: 100, align: 'right',
                    formatter: function (value, row, index) {
                        //返回的内容就是呈现在单元格的内容
                        //value 表示当前字段的值， row当前行的值 index表示索引
                        return value==0?"没审核":"已审核";
                    }
                },
                {field:'opt',title:'编辑|操作',width:100,align:'right',
                    formatter: function(value,row,index){
                        //返回的内容就是呈现在单元格的内容
                        //value 表示当前字段的值， row当前行的值 index表示索引
                        return "<a href='javascript:goEdit("+row.id+");'>审核</a>";
                    }
                }
            ]]
        });
    });
//审核
function goEdit(id){
    $.post("updateHouseIspass",{"id":id},function(data){
        if (data.result>0){
            $.messager.alert("提示","审核成功")
            $("#dg").datagrid("reload")
        }else{
            $.messager.alert("提示","审核失败")
        }
    },"json")
}

//打开添加窗口
function goAdd(){
    $("#AddDialog").dialog("open").dialog("setTitle","添加区域");
}

//关闭窗口
function CloseDialog(){

}


//保存添加的数据     异步添加
function SaveDialog(){
    //alert("多要保存信息，告诉我接口在哪，我去找他");
    //实现异步技术实现添加,借助ajax技术，
    //方法一:使用jquery发送异步请求
    //$.post("地址",参数，回调函数,"json")
    //将表单序列化参数数据
    /*      var param=$("#addDialogForm").serialize();
     //console.log(param);
     $.post("addUsers",param,function(data){
     if(data.result>0){
     //成功关闭窗口
     $("#AddDialog").dialog("close");
     }else{
     //alert("sss");
     $.messager.alert('友情提示','添加失败，请联系管理员:13260601227!','info');

     }
     },"json");*/


    //借助easyui异步提交表单
    $('#addDialogForm').form('submit',{
        url:"addUsers",
        success:function(data){  //{"result":1}
            var obj=$.parseJSON(data);   //将json字符串转化为json对象
            if(obj.result>0){
                //成功关闭窗口
                $("#AddDialog").dialog("close");
            }else{
                //alert("sss");
                $.messager.alert('友情提示','添加失败，请联系管理员:13260601227!','info');
            }
        }
    });
}
//取消按钮
function CloseDialog(id){
    $("#"+id).dialog("close");
}


//去打开修改的窗口
//        function goUpdate(){
//            //1.获取datagrid的选中行
//           var selObjs=$("#dg").datagrid("getSelections");
//           //2.验证是否选中一行
//            if(selObjs.length==1){
//                $("#upDialog").dialog("open").dialog('setTitle',"编辑区域");
//
//                //还原表单数据  查询数据库，通过id获取单行记录的对象，进行回显？
//               // $("#upDialogForm").form('load',json对象:{"表单对象名称":值});
//                $("#upDialogForm").form('load',selObjs[0]);
//
//            }else{
//                $.messager.alert('友情提示','你可能没有选中行，获者选中多行，请选择一行修改，真他妈笨，选一行都不会','info');
//            }
//        }