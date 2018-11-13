<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>首页</title>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<link rel="stylesheet" href="/static/commom/commom.css" type="text/css">
	<script type="text/javascript" src="/static/menu/menu.manage.js"></script>
	<script type="text/javascript">
	$(function(){
		MENU.list();
	});
	</script>
</head>
<body>
<div class="wrapper">
	<table class="dict-table" id="menu_table">
		<tr id="tr_loading" class="tr-loading" align="center"><td colspan="9">数据加载中...</td></tr>
	</table>
</div>
<!-- 弹出框 编辑使用-->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width: 500px;" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">编辑</h4>
      </div>
      <div class="modal-body">
      	<table class="edit-table">
      		<tr>
      			<td align="right" width="20%">菜单名称：</td>
      			<td align="center" width="75%"><input maxlength="32" id="e_name" type="text" class="form-control"></td>
      			<td width="5%"></td>
      		</tr>
      		<tr>
      			<td align="right">权限标识：</td>
      			<td align="center"><input maxlength="512" id="e_permission" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr class="tr-hide">
      			<td align="right">上级菜单：</td>
      			<td align="center"><input disabled="disabled" maxlength="32" id="e_parent" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr class="tr-hide">
      			<td align="right">全部上级：</td>
      			<td align="center"><input disabled="disabled" maxlength="32" id="e_parents" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td align="right">服务IP：</td>
      			<td align="center"><input maxlength="64" id="e_prev_href" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td align="right">链接：</td>
      			<td align="center"><input maxlength="64" id="e_href" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td align="right">图标：</td>
      			<td align="center"><input maxlength="64" id="e_icon" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td align="right">排序：</td>
      			<td align="center"><input maxlength="11" id="e_sort" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td align="right">是否显示：</td>
      			<td align="center">
      				<select class="form-control" id="e_showFlag">
      					<option value="1">是</option>
      					<option value="0">否</option>
      				</select>
      			</td>
      			<td></td>
      		</tr>
      		<tr>
      			<td align="right">备注：</td>
      			<td align="center"><input maxlength="512" id="e_remark" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      	</table>
      </div>
      <div class="modal-footer">
      	<input type="hidden" id="e_menu_id"/>
       	<button type="button" class="btn btn-primary" onclick="MENU.updateMenu()">&nbsp;&nbsp;保&nbsp;&nbsp;存&nbsp;&nbsp;</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">&nbsp;&nbsp;关&nbsp;&nbsp;闭&nbsp;&nbsp;</button>
      </div>
    </div>
  </div>
</div>
<!-- 弹出框 添加同级菜单与子菜单使用-->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" style="width: 500px;" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">添加同级</h4>
      </div>
      <div class="modal-body">
      	<table class="edit-table" id="add_form">
      		<tr>
      			<td align="right" width="20%">菜单名称：</td>
      			<td align="center" width="75%"><input maxlength="32" id="l_name" type="text" class="form-control"></td>
      			<td width="5%"></td>
      		</tr>
      		<tr>	
      			<td align="right">权限标识：</td>
      			<td align="center"><input maxlength="512" id="l_permission" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td align="right">服务IP：</td>
      			<td align="center"><input maxlength="64" id="l_prev_href" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr class="tr-hide">
      			<td align="right">上级菜单：</td>
      			<td align="center"><input disabled="disabled" maxlength="32" id="l_parent_id" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr class="tr-hide">
      			<td align="right">全部上级：</td>
      			<td align="center"><input disabled="disabled" maxlength="32" id="l_parent_ids" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td align="right">菜单链接：</td>
      			<td align="center"><input maxlength="64" id="l_href" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td align="right">菜单图标：</td>
      			<td align="center"><input maxlength="64" id="l_icon" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td align="right">排序：</td>
      			<td align="center"><input maxlength="11" id="l_sort" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td align="right">是否显示：</td>
      			<td align="center">
      				<select class="form-control" id="l_showFlag">
      					<option value="1">是</option>
      					<option value="0">否</option>
      				</select>
      			</td>
      			<td></td>
      		</tr>
      		<tr>
      			<td align="right">备注：</td>
      			<td align="center"><input maxlength="512" id="l_icon" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      	</table>
      </div>
      <div class="modal-footer">
       	<button type="button" class="btn btn-primary" onclick="MENU.saveMenu()">&nbsp;&nbsp;保&nbsp;&nbsp;存&nbsp;&nbsp;</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">&nbsp;&nbsp;关&nbsp;&nbsp;闭&nbsp;&nbsp;</button>
      </div>
    </div>
  </div>
</div>
<!-- 弹出框 删除提示使用-->
<div class="modal fade bs-example-modal-sm" id="delModal" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">提示</h4>
      </div>
      <div class="modal-body" id="del_msg">确定删除吗?</div>
      <input type="hidden" id="del_menuId">
      <div class="modal-tip-footer">
        <button type="button" class="btn btn-danger" onclick="MENU.delConfirm()">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
</div>
<!-- 弹出框 添加编辑提示使用-->
<div class="modal fade bs-example-modal-sm" id="edit_Modal" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">提示</h4>
      </div>
      <div class="modal-body" id="edit_msg"></div>
      <div class="modal-tip-footer">
        <button type="button" class="btn btn-danger" onclick="MENU.list()" data-dismiss="modal">确定</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>