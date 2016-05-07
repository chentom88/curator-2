import gulp from 'gulp';

const runSequence = require('run-sequence').use(gulp);

gulp.task('setup-watchers', (callback) => {
  process.env.WEBPACK_WATCH = true;
  gulp.watch(['src/**/*.js', 'sandbox/index.js', 'sandbox/sandbox.js'], ['sandbox-build-js']);
  gulp.watch(['sandbox/index.html'], ['sandbox-copy-html']);
  gulp.watch(['src/**/*.scss'], ['sandbox-build-sass']);
  callback();
});

gulp.task('sandbox', (callback) => runSequence(
  'setup-watchers',
  'sandbox-serve',
  callback
));

gulp.task('start', ['sandbox', 'jasmine-react']);

gulp.task('build', ['css-build', 'react-build']);
