app.controller('FastMoney', ['$scope', '$http', '$timeout', '$location', function($scope, $http, $timeout, $location) {

    $scope.title = "Bmore Feud (Fast Money)";

    $scope.numberOfRows = 5;
    $scope.numberOfColumns = 2;
    $scope.totalScore = 0;
    $scope.player1 = true;
    $scope.player2 = true;
    $scope.active = true;

    $scope.blankText = "***************";

    $scope.answerRows = [];
    for(var i = 0; i < $scope.numberOfRows;i++) {
        $scope.answerRows[i] = {};
        $scope.answerRows[i].answerFields = [];
        for(var j =0; j<$scope.numberOfColumns;j++) {
            var col = (i*$scope.numberOfColumns)+j;
            $scope.answerRows[i].answerFields.push({number: col+1, visible: false, valueVisible: false, answer: "", points: 0});
        }
    }

    var getGameboard = function() {
        var onPage = ($location.path()=="/fastmoney");

        if(onPage) {
            $http.get("/bmorefeud/fastmoney").success(function (response) {
                var gameboard = response.gameboard;

                if (gameboard.active === false) {
                    $scope.active = false;
                    window.location = '/bmorefeud/#/board';
                    return;
                }

                $scope.totalScore = gameboard.totalScore;
                $scope.player1 = gameboard.player1.visible;
                $scope.player2 = gameboard.player2.visible;

                for (var i = 0; i < $scope.answerRows.length; i++) {
                    for (var j = 0; j < $scope.numberOfColumns; j++) {
                        var field = $scope.answerRows[i].answerFields[j];
                        var answer = (j == 0 ? gameboard.player1 : gameboard.player2).answers[i];
                        if (!field.valueVisible && answer.valueVisible) {
                            if (answer.points == 0) {
                                playIncorrectSound();
                            } else {
                                playCorrectSound();
                            }
                        }

                        field.visible = answer.visible;
                        field.valueVisible = answer.valueVisible;
                        field.answer = answer.answer;
                        field.points = answer.points;
                    }
                }
            });
        }

        $timeout(getGameboard, (onPage?501:2000));
    };

    var incorrectSound = null;

    var playIncorrectSound = function() {
        if(!incorrectSound) {
            incorrectSound = soundManager.createSound({
                url: 'resources/sounds/IncorrectBuzzer.mp3'
            });
        }
        incorrectSound.play();
    }

    var correctSound = null;

    var playCorrectSound = function() {
        if(!correctSound) {
            correctSound = soundManager.createSound({
                url: 'resources/sounds/correctAnswer.mp3'
            });
        }
        correctSound.play();
    }

    $scope.showField = function(answerField) {
        if(answerField.number%2==0) {
            return $scope.player2;
        } else {
            return $scope.player1;
        }
    }

    getGameboard();
}]);

