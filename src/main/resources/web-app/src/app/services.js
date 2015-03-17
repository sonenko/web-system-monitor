angular.module("app")
  .factory("ProcessesService", function ($http) {
    return {
      userProcesses: function (path, fn) {
        $http.get("/api/" + path)
          .success(fn)
          .error(function (error) {console.error("there was server error: " + error)});
      },
      processes: function (path, fn) {
        $http.get("/api/" + path)
          .success(fn)
          .error(function (error) {console.error("there was server error: " + error)});
      }
    }
  });