/**
 * Created by HP on 2019/12/24.
 */
<!--展示分页信息-->
    $(function () {
        $('#data').datagrid({
            url:'getTypeByPage',
            toolbar: '#ToolBar',
            pagination:true,
            pageSize:3,
            pageList:[4,6,8,10],
            columns:[[
                {checkbox:true,width:100,align:'right'},
                {field:'id',title:'编号',width:100,align:'right'},
                {field:'name',title:'姓名',width:100,align:'right'},
                {field:'opt',title:'操作',width:100,align:'right',

                    formatter: function(value,row,index){
                        if (row.user){
                            return row.user.name;
                        } else {
                            return  "<a href='#'>修改</a>|<a href='#'>删除</a>";
                        }
                    }
                }
            ]]
        });
    })
function Add(){
    //打开对话框
    $("#AddDialog").dialog("open").dialog("setTitle","添加区域")
}
function ModifyBySelect(){
    //1.获取datagrid的选中行
    var selObjs=$("#data").datagrid("getSelections");
    //2.验证是否选中一行
    if(selObjs.length==1){
        $("#upDialog").dialog("open").dialog('setTitle',"编辑区域");

        //还原表单数据  查询数据库，通过id获取单行记录的对象，进行回显？
        // $("#upDialogForm").form('load',json对象:{"表单对象名称":值});
//            $("#upDialogForm").form('load',selObjs[0]);
        $.post("updataTypeUI",{"id":selObjs[0].id},function(data){
            $("#updataDialogForm").form('load',data);
        },"json")

    }else{
        $.messager.alert('友情提示','你可能没有选中行，获者选中多行，请选择一行修改，真他妈笨，选一行都不会','info');
    }
}
//提交更新的信息
function SaveUpDialog(){
    $("#updataDialogForm").form('submit',{
        url:"updataType",
        success:function (data) {
            var data=$.parseJSON(data);
            if(data.result>0){
                $("#upDialog").dialog("close");
                $("#data").datagrid("reload");
            }else{
                $.messager.alert('修改失败');
            }
        }
    })
}
function DeleteByFruitName() {
    var selObjs=$("#data").datagrid("getSelections");
    if(selObjs.length>=1){
        $.post("delType",{"id":selObjs[0].id},function (data) {
            $("#data").datagrid("reload");
        },"json")
    }else{
        $.messager.alert('友情提示','你可能没有选中行，真他妈笨，选行都不会');
    }
}
function SaveAddDialog(){
    $("#ModiyDialogForm").form('submit',{
        url:"addType",
        success:function(data){
            var data=$.parseJSON(data);
            if(data.b>0){
                $("#AddDialog").dialog("close");
                $("#data").datagrid("reload");
            }else{
                $.messager.alert('添加失败');
            }
        }
    })
}
//取消按钮
function CloseDialog(id){
    $("#"+id).dialog("close");
}
function toEdit(id){
    var selObjs=$("#data").datagrid("getSelections");
    $("#upDialog").dialog("open").dialog('setTitle',"编辑街道");
    $.post("updataStreetUI",{"id":id},function(data){
        $("#updataDialogForm").form('load',data);
    },"json")
}
function delDistrict(id){
    //确认提示框
    $.messager.confirm('友情提示', '确定要删除吗?', function (r) {
        if (r) {  //r=true表示点击ok 否则点击取消
            //使用jquery的post发送异步请求
            $.post("delDistrict",{"id":id},function(data){
                if(data.result>0){
                    $('#dg').datagrid('reload');  //刷新datagrid
                }else{
                    //alert("sss");
                    $.messager.alert('友情提示','删除失败，请联系管理员:13260601227!','info');
                }
            },"json");
        }
    });
}