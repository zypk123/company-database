define(['app','service/DeployServices'], function (app) {
    app.controller('deployFlowController', function ($scope,$http,$cookies,$uibModal,deployService) {
    	var items  = $scope.items=[];
    	
    	$scope.totalItems = 0;
    	$scope.maxSize = 5;
    	$scope.pageSize = 10;
    	$scope.currentPage  = 1;
    	
		$scope.queryData= function(){
			var pData = {startPage:$scope.currentPage,pageSize:$scope.pageSize}; 
			deployService.postUrl("/EmApl/service/workFlow/wfservice/queryDeployAll", pData,
			function(data) {
				$scope.totalItems=data.count;
				items.splice(0,items.length);
	    		var arry  = data.data;
				for(var i=0; i<arry.length;i++){
					items.push(arry[i]);
				}
			});
		}
		$scope.queryData();
		
		$scope.$watch('currentPage', function(newValue,oldValue) {
			if(newValue != oldValue){
				$scope.queryData();
			}
		});
		
		$scope.details = function(processId){
		    var modalInstance = $uibModal.open(
		    	{
		    		animation: $scope.animationsEnabled,
		    		templateUrl: 'prcossImg',
		    		controller: 'ModalInstanceDeploy',
		    		size:'lg',
		    		resolve: {
		    		header : function() { return processId},
		    		userModel: function () {
		    			return $scope.userModel;
		         	}
		        }   
		     });
			}
		}).controller('ModalInstanceDeploy',function ($scope,$http,$uibModalInstance,userModel,header) {
			$scope.imgUrl="/EmApl/imgServlet?imgType=1&processDefinitionId="+header;
			$scope.imgwd = 880;
			$scope.ok = function () {
				$uibModalInstance.close($scope.result);
			};
			$scope.cancel = function () {
				$uibModalInstance.dismiss('cancel');
			};
			
			var flag ='0';
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
	}); 