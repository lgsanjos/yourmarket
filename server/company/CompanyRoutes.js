var express = require('express');
var router = express.Router();
var CompanyRepository = require('./CompanyRepository.js');

router.get('/get_company', function(req, res, next) {
    var output = new CompanyRepository().getCompany();
    console.log(output);
    res.json(output);
});

module.exports = router;

