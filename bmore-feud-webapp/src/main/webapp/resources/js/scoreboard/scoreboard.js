app.controller('Scoreboard', ['$scope', '$http', '$timeout', function($scope, $http, $timeout) {
    $scope.gameboard = null;

    var getGameboard = function() {
        $http.get("/bmorefeud/gameboard").success(function (response) {
            $scope.gameboard = response.gameboard;
        });
        $timeout(getGameboard, 2001);
    };
    getGameboard();
}]);


