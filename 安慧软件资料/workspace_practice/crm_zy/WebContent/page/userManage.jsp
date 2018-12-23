<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
 var url; //定义url
 //查询
 function searchUser(){
	 $("#dg").datagrid('load',{
		"userName":$("#s_userName").val() //获取输入的用户名的值,条件查询
	 });
 }
	
 //打开添加用户的对话框
 function openUserAddDialog(){
	 $("#dlg").dialog("open").dialog("setTitle","添加用户信息");
	 url="${pageContext.request.contextPath}/user/save.do";
 }
 
 //打开编辑用户对话框
 function openUserModifyDialog(){
	 var selectedRows=$("#dg").datagrid("getSelections");//获取选中的数据
	 if(selectedRows.length!=1){
		 $.messager.alert("系统提示","请选择一条要编辑的数据！");
		 return;
	 }
	 var row=selectedRows[0];
	 $("#dlg").dialog("open").dialog("setTitle","编辑用户信息");
	 $("#fm").form("load",row); //将获取到的数据填充到弹出的对话框中
	 url="${pageContext.request.contextPath}/user/save.do?id="+row.id;
 }
 
 //删除用户
 function deleteUser(){
	 var selectedRows=$("#dg").datagrid("getSelections");//获取选中的数据
	 if(selectedRows.length==0){
		 $.messager.alert("系统提示","请选择要删除的数据！");
		 return;
	 }
	 var strIds=[]; //删除多个数据的到时候，会有多个用户的id组成数组
	 for(var i=0;i<selectedRows.length;i++){
		 strIds.push(selectedRows[i].id);//将获得的id添加到数组中
	 }
	 var ids=strIds.join(",");//分割数组中的id，获得每个id
	 $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
		if(r){
			$.post("${pageContext.request.contextPath}/user/delete.do",{ids:ids},function(result){
				if(result.success){
					 $.messager.alert("系统提示","数据已成功删除！");
					 $("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","数据删除失败，请联系管理员！");
				}
			},"json");
		} 
	 });
 }
 
 //保存
 function saveUser(){
	 $("#fm").form("submit",{
		url:url,
		onSubmit:function(){
			if($("#roleName").combobox("getValue")==""){
				$.messager.alert("系统提示","请选择用户角色！");
				return false;
			}
			return $(this).form("validate");//调用系统的validate的验证
		},
		success:function(result){
			var result=eval('('+result+')');
			if(result.success){
				$.messager.alert("系统提示","保存成功！");
				resetValue();//清空对话框的数据
				$("#dlg").dialog("close");//关闭对话框
				$("#dg").datagrid("reload");//重新加载列表
			}else{
				$.messager.alert("系统提示","保存失败！");
				return;
			}
		}
	 });
 }
 
 //关闭对话框
 function closeUserDialog(){
	 $("#dlg").dialog("close");
	 resetValue();
 }
 
 //将值重置为空
 function resetValue(){
	 $("#userName").val("");
	 $("#password").val("");
	 $("#trueName").val("");
	 $("#email").val("");
	 $("#phone").val("");
	 $("#roleName").combobox("setValue","");
 }
</script>
<title>用户管理</title>
</head>
<body style="margin: 1px">
 <table id="dg" title="用户管理" class="easyui-datagrid"
   fitColumns="true" pagination="true" rownumbers="true"
   url="${pageContext.request.contextPath}/user/list.do" fit="true" toolbar="#tb">
   <thead>
   	<tr>
   		<th field="cb" checkbox="true" align="center"></th>  <!--勾选框 -->
   		<th field="id" width="50" align="center">编号</th>
   		<th field="userName" width="50" align="center">用户名</th>
   		<th field="password" width="50" align="center">密码</th>
   		<th field="trueName" width="50" align="center">真实姓名</th>
   		<th field="email" width="50" align="center">邮件</th>
   		<th field="phone" width="50" align="center">联系电话</th>
   		<th field="roleName" width="50" align="center">角色</th>
   	</tr>
   </thead>
 </table>
 <div id="tb">
 	<div>
 		<a href="javascript:openUserAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
 		<a href="javascript:openUserModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
 		<a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
 	</div>
 	<div>
 		&nbsp;用户名：&nbsp;<input type="text" id="s_userName" size="20" onkeydown="if(event.keyCode==13) searchUser()"/>
 		<a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
 	</div>
 </div>
 
 <!--添加用户对话框dialog  -->
 <div id="dlg" class="easyui-dialog" style="width:620px;height:250px;padding: 10px 20px"
	  closed="true" buttons="#dlg-buttons">
 	<form id="fm" method="post">
	   	<table cellspacing="8px">
	   		<tr>
	   			<td>用户名：</td>
	   			<td><input type="text" id="userName" name="userName" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>密码</td>
	   			<td><input type="password" id="password" name="password" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
	   		</tr>
	   		<tr>
	   			<td>真实姓名：</td>
	   			<td><input type="text" id="trueName" name="trueName" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>邮箱：</td>
	   			<td><input type="text" id="email" name="email" class="easyui-validatebox" validType="email" required="true"/>&nbsp;<font color="red">*</font></td>
	   		</tr>
	   		<tr>
	   			<td>联系电话：</td>
	   			<td><input type="text" id="phone" name="phone" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>用户角色</td>
	   			<td>
	   				<select class="easyui-combobox" id="roleName" name="roleName" style="width: 154px" editable="false" panelHeight="auto">
	   					<option value="">请选择角色...</option>
	   					<option value="系统管理员">系统管理员</option>
	   					<option value="销售主管">销售主管</option>
	   					<option value="客户经理">客户经理</option>
	   					<option value="高管">高管</option>
	   				</select>
	   				&nbsp;<font color="red">*</font>
	   			</td>
	   		</tr>
	   	</table>
   </form>	  
 </div>
 
 <!-- 弹出对话框的按钮 -->
 <div id="dlg-buttons">
	<a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closeUserDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
 </div>
</body>
</html>