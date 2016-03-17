app.controller('FastMoney', ['$scope', '$http', '$timeout', function($scope, $http, $timeout) {

    $scope.title = "Bmore Feud (Fast Money)";

    $scope.showX = false;
    $scope.numberOfRows = 5;
    $scope.numberOfColumns = 1;
    $scope.roundScore = 0;
    $scope.player = 1;

    $scope.blankText = "***************";

    $scope.valueRows = [];
    for(var i = 0; i < $scope.numberOfRows;i++) {
        $scope.valueRows[i] = {};
        $scope.valueRows[i].valueFields = [];
        for(var j =0; j<$scope.numberOfColumns;j++) {
            var col = i+(j*($scope.numberOfRows));
            $scope.valueRows[i].valueFields.push({number: col+1, active: false, visible: false, value: ""});
        }
    }

    var getGameboard = function() {
        $http.get("/bmorefeud/fastmoney").success(function (response) {
            var gameboard = response.gameboard;
            $scope.player = response.player;
            var responses = gameboard.responses[$scope.player-1];

            var playSound = false;

            $scope.roundScore = gameboard.roundScore;

            for(var i = 0; i < $scope.valueRows.length;i++) {
                for(var  j = 0; j<$scope.numberOfColumns;j++) {
                    var col = i+(j*($scope.numberOfRows));
                    var field = $scope.valueRows[i].valueFields[j];
                    var response = responses[col];
                    if(!field.active && response.active) {
                        if(response.value=='') {
                            playIncorrectSound();
                        }
                        playSound = true;
                    } else if(!field.visible && value.visible) {
                        if(response.points>0) {
                            playCorrectSound();
                        } else {
                            playIncorrectSound();
                        }
                    }

                    field.active=response.active;
                    field.visible=response.visible;
                    field.value=response.value;
                    field.points=response.points;
                }
            }
        });
        $timeout(getGameboard, 501);
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
}]);

