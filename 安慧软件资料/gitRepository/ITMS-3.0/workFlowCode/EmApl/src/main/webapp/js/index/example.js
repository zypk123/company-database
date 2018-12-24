angular.module('approveApp', ['ngAnimate', 'ui.bootstrap']);
angular.module('approveApp').controller('mainPage', function ($scope) {
  $scope.sourceUrl = 'html/approveExamine/applicationForm.html';
  $scope.status = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  $scope.selectRestaurant=function(row,url) {
       $scope.rowIndex=row;
       $scope.sourceUrl=url;
  };
});