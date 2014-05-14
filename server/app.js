var express = require('express');
var logger = require('morgan');
var CategoryRoutes = require(__dirname + '/categories/Routes.js');

var app = express();

console.log('Environment: ');
console.log('dirname = ' + __dirname);
console.log('Setting up configuration');

app.use(logger('dev'));
app.set('view engine', 'jade');

console.log('Setting up routes');
app.get('/get_categories', CategoryRoutes.getCategories);
app.get('/get_subcategories/:id', CategoryRoutes.getSubcategoriesFromId);

app.listen(8080);
console.log('Server started');

module.exports = app;