var HOME = HOME || {};
HOME = {
	CONFIG: {
		MENU_RUL : clientUrl+"/"+version+"/menu/parent/a11398fea80b4b0fb5b349d2461b4b74"
	},
	list: function(){
		axios.get(HOME.CONFIG.MENU_RUL, {}).then(function (res) {
			var result = HOME.createMenu(res); //默认有子节点
		});
	},
	createMenu: function(res){
		if(res.data.retCode == 1){ //成功
			var menu_html="";
			$.each(res.data.data,function(index,item){
				menu_html += '<div class="panel panel-default">';
				menu_html += '<div class="panel-heading" data-toggle="collapse" data-parent="#menu_accordion" href="#collapse_'+item.id+'">'+item.name+'</div>';
				if(index == 0){
					menu_html += '<div id="collapse_'+item.id+'" class="panel-collapse in" style="height: auto;">';
				}else{
					menu_html += '<div id="collapse_'+item.id+'" class="panel-collapse collapse" style="height: auto;">';
				}
				menu_html += '<ul class="nav nav-pills nav-stacked">';
				var childList = item.childList;
				if(childList.length > 0){
					$.each(childList,function(inx,itm){
						menu_html += '<li onclick="HOME.openMenu(\''+itm.id+'\',\''+itm.href+'\',\''+itm.name+'\')">'+itm.name+'</li>';
					});
				}
				menu_html += '</ul></div></div>';
			});
			$('#menu_accordion').html(menu_html);
		}
	},
	openMenu: function(id,url,name){
		HOME.careateTab({tabMainName: "tab_list",tabName: name,tabUrl : url,tabId : id});
	},
	createFrame: function(url){
		var content_height = $('.h-body').height()-$('#tab_list').height()-10; //设置除去tab页签高度,显示区域的高度
		var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:'+content_height+'px;"></iframe>';
		return s;
	},
	careateTab: function(options){
		//option:
		//tabMainName:tab标签页所在的容器
		//tabName:当前tab的名称
		//tabId:当前ID
		//tabUrl:当前tab所指向的URL地址
		var exists = HOME.checkTabIsExists(options.tabMainName, options.tabId);
		if(exists){
			$("#tab_a_"+options.tabId).click();
		} else {
			$("#"+options.tabMainName).append('<li role="presentation" id="tab_li_'+options.tabId+'"><a href="#tab_c_'+options.tabId+'" data-toggle="tab" id="tab_a_'+options.tabId+'"><span>'+options.tabName+'</span>&nbsp;&nbsp;<button class="close closeTab" type="button" onclick="HOME.closeTab(this);"><span aria-hidden="true" style="color:red">&times;</span></button></a></li>');
	        $("#tab-content").append('<div id="tab_c_'+options.tabId+'" role="tabpanel" class="tab-pane"></div>');
	        $("#tab_c_"+options.tabId).html(HOME.createFrame(options.tabUrl));
	        $("#tab_a_"+options.tabId).click();
		}
	},
	closeTab: function(button){
		//通过该button找到对应li标签的id
		var li_id = $(button).parent().parent().attr('id');
		var id = li_id.replace("tab_li_","");

		//如果关闭的是当前激活的TAB，激活他的前一个TAB
		if ($("li.active").attr('id') == li_id) {
		    $("li.active").prev().find("a").click();
		}
		//关闭TAB
		$("#" + li_id).remove();
		$("#tab_c_" + id).remove();
	},
	checkTabIsExists: function(tabMainName, tabName){ //判断是否存在指定的标签页
		var tab = $("#"+tabMainName+" > #tab_li_"+tabName);
		return tab.length > 0;
	},
	init: function(){ //初始化页面设置,根据浏览器当前的大小,设置页面,保证不出现滚动条
		HOME.list();
		$(window).resize(function(){
			HOME.resizeWindow();
		});
		HOME.resizeWindow();
	},
	resizeWindow: function(){
		var $window_height = window.innerHeight; //当前浏览器可视区域高度
		var top = $('.h-head').height();  //导航栏高度
		var foot = $('.h-foot').height(); //页脚的高度
		var middle = $window_height - top - foot; //中间的高度 10为padding
		$('.h-body').css('height',middle+"px"); //设置content内容
		
		$window_width = $(document.body).width(); //当前浏览器可视区域高度
		var menu_width = $('#menu_accordion').width(); //菜单栏宽度
		$('.b-content').css("width",($window_width-menu_width)+"px"); //减去菜单,设置右边内容的宽度
		var content_height = middle-$('#tab_list').height()-10; //设置除去tab页签高度,显示区域的高度
		$('#menu_conent').css('height',content_height+"px");
		$('iframe').css('height',content_height+"px");
	}
} 