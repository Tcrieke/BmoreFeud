'use strict';

var app = angular.module('app', ['ngRoute','door3.css']);

app.config(function($routeProvider) {
    $routeProvider

        .when('/board', {
            templateUrl : 'resources/js/gameboard/gameboard.html',
            controller  : 'Gameboard',
            css: ['resources/css/gameboard.css']
        })

        .when('/fastmoney', {
            templateUrl : 'resources/js/fastmoney/fastmoney.html',
            controller  : 'FastMoney',
            css: ['resources/css/fastmoney.css']
        })

        //Removed and added to its own jsp so spring security can manage it
        //
        //.when('/judge', {
        //    templateUrl : 'resources/js/judge/judge.html',
        //    controller  : 'Judge',
        //    css: ['resources/css/judge.css']
        //})

        .when('/team1', {
            templateUrl : 'resources/js/scoreboard/team1scoreboard.html',
            controller  : 'Scoreboard',
            css: ['resources/css/scoreboard.css']
        })

        .when('/team2', {
            templateUrl : 'resources/js/scoreboard/team2scoreboard.html',
            controller  : 'Scoreboard',
            css: ['resources/css/scoreboard.css']
        })

        .when('/host', {
            templateUrl : 'resources/js/host/host.html',
            controller  : 'Host',
            css: ['resources/css/host.css']
        })

        .when('/vote', {
            templateUrl : 'resources/js/vote/vote.html',
            controller  : 'Vote',
            css: ['resources/css/vote.css']
        })


        .otherwise({
            redirectTo: '/board'
        });

});

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