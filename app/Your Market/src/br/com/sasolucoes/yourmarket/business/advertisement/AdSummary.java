package br.com.sasolucoes.yourmarket.business.advertisement;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class AdSummary {
	@SerializedName("id")
	public Integer id;
	
	@SerializedName("title")
	public String title;
	
	@SerializedName("price")
	public Double price;

	@SerializedName("announcementDate")
	public Date announcementDate;
	
	@SerializedName("mainPicture")
	public String mainPicture;

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof AdSummary))
			return false;
		
		AdSummary obj = (AdSummary)o;
		boolean equals = super.equals(o);
		equals &= id.equals(obj.id);
		return equals;
	}	
}
