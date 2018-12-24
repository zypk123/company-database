$rootScope.$setData("vio", "VioInit", function ($scope) {

    $.extend($scope, {
        /**
         * 系统编码常量
         */
        CodeTypeDic: {
            'VEHTYPE': '001',
            'PLATETYPE': '002',
            'PLATECOLOR': '003',
            'APPEARANCE_TYPE': '004',
            'VEHICLE_STATUS': '005',
            'VEHCOLOR': '006',
            'DISTRICT': '007',
            'ISSUEORG': '008',
            'ID_TYPE': '009',
            'VEHCHARCTER': '010',
            'VIOLATION_TYPE': '011',
            'VEHBRAND': '012',
            'VIOLATION_SOURCE': '013',
            'ROAD_TYPE': '016',
            'ROAD_LEVEL': '017',
            'STATUS': '301',
            'CANCEL_UPLOAD_TYPE': '302',
            'ENFORCE_CATEGORY': '303',
            'VIO_CHARACTER': '304',
            'UPLOAD_STATUS': '305',
            'DISCARD_TYPE': '306',
            'PENALTY_TYPE': '307',
            'COLLATE_FLAG': '308',
            'ENABLE_FLAG': '018',
            'EDITABLE': '019',
            'DRIVE_MODE': '050',
            'DIRECTION_TYPE': '070',
            'SPEEDING_TYPE': '309',
            'BNORMAL_DATA_TYPE': '310',
            'SPECIAL_VEH_TYPE': '311',
            'DISCARDED_REASON': '312',
            'LOCAL_PUNISH_FLAG': '313',
            'EXPORT_FLAG': '314',
            'LOCK_FLAG': '315',
            'VIO_PRIOR_TYPE': '316',
            'FLAG_WARN': '317',
            'FLAG_FINE': '318',
            'FLAG_SUSPEND': '319',
            'FLAG_CANCEL': '320',
            'FLAG_DETAIN': '321',
            'FLAG_REVOKE_VEHICLE': '322',
            'FLAG_REVOKE_DRIVER': '323',
            'IS_GB': '324',
            'IS_COMMON_USED': '325',
            'FORCE_TYPE': '326'
        },
        CodeTypeArr: [
            '001', '002', '003', '004', '005', '006', '008', '009', '010', '011', '012', '013', '016', '017', '018', '019', '050', '070', '301', '302', '303', '304', '305', '306', '307', '308', '309', '310', '311', '312', '313', '314', '315', '316', '317', '318', '319', '320', '321', '322', '323', '324', '325', '326'
        ],//'007',
        ConstantSysCode: {
            STATUS: {
                New: '0',
                Filter: '1',
                Entry: '2',
                ReEntry: '3',
                Discard: '9'
            },
            VIOLATION_TYPE: {
                CHAO_GS: '1',
                NI_X: '3',
                CHUANG_HD: '4',
                WZTC: '6',
                QU_JCS: '7',
                JIN_X: '8'
            }
        },
        /**
         * 快捷键注册管理
         * @param $scope
         * @returns {Function}
         */
        "selectViewFn":  function (mainViewClass, menuId) {
                $scope.$("." + mainViewClass + ">.easyui-layout").hide();
                $scope.$("." + mainViewClass + ">#" + menuId).show().layout("resize");
        },
        /**
         * ajax post请求简易写法
         * @param url
         * @param data
         * @param successFn
         * @param errorFn
         */
        "PostAjax": function (url, data, successFn, errorFn) {
            if (!errorFn || typeof(errorFn) == "undefined") {
                errorFn = function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(XMLHttpRequest);
                    console.log(textStatus);
                    console.log(errorThrown);
                };
            }
            $scope.$ajaxRequest({
                url: url,
                params: data,
                dataType: "json",
                method: "post",
                success: successFn,
                fail: errorFn
            })
        },
        /**
         * 格式化参数
         * @param $scope
         * @param initCodeArr
         * @returns {{DateTimeFmt: DateTimeFmt, getGridColumnSysCodeFmt: getGridColumnSysCodeFmt, getCyFilterSysCodeFmt: getCyFilterSysCodeFmt, gridColumnOrgFmt: gridColumnOrgFmt, CyFilterOrgFmt: CyFilterOrgFmt, CyFilterSiteFmt: CyFilterSiteFmt, GridColumnRoadFmt: GridColumnRoadFmt, CyFilterRoadFmt: CyFilterRoadFmt, RoadJunctionFmt: RoadJunctionFmt}}
         */
        "Formatter": {
            //时间格式化
            DateTimeFmt: function (value) {
                try {
                    var miliSeconds = parseInt(value);
                    var dateNew = new Date(miliSeconds);
                    return dateNew.format("yyyy-MM-dd HH:mm:ss")
                } catch (err) {
                    console.log('DateTimeFmt');
                    console.log(err);
                } finally {
                    return value;
                }
                ;
            },

            //系统编码grid格式化
            getGridColumnSysCodeFmt: function (codeType) {
                return function (value, rowData, rowIndex) {
                   var  name = value;
                    try {
                        if (value && value != '') {
                                name = $scope.$getCodeName(codeType, value);
                                if (!name || name == '') {
                                    name = '系统编码类型[' + codeType + ']不存在值[' + value + ']';
                                }
                        }
                        else
                        {
                            name='';
                        }
                    } catch (err) {
                        console.log("getCyFilterSysCodeFmt");
                        console.log(err);
                    } finally {
                        return name;
                    };
                };
            },
            //系统编码cy-filter函数
            getCyFilterSysCodeFmt: function (codeType) {
                return function (value) {
                    var name = value;
                    try {
                        if (value && value != '') {
                                name = $scope.$getCodeName(codeType, value);
                                if (!name || name == '') {
                                    name = '系统编码类型[' + codeType + ']不存在值[' + value + ']';
                                }
                        }
                        else
                        {
                            name='';
                        }
                    } catch (err) {
                        console.log("getCyFilterSysCodeFmt");
                        console.log(err);
                    } finally {
                        return name;
                    }
                    ;
                };
            },
            //机构编码grid格式化
            gridColumnOrgFmt: function (value, rowData, rowIndex) {
                var name = value;
                try {
                    if (value && value != '') {
                        var name = $scope.$getOrgName(value);
                        if (!name || name == '') {
                            name = '机构编码[' + value + ']不存在';
                        }
                    }
                    else
                    {
                        name='';
                    }
                } catch (err) {
                    console.log("gridColumnOrgFmt");
                    console.log(err);
                } finally {
                    return name;
                }
            },

            //机构编码cy-filter函数
            CyFilterOrgFmt: function (value) {
                var name = value;
                try {
                    if (value && value != '') {
                        name = $scope.$getOrgName(value);
                        if (!name || name == '') {
                            name = '机构编码[' + value + ']不存在';
                        }
                    }
                    else
                    {
                        name='';
                    }
                } catch (err) {
                    console.log("CyFilterOrgFmt");
                    console.log(err);
                } finally {
                    return name;
                }
            },

            //点位编码cy-filter函数
            CyFilterSiteFmt: function (value) {
                return value;
                //todo
                if (!value || value == '') {
                    return '';
                }
                return value;
            },
            //道路编码grid格式化
            GridColumnRoadFmt: function (value, rowData, rowIndex) {
                var name = value;
                try {
                    if (value && value != '') {
                        name = $scope.$getRoadName(value);
                        if (!name || name == '') {
                            name = '道路编码[' + value + ']不存在';
                        }
                    }
                } catch (err) {
                    console.log("GridColumnRoadFmt");
                    console.log(err);
                } finally {
                    return name;
                }
                ;
            },

            //道路编码cy-filter函数
            CyFilterRoadFmt: function (value) {
                var name = value;
                try {
                    if (value && value != '') {
                        name = $scope.$getRoadName(value);
                        if (!name || name == '') {
                            name = '道路编码[' + value + ']不存在';
                        }
                    }
                } catch (err) {
                    console.log("GridColumnRoadFmt");
                    console.log(err);
                } finally {
                    return name;
                }
                ;
            },
            //路段编码
            RoadJunctionFmt: function (value) {
                var name = value;
                try {
//                if(value){
//                    name= $scope.$getRoadName(value);
//                    if(!name||name==''){
//                        name= '路段编码编码['+value+']不存在';
//                    }
//                }
                    //TODO
                } catch (err) {
                    console.log("RoadJunctionFmt");
                    console.log(err);
                } finally {
                    return name;
                }
                ;
            }
        },
        "HotKeyHandler": {
            _isMainKeyReady: false,
            _MainKey: null,
            _RegisterFunDic: {},
            Init: function (mainKey, FncheckStatus) {
                this._MainKey = mainKey;
                document.onkeyup = function (event) {
                    if (event.keyCode == this._MainKey) {
                        this._isMainKeyReady = false;
                        console.log("main key false");
                    }
                };
                document.onkeydown = function (event) {

                    if (FncheckStatus()) {
                        var keyCode = event.keyCode;

                        var keyValue = String.fromCharCode(event.keyCode);
                        //获取键值
                        if (this._isMainKeyReady) {
                            for (var key in this._RegisterFunDic) {
                                if (keyValue == key) {
                                    this._isMainKeyReady = null;
                                    if (this._RegisterFunDic[key] != null) {
                                        console.log("$scope.HotKeyHandler excute funtion");
                                        this._RegisterFunDic[key]();
                                    }
                                }
                            }
                        }
                        if (keyCode == this._MainKey) {
                            this._isMainKeyReady = true;
                        }
                    }
                };
            },
            Register: function (value, func) {
                if (this._RegisterFunDic[value]) {
                    console.log("已注册快捷键" + value);
                }
                else {
                    this._RegisterFunDic[value] = func;
                }
            },
            Log: function () {
                var text = '';
                for (var key in this._RegisterFunDic) {
                    text += key + ',';
                }
                console.log("注册了:" + text);
            }
        }
    });
    $scope.DefaultPlateNbr = "";
    $scope.QueryTimeRange = 7;
    $scope.$getDefaultCode($scope.CodeTypeArr);
});

$rootScope.$setData("vio", "TidyInit", function ($scope, viewClass, queryOperationName) {

    $scope.$getData("vio", "VioInit")($scope);

    $scope.HotKeyHandler.Init(16, function () {
        return $scope.$("#detailView").is(":visible");
    });
    //注册录入快捷键
    $scope.HotKeyHandler.Register('E', function () {
        console.log("entry");
        $scope.InputViolation();
    });
    //注册废弃快捷键
    $scope.HotKeyHandler.Register('C', function () {
        console.log("discard");
        $scope.DiscardViolation('02');
    });
    $scope.HotKeyHandler.Register('M', function () {
        console.log("discard");
        $scope.DiscardViolation('04');
    });
    $scope.HotKeyHandler.Register('Z', function () {
        console.log("discard");
        $scope.DiscardViolation('05');
    });
    $scope.HotKeyHandler.Register('W', function () {
        console.log("discard");
        $scope.DiscardViolation('06');
    });
    $scope.HotKeyHandler.Register('D', function () {
        console.log("discard");
        $scope.DiscardViolation('08');
    });
    $scope.HotKeyHandler.Register('T', function () {
        console.log("discard");
        $scope.DiscardViolation('21');
    });

    $scope.HotKeyHandler.Register('F', function () {
        console.log("discard");
        $scope.DiscardViolation('22');
    });
    //注册返回快捷键
    $scope.HotKeyHandler.Register('R', function () {
        console.log("return");
        $scope.selectViewFn(viewClass, 'queryView');
    });

    $scope.HotKeyHandler.Register('J', function () {
        console.log("discard");
        $scope.DiscardViolation('24');
    });

    $scope.$("#btnDiscard").menubutton({
        menu: $scope.$("#mbtnDiscard")
    });
    $scope.$("#btnDiscardBatch").menubutton({
        menu: $scope.$("#mbtnDiscardBatch")
    });

    $scope.$("#btnExport").menubutton({
        menu: $scope.$("#mbtnExport")
    });

    $.extend($scope, {
        //当前违法详细信息
        VioDetail: {},

        //缓存待处理数据
        UnConfirmViolation: {},

        //查询绑定表单
        cyFrmCond: {
            operationName: queryOperationName,
            violationBeginTime: ((new Date()).dateAdd('d', -$scope.QueryTimeRange)).format('yyyy-MM-dd HH:mm:ss'),
            violationEndTime: (new Date()).format('yyyy-MM-dd HH:mm:ss')
        },
        LoadDetailViolation: function (violation) {
            if (typeof(violation) != 'undefined' && violation) {
                $scope.PostAjax(
                        $scope.$restRoot + "violation/viewViolationOfIdWithLock",
                    {param: JSON.stringify(
                        {
                            arrayData: [violation]
                        })
                    },
                    function (data) {
                        if (data.ResultCode >= "0") {
                            //todo
                            //加载图片
                            $scope.VioDetail = data.Data[0];
                            $scope.displayDetailImage($scope.VioDetail);
                        }
                        else {
                            alert("操作失败，原因:" + data.ErrorMsg);
                        }
                    },
                    null)
            }
        },
        //选择下一条
        selectNext: function () {
            var row = $scope.$('#tbVio').datagrid('getSelected');
            if (row) {
                var index = $scope.$('#tbVio').datagrid('getRowIndex', row);
                $scope.$('#tbVio').datagrid('selectRow', index + 1);
                row = $scope.$('#tbVio').datagrid('getSelected');
                if (row) {
                    $scope.VioDetail = row;
                    $scope.$updateDom('VioDetail');
                    $scope.LoadDetailViolation($scope.VioDetail);
                }
                else {
                    $scope.$('#tbVio').datagrid('selectRow', index);
                    alert('已到最后一条');
                }
            }
            else {
                alert('已到最后一条');
            }
        },
        //选择上一条
        selectPre: function () {
            var row = $scope.$('#tbVio').datagrid('getSelected');
            if (row) {
                var index = $scope.$('#tbVio').datagrid('getRowIndex', row);
                var obj = $scope.$('#tbVio').datagrid('selectRow', index - 1);
                row = $scope.$('#tbVio').datagrid('getSelected');
                if (row) {
                    $scope.VioDetail = row;
                    $scope.$updateDom('VioDetail');
                    $scope.LoadDetailViolation($scope.VioDetail);
                }
                else {
                    $scope.$('#tbVio').datagrid('selectRow', index);
                    alert('已到第一条');
                }
            }
            else {
                alert('已到第一条');
            }
        },
        /**
         * 返回列表页面
         */
        returnToListView: function () {
            $scope.selectViewFn(viewClass, 'queryView');
            $scope.SearchAsync();
        },

        //录入有效数据
        InputViolation: function () {
            $scope.$updateData("VioDetail");
            $scope.PostAjax(
                    $scope.$restRoot + "violation/inputViolationForConfirm",
                {param: JSON.stringify(
                    {
                        arrayData: [$scope.VioDetail]
                    })
                },
                function (data) {
                    if (data.ResultCode == "0") {
                        console.log("成功录入");
                        $scope.UnShiftAndLoadNext();
                    }
                    else {
                        alert("操作失败，原因:" + data.ErrorMsg);
                    }
                }
            );
        },
        //重新加载下一条数据
        UnShiftAndLoadNext: function () {
            if ($scope.UnConfirmViolation && $scope.UnConfirmViolation.length > 0) {
                $scope.UnConfirmViolation.shift();
                $scope.VioDetail = $scope.UnConfirmViolation[0];
                $scope.$updateDom('VioDetail');
                $scope.LoadDetailViolation($scope.VioDetail);
            }
            else {
                $scope.SearchAsync();
            }
        },

        /**
         * 收起剩余搜索项
         * @return {[type]} [description]
         */
        searchSlideUp: function () {
            $scope.$(".hide").hide();
            $scope.$("#queryView").layout("panel", "north").panel("resize", {
                height: 115
            });
            $scope.$("#queryView").layout("resize");
            $scope.$("#searchSlideUp").hide();
            $scope.$("#searchSlideDown").show().after($scope.$("#searchButton")).after(" ");
        },
        /**
         * 展开剩余搜索项
         * @return {[type]} [description]
         */
        searchSlideDown: function () {
            $scope.$(".hide").slideDown("fast");
            $scope.$("#queryView").layout("panel", "north").panel("resize", {
                height: 165
            });
            $scope.$("#queryView").layout("resize");
            $scope.$("#searchSlideDown").hide();
            $scope.$("#searchSlideUp").show().after($scope.$("#searchButton"));
        },

        //列表查询
        SearchAsync: function () {
            var Msg = $scope.CheckCondition();
            if (!Msg) {
                $scope.$updateData("cyFrmCond");
                $scope.$search("cyFrmCond", "", function (data) {
                    if (data && data != null&&data.rows.length>0) {
                        $scope.$('#tbVio').datagrid('selectRow', 0);
                        $scope.UnConfirmViolation = data.rows;
                        $scope.displayListImage($scope.$('#tbVio').datagrid('getSelected'));
                    }
                    else {
                        alert("查无数据");
                        $scope.VioDetail = {};
                        $scope.$updateDom('VioDetail');
                    };
                });
            }
            else {
                alert(Msg);
            }
        },
        SelectExport: function () {
            $scope.PostAjax(
                    $scope.$restRoot + "violation/exportViolationSet",
                {param: JSON.stringify(
                    {
                        arrayData: $scope.$('#tbVio').datagrid('getChecked')
                    })
                },
                function (data) {
                    if (data.error && data.error != '') {
                        alert(data.error);
                    }
                    else {
                        $scope.$setParam("url", data.zipFilePath);
                        $scope.$openDialog("addDialog", {
                            title: "下载违法信息",
                            width: 300,
                            height: 80,
                            href: "tpls/violationMgr/DownLoad.html"
                        }, true);
                    }
                }
            )
        },
        AllExport: function () {
            $scope.PostAjax(
                    $scope.$restRoot + "violation/exportViolationSet",
                {param: JSON.stringify(
                    {
                        opCondition: $scope.cyFrmCond
                    })
                },
                function (data) {
                    if (data.error && data.error != '') {
                        alert(data.error);
                    }
                    else {
                        $scope.$setParam("url", data.zipFilePath);
                        $scope.$openDialog("addDialog", {
                            title: "下载违法信息",
                            width: 300,
                            height: 80,
                            href: "tpls/violationMgr/DownLoad.html"
                        }, true);
                    }
                }
            )

        },
        /**
         * 详细页面废弃数据
         * @param discardReaso
         * @constructor
         */
        DiscardViolation: function (discardReaso) {
            $scope.$updateData("VioDetail");
            $scope.PostAjax(
                    $scope.$restRoot + "violation/abandonViolation",
                {param: JSON.stringify(
                    {
                        arrayData: [$scope.VioDetail],
                        opField: {
                            discardedReason: discardReaso
                        }
                    }
                )},
                function (data) {
                    if (data.ResultCode == "0") {
                        console.log("成功废弃");
                        $scope.UnShiftAndLoadNext();
                    }
                },
                function (XMLHttpRequest, textStatus, errorThrown) {
                    //TODO
                }
            );
        },
        /**
         * 选择废弃数据
         * @constructor
         */
        SelectDiscard: function () {
            $scope.$("#tbVio").datagrid("loading");
            $scope.PostAjax(
                    $scope.$restRoot + "violation/abandonViolation",
                {
                    param: JSON.stringify(
                        {    arrayData: $scope.$('#tbVio').datagrid('getChecked'),
                            opField: {
                                specialVehType: '',
                                discardedReason: '08'
                            }
                        }
                    )

                },
                function (data) {
                    $scope.$("#tbVio").datagrid("loaded");
                    if (data.ErrorMsg && data.ErrorMsg != '') {
                        alert(data.ErrorMsg);
                    }
                    else {
                        console.log('选择废弃成功');
                        $scope.SearchAsync();
                    }
                }
            )
        },
        /**
         * 根据条件全部废弃
         * @constructor
         */
        AllDiscard: function () {

            $scope.$updateData("cyFrmCond");
            $scope.$("#tbVio").datagrid("loading");
            $scope.PostAjax(
                    $scope.$restRoot + "violation/abandonViolation",
                {param: JSON.stringify(
                    {
                        opCondition: $scope.cyFrmCond,
                        opField: {
                            specialVehType: '',
                            discardedReason: '08'
                        }
                    })
                },
                function (data) {
                    $scope.$("#tbVio").datagrid("loaded");
                    if (data.ErrorMsg && data.ErrorMsg != '') {
                        alert(data.ErrorMsg);
                    }
                    else {
                        console.log('全部废弃成功');
                        $scope.SearchAsync();
                    }
                }
            )
        },
        CheckCondition: function () {
            $scope.cyFrmCond.ViolationTimeBegin
            return "";
        },
        showMessage: function (msg) {
            $.messager.show({
                title: '巡航提示',
                msg: msg,
                timeout: 3 * 1000,
                showType: 'slide',
                style: {
                    right: '',
                    top: 220,
                    bottom: ''
                }
            });
        },
        alertMessage:function(msg){

        }
    });
});