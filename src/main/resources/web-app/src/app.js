angular.module("app", [
  "ngRoute",
  "angular-loading-bar",
  "ngAnimate",
  "ngResource",
  "ui.grid",
  "ui.grid.edit",
  "ui.grid.selection",
  "ui.grid.rowEdit",
  "ui.grid.cellNav"])
  .config(["$routeProvider", function ($routeProvider) {
    var rootFolder = "src/app/";

    $routeProvider
      .when("/user", {
        templateUrl: rootFolder + "views/grid.html",
        controller: "GridCtrl"
      })
      .when("/administrator", {
        templateUrl: rootFolder + "views/grid.html",
        controller: "GridCtrl"
      })
      .when("/configuration", {
        templateUrl: rootFolder + "views/configuration.html",
        controller: "ConfigurationCtrl"
      })
      .otherwise({
        redirectTo: "/user"
      });
  }])

  .factory("CurPageService", ["$rootScope", "$location", function ($rootScope, $location) {
    return {
      isAdministrator: function () {
        return $location.path().substring(1) === "administrator"
      },
      isUser: function () {
        return $location.path().substring(1) === "user"
      },
      isConfiguration: function () {
        return $location.path().substring(1) === "configuration"
      }
    };
  }])

  .controller("AppController", ["$scope", "$interval", "CurPageService",
    function ($scope, $interval, CurPageService) {

      $scope.curPage = CurPageService;
      $scope.intervals = [];
      $scope.watchInterval = function (interval) {$scope.intervals.push(interval);}

      $scope.cancelPrevIntervals = function () {
        _.forEach($scope.intervals, function (interval) {
          $interval.cancel(interval);
        });
        $scope.intervals = [];
      };

      $scope.config = {
        maxCpu: 10,
        refreshRate: 1000
      }
    }]);