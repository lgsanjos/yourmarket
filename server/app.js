var express = require('express');
var logger = require('morgan');

var CategoryService = require(__dirname + '/categories/CategoryService.js');

var app = express();

console.log('Environment: ');
console.log('dirname = ' + __dirname);
console.log('Setting up configuration');

app.use(logger('dev'));
app.set('view engine', 'jade');

console.log('Setting up routes');

app.get('/get_categories', function(req, res, next) {
    categories = new CategoryService().getAllCategories();
	subCategories = new CategoryService().getSubCategories();
	
	output = categories.concat(subCategories);
    console.log(output);
    res.json(output);
});

app.listen(8080);
console.log('Server started');

module.exports = app;