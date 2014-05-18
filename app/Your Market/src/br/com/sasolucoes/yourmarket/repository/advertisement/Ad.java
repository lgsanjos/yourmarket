package br.com.sasolucoes.yourmarket.repository.advertisement;

import com.google.gson.annotations.SerializedName;

public class Ad extends AdSummary {

	@SerializedName("categoryId")
	public Integer categoryId;
	
	@SerializedName("subcategoryId")
	public Integer subcategoriId;
	
	@SerializedName("description")
	public String description;
	
	@SerializedName("pictureOne")
	public String pictureOne;
	
	@SerializedName("pictureTwo")
	public String pictureTwo;
	
	@SerializedName("pictureTree")
	public String pictureTree;
	
	@SerializedName("pictureFour")
	public String pictureFour;
	
	@SerializedName("pictureFive")
	public String pictureFive;
	
	@SerializedName("advertiserId")
	public Integer advertiserId;

}
