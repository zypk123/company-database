define(['app','service/DeployServices'], function (app) {
    app.controller('deployFlowController', function ($scope,$http,$uibModal,Upload,deployService) {
    	var items  = $scope.items=[];
		$scope.queryData= function(){
			var pData = {}; 
			deployService.postUrl("/EmApl/service/workFlow/wfservice/queryDeployAll", pData,
			function(data) {
				items.splice(0,items.length);
	    		var arry  = data.data;
				for(var i=0; i<arry.length;i++){
					items.push(arry[i]);
				}
			});
		}
		
		$scope.queryData();
		
		$scope.details = function(processId){
		    var modalInstance = $uibModal.open(
		    	{
		    		animation: $scope.animationsEnabled,
		    		templateUrl: 'prcossImg',
		    		controller: 'ModalInstanceCtrl',
		    		size:'lg',
		    		resolve: {
		    		header : function() { return processId},
		    		userModel: function () {
		    			return $scope.userModel;
		         	}
		        }   
		     });
			}
		}).controller('ModalInstanceCtrl',function ($scope,$http,$uibModalInstance,userModel,header) {
			$scope.imgUrl="/EmApl/imgServlet?imgType=1&processDefinitionId="+header;
			$scope.ok = function () {
				$uibModalInstance.close($scope.result);
			};
			$scope.cancel = function () {
				$uibModalInstance.dismiss('cancel');
			};
			});
	}); 