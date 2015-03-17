angular.module("app")
  .controller("GridCtrl", function ($scope, CurPageService, ProcessesService, $interval) {

    $scope.config.maxCpu = parseInt($scope.config.maxCpu, 10);

    $scope.gridOptions = {
      enableFiltering: true,
      columnDefs: [
        {field: 'taskName', displayName: 'Task Name', enableCellEdit: false},
        {field: 'user', displayName: 'User', enableCellEdit: false},
        {field: 'cpu', displayName: 'Processor %', enableCellEdit: false,
          cellClass: function(grid, row, col, rowRenderIndex, colRenderIndex) {
            if (grid.getCellValue(row,col) > $scope.config.maxCpu) return 'red';
          }
        },
        {field: 'memory', displayName: 'Memory %', enableCellEdit: false},
        {field: 'description', displayName: 'Description', enableCellEdit: false}
      ],
      sortInfo: {
        fields: ['cpu'],
        directions: ['desc']
      },
      data: $scope.data
    };

    function apiPath() {
      return CurPageService.isAdministrator() ? "info" : "userinfo";
    }


    function refresh() {
      ProcessesService.userProcesses(apiPath(), function (data) {
        $scope.gridOptions.data = data
      });
    }
    refresh();
    var interval = $interval(refresh, parseInt($scope.config.refreshRate, 10));
    $scope.cancelPrevIntervals();
    $scope.watchInterval(interval);
  })

  .controller("ConfigurationCtrl", function ($scope) {
    $scope.cancelPrevIntervals();
  });