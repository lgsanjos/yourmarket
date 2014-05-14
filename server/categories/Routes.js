 var CategoryService = require('./CategoryService.js');

exports.getCategories = function(req, res, next) {
    var output = new CategoryService().getAllCategories();	
    console.log(output);
    res.json(output);
};

exports.getSubcategoriesFromId = function(req, res, next) {
    id = req.params.id;
    var output = new CategoryService().getSubCategoriesFromId(id);
    console.log(output);
    res.json(output);
};