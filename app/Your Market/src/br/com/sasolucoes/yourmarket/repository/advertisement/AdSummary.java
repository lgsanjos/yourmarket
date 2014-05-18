package br.com.sasolucoes.yourmarket.repository.advertisement;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class AdSummary {
	@SerializedName("id")
	public int id;
	
	@SerializedName("title")
	public String title;
	
	@SerializedName("price")
	public double price;

	@SerializedName("announcementDate")
	public Date announcementDate;
	
	@SerializedName("mainPicture")
	public String mainPicture;
	
}
