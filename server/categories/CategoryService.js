var cat1 = require('./Category.js');

function CategoryService() {
	
  this.getAllCategories = function() {
  	cat1.id = 1;
  	cat1.name = 'cate1';
  	cat1.description = 'category 1';


  	return cat1;
  }

}

module.exports = CategoryService;