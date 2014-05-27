package br.com.sasolucoes.yourmarket.business.category.subcategory;

import br.com.sasolucoes.yourmarket.business.category.Category;

import com.google.gson.annotations.SerializedName;

public class Subcategory extends Category {
	
	@SerializedName("category_id")
	public Integer categoryId;

}
