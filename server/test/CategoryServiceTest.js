var assert = require("assert")
var should = require('should');
var CategoryService = require('../categories/CategoryService.js');

describe('CategoryService Integration Test', function() {
	
	describe('getCategories', function() {

		it('getAllCategories should return three categories', function(done) {
			var categories = new CategoryService().getAllCategories();
			categories.should.have.lengthOf(3);
			done();
		});

	});

});