var assert = require("assert")
var should = require('should');
var CategoryService = require('../categories/CategoryService.js');

describe('CategoryService Integration Test', function() {
	
	describe('getCategories', function() {

		it('getAllCategories should return three categories', function() {
			var categories = new CategoryService().getAllCategories();
			categories.should.have.lengthOf(3);
		});

		it('getSubCategoriesFromId should return one subcategory', function() {
			var subcategories = new CategoryService().getSubCategoriesFromId(5);
			subcategories.should.have.lengthOf(1);
			subcategory = subcategories[0];

			subcategory.should.have.property('id', '1');
			subcategory.should.have.property('category_id', '5');
			subcategory.should.have.property('description', 'subcategory 1 from cat 5');
		});

		it('getSubcategoriesFromId should return five subcategories', function() {
			var categories = new CategoryService().getSubCategories();
			categories.should.have.lengthOf(5);
		});

	});

});