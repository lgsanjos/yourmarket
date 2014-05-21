package br.com.sasolucoes.yourmarket.business.category;

import com.google.gson.annotations.SerializedName;

public class Category {
	
	@SerializedName("id")
	public Integer id;
	
	@SerializedName("name")
	public String name;
	
	@SerializedName("description")
	public String description;
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Category))
			return false;
		
		Category obj = (Category) o;
		boolean equals = super.equals(o);
		equals &= id.equals(obj.id);
		return equals;
	}

}
