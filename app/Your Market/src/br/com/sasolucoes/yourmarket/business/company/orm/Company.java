package br.com.sasolucoes.yourmarket.business.company.orm;

import br.com.sasolucoes.yourmarket.orm.Entity;

public class Company extends Entity {
	static final String TAG = "Company";
	
	private String name;
	private String phone;
	private String cellphone;
	private String email;
	private String logo;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
}
