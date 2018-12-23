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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
 
 var url;

 function openCustomerServiceAssignDialog(){
	 var selectedRows=$("#dg").datagrid("getSelections");
	 if(selectedRows.length!=1){
		 $.messager.alert("系统提示","请选择一条要分配的客户服务数据！");
		 return;
	 }
	 var row=selectedRows[0];
	 $("#dlg").dialog("open").dialog("setTitle","分配客服服务");
	 $("#fm").form("load",row);
	 $("#state").val("已分配");
	 $("#assignTime").val(getCurrentDate());
	 url="${pageContext.request.contextPath}/customerService/save.do?id="+row.id;
 }
 
 function saveCustomerService(){
	 $("#fm").form("submit",{
		url:url,
		onSubmit:function(){
			return $(this).form("validate");
		},
		success:function(result){
			var result=eval('('+result+')');
			if(result.success){
				$.messager.alert("系统提示","分配成功！");
				resetValue();
				$("#dlg").dialog("close");
				$("#dg").datagrid("reload");
			}else{
				$.messager.alert("系统提示","分配失败！");
				return;
			}
		}
	 });
 }
 
 function resetValue(){
	 $("#assigner").combobox("setValue","");
 }
 
 function closeCustomerDialog(){
	 $("#dlg").dialog("close");
	 resetValue();
 }
 
 
</script>
<title>Insert title here</title>
</head>
<body style="margin: 1px">
 <table id="dg" title="客服服务分配管理" class="easyui-datagrid"
   fitColumns="true" pagination="true" rownumbers="true"
   url="${pageContext.request.contextPath}/customerService/list.do?state=新创建" fit="true" toolbar="#tb">
   <thead>
   	<tr>
   		<th field="cb" checkbox="true" align="center"></th>
   		<th field="id" width="50" align="center">编号</th>
   		<th field="customer" width="100" align="center">客户</th>
   		<th field="overview" width="200" align="center">概要</th>
   		<th field="serveType" width="100" align="center">服务类型</th>
   		<th field="createPeople" width="100" align="center">创建人</th>
   		<th field="createTime" width="100" align="center">创建日期</th>
   		<th field="state" width="50" align="center" hidden="true">客服服务状态</th>
   		<th field="assigner" width="50" align="center" hidden="true">分配人</th>
   		<th field="assignTime" width="50" align="center" hidden="true">分配日期</th>
   		<th field="serviceProce" width="50" align="center" hidden="true">服务处理</th>
   		<th field="serviceProcePeople" width="50" align="center" hidden="true">服务处理人</th>
   		<th field="serviceProceTime" width="50" align="center" hidden="true">服务处理日期</th>
   		<th field="serviceProceResult" width="50" align="center" hidden="true">服务处理结果</th>
   		<th field="myd" width="50" align="center" hidden="true">客户满意度</th>
   	</tr>
   </thead>
 </table>
 <div id="tb">
 	<div>
 		<a href="javascript:openCustomerServiceAssignDialog()" class="easyui-linkbutton" iconCls="icon-fwfp" plain="true">分配</a>
 	</div>
 </div>
 
 <div id="dlg" class="easyui-dialog" style="width:750px;height:450px;padding: 10px 20px"
   closed="true" buttons="#dlg-buttons">
   
   <form id="fm" method="post">
   	<table cellspacing="8px">
   		<table cellspacing="8px">
   		<tr>
   			<td>服务类型：</td>
   			<td>
   				<input type="text" id="serveType" name="serveType" readonly="readonly"/>
   			</td>
   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>客户</td>
   			<td><input type="text" id="customer" name="customer" readonly="readonly"/></td>
   		</tr>
   		<tr>
   			<td>概要：</td>
   			<td colspan="4">
   				<input type="text" id="overview" name="overview" style="width: 419px" readonly="readonly"/>
   			</td>
   		</tr>
   		<tr>
   			<td>服务请求：</td>
   			<td colspan="4">
   				<textarea id="servicerequest" name="servicerequest" rows="5" cols="49" readonly="readonly"></textarea>
   			</td>
   		</tr>
   		<tr>
   			<td>创建人：</td>
   			<td>
   				<input type="hidden" id="state" name="state" />
   				<input type="text" readonly="readonly" id="createPeople" name="createPeople" />
   			</td>
   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>创建时间：</td>
   			<td><input type="text" id="createTime" name="createTime" readonly="readonly"/></td>
   		</tr>
   		<tr>
   			<td>分配给：</td>
   			<td>
   				<input class="easyui-combobox" id="assigner" name="assigner" data-options="panelHeight:'auto',editable:false,valueField:'trueName',textField:'trueName',url:'${pageContext.request.contextPath}/user/customerManagerComboList.do'"/>&nbsp;<font color="red">*</font>
   			</td>
   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>分配时间：</td>
   			<td>
   				<input type="text" id="assignTime" name="assignTime" readonly="readonly"/>
   			</td>
   		</tr>
   	</table>
   </form>
 </div>
 
 <div id="dlg-buttons">
 	<a href="javascript:saveCustomerService()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
 	<a href="javascript:closeCustomerDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
 </div>
</body>
</html>