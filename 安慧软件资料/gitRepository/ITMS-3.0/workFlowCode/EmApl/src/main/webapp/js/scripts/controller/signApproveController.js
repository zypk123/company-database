define(
		[ 'app', 'service/SignApproveServices' ],
		function(app) {
			app
					.controller(
							'signApproveController',
							function($scope, $http, $cookies, $uibModal,signApproveService) {
								var groups = $scope.groups = [];
								var items = $scope.items = [];
								
								$scope.initPage = function() {
									var pData = {
										keys : 'groups'
									};
									signApproveService.postUrl(
													'/EmApl/service/workFlow/dictionaryService/getAllDictionaryByKeys',
													pData, function(data){
														var arry = data.groups;
														for ( var i = 0; i < arry.length; i++) {
															groups.push(arry[i]);
														}
														$scope.group = $scope.groupId;
														$scope.queryData();
													});
								};
								
								$scope.queryData = function() {
									var pData = {
										groupId : $scope.groupId,
										pageInfo:'0',
										serviceMethod:'selectTaskByGroup'
									};
									signApproveService.ajaxPostForm(
													'/EmApl/service/workFlow/commonBaseService/selectAllData',
													pData, function(data){
														 items
														.splice(
																0,
																items.length);
														var arry = data.data;
														for ( var i = 0; i < arry.length; i++) {
															console.log(arry[i]);
															items.push(arry[i]);
														}
													});
								}
								
								
								$scope.queryAjax = function(url, pData, type) {
									$http({
										method : 'POSt',
										url : url,
										params : pData
									})
											.success(
													function(data) {
														if (type == '1') {
															$scope
																	.initData(data);
															$scope.queryData();
														}
														if (type == '2') {
															items
																	.splice(
																			0,
																			items.length);
															var arry = data.data;
															for ( var i = 0; i < arry.length; i++) {
																items
																		.push(arry[i]);
															}
														}
														if (type == '3') {
															if (data.status == '1') {
																$scope
																		.queryData();
															}
														}
													});
								};
								
								$scope.tackClaim = function() {
									var array = $scope.selected;
									var taskIds = "";
									if (array.length == 0) {
										alert("请选择记录！");
									}
									for ( var i = 0; i < array.length; i++) {
										if (i == 0) {
											taskIds += array[i];
										} else {
											taskIds += "," + array[i];
										}
									}
									var pData = {
										userId : $cookies.get("loginName"),
										taskIds : taskIds
									};
									$scope
											.queryAjax(
													'/EmApl/service/workFlow/wfservice/completeClaimTask',
													pData, '3');

								}
								$scope.initPage();
								$scope.selected = [];
								$scope.selectedTags = [];
								var updateSelected = function(action, id, name) {
									if (action == 'add'
											&& $scope.selected.indexOf(id) == -1) {
										$scope.selected.push(id);
										$scope.selectedTags.push(name);
									}
									if (action == 'remove'
											&& $scope.selected.indexOf(id) != -1) {
										var idx = $scope.selected.indexOf(id);
										$scope.selected.splice(idx, 1);
										$scope.selectedTags.splice(idx, 1);
									}
								}

								$scope.updateSelection = function($event, id) {
									var checkbox = $event.target;
									var action = (checkbox.checked ? 'add'
											: 'remove');
									updateSelected(action, id, checkbox.name);
								}
								$scope.isSelected = function(id) {
									return $scope.selected.indexOf(id) >= 0;
								}
								
							});
		});