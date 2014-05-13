var Category = require('./Category.js');

function CategoryService() {
	
  this.getAllCategories = function() {
  	var cat1 = new Category();
  	cat1.id = 1;
  	cat1.name = 'cate1';
  	cat1.description = 'category 1';

  	var cat2 = new Category();
  	cat2.id = 2;
  	cat2.name = 'cate2';
  	cat2.description = 'category 2';

  	return [ cat1, cat2 ];
  };

};

module.exports = CategoryService;