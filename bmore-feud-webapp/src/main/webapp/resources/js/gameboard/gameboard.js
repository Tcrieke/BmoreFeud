app.controller('Gameboard', ['$scope', '$http', '$timeout', function($scope, $http, $timeout) {

    $scope.title = "Bmore Fun";
    $scope.incorrectValue=0;
    $scope.incorrectCount=0;
    $scope.showX = false;
    $scope.numberOfRows = 4;
    $scope.numberOfColumns = 2;
    $scope.incorrectCountXs = [];
    $scope.roundScore = 0;

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
        $http.get("/bmorefeud/gameboard").success(function (response) {
            var gameboard = response.gameboard;
            var category = gameboard.category;

            var values = category.values;

            var playSound = false;

            $scope.roundScore = gameboard.roundScore;

            for(var i = 0; i < $scope.valueRows.length;i++) {
                for(var  j = 0; j<$scope.numberOfColumns;j++) {
                    var col = i+(j*($scope.numberOfRows));
                    var field = $scope.valueRows[i].valueFields[j];
                    if(col<values.length) {
                        var value = values[col];
                        field.active=true;
                        if(!field.visible && value.visible) {
                            playSound = true;
                        }
                        field.visible=value.visible;
                        field.value=value.value;
                        field.points=value.points;
                    } else {
                        field.visible=false;
                        field.active=false;
                        field.value="";
                    }
                }
            }

            if(playSound) {
                playCorrectSound();
            }

            if($scope.incorrectValue!=gameboard.incorrectValue) {
                if(gameboard.gameMode=='REBUTTAL') {
                    gameboard.incorrectCount=3;
                } else if (gameboard.gameMode=='ROUNDOVER'){
                    gameboard.incorrectCount=1;
                }
                $scope.incorrectCountXs=[];
                for(var i = 0; i < gameboard.incorrectCount;i++) {
                    $scope.incorrectCountXs.push(i);
                }
                if($scope.incorrectCountXs.length>0) {
                    $scope.showX = true;
                    playIncorrectSound();
                    $timeout(function () {
                        $scope.showX = false;
                    }, 2500);
                }
            }
            $scope.incorrectValue=gameboard.incorrectValue;
        });
        $timeout(getGameboard, 501);
    };
    getGameboard();

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


