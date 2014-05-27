var assert = require('assert')
var should = require('should');
var Company = require('../company/Company.js');

describe('Company', function() {
	
	describe('properties', function() {
		
		var reg = new Company();

		it('should have id', function () {
			reg.should.have.property('id');
		});

		it('should have name', function () {
			reg.should.have.property('name');
		});

		it('should have phone', function () {
			reg.should.have.property('phone');
		});

		it('should have cellphone', function () {
			reg.should.have.property('cellphone');
		});

		it('should have email', function () {
			reg.should.have.property('email');
		});

		it('should have logo', function () {
			reg.should.have.property('logo');
		});

	});

});