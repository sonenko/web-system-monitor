module.exports = function(config){
  config.set({

    basePath : '../',

    files : [
      'resources/libs/angular/angular.js',
      'resources/libs/angular-route/angular-route.js',
      'resources/libs/angular-cookies/angular-cookies.min.js',
      'resources/libs/angular-animate/angular-animate.min.js',
      'resources/libs/angular-loading-bar/src/loading-bar.js',
      'resources/libs/angular-resource/angular-resource.js',
      'resources/libs/angular-animate/angular-animate.js',
      'resources/libs/angular-mocks/angular-mocks.js',
      'resources/dist/*.js',
      'test/unit/**/*.js'
    ],

    autoWatch : true,

    frameworks: ['jasmine'],

    browsers : ['Chrome'],

    plugins : [
            'karma-chrome-launcher',
            'karma-firefox-launcher',
            'karma-jasmine'
            ],

    junitReporter : {
      outputFile: 'test_out/unit.xml',
      suite: 'unit'
    }

  });
};
