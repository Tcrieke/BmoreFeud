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

app.directive( 'goClick', function ( $location ) {
    return function ( scope, element, attrs ) {
        var path;

        attrs.$observe( 'goClick', function (val) {
            path = val;
        });

        element.bind( 'click', function () {
            scope.$apply( function () {
                $location.path( path );
            });
        });
    };
});

app.controller('JudgeFastMoney', ['$scope', '$http', '$timeout', function($scope, $http, $timeout) {

    $scope.title = "Bmore Fun (Admin)";
    $scope.incorrectCount=0;
    $scope.numberOfFields = 10;
    $scope.incorrectCountXs = [];
    $scope.gameboard = {};

    $scope.player1 = true;
    $scope.player2 = true;
    $scope.currentPlayer = null;
    $scope.answerEditIndex=null;
    $scope.selectedCategoryIndex=0;


    $scope.answers = [];
    $scope.categories = [];


    $scope.blankText = "***************";

    $scope.answerFields = [];

    var updateGameboard = function(gameboard) {
        if($scope.answerEditIndex==null) {
            $scope.gameboard = gameboard;


            $scope.currentPlayer = gameboard.currentPlayer;
            $scope.categories = $scope.currentPlayer.categories;
            $scope.answers = $scope.currentPlayer.answers;
            $scope.selectedCategoryIndex = $scope.gameboard.selectedCategoryIndex;

            $scope.player1 = gameboard.player1.visible;
            $scope.player2 = gameboard.player2.visible;



            for (var i = 0; i < $scope.answers.length; i++) {
                if (!$scope["answer" + i + "Edit"]) {
                    $scope.answerFields[i] = $scope.answers[i].answer;
                }
            }
        }
    };

    var setPlayersEnabled = function() {
        $http.get("/bmorefeud/fastmoney_admin/enable_player.json?player1="+$scope.player1+"&player2="+$scope.player2).success(function (response) {
            updateGameboard(response.gameboard);
        });
    };

    $scope.flipField = function(field,category) {
        $http.get("/bmorefeud/fastmoney_admin/flip.json?category="+encodeURIComponent(category.id)+"&value="+encodeURIComponent(field.value)+"&visible="+(!field.visible)).success(function (response) {
            updateGameboard(response.gameboard);
        });
    };

    $scope.editAnswer = function(index,edit) {
        $scope["answer"+index+"Edit"] = edit;
        $scope["answer"+index+"EditFocus"] = edit;
        if(edit) {
            $scope.answerEditIndex =index;
            document.getElementById("answer"+index+"Input").focus();
        } else {
            $scope.answerEditIndex =null;
        }
    };

    $scope.onAnswerChange = function() {
        var index = $scope.answerEditIndex;
        var answerEl = document.getElementById("answer"+index+"Input");
        if(answerEl && answerEl.value) {
            var answer = answerEl.value;

            $http.get("/bmorefeud/fastmoney_admin/update_answer.json?answer=" + index + "&value=" + encodeURIComponent(answer)).success(function (response) {
                updateGameboard(response.gameboard);
            });
            $scope.editAnswer(index, false);
            $scope.$apply();
        } else {
            for (var i = 0; i < $scope.answerFields.length; i++) {
                $scope.editAnswer(i, false);
            }
        }
    };

    $scope.blurField = function(field) {
        field.blur();
    }

    var getGameboard = function() {
        $http.get("/bmorefeud/fastmoney").success(function (response) {
            updateGameboard(response.gameboard);
        });
        $timeout(getGameboard, 3001);
    };
    getGameboard();

    $scope.togglePlayer = function(player) {
        if(player==1) {
            $scope.player1 = !$scope.player1;
        } else if(player==2) {
            $scope.player2 = !$scope.player2;
        }
        setPlayersEnabled();
    };

    $scope.toggleCurrentPlayer = function(player) {
        $http.get("/bmorefeud/fastmoney_admin/current_player.json?player="+player).success(function (response) {
            updateGameboard(response.gameboard);
        });
    };

    $scope.selectCategory = function(index) {
        $http.get("/bmorefeud/fastmoney_admin/select_category.json?categoryIndex="+index).success(function (response) {
            updateGameboard(response.gameboard);
        });
    };

    $scope.toggleAnswerVisible = function(index) {
        $http.get("/bmorefeud/fastmoney_admin/update_answer.json?answer=" + index + "&visible=" + !$scope.answers[index].visible).success(function (response) {
            updateGameboard(response.gameboard);
        });
    };

    $scope.toggleAnswerValueVisible = function(index) {
        $http.get("/bmorefeud/fastmoney_admin/update_answer.json?answer=" + index + "&valueVisible=" + !$scope.answers[index].valueVisible).success(function (response) {
            updateGameboard(response.gameboard);
        });
    };

    $scope.newGame = function() {
        $http.get("/bmorefeud/fastmoney_admin/new").success(function (response) {
            updateGameboard(response.gameboard);
        });
    };

    $scope.blurField = function(field) {
        field.blur();
    };

    $scope.toFamilyFeud = function() {
        $http.get("/bmorefeud/fastmoney_admin/family_feud").success(function (response) {
            window.location = '/bmorefeud/judge.jsp';
        });
    };
}]);


