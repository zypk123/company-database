/*
  angularjs 集成zTree
*/
define(['app', 'service/userService/userManageServices'], function(app) {
    app.directive('zTree', function($compile, UserManageService) {
        return {
            require: '?ngModel',
            restrict: 'EA',
            link: function($scope, element, attrs, ngModel) {
                $scope.checkNodes = [];

                $scope.showCheck = true;
                //show checkbox or not
                if ($scope.noCheck) {
                    //console.log("noCheck!!!!!!");
                    $scope.showCheck = false;
                }

                var setting = {
                    check: {
                        enable: $scope.showCheck
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        onCheck: function(treeId, treeNode) { //点击菜单时进行的处理
                            //选中或是取消选中触发
                            var treeObj = $.fn.zTree.getZTreeObj("tree");
                            var nodes = treeObj.getCheckedNodes(true);
                            $scope.$emit("checkedTree", nodes);
                        },
                        onClick: function(event, treeId, treeNode) {
                            $scope.$emit("slecttreeNode", treeNode); //此处attrs["value"]为ul中的value值
                            //console.log(treeNode);
                        }
                    }
                };
                var param = {};
                var nodeList = [];
                $scope.$emit("menus", param); //此处attrs["value"]为ul中的value值
                $scope.$on("menuData", function(event, treeData) {
                    //所有菜单项
                    var data = treeData.data;
                    //所有选中菜单项
                    nodeList = treeData.nodeList;
                    $scope.$emit("nodeInit", nodeList);
                    if (nodeList != null && nodeList != 'undefind' && nodeList != "") {
                        if (nodeList.length > 0) {
                            for (var i = 0; i < data.length; i++) {
                                //console.log(data[i]);
                                for (var m = 0; m < nodeList.length; m++) {
                                    if (data[i].id == nodeList[m].menuId) {
                                        data[i].checked = true;
                                        for (var n = 0; n < data.length; n++) {
                                            //根据子节点选中的状态，来显示父节点的状态
                                            if (data[n].value == data[i].pId && data[n].pId == 0) {
                                                data[n].checked = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //列表显示已选中menu名称
                    $.fn.zTree.init(element, setting, data); //进行初始化树形菜单
                    var zTree = $.fn.zTree.getZTreeObj("tree");
                    var selectName = $("#selectName").val();
                    if (typeof selectName == "undefined" || selectName == "") {
                        //zTree.selectNode(zTree.getNodeByParam("id","1"));//默认第一个选中
                        //$("#selectName").val(zTree.getSelectedNodes()[0].code);//赋值
                    } else {
                        for (var i = 0; i < data.length; i++) {
                            if (data[i]["code"] == selectName) {
                                zTree.selectNode(zTree.getNodeByParam("code", data[i]["code"]));
                            }
                        }
                    }
                });
            }
        }
    });
});
