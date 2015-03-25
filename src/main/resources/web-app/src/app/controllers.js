angular.module("app")
  .controller("ProcessesCtrl", function ($scope, ProcessesService, $interval, uiGridConstants) {

    $scope.gridDynConfig.maxCpu = parseInt($scope.gridDynConfig.maxCpu, 10);
    $scope.data = [];
    $scope.gridOptions = {
      enableFiltering: true,
      columnDefs: [
        {field: 'taskName', displayName: 'Task', enableCellEdit: false},
        {field: 'pid', displayName: 'PID', enableCellEdit: false},
        {field: 'user', displayName: 'User', enableCellEdit: false},
        {field: 'cpu', displayName: 'Processor %', enableCellEdit: false,
          cellClass: function(grid, row, col, rowRenderIndex, colRenderIndex) {
            if (grid.getCellValue(row, col) > $scope.gridDynConfig.maxCpu) return 'red';
          }
        },
        {field: 'memory', displayName: 'Memory %', enableCellEdit: false},
        {field: 'description', displayName: 'Command', enableCellEdit: false}
      ],
      sortInfo: {
        fields: ['cpu'],
        directions: ['desc']
      },
      data: $scope.data
    };

    function refresh() {
      ProcessesService.processes(function (data) {
        $scope.gridOptions.data = data
      });
    }
    refresh();
    var interval = $interval(refresh, parseInt($scope.gridDynConfig.refreshRate, 10));
    $scope.cancelPrevIntervals();
    $scope.watchInterval(interval);
  })

  .controller("ConfigurationCtrl", function ($scope) {
    $scope.cancelPrevIntervals();

    $scope.options = [
      {value: 1000, name: "1 sec"},
      {value: 2000, name: "2 sec"},
      {value: 10000, name: "10 sec"},
      {value: 60000, name: "1 min"}
    ];

  });