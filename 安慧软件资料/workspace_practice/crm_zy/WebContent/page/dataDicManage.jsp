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
 //查询
 function searchDataDic(){
	 $("#dg").datagrid('load',{
		"dataDicName":$("#s_dataDicName").combobox("getValue"), //获得下拉框的数据字典名
		"dataDicValue":$("#s_dataDicValue").val() //获得输入的数据字典值
	 });
 }
 
 //打开添加数据字典的对话框
 function openDataDicAddDialog(){
	 $("#dlg").dialog("open").dialog("setTitle","添加数据字典信息");
	 url="${pageContext.request.contextPath}/datadic/save.do";
 }
 
 //打开编辑数据字典的对话框
 function openDataDicModifyDialog(){
	 var selectedRows=$("#dg").datagrid("getSelections");
	 if(selectedRows.length!=1){
		 $.messager.alert("系统提示","请选择一条要编辑的数据！");
		 return;
	 }
	 var row=selectedRows[0];
	 $("#dlg").dialog("open").dialog("setTitle","编辑数据字典信息");
	 $("#fm").form("load",row);
	 url="${pageContext.request.contextPath}/datadic/save.do?id="+row.id;
 }
 
 //保存
 //注意：可编辑的下拉框的保存不能用submit，必须手动写ajax方式用来保存
 function saveDataDic(){
	 var dataDicName=$("#dataDicName").combobox("getText");//获取下拉框的值
	 var dataDicValue=$("#dataDicValue").val();
	 if(dataDicName==""){
		 $.messager.alert("系统提示","数据字典名不能为空！");
		 return;
	 }
	 if(dataDicValue==""){
		 $.messager.alert("系统提示","数据字典值不能为空！");
		 return;
	 }
	 $.post(url,{dataDicName:dataDicName,dataDicValue:dataDicValue},function(result){
			if(result.success){
				 $.messager.alert("系统提示","保存成功！");
				 $("#dlg").dialog("close");
				 $("#dg").datagrid("reload");
				 resetValue();
			}else{
				$.messager.alert("系统提示","保存失败！");
			}
		},"json");
 }
 
 //重置对话框的值
 function resetValue(){
	 $("#dataDicName").combobox("setValue","");
	 $("#dataDicValue").val("");
 }
 
 //关闭对话框
 function closeDataDicDialog(){
	 $("#dlg").dialog("close");
	 resetValue();
 }
 
 //删除
 function deleteDataDic(){
	 var selectedRows=$("#dg").datagrid("getSelections");
	 if(selectedRows.length==0){
		 $.messager.alert("系统提示","请选择要删除的数据！");
		 return;
	 }
	 var strIds=[];
	 for(var i=0;i<selectedRows.length;i++){
		 strIds.push(selectedRows[i].id);
	 }
	 var ids=strIds.join(",");
	 $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
		if(r){
			$.post("${pageContext.request.contextPath}/datadic/delete.do",{ids:ids},function(result){
				if(result.success){
					 $.messager.alert("系统提示","数据已成功删除！");
					 $("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","数据删除失败，请联系系统管理员！");
				}
			},"json");
		} 
	 });
 }
</script>
<title>数据字典管理</title>
</head>
<body style="margin: 1px">
 <table id="dg" title="数据字典管理" class="easyui-datagrid"
   fitColumns="true" pagination="true" rownumbers="true"
   url="${pageContext.request.contextPath}/datadic/list.do" fit="true" toolbar="#tb">
   <thead>
   	<tr>
   		<th field="cb" checkbox="true" align="center"></th>
   		<th field="id" width="50" align="center">编号</th>
   		<th field="dataDicName" width="200" align="center">数据字典名</th>
   		<th field="dataDicValue" width="200" align="center">数据字典值</th>
   	</tr>
   </thead>
 </table>
 <div id="tb">
  	<div>
 		<a href="javascript:openDataDicAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
 		<a href="javascript:openDataDicModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
 		<a href="javascript:deleteDataDic()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
 	</div>
 	<div>
 		&nbsp;数据字典名：&nbsp;<input class="easyui-combobox" id="s_dataDicName" data-options="panelHeight:'auto',editable:true,valueField:'dataDicName',textField:'dataDicName',url:'${pageContext.request.contextPath}/datadic/findDataDicName.do'"/>
 		&nbsp;数据字典值：&nbsp;<input type="text" id="s_dataDicValue" size="20" onkeydown="if(event.keyCode==13) searchDataDic()"/>
 		<a href="javascript:searchDataDic()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
 	</div>
 </div>
 
 <div id="dlg" class="easyui-dialog" style="width:620px;height:150px;padding: 10px 20px"
   closed="true" buttons="#dlg-buttons">
   <form id="fm" method="post">
   	<table cellspacing="8px">
   		<tr>
   			<td>数据字典名：</td>
   			<td><input class="easyui-combobox" id="dataDicName" name="dataDicName" data-options="panelHeight:'auto',valueField:'dataDicName',textField:'dataDicName',url:'${pageContext.request.contextPath}/datadic/findDataDicName.do'"/>&nbsp;<font color="red">*</font></td>
   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>数据字典值：</td>
   			<td><input type="text" id="dataDicValue" name="dataDicValue" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
   		</tr>
   	</table>
   </form>
 </div>
 <div id="dlg-buttons">
 	<a href="javascript:saveDataDic()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
 	<a href="javascript:closeDataDicDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
 </div>
</body>
</html>