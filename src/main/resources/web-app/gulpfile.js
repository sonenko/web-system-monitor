var gulp = require("gulp");
var jshint = require("gulp-jshint");
var concat = require("gulp-concat");
var concatMap = require("gulp-concat-sourcemap");
var continuousConcat = require("gulp-continuous-concat");
var rename = require("gulp-rename");
var uglify = require("gulp-uglify");
var watch = require("gulp-watch");
var plumber = require("gulp-plumber");

var distFolder = "resources/dist/",
  apps = {
    index: {
      js: {
        dest: "all.min.js",
        src: ["src/app.js", "src/app/**/*js"]
      }
    }
  };

///////////////
// T A S K S //
///////////////

gulp.task("watch-js", function() {
  myWatch([apps.index.js]);
});



function myConcatAndAglify(src, dest) {
  return function(){
    gulp.src(src)
      //.pipe(uglify())
      .on("error", mySwallowError)
      .pipe(concatMap(dest))
      .pipe(gulp.dest(distFolder));
  };
}

function mySwallowError (error) {
  console.warn(error.toString());
  this.emit("end");
}

// @configList - List[{src: String, dest: List[String]}]
function myWatch(configList) {
  configList.forEach(function (value, i, all) {
    var src = value.src,
      dest = value.dest;
    gulp.src(src)
      .pipe(watch(src, myConcatAndAglify(src, dest)))
      .on("error", mySwallowError)
  });
}


