var DICT = DICT || {};
DICT = {
	CONFIG:{
		ROOT_URL : clientUrl+"/"+version+"/dict/", //默认访问路径
		DICT_BASE : "childs/",
		DICT_TYPE : {"1":"字典","2":"字典值","3":"树"},
		FIRST_LEVEL : 1
	},
	list : function(){ //展示第一级节点
		axios.get(DICT.CONFIG.ROOT_URL, {}).then(function (res) {
			var result = DICT.create(res,DICT.CONFIG.FIRST_LEVEL); //默认有子节点
			$('#dict_table').html(result);
		}).catch(function(err){
			console.log(err);
		});
	},
	create: function(res,level,pclass){ //渲染表格res返回值,level当前展开的级数,flag是否含有子节点
		var list = res.data.data;
		if(res.data.retCode == 1){
			var html = '';
			var dictType = DICT.CONFIG.DICT_TYPE;
			var next_level = level+1;
			var child_padding = level*16+"px";
			$.each(list,function(i,item){
				if(level == 1){
					html += '<tr class="s_'+item.id+'">';
				}else{
					html += '<tr class="'+pclass+'_'+item.id+'">';
				}
				html += "<td style='padding-left:"+child_padding+"' onclick='DICT.showChild(this,\""+item.id+"\","+next_level+")'><span class='expand'></span>"+item.label+"</td>";
				html += "<td>"+item.code+"</td><td>"+item.value+"</td><td>"+dictType[item.type]+"</td>";
				html += "<td>"+item.sort+"</td><td>"+item.creator+"</td><td>"+item.remark+"</td>";
				html += '<td>';
				html += '<button type="button" class="btn btn-success btn-xs" onclick="DICT.editNode(\''+item.id+'\')">修改</button>';
				html += '<button type="button" class="btn btn-danger btn-xs btn-space" onclick="DICT.delNode(\''+item.id+'\')">删除</button>';
				html += '<button type="button" class="btn btn-info btn-xs btn-space" data-toggle="modal" onclick="DICT.addLevel(\''+item.id+'\')">添加同级</button>';
				if(item.type != "2"){
					html += '<button type="button" class="btn btn-primary btn-xs btn-space" onclick="DICT.addChild(\''+item.id+'\')">添加下级</button>';
				}
				html += '</td>';
				html += "</tr>";
			});
			return html;
		}
	},
	showChild : function(obj,id,level){ //展开子节点
		var pclass = $(obj).parent().attr('class');
		if($(obj).attr("child-node") == "expand"){ //第一次查询访问后台.后面只显示展开折叠效果
			if($(obj).find('span').hasClass('expand')){ //当前未展开状态
				$(obj).find('span').removeClass('expand').addClass('collapse'); //设置展开样式
				//展开子节点区域
				$('tr[class^="'+pclass+'_"]').show();
			}else{
				$(obj).find('span').removeClass('collapse').addClass('expand');
				//隐藏子节点
				$('tr[class^="'+pclass+'_"]').hide();
			}
		}else{
			axios.get(DICT.CONFIG.ROOT_URL+DICT.CONFIG.DICT_BASE+id, {}).then(function (res) {
				if(res.data.data.length > 0){
					var result = DICT.create(res,level,pclass);
					$(obj).attr("child-node","expand").parent().after(result);
					$(obj).find('span').removeClass('expand').addClass('collapse');
				}else{
					$(obj).css('padding-left',level*16+"px").find('span').remove();
				}
			}).catch(function(err){
				console.log(err);
			});
		}
	},
	delNode: function(nodeId){ //删除节点前置提示
		axios.get(DICT.CONFIG.ROOT_URL+"childNode/"+nodeId,{}).then(function(res){
			if(res.data.data){
				$('#del_msg').text("该节点包含子节点,是否全部删除?");
			}else{
				$('#del_msg').text("是否确定删除?");
			}
			$('#delModal').modal();
			$('#del_nodeId').val(nodeId);
		});
	},
	delNodeConfirm: function(){
		var nodeId  = $('#del_nodeId').val();
		axios.delete(DICT.CONFIG.ROOT_URL+nodeId,{}).then(function(res){
			if(res.data.retCode == 1){
				$('#edit_msg').text("删除成功!");
				$('#edit_Modal').modal();
				$('#delModal').modal('hide');
			}
		});
	},
	editNode: function(nodeId){ //编辑节点
		axios.get(DICT.CONFIG.ROOT_URL+nodeId,{}).then(function(res){
			var item = res.data.data;
			var type = item.type;
			if(type == "1"){
				$('#e_value_tr').hide();
			}else{
				$('#e_value_tr').show();
			}
			$('#e_value').val(item.value);
			$('#e_label').val(item.label);
			$('#e_code').val(item.code);
			$('#e_type option[value="'+item.type+'"').attr("selected","selected");
			$('#e_parent').val(item.parentId);
			$('#e_sort').val(item.sort);
			$('#e_remark').val(item.remark);
			$("#e_node_id").val(item.id);
			$('#editModal').modal();
		});
	},
	saveNode: function(){
		var dict = new Object() || {};
		dict.id = $('#e_node_id').val();
		dict.label = $('#e_label').val();
		dict.code = $('#e_code').val();
		dict.value = $('#e_value').val();
		dict.type = $('#e_type').val();
		dict.parentId = $('#e_parent').val();
		dict.sort = $('#e_sort').val();
		dict.remark = $('#e_remark').val();
		axios.put(DICT.CONFIG.ROOT_URL,dict).then(function(res){
			if(res.data.retCode == 1){
				$('#edit_msg').text("修改成功!");
				$('#edit_Modal').modal();
				$('#editModal').modal('hide');
			}
		});
	},
	addLevel: function(nodeId){ //添加同级
		$('#myModalLabel').text("添加同级");
		DICT.initForm();
		axios.get(DICT.CONFIG.ROOT_URL+nodeId,{}).then(function(res){
			var item = res.data.data;
			$('#l_parent').val(item.parentId);
			$('#l_type').attr('disabled',false);
			if(item.type == "1"){
				$('#e_value_tr').hide();
				$("#tr_node_id").hide();
				$('#l_type').val("1").parent().parent().show();
				$('#l_code').val('').attr('disabled',false);
			}else if(item.type == "2"){
				$('#e_value_tr').show();
				$("#tr_node_id").hide();
				$('#l_type').val(item.type).parent().parent().hide();
				$('#l_code').val(item.code).parent().parent().hide();
			}else{
				$("#tr_node_id").show();
				$('#l_type').val("3").parent().parent().show();
				$('#l_code').val('').attr('disabled',false);
			}
			$('#levelModal').modal();
		});
	},
	saveLevel: function(){
		var dict = new Object() || {};
		dict.id = $('#l_node_id').val();
		dict.label = $('#l_label').val();
		dict.code = $('#l_code').val();
		dict.value = $('#l_value').val();
		dict.type = $('#l_type').val();
		dict.parentId = $('#l_parent').val();
		dict.sort = $('#l_sort').val();
		dict.remark = $('#l_remark').val();
		dict.creator = 'wn';
		axios.post(DICT.CONFIG.ROOT_URL,dict).then(function(res){
			if(res.data.retCode == 1){
				$('#edit_msg').text("添加成功!");
				$('#edit_Modal').modal();
				$('#levelModal').modal('hide');
			}
		});
	},
	addChild: function(nodeId){ //添加下级
		$('#myModalLabel').text("添加下级");
		DICT.initForm();
		axios.get(DICT.CONFIG.ROOT_URL+nodeId,{}).then(function(res){
			var item = res.data.data;
			$('#e_value_tr').hide();
			$('#l_parent').val(item.id);
			if(item.type == "1"){
				$('#e_value_tr').hide();
				$("#tr_node_id").hide();
				$('#l_type').val('2').parent().parent().hide();
				$('#l_code').val(item.code).parent().parent().hide();
			}else if(item.type == "2"){
				$('#e_value_tr').show();
				$("#tr_node_id").hide();
			}else{
				$("#tr_node_id").show();
				$('#l_type').val('3').parent().parent().hide();
				$('#l_code').val('').parent().parent().show();
			}
			$('#levelModal').modal();
		});
	},
	selectEvent: function(obj){
		var val = $(obj).val();
		if(val == '1'){
			$('#l_node_id').parent().parent().hide();
		}else{
			$('#l_node_id').parent().parent().show();
		}
	},
	initForm: function(){
		$('#add_dict_form input').val('');
		$("#l_type option:first").prop("selected", 'selected');
	}
}