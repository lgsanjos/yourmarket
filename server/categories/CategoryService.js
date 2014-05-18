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
  
  this.getSubCategoriesFromId = function(id) {
	var subcat1 = new SubCategory();
	subcat1.id = 1;
	subcat1.category_id = id;
	subcat1.description = 'subcategory 1 from cat ' + id;
	
	return [ subcat1 ];
  };

  this.getSubCategories = function() {
    var subcat1 = new SubCategory();
    subcat1.id = 1;
    subcat1.category_id = 1;
    subcat1.name = "subCat 01";
    subcat1.description = 'subCat 1 from cat 1';

    var subcat2 = new SubCategory();
    subcat2.id = 2;
    subcat2.category_id = 1;
    subcat2.name = "subCat 02";
    subcat2.description = 'subCat 2 from cat 1';

    var subcat3 = new SubCategory();
    subcat3.id = 3;
    subcat3.category_id = 1;
    subcat3.name = "subCat 03";
    subcat3.description = 'subCat 3 from cat 1';

    var subcat4 = new SubCategory();
    subcat4.id = 4;
    subcat4.category_id = 2;
    subcat4.name = "subCat 04";
    subcat4.description = 'subCat 4 from cat 2';

    var subcat5 = new SubCategory();
    subcat5.id = 5;
    subcat5.category_id = 2;
    subcat5.name = "subCat 05";
    subcat5.description = 'subCat 5 from cat 2';

    return [ subcat1, subcat2, subcat3, subcat4, subcat5 ];
  };

};

module.exports = CategoryService;