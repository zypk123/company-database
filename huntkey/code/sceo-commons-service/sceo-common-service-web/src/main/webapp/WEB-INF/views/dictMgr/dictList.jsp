<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
	<title>首页</title>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<link rel="stylesheet" href="/static/commom/commom.css" type="text/css">
	<script type="text/javascript" src="/static/dict/dict.manage.js"></script>
	<script type="text/javascript">
	$(function(){
		DICT.list();
	});
	</script>
</head>
<body>
<div class="wrapper">
	<table class="dict-table">
		<thead>
			<tr>
	 			<th width="17%">类型名称</th>
	 			<th width="13%">类型编码</th>
	 			<th width="12%">值</th>
	 			<th width="4%">类型</th>
	 			<th width="7%">排序</th>
	 			<th width="7%">创建人</th>
	 			<th width="15%">类型描述</th>
	 			<th width="250">操作</th>
	 		</tr>
		</thead>
		<tbody id="dict_table">
			<tr id="tr_loading" class="tr-loading" align="center"><td colspan="9">数据加载中...</td></tr>
		</tbody>
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
      <div class="modal-body" >
      	<table class="edit-table">
      		<tr>
      			<td align="right" width="20%">类型名称：</td>
      			<td align="center" width="75%"><input maxlength="32" id="e_label" type="text" class="form-control"></td>
      			<td align="center" width="5%"><font class="required">*</font></td>
      		</tr>
      		<tr>
      			<td align="right">类型编码：</td>
      			<td align="center"><input maxlength="32" id="e_code" type="text" class="form-control"></td>
      			<td><font class="required">*</font></td>
      		</tr>
      		<tr id="e_value_tr">
      			<td align="right">值：</td>
      			<td align="center"><input maxlength="32" id="e_value" type="text" class="form-control"></td>
      			<td><font class="required">*</font></td>
      		</tr>
      		<tr>
      			<td align="right">排序：</td>
      			<td align="center"><input maxlength="11" id="e_sort" type="text" class="form-control"></td>
      			<td><font class="required">*</font></td>
      		</tr>
      		<tr class="tr-hide">
      			<td align="right">父级ID：</td>
      			<td align="center"><input disabled="disabled" id="e_parent" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr class="tr-hide">
      			<td align="right">类型：</td>
      			<td align="center">
      				<select disabled="disabled" class="form-control" id="e_type">
      					<option value="1">字典</option>
      					<option value="2">字典值</option>
      					<option value="3">树</option>
      				</select>
      			</td>
      			<td></td>
      		</tr>
      		<tr>
      			<td align="right">类型描述：</td>
      			<td align="center"><input maxlength="512" id="e_remark" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      	</table>
      </div>
      <div class="modal-footer">
      	<input type="hidden" id="e_node_id"/>
       	<button type="button" class="btn btn-primary" onclick="DICT.saveNode()">&nbsp;&nbsp;保&nbsp;&nbsp;存&nbsp;&nbsp;</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">&nbsp;&nbsp;取&nbsp;&nbsp;消&nbsp;&nbsp;</button>
      </div>
    </div>
  </div>
</div>
<!-- 弹出框 添加同级使用-->
<div class="modal fade" id="levelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" style="width: 500px;" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">添加同级</h4>
      </div>
      <div class="modal-body">
      	<table class="edit-table" id="add_dict_form">
      		<tr id="tr_node_id">
      			<td align="right" width="20%">ID(可为空)：</td>
      			<td align="center" width="75%"><input maxlength="32" id="l_node_id" type="text" class="form-control"></td>
      			<td align="center" width="5%"></td>
      		</tr>
      		<tr>
      			<td align="right" width="20%">类型名称：</td>
      			<td align="center"  width="75%"><input maxlength="32" id="l_label" type="text" class="form-control"></td>
      			<td width="5%"><font class="required">*</font></td>
      		</tr>
      		<tr>
      			<td align="right">类型编码：</td>
      			<td align="center"><input maxlength="32" id="l_code" type="text" class="form-control"></td>
      			<td><font class="required">*</font></td>
      		</tr>
      		<tr>
      			<td align="right">值：</td>
      			<td align="center"><input maxlength="32" id="l_value" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td align="right">类型：</td>
      			<td align="center">
      				<select class="form-control" id="l_type" onchange="DICT.selectEvent(this)">
      					<option value="1">字典</option>
      					<option value="2" style="display: none;">字典值</option>
      					<option value="3">树</option>
      				</select>
      			</td>
      			<td></td>
      		</tr>
      		<tr class="tr-hide">
      			<td align="right">上级ID：</td>
      			<td align="center"><input disabled="disabled" id="l_parent" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td align="right">排序：</td>
      			<td align="center"><input maxlength="11" id="l_sort" type="text" class="form-control"></td>
      			<td><font class="required">*</font></td>
      		</tr>
      		<tr>
      			<td align="right">类型描述：</td>
      			<td align="center"><input maxlength="512" id="l_remark" type="text" class="form-control"></td>
      			<td></td>
      		</tr>
      	</table>
      </div>
      <div class="modal-footer">
       	<button type="button" class="btn btn-primary" onclick="DICT.saveLevel()">&nbsp;&nbsp;保&nbsp;&nbsp;存&nbsp;&nbsp;</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">&nbsp;&nbsp;取&nbsp;&nbsp;消&nbsp;&nbsp;</button>
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
      <div class="modal-tip-footer">
       <input type="hidden" id="del_nodeId">
        <button type="button" class="btn btn-danger" onclick="DICT.delNodeConfirm()">确定</button>
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
        <button type="button" class="btn btn-danger" onclick="DICT.list()" data-dismiss="modal">确定</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>