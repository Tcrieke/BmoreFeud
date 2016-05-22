'use strict';

var app = angular.module('app', []);

app.controller('Banner', function($scope) {
    $scope.title = "No Title";
});

app.directive('focusMe', function($timeout) {
    return {
        link: function(scope, element, attrs) {
            scope.$watch(attrs.focusMe, function(value) {
                if(value === true) {
                    console.log('value=',value);
                    //$timeout(function() {
                    element[0].focus();
                    scope[attrs.focusMe] = false;
                    //});
                }
            });
        }
    };
});

app.directive('ngEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if(event.which === 13) {
                element[0].blur();
                event.preventDefault();
            }
        });
    };
});

app.controller('Judge', ['$scope', '$http', '$timeout', function($scope, $http, $timeout) {

    $scope.title = "Bmore Fun (Admin)";
    $scope.incorrectCount=0;
    $scope.numberOfFields = 10;
    $scope.incorrectCountXs = [];
    $scope.gameboard = {};

    $scope.team1Name="Team 1";
    $scope.team2Name="Team 2";
    $scope.team1Score=0;
    $scope.team2Score=0;

    $scope.team1NameEdit = false;
    $scope.team1ScoreEdit = false;
    $scope.team2NameEdit = false;
    $scope.team2ScoreEdit = false;

    $scope.team1NameEditFocus = false;
    $scope.team1ScoreEditFocus = false;
    $scope.team2NameEditFocus = false;
    $scope.team2ScoreEditFocus = false;


    $scope.blankText = "***************";

    $scope.valueFields = [];
    for(var i = 0; i < $scope.numberOfFields;i++) {
        $scope.valueFields[i] = {active: false, visible: false, value: "", accepted:[]};
    }

    var updateGameboard = function(gameboard) {
        $scope.gameboard = gameboard;
        var category = gameboard.category;
        var values = category.values;

        for(var i = 0; i < $scope.numberOfFields;i++) {
            var field = $scope.valueFields[i];
            if(i<values.length) {
                var value = values[i];
                field.active=true;
                field.visible=value.visible;
                field.value=value.value;
                field.points=value.points;
                field.accepted=value.accepted;
            } else {
                field.visible=false;
                field.active=false;
                field.value="";
                field.accepted=[];
            }
        }

        if(!$scope.team1NameEdit) {
            $scope.team1Name = gameboard.team1Name;
        }
        if(!$scope.team2NameEdit) {
            $scope.team2Name = gameboard.team2Name;
        }

        if(!$scope.team1ScoreEdit) {
            $scope.team1Score = gameboard.team1Score;
        }
        if(!$scope.team2ScoreEdit) {
            $scope.team2Score = gameboard.team2Score;
        }

        $scope.incorrectCountXs=[];
        for(var i = 0; i < gameboard.incorrectCount;i++) {
            $scope.incorrectCountXs.push(i);
        }
        $scope.incorrectCount=gameboard.incorrectCount;
    };

    $scope.flipField = function(field) {
        $http.get("/bmorefeud/admin/flip.json?value="+encodeURIComponent(field.value)+"&visible="+(!field.visible)).success(function (response) {
            updateGameboard(response.gameboard);
        });
    };

    $scope.subIncorrect = function() {
        if (!$scope.incorrectEmpty()) {
            $http.get("/bmorefeud/admin/subincorrect").success(function (response) {
                updateGameboard(response.gameboard);
            });
        }
    };

    $scope.markIncorrect = function(quiet) {
        if(!$scope.incorrectFull()) {
            $http.get("/bmorefeud/admin/incorrect.json" + (quiet ? "?quiet=" + quiet : '')).success(function (response) {
                updateGameboard(response.gameboard);
            });
        }
    };

    var getGameboard = function() {
        $http.get("/bmorefeud/gameboard").success(function (response) {
            updateGameboard(response.gameboard);
        });
        $timeout(getGameboard, 3001);
    };
    getGameboard();


    $scope.incorrectEmpty = function() {
        return $scope.incorrectCount<1;
    }

    $scope.incorrectFull = function() {
        return $scope.incorrectCount>2;
    }

    $scope.onTeamNameChange = function(team) {
        var name = document.getElementById("team"+team+"NameInput").value;
        $http.get("/bmorefeud/admin/update_name.json?team=" +team+"&name="+encodeURIComponent(name)).success(function (response) {
            updateGameboard(response.gameboard);
        });
        $scope.editTeamName(team,false);
        $scope.$apply();
    };

    $scope.onTeamScoreChange = function(team) {
        var score = document.getElementById("team"+team+"ScoreInput").value;
        $http.get("/bmorefeud/admin/update_score.json?team=" +team+"&score="+encodeURIComponent(score)).success(function (response) {
            updateGameboard(response.gameboard);
        });
        $scope.editTeamScore(team,false);
        $scope.$apply();
    };

    $scope.editTeamName = function(team,edit) {
        $scope["team"+team+"NameEdit"] = edit;
        $scope["team"+team+"NameEditFocus"] = edit;
    };

    $scope.editTeamScore = function(team,edit) {
        $scope["team"+team+"ScoreEdit"] = edit;
        $scope["team"+team+"ScoreEditFocus"] = edit;
    };

    $scope.blurField = function(field) {
        field.blur();
    }

    $scope.setTeamPlaying = function(team) {
        $http.get("/bmorefeud/admin/playing?team="+team).success(function (response) {
            updateGameboard(response.gameboard);
        });
    };

    $scope.newCategory = function() {
        $http.get("/bmorefeud/admin/new").success(function (response) {
            updateGameboard(response.gameboard);
        });
    };

    $scope.toFastMoney = function() {
        $http.get("/bmorefeud/admin/fast_money").success(function (response) {
            window.location = '/bmorefeud/judge-fastmoney.jsp';
        });
    };

}]);


