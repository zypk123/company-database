var  app = angular.module('newApproveApp', [ 'ngAnimate', 'ui.bootstrap','ngFileUpload','ngCookies','ngDialog']);

app.directive('zTree', function($compile) {
	return{
    require:'?ngModel',
    restrict:'EA',
    link:function($scope,element,attrs,ngModel){
		$scope.checkNodes = [];
        var setting = {
            data :{
                simpleData:{
                    enable:true
                }
            },
            callback:{
                onCheck:function(treeId, treeNode) {//点击菜单时进行的处理
            	   //选中或是取消选中触发
            	   var treeObj = $.fn.zTree.getZTreeObj("tree");
            	   var nodes = treeObj.getCheckedNodes(true);
            	   $scope.$emit("checkedTree",nodes);
                },
            	onClick:function(event, treeId,treeNode){
                	$scope.$emit("closeWin",treeNode);//此处attrs["value"]为ul中的value值
                }
            }
        };
        var param = {};
        $scope.$emit("groups",param);//此处attrs["value"]为ul中的value值
		$scope.$on("groupData",function(event,data){
			$.fn.zTree.init(element, setting, data);//进行初始化树形菜单
		});
      }
	}
});

var controller = app.controller(
				'approveForm',
				function($scope,$http,$uibModal,$cookies,Upload,$rootScope,ngDialog) {
					$scope.groupId=window.opener.$scope.groupId;
					$scope.viewFlag=window.opener.$scope.viewFlag;
					var  imgSer  =  "http://"+window.opener.$scope.servieImg+"/";
					if(imgSer.indexOf('"') > -1){
						imgSer  = imgSer.replace('"','');
						if(imgSer.indexOf('"') > -1){
							imgSer  = imgSer.replace('"','');
						}
					}
					$scope.valflag=false;
					$scope.noWrapSlides = false;
					$scope.active = 0;
					var slides = $scope.slides = [];
					$scope.formModel = {};
					var  dpData=[];
					var units = $scope.units = [];
					var contents = $scope.contents = [];
					var approveTypes = $scope.approveTypes = [];
					var roads= $scope.roads = [];
					var sections = $scope.sections = [];
					var roadTypes = $scope.roadTypes = [];
					var roadLevels = $scope.roadLevels = [];
					var terrains = $scope.terrains = [];
					var devicePositions = $scope.devicePositions = [];
					var pavementLevels = $scope.pavementLevels = [];
					var signatureImgFiles='';
					$scope.signatureImgFile='';
					var signFiles=[];
					var solFiles=[];
					var roadStatusImgFiles=[];
					var Files=[];

					$scope.status = [];
					$scope.solides = [];
					//
					$scope.queryAjax= function(url,pData,type){
				        $http(
				        	{
				        		method:'POSt',
				        		url:url,
				        		params:pData
				        	}
				        )
				        .success(function(data){
				        	if(type == '1'){
				        		$scope.initData(data);
				        	}
				        	if(type == '2'){
				        		roads.splice(0,roads.length);
				        		var arry  = data.road;
								for(var i=0; i<arry.length;i++){
									roads.push(arry[i]);
								}
				        	}
				        	if(type == '3'){
				        		sections.splice(0,sections.length);
				        		var arry  = data.roadSection;
								for(var i=0; i<arry.length;i++){
									sections.push(arry[i]);
								}
				        	}
				        	if(type == '4'){
				        		//获取单位名称
				        		$scope.formModel = data.data;
				        		//图片显示
				        		var  approveDevice = $scope.formModel;
					        	var roadStatusImgs=approveDevice.roadStatusImg;
					        	if(roadStatusImgs==null){
					        		roadStatusImgs = '';
					        	}
					        	var tmp = roadStatusImgs.split(",");
					        	slides.splice(0,slides.length);
					        	$scope.solides.splice(0,$scope.solides.length);
					        	$scope.status.splice(0,$scope.status.length);
					        	$scope.imges1 = "";
					        	for(var  i=0;i<tmp.length;i++){
					        		var  obj = {};
					        		obj.value = imgSer+tmp[i];
					        		obj.name = tmp[i].substring(tmp[i].lastIndexOf('/')+1);
					        		$scope.imges1 += obj.name+"，";
					        		$scope.status.push(obj);
					        		slides.push({
									  image: imgSer+tmp[i],
								      text:'道路状况'+i,
								      id: i
					        		});
					        	}
					        	$scope.signatureImgFile=imgSer+approveDevice.signatureImg;
					        	$scope.imges2 = approveDevice.signatureImg.substring(approveDevice.signatureImg.lastIndexOf('/')+1);

					        	if(approveDevice.solutionFile == null){
					        		approveDevice.solutionFile = "";
					        	}
					        	tmp = approveDevice.solutionFile.split(",");
					        	$scope.files1 = "";
					        	for(var  i=0;i<tmp.length;i++){
					        		var  obj = {};
					        		obj.value = imgSer+tmp[i];
					        		obj.name = tmp[i].substring(tmp[i].lastIndexOf('/')+1);
					        		$scope.files1 += obj.name+"，";
					        		$scope.solides.push(obj);
					        	}
				        	}
			        	});
				    };
				    var nameObj = {'000006':'固定测速','000007':'区间测速','000008':'闯红灯',
				                   '000009':'取证','000010':'卡口','000011':'禁行'};
				    //设备名称，规则为
				    $scope.generateDeviceName = function() {
				    	//名字后缀
				    	var deviceName  =  nameObj[$scope.formModel.content];
				    	var obj=document.getElementsByName('roadId')[0];
				    	var index=obj.selectedIndex; //序号，取当前选中选项的序号
				    	var  roadName  =  obj.options[index].text;
				    	//区间测速
				    	if($scope.formModel.content == '000007' &&($scope.formModel.approveType=='000003'|| $scope.formModel.approveType=='000004')){//道路名称加起点公里米数至终点公里米 + 区间测速
				    		$scope.formModel.deviceName = roadName +"K"+$scope.formModel.kmeter+'至'
				    		+ "K"+$scope.formModel.ekmeter +"段"+deviceName;
				    	}else if($scope.formModel.content != '000007' &&($scope.formModel.approveType=='000003'|| $scope.formModel.approveType=='000004')){
				    		$scope.formModel.deviceName = roadName +"K"+$scope.formModel.kmeter +"+" + $scope.formModel.meter +'米'+deviceName;
				    	}else if($scope.formModel.approveType != '000005'){
				    		var sectionobj=document.getElementsByName('section')[0];
				    		var index1=sectionobj.selectedIndex; //序号，取当前选中选项的序号
				    		var sectionName  =  sectionobj.options[index1].text;
				    		$scope.formModel.deviceName = roadName +"-"+ sectionName +"-"+ deviceName;
				    	}else{
				    		alert('移动点请手工填写！');
				    	}
				    }

					$scope.initPage = function() {
						var pData = {keys:'roadType,roadLevel,terrain,pavementLevel,approveType,content,devicePosition,orgGrid,road'};
						$scope.queryAjax('/EmApl/service/workFlow/dictionaryService/getAllDictionaryByKeys',pData,'1');
					};

					$scope.initData = function(data) {
						var arry  = data.content;
						for(var i=0; i<arry.length;i++){
							contents.push(arry[i]);
						}
						$scope.formModel.content=arry[0].value;
						arry  = data.roadType;
						for(var i=0; i<arry.length;i++){
							roadTypes.push(arry[i]);
						}
						arry  = data.approveType;
						for(var i=0; i<arry.length;i++){
							approveTypes.push(arry[i]);
						}
						$scope.formModel.approveType=arry[0].value;
						arry  = data.roadLevel;
						for(var i=0; i<arry.length;i++){
							roadLevels.push(arry[i]);
						}
						arry  = data.terrain;
						for(var i=0; i<arry.length;i++){
							terrains.push(arry[i]);
						}
						arry  = data.pavementLevel;
						for(var i=0; i<arry.length;i++){
							pavementLevels.push(arry[i]);
						}

						roads.splice(0,roads.length);
		        		var arry  = data.road;
						for(var i=0; i<arry.length;i++){
							roads.push(arry[i]);
						}
						arry  = data.devicePosition;
						for(var i=0; i<arry.length;i++){
							dpData=arry;
							if(arry[i].parentCode==null){
								devicePositions.push(arry[i]);
							}
						}

						//判断弹出是新增还是修改
						$scope.deviceApproveId = window.opener.$scope.deviceApproveId;
						if($scope.deviceApproveId != ''){//修改
							var  pData = {approveId:$scope.deviceApproveId};
							try{
								$scope.queryAjax('/EmApl/service/workFlow/dAservice/queryDeviceApproveById',pData,'4');
							}catch(e){
								//修改出现异常先不做处理
							}
						}
					};

					$scope.$watch('formModel.roadId', function(newValue,oldValue) {
						if(newValue != oldValue){
						    var pData = {key:'roadSection',parentCode:$scope.formModel.roadId};
						    $scope.queryAjax('/EmApl/service/workFlow/dictionaryService/getCascadeData',pData,'3');
						}
					});


					$scope.$watch('formModel.content', function(newValue,oldValue) {
						if(newValue != '000012'){
							if($scope.formModel.approveType == '000005'){
								$scope.formModel.approveType='000001';
							}
						}else{
							$scope.formModel.approveType = '000005'
						}
						if($scope.formModel.approveType == '000005'&& $scope.formModel.content=='000012'){
							return;
						}
						if(newValue != oldValue){
							devicePositions.splice(0,devicePositions.length);
							for(var i=0;i<dpData.length;i++){
								if(dpData[i].parentCode==null){
									devicePositions.push(dpData[i]);
								}else{
									if(dpData[i].parentCode == $scope.formModel.content){
										devicePositions.push(dpData[i]);
									}
								}
							}
						}
					});
					$scope.$watch('formModel.approveType', function(newValue,oldValue) {
						if(newValue == '000005'){
							$scope.formModel.content='000012';
							devicePositions.splice(0,devicePositions.length);
							for(var i=0;i<dpData.length;i++){
								if(dpData[i].parentCode==null){
									devicePositions.push(dpData[i]);
								}else{
									if(dpData[i].parentCode == $scope.formModel.approveType){
										devicePositions.push(dpData[i]);
									}
								}
							}
						}
					});
					$scope.uploadType = 0;
					//保存申请单
					$scope.submitFormData=function(){
						//验证通过后才可以提交，要根据不同的选择有不同的验证规则
						$scope.uploadType = 0;
						if($scope.validate()){
							//通过后启动蒙版
							dialog=ngDialog.open(
				    		{
					    		template: '../popupTmpl.html',
					    		closeByDocument:false,
					    		closeByEscape:false,
					    		showClose:false,
					    		className: 'ngdialog-theme-default'
					    	});
							$scope.uploadFiles();
						}else{
							alert("请核对输入信息后，重新提交！");
						}
					}
					$scope.editFormData=function(){
						//验证通过后才可以提交，要根据不同的选择有不同的验证规则
						$scope.uploadType = 0;
						if($scope.validate()){
							//通过后启动蒙版
							dialog=ngDialog.open(
				    		{
					    		template: '../popupTmpl.html',
					    		closeByDocument:false,
					    		closeByEscape:false,
					    		showClose:false,
					    		className: 'ngdialog-theme-default'
					    	});
							//更新数据信息
					    	$scope.processForm('/EmApl/service/workFlow/dAservice/updateDAFrom');
						}else{
							alert("请核对输入信息后，重新提交！");
						}
					}


					$scope.uploadFiles=function(){
						$scope.size = 0;//文件总数清零
						//上传文件
						if(signFiles.length>0){
							$scope.size += 1;
						}
						if(solFiles.length>0){
							$scope.size += 1;
						}
						if(roadStatusImgFiles.length>0){
							$scope.size += 1;
						}
						//文件上传
						$scope.formModel.roadStatusImg='';
						$scope.formModel.solutionFile='';
						$scope.uploads = 0;
						if(signFiles.length > 0 ){
							$scope.fileUpload(signFiles,'2',"DWQZ");
						}
						if(solFiles.length > 0 ){
							$scope.fileUpload(solFiles,'3','JSFA');
						}
						if(roadStatusImgFiles.length > 0 ){
							$scope.fileUpload(roadStatusImgFiles,'1','DLZK');
						}
					}
					var dialog;
					//保存并提交
					$scope.submitFormAndstart=function(){
						$scope.uploadType = 1;
						if($scope.validate()){
							//验证通过后，上传文件，计算所有上传文件的数量，然后监控上传文件的数量
							//上传成功的数量等于所有文件的数量，提交表单
							//通过后启动蒙版
							dialog=ngDialog.open(
				    		{
					    		template: '../popupTmpl.html',
					    		closeByDocument:false,
					    		closeByEscape:false,
					    		showClose:false,
					    		className: 'ngdialog-theme-default'
					    	});
							$scope.uploadFiles();
						}
					}
					//提交申请单时的验证规则
					$scope.validate=function(){
						//移动点的验证规则
						if($scope.formModel.approveType == '000005'){
							return $scope.validateMovePoint();
						}else if($scope.formModel.content == '000007' && ($scope.formModel.approveType=='000003' || $scope.formModel.approveType=='000004')){//新增区间测速路段+公路
							return $scope.validateRegion();
						}else if($scope.formModel.approveType=='000003' || $scope.formModel.approveType=='000004'){//公路的
							return $scope.validateRoad();
						}else{//城区的
							return $scope.validateCity();
						}
					}
					//非公路
					$scope.validateCity=function(){
						//如果有一个TRUE则验证不通过
						if($scope.formObj.unitName.$error.required
							||$scope.formObj.contacts.$error.required
							||$scope.formObj.phone.$error.required
							||$scope.formObj.phone.$error.minlength
							||$scope.formObj.devicePosition.$error.required
							||$scope.formObj.deviceName.$error.required
							||$scope.formObj.roadId.$error.required
							||$scope.formObj.section.$error.required
							||$scope.formObj.deviceName.$error.required
							//城区目前没限速设置 ，后续根据需求修改||$scope.formObj.vehicleLimit.$error.required
							//||$scope.formObj.carLimit.$error.required
							//||$scope.formObj.otherLimit.$error.required
							||$scope.formObj.roadType.$error.required
							||$scope.formObj.roadLevel.$error.required
							||$scope.formObj.pavementLevel.$error.required
							||$scope.formObj.terrain.$error.required
							||$scope.signatureImgFile == ''
							){
							return false;
						}else{
							return true;
						}
					}
					//公路
					$scope.validateRoad=function(){
						//如果有一个TRUE则验证不通过
						if($scope.formObj.unitName.$error.required
							||$scope.formObj.contacts.$error.required
							||$scope.formObj.phone.$error.required
							||$scope.formObj.phone.$error.minlength
							||$scope.formObj.devicePosition.$error.required
							||$scope.formObj.deviceName.$error.required
							||$scope.formObj.roadId.$error.required
							||$scope.formObj.kmeter.$error.required
							||$scope.formObj.meter.$error.required
							||$scope.formObj.longitude.$error.required
							||$scope.formObj.longitude.$error.max
							||$scope.formObj.latitude.$error.required
							||$scope.formObj.latitude.$error.max
							||$scope.formObj.deviceName.$error.required
							||$scope.formObj.vehicleLimit.$error.required
							||$scope.formObj.carLimit.$error.required
							||$scope.formObj.otherLimit.$error.required
							||$scope.formObj.roadType.$error.required
							||$scope.formObj.roadLevel.$error.required
							||$scope.formObj.pavementLevel.$error.required
							||$scope.formObj.terrain.$error.required
							||$scope.signatureImgFile == ''
							){
							return false;
						}else{
							return true;
						}
					}
					//移动点
					$scope.validateMovePoint=function(){
						//如果有一个TRUE则验证不通过
						if($scope.formObj.unitName.$error.required
							||$scope.formObj.contacts.$error.required
							||$scope.formObj.phone.$error.required||$scope.formObj.phone.$error.minlength
							||$scope.formObj.devicePosition.$error.required
							||$scope.formObj.deviceName.$error.required
							||$scope.signatureImgFile == ''
							){
							return false;
						}else{
							return true;
						}
					}
					//公路加区间
					$scope.validateRegion=function(){
						//如果有一个TRUE则验证不通过
						if($scope.formObj.unitName.$error.required
							||$scope.formObj.contacts.$error.required
							||$scope.formObj.phone.$error.required
							||$scope.formObj.phone.$error.minlength
							||$scope.formObj.devicePosition.$error.required
							||$scope.formObj.deviceName.$error.required
							||$scope.formObj.roadId.$error.required
							||$scope.formObj.kmeter.$error.required
							||$scope.formObj.meter.$error.required
							||$scope.formObj.longitude.$error.required
							||$scope.formObj.longitude.$error.max
							||$scope.formObj.latitude.$error.required
							||$scope.formObj.latitude.$error.max
							||$scope.formObj.ekmeter.$error.required
							||$scope.formObj.emeter.$error.required
							||$scope.formObj.elongitude.$error.required
							||$scope.formObj.elongitude.$error.max
							||$scope.formObj.elatitude.$error.required
							||$scope.formObj.elatitude.$error.max
							||$scope.formObj.deviceName.$error.required
							||$scope.formObj.vehicleLimit.$error.required
							||$scope.formObj.carLimit.$error.required
							||$scope.formObj.otherLimit.$error.required
							||$scope.formObj.roadType.$error.required
							||$scope.formObj.roadLevel.$error.required
							||$scope.formObj.pavementLevel.$error.required
							||$scope.formObj.terrain.$error.required
							){
							return false;
						}else{
							return true;
						}
					}

				    $scope.initPage();

				    $scope.userEdit = function(){
				    	var scope = $rootScope.$new();
				    	 scope.data = {
				    	    msg:$scope.groupId,
				    	    header:"header"
				    	}
					    var modalInstance = $uibModal.open(
					    	{
					    		animation: $scope.animationsEnabled,
					    		templateUrl: 'userEdit.html',
					    		controller: 'ModalInstanceCtrl',
					    		size:'sm',
					    		openedClass:'body',
					    		scope:scope
					     });
					     modalInstance.result.then(function (result) {
	                        $scope.formModel.unit=result.id;
	                        $scope.formModel.unitName=result.name;
		                 }, function (reason) {
		                      console.log(reason);
		                 });
					}


				    $scope.enclosureManager = function(){
				    	var scope = $rootScope.$new();
				    	scope.data = {
				    		deviceApproveId:$scope.deviceApproveId,
				    		status:$scope.status,
				    		approveType:$scope.formModel.approveType,
				    		unitImg:imgSer+$scope.formModel.signatureImg,
				    		imgSer:imgSer,
				    		solides: $scope.solides
				    	}
					    var modalInstance = $uibModal.open(
					    	{
					    		animation: $scope.animationsEnabled,
					    		templateUrl: 'enclosureManager',
					    		controller: 'EnclosureCtrl',
					    		size:'lg',
					    		openedClass:'body',
					    		scope:scope
					     });
					     modalInstance.result.then(function (result) {
					    	 //刷新当前界面
					    	 $scope.initPage();

		                 }, function (reason) {
		                      console.log(reason);
		                 });
					}

					$scope.toggleAnimation = function (){
					   $scope.animationsEnabled = !$scope.animationsEnabled;
					};

					$scope.preViewImg = function(fileobj) {
						$scope.imges1 = "";
						if(slides.length > 0){
							slides.splice(0,slides.length);
							roadStatusImgFiles.splice(0,roadStatusImgFiles.length);
						}
						if (!window.FileReader)
							return;
						var files = fileobj.files;
						if (files.length == 0) {
							return;
						}
						if(files.length > 5){
							alert("解决方案文件不能超过5个，请重新选择！");
							return;
						}
						var URL = window.URL || window.webkitURL;
						for(var i=0;i<files.length;i++){
							var  file  = files[i];
							roadStatusImgFiles.push(file);
							var imgURL = URL.createObjectURL(file);
							slides.push({
							      image: imgURL,
							      text:'道路状况'+i,
							      id: i
							});
							$scope.imges1 += file.name+"，";
						}
						$scope.active = 0;
						$scope.$apply();
					};

					$scope.preViewImg2 = function(fileobj) {
						signatureImgFiles=fileobj;
						var files = fileobj.files;
						signFiles.splice(0,signFiles.length);
						signFiles.push(files[0]);
						if (files.length == 0) {
							return;
						}
						var URL = window.URL || window.webkitURL;
						var imgURL = URL.createObjectURL(files[0]);
						$scope.signatureImgFile=imgURL;
						//名字
						$scope.imges2 = files[0].name;
						$scope.$apply();
					};

					$scope.preViewImg3 = function(fileobj) {
						var files = fileobj.files;
						$scope.files1="";
						solFiles.splice(0,solFiles.length);
						if(files.length > 5){
							alert("解决方案文件不能超过5个，请重新选择！");
							return;
						}
						for(var i=0;i<files.length;i++){
							solFiles.push(files[i]);
							$scope.files1 += files[i].name+"，";
						}
						$scope.$apply();
					};
					$scope.size = 0;
					$scope.uploads = 0;
					$scope.fileUpload=function(files,type,fileName){
			        	Upload.upload({
					          url: '/EmApl/fileUpload',
					          data: {},
					          file: files
					         }).success(function (data) {
					          $scope.uploads +=1;
					          if(type == '1'){
					        	  $scope.formModel.roadStatusImg =  data.fileUrl;
						      }else if(type =='2'){
						    	  //如果第一次的值得为isUpload
						    	  $scope.formModel.signatureImg = data.fileUrl;
						      }else{
						       	  $scope.formModel.solutionFile =  data.fileUrl;
						      }
					          if($scope.uploads == $scope.size){
					        	  if($scope.uploadType == '1'){
					        		  $scope.processForm('/EmApl/service/workFlow/dAservice/saveDAFromSubmit');
					        	  }else{
					        		  $scope.processForm('/EmApl/service/workFlow/dAservice/saveDAFrom');
					        	  }
					          }
					        }).error(function (data, status, headers, config) {
					          console.log('error status: ' + status);
				        });
			        }

			        $scope.processForm = function(url) {
			        	//根据URL 判定是否走秩序科流程000002，000004
			        	if($scope.formModel.approveType=='000002' || $scope.formModel.approveType=='000004'){
			        		$scope.formModel.processId='deviceApprove2';
			        	}else{
			        		$scope.formModel.processId='deviceApprove';
			        	}
			        	$http({
						method  : 'POST',
						url     : url,
			        	headers : {'Content-Type' : 'application/json'},
						data    :  $scope.formModel  // pass in data as strings
						})
						.success(function(data) {
							dialog.close();
							window.opener.$scope.queryData();
						    window.close();
						});
			        }

				});
			controller.controller('ModalInstanceCtrl',function ($scope,$http,$uibModalInstance) {
					$scope.result={};
					$scope.ok = function () {
						$uibModalInstance.close($scope.result);
					};
					$scope.cancel = function () {
						$uibModalInstance.dismiss('cancel');
					};

					$scope.$on("groups",function(event,params){
						params.groupId = $scope.data.msg;
						$scope.ajaxPost("/EmApl/service/workFlow/dictionaryService/getGroupTrees",params,
					     function(data) {
					    	$scope.$broadcast("groupData",data);
						});
					 });
					 $scope.$on("closeWin",function(event,params){
						 $uibModalInstance.close(params);
					 });

					$scope.ajaxPost = function(url, data, success) {
						$http({
							method : "POST",
							url : url,
							params : data
						}).success(function(data) {
							success(data);
						})
					}
			   });
			   controller.controller('messageCtrl',function ($scope,$http,$uibModalInstance) {
					$scope.result={};
			   });

			   controller.controller('EnclosureCtrl',function ($scope,$http,$uibModalInstance,Upload,ngDialog) {
					$scope.result={};
					//formModel.roadStatusImg,formModel.signatureImg,solutionFile
					$scope.formModel = {};
					$scope.formModel = {};
					$scope.formModel.roadStatusImg='';
					$scope.formModel.signatureImg='';
					$scope.formModel.solutionFile='';
					$scope.formModel.deviceApproveId=$scope.data.deviceApproveId;
					$scope.approveType=$scope.data.approveType;
					$scope.unitImg = $scope.data.unitImg;
					$scope.solides  = $scope.data.solides;
					var oldRoads = [];
					var oldFiles = [];
					$scope.items = $scope.data.status;
					for(var  i =0;i<$scope.items.length;i++){
						oldRoads.push($scope.items[i]);
					}
					for(var  i =0;i<$scope.solides.length;i++){
						oldFiles.push($scope.solides[i]);
					}
					var  imgSer=$scope.data.imgSer;
					//判定是否需要展示路况状态图
					var  deleFiles = [];//最后删除
					var  uploadRoadFiles = [];//道路状态
					var  uploadElFiles = [];//解决方案附件
					var  uploadUnitFiles = [];//单位签章

					$scope.addFiles = function(fileobj) {
						//替换图片
						if (!window.FileReader)
							return;
						var files = fileobj.files;
						if (files.length == 0) {
							return;
						}
						if(($scope.solides.length + files.length)>5){
							alert("解决方案附件不能超过5个,请重新选择！");
							return;
						}
						for(var i=0;i<files.length;i++){
							var obj = {};
							obj.value = "#";
					        obj.name = files[i].name;
					        $scope.solides.push(obj);
					        //添加文件
					        uploadElFiles.push(files[i]);
						}
						$scope.$apply();
					}

					$scope.deleteFile = function(fileName) {
						for(var i=0;i<$scope.items.length;i++){
							if($scope.items[i].name == fileName){
								if($scope.items[i].value.indexOf(imgSer)>-1){
									deleFiles.push($scope.items[i].value.replace(imgSer,""));
								}
								$scope.items.splice(i,1);
								oldRoads.splice(i,1);//保留下的所有的以前的URL
								//删除文件
								break;
							}
						}
						for(var i=0;i<uploadRoadFiles.length;i++){
							if(uploadRoadFiles[i].name == fileName){
								uploadRoadFiles.splice(i,1);
								break;
							}
						}
					}

					$scope.deleteFile2 = function(fileName) {
						for(var i=0;i<$scope.solides.length;i++){
							if($scope.solides[i].name == fileName){
								if($scope.solides[i].value.indexOf(imgSer)>-1){
									deleFiles.push($scope.solides[i].value.replace(imgSer,""));
								}
								$scope.solides.splice(i,1);
								oldFiles.splice(i,1);//保留下的以前的解决方案
								break;
							}
						}
						for(var i=0;i<uploadElFiles.length;i++){
							if(uploadElFiles[i].name == fileName){
								uploadElFiles.splice(i,1);
								break;
							}
						}
					}

					$scope.refresh = function(fileobj) {
						//替换图片
						if (!window.FileReader)
							return;
						var files = fileobj.files;
						if (files.length == 0) {
							return;
						}
						uploadUnitFiles = [];
						var URL = window.URL || window.webkitURL;
						var imgURL = URL.createObjectURL(files[0]);
						//更换文件
						uploadUnitFiles.push(files[0]);

						if($scope.unitImg.indexOf(imgSer)>-1){
							deleFiles.push($scope.unitImg.replace(imgSer,""));
						}
						$scope.unitImg=imgURL;
						$scope.$apply();
					}
					var size =0;
					var uploads = 0;
					var dialogObj;

					$scope.submit = function(fileobj) {
						size = 0;//文件总数清零
						//上传文件
						if(uploadRoadFiles.length>0){
							size += 1;
						}
						if(uploadElFiles.length>0){
							size += 1;
						}
						if(uploadUnitFiles.length>0){
							size += 1;
						}
						if(size == 0){
							if( deleFiles.length > 0 ){
								dialogObj = ngDialog.open(
						    	{
						    		template: '../popupTmpl.html',
						    		closeByDocument:false,
						    		closeByEscape:false,
						    		showClose:false,
						    		className: 'ngdialog-theme-default'
							    });
								//只删除了文件
								for(var i=0;i<oldRoads.length;i++){
					            	  var temp  = oldRoads[i].value;
					            	  temp = temp.replace(imgSer,"");
					            	  $scope.formModel.roadStatusImg += temp+",";
					            }
								if($scope.formModel.roadStatusImg.indexOf(",")>-1){
									$scope.formModel.roadStatusImg= $scope.formModel.roadStatusImg.substring(0,$scope.formModel.roadStatusImg.length-1);
								}
					        	for(var i=0;i<oldFiles.length;i++){
					            	  var temp  = oldFiles[i].value;
					            	  temp =temp.replace(imgSer,"");
					            	  $scope.formModel.solutionFile += temp+",";
					            }
					        	if($scope.formModel.solutionFile.indexOf(",")>-1){
					        		$scope.formModel.solutionFile= $scope.formModel.solutionFile.substring(0,$scope.formModel.solutionFile.length-1);
								}
							    $scope.processForm('/EmApl/service/workFlow/dAservice/updateDAFrom');
					        	//删除服务器上文件，可能包含中文
					        	$scope.deleteFiles();
							}else{
								alert("没有修改任何数据！");
								return;
							}
						}
						dialogObj = ngDialog.open(
				    	{
				    		template: '../popupTmpl.html',
				    		closeByDocument:false,
				    		closeByEscape:false,
				    		showClose:false,
				    		className: 'ngdialog-theme-default'
					    });
						//文件上传
						var uploads = 0;
						if(uploadUnitFiles.length > 0 ){
							$scope.fileUpload(uploadUnitFiles,'2');
						}
						if(uploadElFiles.length > 0 ){
							$scope.fileUpload(uploadElFiles,'3');
						}
						if(uploadRoadFiles.length > 0 ){
							$scope.fileUpload(uploadRoadFiles,'1');
						}
						//上传文件
					}
					$scope.deleteFiles=function(){
						var files = "";
						for(var i=0;i<deleFiles.length;i++){
							files += deleFiles[i]+",";
						}

						var data = {};
						data.data = files;
						$http({
						method  : 'POST',
						url     : "/EmApl/fileDelete",
			        	params  : data  // pass in data as strings
						})
						.success(function(data) {

						});
					}
					$scope.fileUpload=function(files,type,fileName){
			        	Upload.upload({
					          url: '/EmApl/fileUpload',
					          data: {},
					          file: files
					         }).success(function (data) {
					          uploads +=1;
					          if(type == '1'){
					        	  //路况附件图，只是新上传的文件，以前老的文件也需要加上
					              for(var i=0;i<oldRoads.length;i++){
					            	  var temp  = oldRoads[i].value;
					            	  temp = temp.replace(imgSer,"");
					            	  $scope.formModel.roadStatusImg += temp+",";
					              }
					        	  $scope.formModel.roadStatusImg += data.fileUrl;
						      }else if(type =='2'){
						    	  //单位只有一个不需要修改
						    	  $scope.formModel.signatureImg = data.fileUrl.replace(imgSer,"");
						      }else{
						    	  //解决方案多个也需要加上以前的
					              for(var i=0;i<oldFiles.length;i++){
					            	  var temp  = oldFiles[i].value;
					            	  temp = temp.replace(imgSer,"");
					            	  $scope.formModel.solutionFile += temp+",";
					              }
						       	  $scope.formModel.solutionFile =  data.fileUrl;
						      }
					          if(uploads == size){
					        	$scope.processForm('/EmApl/service/workFlow/dAservice/updateDAFrom');
					          }
					          if(deleFiles.length > 0){
					        	  $scope.deleteFiles();
					          }
					        }).error(function (data, status, headers, config) {
					          console.log('error status: ' + status);
				        });
			        }

			        $scope.processForm = function(url) {
			        	$http({
						method  : 'POST',
						url     : url,
			        	headers : {'Content-Type' : 'application/json'},
						data    :  $scope.formModel  // pass in data as strings
						})
						.success(function(data) {
							dialogObj.close();
							//成功后操作，关闭当前界面，刷新父界面
							$uibModalInstance.close('');
							//刷新界面
						});
			        }

					$scope.addRoadSatus = function(fileobj) {
						if (!window.FileReader)
							return;
						var files = fileobj.files;
						if (files.length == 0) {
							return;
						}
						if(($scope.items.length + files.length)>5){
							alert("道路状况图不能超过5个,请重新选择！");
							return;
						}
						var URL = window.URL || window.webkitURL;
						for(var i=0;i<files.length;i++){
							var  file  = files[i];
							var imgURL = URL.createObjectURL(file);
							//道路状态图片
							uploadRoadFiles.push(file);
							var obj  = {};
							obj.value = imgURL;
							obj.name = file.name;
							$scope.items.push(obj);
						}
						$scope.$apply();
					};
			   });