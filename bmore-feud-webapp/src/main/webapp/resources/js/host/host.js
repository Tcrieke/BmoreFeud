app.controller('Host', ['$scope', '$http', '$timeout', '$location', function($scope, $http, $timeout, $location) {
    $scope.category = null;
    $scope.isFastMoney = true;

    var getCategory = function() {
        var onPage = ($location.path()=="/host");

        if(onPage) {
            $http.get("/bmorefeud/host/category").success(function (response) {
                updateCategory(response.category);
                $scope.isFastMoney = response.isFastMoney;
            });
        }
        $timeout(getCategory, 2001);
    };
    getCategory();

    $scope.nextCategory = function(team) {
        $http.get("/bmorefeud/host/next").success(function (response) {
            updateCategory(response.category);
        });
    };

    var updateCategory = function(category) {
        $scope.category = category;
    };
}]);
