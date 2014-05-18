package br.com.sasolucoes.yourmarket.repository.advertiser;

import com.google.gson.annotations.SerializedName;

public class Advertiser {

	@SerializedName("id")
	public int id;
	
	@SerializedName("name")
	public String name;
	
	@SerializedName("email")
	public String email;
	
	@SerializedName("cellphone")
	public String cellphone;
	
	@SerializedName("phone")
	public String phone;
	
}
