<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<link rel="stylesheet" href="/static/commom/commom.css" type="text/css">
	<title>表单列表</title>
</head>
<body>
	<div class="wrapper">
		<table class="dict-table">
			<thead>
				<tr>
					<th width="5%">序号</th>
					<th width="25%">ID</th>
		 			<th width="20%">表单名称</th>
		 			<th width="20%">关联的表</th>
		 			<th width="15%">创建人</th>
		 			<th width="15%">操作</th>
		 		</tr>
			</thead>
			<tbody id="flow_list">
				<tr id="tr_loading" class="tr-loading" align="center"><td colspan="6">数据加载中...</td></tr>
			</tbody>
		</table>
	</div>
</body>
<script type="text/javascript">
    $(function(){
        var url = clientUrl+"/"+version+"/form/list/form";
        axios.get(url, {}).then(function (res) {
        	debugger;
            if(res.data.retCode == "1"){
                var data = res.data.data;
                var html = "";
                $.each(data, function(i, n){
                    html += '<tr>'
                        +"<td>"+(i+1)+"</td>"
                        +"<td>"+n.id+"</td>"
                        +"<td>"+n.name+"</td>"
                        +"<td>"+n.tables+"</td>"
                        +"<td>"+(n.creator == null?"":n.creator)+"</td>"
                        +"<td>"
                        	+"<input type='button' class='btn btn-success btn-xs' onclick='openFormDesigner()' value='添加'>"
                        	+"<input type='button' class='btn btn-info btn-xs btn-space' onclick='openFormDesigner(\""+n.id+"\")' value='修改'>"
                        	+"<input type='button' class='btn btn-success btn-xs btn-space' onclick='formPreview(\""+n.id+"\")' value='预览'>"
                        +"</td>"
                        +"</tr>";
                });
                
                $("#flow_list").html(html);

            }else{
                alert(res.data.errMsg);
            }
        }).catch(function(err){
            console.log(err);
        });
        $("#mysubmit").click(function (){
            var paramJson = {
                "username": $("#username").val(),
                "department": $("#department").val(),
                "day": $("#day").val(),
                "reason": $("#reason").val()
            }
            var url = clientUrl+"/workFlow/startUserProcess?key="+$("#process_key").val();
            axios.post(url, paramJson).then(function (res) {
                if(res.data.retCode == "1"){
                    $('#start_flow').modal('hide');
                    $('#operate_msg').text("提交成功!");
                    $('#tip_Modal').modal();
                }else{
                    alert(res.data.errMsg);
                }
            }).catch(function(err){
                console.log(err);
            });
        });
    });
    function openFormDesigner(id){
    	var url = ctxStatic + "autoform/index.html";
    	window.open(url);
    	//window.location.href = url;
    }
    function formPreview(id){
    	alert("待实现！");
    }
</script>
</html>