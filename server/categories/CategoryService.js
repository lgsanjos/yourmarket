var Category = require('./Category.js');
var SubCategory = require('./SubCategory.js');

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

	
  	var cat3 = new Category();
  	cat3.id = 3;
  	cat3.name = 'cate3';
  	cat3.description = 'category 3';
	
  	return [ cat1, cat2, cat3 ];
  };
  
  this.getSubCategories = function() {
	var subcat1 = new SubCategory();
	subcat1.id = 1;
	subcat1.category_id = 1;
	subcat1.description = 'subcategory 1';
	
	return [ subcat1 ];
  };
};

module.exports = CategoryService;