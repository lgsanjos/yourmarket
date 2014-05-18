package br.com.sasolucoes.yourmarket.repository.advertiser;

import com.google.gson.annotations.SerializedName;

public class Advertiser {

	@SerializedName("id")
	public Integer id;
	
	@SerializedName("name")
	public String name;
	
	@SerializedName("email")
	public String email;
	
	@SerializedName("cellphone")
	public String cellphone;
	
	@SerializedName("phone")
	public String phone;

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Advertiser))
			return false;
		
		Advertiser obj = (Advertiser)o;
		boolean equals = super.equals(o);
		equals &= id.equals(obj.id);
		return equals;
	}	
}
