
app.controller('menu', ['$scope', '$location', function ($scope, $location) {
    $scope.navClass = function (page) {
        var currentRoute = $location.path().substring(1) || 'server';
        return page === currentRoute ? 'active' : '';
    };

}]);

