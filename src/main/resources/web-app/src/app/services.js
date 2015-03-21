angular.module("app")
  .factory("ProcessesService", function ($http) {
    return {
      processes: function (fn) {
        $http.get("/api/info")
          .success(fn)
          .error(function (error) {console.error("there was server error: " + error)});
      }
    }
  });