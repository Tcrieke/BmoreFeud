app.controller('Vote', ['$scope', '$http', '$timeout', function($scope, $http, $timeout) {

    $scope.poll = [];
    $scope.vote = 0;

    var getPoll = function() {
        $http.get("/bmorefeud/audience/poll.json").success(function (response) {
            $scope.poll = response.poll;
            $scope.vote = response.vote;
        });
        $timeout(getPoll, 3001);
    };
    getPoll();

    $scope.voteFor = function(id) {
        $http.get("/bmorefeud/audience/vote.json?category_id="+id).success(function (response) {
            $scope.poll = response.poll;
            $scope.vote = response.vote;
        });
    };


}]);


