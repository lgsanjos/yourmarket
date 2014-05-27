var Company = require('./Company.js');

function CompanyRepository() {
	this.getCompany = function() {
		var reg = new Company();
		reg.id = 1;
		reg.name = "Momento Magico Atelie";
		reg.phone = "44 3028-6104";
		reg.cellphone = "44 9982-6104"
		reg.email = 'contato@momentomagicoatelie.com.br';
		reg.logo = 'logo.png';

		return reg;
	};
};

module.exports = CompanyRepository;