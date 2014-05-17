var CategoryService = require('./CategoryService.js');
var express = require('express');
var router = express.Router();

router.get('/get_categories', function(req, res, next) {
    var output = new CategoryService().getAllCategories();	
    console.log(output);
    res.json(output);
});

router.get('/get_subcategories/:id', function(req, res, next) {
    id = req.params.id;
    var output = new CategoryService().getSubCategoriesFromId(id);
    console.log(output);
    res.json(output);
});

module.exports = router;

