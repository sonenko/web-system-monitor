angular.module("app", [
  "ngRoute",
  "angular-loading-bar",
  "ngAnimate",
  'ngTouch',
  "ngResource",
  "ui.grid",
  "ui.grid.edit",
  "ui.grid.selection",
  "ui.grid.rowEdit",
  "ui.grid.cellNav"])
  .config(["$routeProvider", function ($routeProvider) {
    var rootFolder = "src/app/";

    $routeProvider
      .when("/processes", {
        templateUrl: rootFolder + "views/processes.html",
        controller: "ProcessesCtrl"
      })
      .when("/configuration", {
        templateUrl: rootFolder + "views/configuration.html",
        controller: "ConfigurationCtrl"
      })
      .otherwise({
        redirectTo: "/processes"
      });
  }])

  .controller("AppCtrl", ["$scope", "$interval", "$location",
    function ($scope, $interval, $location) {
      // pages
      $scope.isProcessesPage = function () {
        return $location.path().substring(1) === "processes"
      };

      $scope.isConfigurationPage = function () {
        return $location.path().substring(1) === "configuration"
      };

      // to stop asking server for new data if page changed
      $scope.intervals = [];
      $scope.watchInterval = function (interval) {$scope.intervals.push(interval);};
      $scope.cancelPrevIntervals = function () {
        _.forEach($scope.intervals, function (interval) {
          $interval.cancel(interval);
        });
        $scope.intervals = [];
      };

      //
      $scope.gridDynConfig = {
        maxCpu: 10,
        refreshRate: 2000
      };
    }]);