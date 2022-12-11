package de.wi2020sebgroup1.nachhilfe.gateway.util;

import java.util.Objects;

public class Bill {
	
	private String name;
	private String mail;
	private String street;
	private String houseNr;
	private String zipCode;
	private String city;
	private String article;
	private String price;
	
	public Bill(String name, String mail, String street, String houseNr, String zipCode, String city, String article,
			String price) {
		super();
		this.name = name;
		this.mail = mail;
		this.street = street;
		this.houseNr = houseNr;
		this.zipCode = zipCode;
		this.city = city;
		this.article = article;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNr() {
		return houseNr;
	}

	public void setHouseNr(String houseNr) {
		this.houseNr = houseNr;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Bill [name=" + name + ", mail=" + mail + ", street=" + street + ", houseNr=" + houseNr + ", zipCode="
				+ zipCode + ", city=" + city + ", article=" + article + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(article, city, houseNr, mail, name, price, street, zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bill other = (Bill) obj;
		return Objects.equals(article, other.article) && Objects.equals(city, other.city)
				&& Objects.equals(houseNr, other.houseNr) && Objects.equals(mail, other.mail)
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price)
				&& Objects.equals(street, other.street) && Objects.equals(zipCode, other.zipCode);
	}

}
