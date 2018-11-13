var MENU = MENU || {};
MENU = {
	CONFIG: {
		ROOT_URL : clientUrl+"/"+version+"/menu/" //默认访问路径
	},
	list: function(){
		axios.get(MENU.CONFIG.ROOT_URL, {}).then(function (res) {
			var result = MENU.create(res); //默认有子节点
			$('#menu_table').html(result);
		});
	},
	create: function(res){
		var html = MENU.getThead();
		if(res){
			var list = res.data.data;
			$.each(list,function(index,item){
				var childs = item.childList;
				html += '<tbody><tr>';
				if(childs.length > 0){
					html += '<td onclick="MENU.toggleChild(this,\''+item.id+'\')"><span class="expand"></span>'+item.name+'</td>';
				}else{
					html += '<td style="padding-left:24px;">'+item.name+'</td>';
				}
				html += '<td>'+MENU.replaceNull(item.href)+'</td>';
				html += '<td>'+MENU.replaceNull(item.icon)+'</td>';
				html += '<td>'+item.permission+'</td>';
				html += '<td>'+(item.showFlag == '1'?"是":"否")+'</td>';
				html += '<td>'+item.sort+'</td>';
				html += '<td>';
				html += '<button type="button" class="btn btn-success btn-xs" onclick="MENU.edit(\''+item.id+'\')">修改</button>';
				html += '<button type="button" class="btn btn-danger btn-xs btn-space" onclick="MENU.delete(\''+item.id+'\')">删除</button>';
				html += '<button type="button" class="btn btn-info btn-xs btn-space" data-toggle="modal" onclick="MENU.addParent(\''+item.id+'\')">添加同级</button>';
				html += '<button type="button" class="btn btn-primary btn-xs btn-space" onclick="MENU.addChild(\''+item.id+'\')">添加下级</button>';
				html += '</td>';
				html += '</tr></tbody>';
				if(childs.length > 0){
					html += '<tbody id="parent_'+item.id+'" class="child_tbody">';
					$.each(childs,function(idx,itm){
						var sec_childs = itm.childList;
						html += '<tr>';
						if(sec_childs.length > 0){
							html += '<td style="padding-left:36px;" onclick="MENU.toggleSecChild(this,\''+itm.id+'\')"><span class="expand"></span>'+itm.name+'</td>';
						}else{
							html += '<td style="padding-left:48px;">'+itm.name+'</td>';
						}
						html += '<td>'+MENU.replaceNull(itm.href)+'</td>';
						html += '<td>'+MENU.replaceNull(itm.icon)+'</td>';
						html += '<td>'+itm.permission+'</td>';
						html += '<td>'+(itm.showFlag == '1'?"是":"否")+'</td>';
						html += '<td>'+itm.sort+'</td>';
						html += '<td>';
						html += '<button type="button" class="btn btn-success btn-xs" onclick="MENU.edit(\''+itm.id+'\')">修改</button>';
						html += '<button type="button" class="btn btn-danger btn-xs btn-space" onclick="MENU.delete(\''+itm.id+'\')">删除</button>';
						html += '<button type="button" class="btn btn-info btn-xs btn-space" data-toggle="modal" onclick="MENU.addParent(\''+itm.id+'\')">添加同级</button>';
						html += '<button type="button" class="btn btn-primary btn-xs btn-space" onclick="MENU.addChild(\''+itm.id+'\')">添加下级</button>';
						html += '</td>';
						html += '</tr>';
						var sec_childs = itm.childList;
						if(sec_childs.length > 0){
							$.each(sec_childs,function(ix,im){
								html += '<tr class="son_'+itm.id+'" style="display:none;">';
								html += '<td style="padding-left:60px;">'+im.name+'</td>';
								html += '<td>'+MENU.replaceNull(im.href)+'</td>';
								html += '<td>'+MENU.replaceNull(im.icon)+'</td>';
								html += '<td>'+im.permission+'</td>';
								html += '<td>'+(im.showFlag == '1'?"是":"否")+'</td>';
								html += '<td>'+im.sort+'</td>';
								html += '<td>';
								html += '<button type="button" class="btn btn-success btn-xs" onclick="MENU.edit(\''+im.id+'\')">修改</button>';
								html += '<button type="button" class="btn btn-danger btn-xs btn-space" onclick="MENU.delete(\''+im.id+'\')">删除</button>';
								html += '<button type="button" class="btn btn-info btn-xs btn-space" data-toggle="modal" onclick="MENU.addParent(\''+im.id+'\')">添加同级</button>';
								html += '</td>';
								html += '</tr>';
							});
						}
					});
					html += '</tbody>';
				}
			});
		}else{
			html += '<tr align="center"><td colspan="9">暂时无数据...</td></tr>';
		}
		$('#tr_loading').remove();
		return html;
	},
	toggleChild: function(obj,id){
		if($('#parent_'+id).is(":hidden")){
			$(obj).find('span').removeClass('expand').addClass('collapse');
			$('#parent_'+id).show();
		}else{
			$(obj).find('span').removeClass('collapse').addClass('expand');
			$('#parent_'+id).hide();
		}
	},
	toggleSecChild: function(obj,id){
		if($(obj).find('span').hasClass('expand')){
			$(obj).find('span').removeClass('expand').addClass('collapse');
			$('tr[class="son_'+id+'"]').show();
		}else{
			$(obj).find('span').removeClass('collapse').addClass('expand');
			$('tr[class="son_'+id+'"]').hide();
		}
	},
	replaceNull: function(str){
		if(str == null || str == ""){
			return "/";
		}
		return $.trim(str);
	},
	getThead: function(){
		var t_head = '<thead><tr><th width="20%">菜单名称</th><th width="18%">链接</th><th width="12%">图标</th>';
 			t_head += '<th width="15%">权限标识</th><th width="8%">是否显示</th><th width="7%">排序</th><th style="min-width:260px">操作</th></tr></thead>';
 		return t_head;
	},
	edit: function(id){ //编辑菜单
		axios.get(MENU.CONFIG.ROOT_URL+id,{}).then(function(res){
			var item = res.data.data;
			if(item.parentId == "0"){
				$('#e_prev_href').val(item.prevHref).parent().parent().show();
			}else{
				$('#e_prev_href').parent().parent().hide();
			}
			$('#e_name').val(item.name);
			$('#e_permission').val(item.permission);
			$('#e_parent').val(item.parentId);
			$('#e_parents').val(item.parentIds);
			$('#e_href').val(item.href);
			$('#e_icon').val(item.icon);
			$('#e_sort').val(item.sort);
			$("#e_showFlag").val(item.showFlag);
			$("#e_remark").val(item.remark);
			$('#e_menu_id').val(id);
			$('#editModal').modal();
		});
	},
	updateMenu: function(){
		var Menu = Menu || {};
		Menu = {
			id: $('#e_menu_id').val(),
			name: $('#e_name').val(),
			permission: $('#e_permission').val(),
			parentId: $('#e_parent').val(),
			parentIds: $('#e_parents').val(),
			prevHref: $('#e_prev_href').val(),
			href: $('#e_href').val(),
			icon: $('#e_icon').val(),
			sort: $('#e_sort').val(),
			showFlag: $('#e_showFlag').val(),
			remark: $('#e_remark').val()
		}
		axios.put(MENU.CONFIG.ROOT_URL,Menu).then(function(res){
			if(res.data.retCode == 1){
				$('#edit_msg').text("修改成功!");
				$('#editModal').modal('hide');
				$('#edit_Modal').modal();
			}
		});
	},
	addParent: function(id){ //增加同级菜单
		$('#myModalLabel').text("添加同级");
		axios.get(MENU.CONFIG.ROOT_URL+id,{}).then(function(res){
			MENU.initForm();
			var item = res.data.data;
			if(item.parentId == "0"){
				$('#l_prev_href').parent().parent().show();
			}else{
				$('#l_prev_href').parent().parent().hide();
			}
			$('#l_parent_id').val(item.parentId);
			$('#l_parent_ids').val(item.parentIds);
			$('#addModal').modal();
		});
	},
	addChild: function(id){ //增加子菜单
		$('#myModalLabel').text("添加下级");
		axios.get(MENU.CONFIG.ROOT_URL+id,{}).then(function(res){
			MENU.initForm();
			var item = res.data.data;
			$('#l_prev_href').parent().parent().hide();
			$('#l_parent_id').val(item.id);
			$('#l_parent_ids').val(item.id);
			$('#addModal').modal();
		});
	},
	saveMenu: function(){
		var Menu = Menu || {};
		Menu = {
			id: $('#l_menu_id').val(),
			name: $('#l_name').val(),
			permission: $('#l_permission').val(),
			parentId: $('#l_parent_id').val(),
			parentIds: $('#l_parent_ids').val(),
			prevHref: $('#l_prev_href').val(),
			href: $('#l_href').val(),
			icon: $('#l_icon').val(),
			sort: $('#l_sort').val(),
			showFlag: $('#l_showFlag').val(),
			remark: $('#l_remark').val()
		}
		axios.post(MENU.CONFIG.ROOT_URL,Menu).then(function(res){
			if(res.data.retCode == 1){
				$('#edit_msg').text("添加成功!");
				$('#addModal').modal('hide');
				$('#edit_Modal').modal();
			}
		});
	},
	delete: function(id){ //删除菜单
		axios.get(MENU.CONFIG.ROOT_URL+"child/"+id,{}).then(function(res){
			if(res.data.data){
				$('#del_msg').text("该节点包含子节点,是否全部删除?");
			}else{
				$('#del_msg').text("是否确定删除?");
			}
			$('#delModal').modal();
			$('#del_menuId').val(id);
		});
	},
	delConfirm: function(){
		var nodeId  = $('#del_menuId').val();
		axios.delete(MENU.CONFIG.ROOT_URL+nodeId,{}).then(function(res){
			if(res.data.retCode == 1){
				$('#edit_msg').text("删除成功!");
				$('#delModal').modal('hide');
				$('#edit_Modal').modal();
			}
		});
	},
	initForm: function(){ //初始化表单
		$('#add_form input').val('');
		$("#l_showFlag option:first").prop("selected", 'selected');
	}
}