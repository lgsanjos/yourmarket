var assert = require('assert')
var should = require('should');
var CompanyRepository = require('../company/CompanyRepository.js');

describe('CompanyRepository', function() {
	
	describe('Select data', function() {

		it('getCompany should return one company', function() {
			var target = new CompanyRepository().getCompany();

			target.should.have.property('id', '1');
			target.should.have.property('name', 'Momento Magico Atelie');
			target.should.have.property('phone', '44 3028-6104');
			target.should.have.property('cellphone', '44 9982-6104');
			target.should.have.property('email', 'contato@momentomagicoatelie.com.br');
			target.should.have.property('logo', 'logo.png');
		});
	});

});