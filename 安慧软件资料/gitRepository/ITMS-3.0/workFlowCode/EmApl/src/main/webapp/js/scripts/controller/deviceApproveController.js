define(['app','service/DeviceApproveServices'], function (app) {
var modulesapp  = app.controller('deviceApproveController', function ($scope,$http,$cookies,$uibModal,deviceApproveService,$window) {
	
    var   items  = $scope.items=[];
  	$scope.totalItems = 0;
	$scope.maxSize = 5;
	$scope.pageSize = 5;
	$scope.currentPage  = 1;
	
	$window.$scope= $scope;
	
	$scope.inlineOptions = {
		showWeeks : false
	};
	$scope.dateOptions = {
		formatYear : 'yyyy',
		startingDay : 1
	};
	$scope.open1 = function() {
		$scope.popup1.opened = true;
	};
	$scope.open2 = function() {
		$scope.popup2.opened = true;
	};
	$scope.popup1 = {
		opened : false
	};

	$scope.popup2 = {
		opened : false
	};
	$scope.$watch('currentPage', function(newValue,oldValue) {
		if(newValue != oldValue){
			$scope.queryData();
		}
	});
	
	//逻辑删除申请单
	$scope.deleteApprove= function(value) {
		var param ={approveId:value};
		deviceApproveService.postUrl("/EmApl/service/workFlow/dAservice/deleteDeviceApprove",param,
		function(data) {
			//刷新数据
			$scope.queryData();
	});
	}
	//默认查询新建的数据
	$scope.status = '';
	
	$scope.formater = function(value) {
		var  returnStr = '';
		if(value == '0'){
			returnStr = '新建';
		}else if(value == '1'){
			returnStr = '审批中';
		}else if(value == '2'){
			returnStr = '通过';
		}else{
			returnStr = '不通过';
		}
		return returnStr;
	}
	
	 $scope.$watch('startTime', function(newValue,oldValue) {
			if(newValue != oldValue){
				var  stime = $scope.startTime.getTime();
				var  etime = $scope.endTime.getTime();
				if(etime<stime){
					alert("开始时间不能大于结束时间！");
					$scope.startTime = $scope.endTime;
				}
			}
		});
		
		$scope.$watch('endTime', function(newValue,oldValue) {
			if(newValue != oldValue){
				var  stime = $scope.startTime.getTime();
				var  etime = $scope.endTime.getTime();
				if(etime<stime){
					alert("开始时间不能大于结束时间！");
					$scope.startTime = $scope.endTime;
				}
			}
		});
		
	//如果是管理员，管理员可以看所有部门的数据，如果不是只能看自己部门的申请单
	$scope.queryData = function() {
		var  startTime = '';
		var  groupId= $scope.groupId;
		if($scope.startTime != undefined){
			startTime=+$scope.startTime;
		}
		var  endTime = '';
		if($scope.endTime != undefined){
			endTime=+$scope.endTime;
		}
		if($scope.groupId == 'ADMIN'){
			groupId  = '';
		}
		var param = {groupId:groupId,deviceName:$scope.deviceName,
		             status:$scope.status,startTime:startTime,endTime:endTime,
		             startPage:$scope.currentPage,pageSize:$scope.pageSize};
		deviceApproveService.ajaxPostForm("/EmApl/service/workFlow/dAservice/queryDeviceApproveData",param,
			function(data) {
				$scope.totalItems=data.total;
				items.splice(0,items.length);
		    	var arry  = data.data;
				for(var i=0; i<arry.length;i++){
					items.push(arry[i]);
				}
		});
	};
	
	$scope.subApprove= function(deviceApproveId,processId){
		var info  = processId;
		if(info == '' || info == undefined){
			info = "deviceApprove";
		}
		var pData = {deviceApproveId:deviceApproveId,processId:info}; 
		deviceApproveService.postUrl("/EmApl/service/workFlow/dAservice/submitDeviceApprove", pData,
			function(data) {
				$scope.queryData();
		});
	}

	$scope.winOpen = function() {
		$scope.deviceApproveId="";
		$scope.viewFlag='0';
		window.open("html/approveExamine/applicationManager.html");
	};
	
	$scope.editData = function(deviceApproveId) {
		$scope.deviceApproveId=deviceApproveId;
		$scope.viewFlag='0';
		window.open("html/approveExamine/applicationManager.html");
	}
	
	//查看的单据应该带审批记录
	$scope.viewData = function(deviceApproveId) {
		var pdata = {
				approveId:deviceApproveId,
				userId : $scope.loginName,
				servieImg : $scope.servieImg
			}
		var modalInstance = $uibModal.open({
			animation : $scope.animationsEnabled,
			templateUrl:'viewExamine',
			controller : 'ViewInstanceCtrl',
			size : 'lg',
			resolve : {
				header : function() {
					return pdata
				},
				userModel : function() {
					return $scope.userModel;
				}
			}
		});
	}
	
	$scope.openImg = function(processId){
	    var modalInstance = $uibModal.open(
	    	{
	    		animation: $scope.animationsEnabled,
	    		templateUrl: 'prcossImg',
	    		controller: 'ShowImgCtrl',
	    		size:'lg',
	    		resolve: {
	    		header : function() { return processId},
	    		userModel: function () {
	    			return $scope.userModel;
	         	}
	        }   
	     });
	}
	
	$scope.queryData();
	
	});
modulesapp.controller('ShowImgCtrl',function ($scope,$http,$uibModalInstance,userModel,header) {
		$scope.imgUrl="/EmApl/imgServlet?imgType=2&processDefinitionId="+header;
		$scope.ok = function () {
			$uibModalInstance.close($scope.result);
		};
		$scope.cancel = function () {
			$uibModalInstance.dismiss('cancel');
		};
		
					
		var flag ='0';
		$scope.imgwd=880;
		$scope.enlarge = function(){
			if(flag =='0'){
				$scope.imgwd=880*1.5;
				flag = '1';
			}else{
				$scope.imgwd=880;
				flag = '0';
			}
			
		}
});
modulesapp.controller(
		'ViewInstanceCtrl',
		function($scope, $http, $uibModalInstance,
				userModel, header,deviceApproveService) {
			var slides = $scope.slides = [];
			var records = $scope.records = [];
			
			var  scopeData;								
			var pData = {keys:'roadType,roadLevel,terrain,pavementLevel,approveType,content,devicePosition'}; 
			deviceApproveService.postUrl('/EmApl/service/workFlow/dictionaryService/getAllDictionaryByKeys',pData,function(data){
				scopeData = data;
				$scope
				.queryAjax(
						'/EmApl/service/workFlow/dAservice/queryDeviceApproveDataById',
						pData, 'showData');
			});
			
			$scope.results = [ {
				value : "0",
				name : "不通过"
			}, {
				value : "1",
				name : "通过"
			} ];
			$scope.result = '1';
			var approveId = header.approveId;
			var userId = header.userId;
			var taskId = header.taskId;
			var pData = {
				approveId : approveId
			};
			$scope.queryAjax = function(url, pData,
					funcName) {
				$http({
					method : 'POSt',
					url : url,
					params : pData
				}).success(function(data) {
					eval("$scope." + funcName + "(data)");
				});
			};
			$scope.queryAjaxForm = function(url, pData,
					funcName) {
				$http({
					method : 'POSt',
					url : url,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : pData
				}).success(function(data) {
					eval("$scope." + funcName + "(data)");
				});
			};
			
			var  imgSer  =  "http://"+header.servieImg+"/";
			if(imgSer.indexOf('"') > -1){
				imgSer  = imgSer.replace('"','');
				if(imgSer.indexOf('"') > -1){
					imgSer  = imgSer.replace('"','');
				}
			}
			
			$scope.showData = function(data) {
				var approveDevice = $scope.approveDevice = data.data;
				//清空审批记录
	        	$scope.records=[];
	        	var  array  = data.dataChk;
	        	for(var i=0;i<array.length;i++){
	        		$scope.records.push(array[i]);
	        	}
	        	
				if(approveDevice.approveType != '000005'){
					var roadStatusImgs = "";
					if(approveDevice.roadStatusImg != null && approveDevice.roadStatusImg != undefined){
						roadStatusImgs=approveDevice.roadStatusImg;
					}
		        	var tmp = roadStatusImgs.split(",");
		        	slides.splice(0,slides.length);
		        	for(var  i=0;i<tmp.length;i++){
		        		slides.push({
						  image: imgSer+tmp[i],
					      text:'',
					      id: i
		        		});
		        	}
				}
				//单位签章
	        	$scope.signatureImgFile=imgSer+approveDevice.signatureImg;
	        	//技术方案可能多个
	        	$scope.solides=[];
	        	if(approveDevice.solutionFile != null){
	        		var tmp = approveDevice.solutionFile.split(",");
	        		for(var  i=0;i<tmp.length;i++){
		        		var  obj = {};
		        		obj.value = imgSer+tmp[i];
		        		obj.name = tmp[i].substring(tmp[i].lastIndexOf('/')+1);
		        		$scope.solides.push(obj);
		        	}
	        	}
	        	//把下拉列表值转换
	        	$scope.getKeyName('roadType');
	        	$scope.getKeyName('roadLevel');
	        	$scope.getKeyName('terrain');
	        	$scope.getKeyName('pavementLevel');
	        	$scope.getKeyName('approveType');
	        	$scope.getKeyName('content');
	        	$scope.getKeyName('devicePosition');
			}
			
			$scope.getKeyName= function(key) {
				var array =  scopeData[key];
				for(var i =0;i<array.length;i++){
					if(array[i].value ==  $scope.approveDevice[key]){
						$scope.approveDevice[key] = array[i].name;
						break;
					}
				}
			}
			$scope.ok = function() {
				$uibModalInstance.close($scope.result);
			};
			$scope.cancel = function() {
				$uibModalInstance.dismiss('cancel');
			};
		});
}); 
