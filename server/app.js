var express = require('express');
var logger = require('morgan');
var categoryRoutes = require( __dirname + '/categories/Routes.js');

var app = express();

console.log('Environment: ');
console.log('dirname = ' + __dirname);
console.log('Setting up configuration');


app.use(logger('dev'));
app.set('view engine', 'jade');

console.log('Setting up routes');

app.use('/', categoryRoutes);

/// catch 404 and forward to error handler
app.use(function(req, res, next) {
    var err = new Error('Not Found');
    err.status = 404;
    next(err);
});

/// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
    app.use(function(err, req, res, next) {
        res.status(err.status || 500);
        res.render('error', {
            message: err.message,
            error: err
        });
    });
}

// production error handler
// no stacktraces leaked to user
app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
        message: err.message,
        error: {}
    });
});


app.listen(8080);
console.log('Server started');

module.exports = app;