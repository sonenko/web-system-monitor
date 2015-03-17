exports.config = {
  allScriptsTimeout: 11000,

  specs: [
    'e2e/**/*.js'
  ],

  capabilities: {
    'browserName': 'chrome'
  },

  directConnect: true,

  baseUrl: 'http://localhost:8080/',  // for real app

  framework: 'jasmine',

  jasmineNodeOpts: {
    showColors : true,
    defaultTimeoutInterval : 60000,
    isVerbose : true,
    includeStackTrace : true
  }
};