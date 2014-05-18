package br.com.sasolucoes.yourmarket.repository.subcategory;

import br.com.sasolucoes.yourmarket.repository.category.Category;

import com.google.gson.annotations.SerializedName;

public class Subcategory extends Category {
	
	@SerializedName("categoryId")
	public Integer categoryId;

}
