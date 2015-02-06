(function () {
    "use strict";
    var app = angular.module('mockyVote', ['ngRoute', 'ngResource']);

    app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/result', {
            templateUrl: 'views/result.html',
            controller: 'ResultController'
        })
            .when('/', {
                templateUrl: 'views/postcode.html',
                controller: 'PostCodeController'
            })
            .when('/confirm/:postCode', {
                templateUrl: 'views/confirm.html',
                controller: 'ConfirmController'
            })
            .when('/candidate/:constituencyId', {
                templateUrl: 'views/candidate.html',
                controller: 'CandidateController'
            })
            .when('/thanks', {
                templateUrl: 'views/thanks.html',
                controller: 'ThanksController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]);

    app.service('voteService', function () {
        var data = {};
        return {
            data: function () {
                return data;
            }
        };
    });

    app.controller('PostCodeController', function ($scope, $location) {
        $scope.go = function () {
            $location.path("confirm/" + $scope.postCode);
        };
    });
    app.controller('ConfirmController', function ($scope, $location, $resource, $routeParams) {
        var postCode = $routeParams.postCode,
            ConstituencySearch = $resource('constituency/postcode/:postCode'),
            ConstituencyNotVoting = $resource('constituency/:id/notvoting');
        if (postCode === undefined) {
            $location.path('/');
        }
        $scope.constituency = ConstituencySearch.get({postCode: postCode});
        $scope.no = function () {
            ConstituencyNotVoting.save({id: $scope.constituency.id}, {}, function () {
                $location.path("thanks");
            });
        };
        $scope.yes = function () {
            $location.path("candidate/" + $scope.constituency.id);
        };
    });
    app.controller('CandidateController', function ($scope, $resource, $location, $routeParams) {
        var constituencyId = $routeParams.constituencyId,
            Constituency = $resource('constituency/:id'),
            CandidateVote = $resource('/candidate/:id/vote');

        $scope.constituency = Constituency.get({id: constituencyId});
        $scope.vote = function (id) {
            CandidateVote.save({id: id}, {}, function () {
                $location.path("thanks");
            });
        };
    });
    app.controller('ThanksController', function ($scope, $location) {
        $scope.view = function () {
            $location.path('result');
        };
    });
    app.controller('ResultController', function ($scope, $resource) {
        var Constituency = $resource('constituency');
        $scope.constituencies = Constituency.query();
    });
}());
