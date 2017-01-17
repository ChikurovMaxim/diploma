var app = angular.module('server', ['ngTable','ngResource', 'ui.bootstrap']);

app.controller('serverController', function ($scope, isLogIn, logOut,
             $window, getAllPlains, getAllUsersRecord,NgTableParams,loginProc) {

    $scope.records = this;
    $scope.recordsData =
        [{id: 1, user: {name: "User1"}, date: "2016-11-12", PLAIN_MODEL: {name: "Plain"}},
        {id: 2, user: {name: "User1"}, date: "2016-11-13", PLAIN_MODEL: {name: "Plain"}}];
    $scope.records.tableParams = new NgTableParams({}, {dataset: $scope.recordsData});
    $scope.pass = null;
    $scope.login = null;
    $scope.isLogedInP = false;


    $scope.loginFunc = function () {
        $scope.proceed = loginProc.save({login:$scope.login},$scope.pass).$promise.then(
            function () {
                $scope.isLogedInP = true;
            },
            function () {
                $scope.alerts = [
                    {type: 'success', msg: 'Sorry, you are not authorized!Check your login and password'}
                ];
                $scope.isLogedInP = false;
            });
    };

    isLogedIn();
    function isLogedIn(){
        if($scope.isLogedInP) {
            $scope.logedPerson = isLogIn.get();
            if ($scope.logedPerson.$resolved !== false)
                $scope.isLogedInP = true;
        }
    }


    $scope.logOutF = function (){
        logOut.get();
        $scope.isLogedInP = false;
    };


    $scope.getUsersRecords = function(){
        $scope.recordsGetData = getAllUsersRecord.query({id: $scope.logedPerson.id}).$promise.then(
            function () {
                $scope.userSelected = true;
            }
        )
    };

    $scope.plains = this;
    $scope.plainData = getAllPlains.query();
    $scope.plains.tableParams = new NgTableParams({}, {dataset: $scope.plainData});
});
app.factory('isLogIn',function($resource){
    return $resource('resources/server/logedIn/');
});
app.factory('getAllUsersRecord',function($resource){
    return $resource('resources/server/get-all-user-records/:id');
});
app.factory('getAllPlains',function($resource){
    return $resource('resources/server/get-plains');
});
app.factory('loginProc', function ($resource) {
    return $resource('resources/login/:login')
});
app.factory('logOut',function($resource){
    return $resource('resources/server/logout/');
});



