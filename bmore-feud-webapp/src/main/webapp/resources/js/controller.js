'use strict';

var app = angular.module('app', []);

//app.config(function($routeProvider) {
//    $routeProvider
//
//        // route for the metrics page
//        .when('/board', {
//            templateUrl : 'resources/js/gameboard/gameboard.html',
//            controller  : 'Gameboard'
//        })
//
//
//        .otherwise({
//            redirectTo: '/board'
//        });
//});

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