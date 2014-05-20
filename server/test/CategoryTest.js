var assert = require('assert')
var should = require('should');
var Category = require('../categories/Category.js');

describe('Category', function() {
	
	describe('properties', function() {
		
		var category = new Category();

		it('should have id', function () {
			category.should.have.property('id');
		});

		it('should have name', function () {
			category.should.have.property('name');
		});

		it('should have description', function () {
			category.should.have.property('description');
		});
	});

});