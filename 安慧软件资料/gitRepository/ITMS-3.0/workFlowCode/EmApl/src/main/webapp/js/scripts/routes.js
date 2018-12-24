define([], function () {
    return {
        defaultRoute: '/homePage',
        routes: {
            'deployFlow': {
                templateUrl: 'html/approveExamine/deployListProcess.html',
                url: '/deployFlow',
                dependencies: ['controller/deployFlowController'],
                allowAnonymous: true
            },
            'deviceApprove': {
                templateUrl: 'html/approveExamine/applicationForm.html',
                url: '/deviceApprove',
                dependencies: ['controller/deviceApproveController'],
                allowAnonymous: true
            },
            'treeView': {
                templateUrl: 'html/common/departmentTree.html',
                url: '/treeView',
                dependencies: ['controller/departmentTreeController','directive/zTree'],
                allowAnonymous: true
            },
            'signApprove': {
                templateUrl: 'html/approveExamine/taskClaim.html',
                url: '/signApprove',
                dependencies: ['controller/signApproveController'],
                allowAnonymous: true
            },
            'checkApprove':{
            	 templateUrl: 'html/approveExamine/taskCheck.html',
                 url: '/checkApprove',
                 dependencies: ['controller/checkApproveController'],
                 allowAnonymous: true
            },
            'sysManage':{
                templateUrl: 'html/sysFunc/sysManage.html',
                url: '/sysManage',
                dependencies: ['controller/sysManageController/sysManageController','directive/zTree'],
                allowAnonymous: true
            },
            'orgManage':{
                templateUrl: 'html/sysFunc/orgManage.html',
                url: '/orgManage',
                dependencies: ['controller/sysManageController/orgManageController','directive/zTree'],
                allowAnonymous: true
            },
            'appFormFlow':{
                templateUrl: 'html/appFormManage/appFormFlow.html',
                url: '/appFormFlow',
                dependencies: ['controller/appFormManageController/appFormFlowController'],
                allowAnonymous: true
            },
            'appFormMonitor':{
                templateUrl: 'html/appFormManage/appFormMonitor.html',
                url: '/appFormMonitor',
                dependencies: ['controller/appFormManageController/appFormMonitorController'],
                allowAnonymous: true
            },
            'homePage':{
                templateUrl: 'html/sysFunc/homePage.html',
                url: '/homePage',
                dependencies: ['controller/sysManageController/homePageController'],
                allowAnonymous: true
            },
            'sysNotice':{
                templateUrl: 'html/sysFunc/sysNotice.html',
                url: '/sysNotice',
                dependencies: ['controller/sysManageController/sysNoticeController'],
                allowAnonymous: true
            },
            'historyRecords':{
                templateUrl: 'html/appFormManage/historyRecords.html',
                url: '/historyRecords',
                dependencies: ['controller/appFormManageController/historyRecordsContoller'],
                allowAnonymous: true
            },
            'sysApproveForm':{
                templateUrl: 'html/sysApproveManage/sysApproveForm.html',
                url: '/sysApproveForm',
                dependencies: ['controller/sysApproveManageController/sysApproveFormController'],
                allowAnonymous: true
            },
             'sysApproveCompleteForm':{
                templateUrl: 'html/sysApproveManage/sysApproveCompleteForm.html',
                url: '/sysApproveCompleteForm',
                dependencies: ['controller/sysApproveManageController/sysApproveCompleteFormController'],
                allowAnonymous: true
            },
            'newSysApprove':{
                templateUrl: 'html/sysApproveManage/newSysApprove.html',
                url: '/newSysApprove',
                dependencies: ['controller/sysApproveManageController/newSysApproveController','directive/zTree'],
                allowAnonymous: true
            },
             'sysHistoryRecords':{
                templateUrl: 'html/appFormManage/sysHistoryRecords.html',
                url: '/sysHistoryRecords',
                dependencies: ['controller/appFormManageController/sysHistoryRecordsController'],
                allowAnonymous: true
            },
             'groupChkHistory':{
                templateUrl: 'html/appFormManage/groupChkHistory.html',
                url: '/groupChkHistory',
                dependencies: ['controller/appFormManageController/groupChkHistoryController'],
                allowAnonymous: true
            },
             'newSysCheckForm':{
                templateUrl: 'html/sysApproveManage/newSysCheckForm.html',
                url: '/newSysCheckForm',
                dependencies: ['controller/sysApproveManageController/newSysCheckFormController'],
                allowAnonymous: true
            }


        }
    };
});