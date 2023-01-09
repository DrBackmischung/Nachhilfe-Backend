package de.wi2020sebgroup1.nachhilfe.gateway.util;

import java.util.Objects;

public class Bill {
	
	private String name;
	private String mail;
	private String address;
	private String article;
	private String price;
	
	public Bill(String name, String mail, String address, String article, String price) {
		super();
		this.name = name;
		this.mail = mail;
		this.address = address;
		this.article = article;
		this.price = price;
	}
	
	public Bill() {
		
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		return "Bill [name=" + name + ", mail=" + mail + ", address=" + address + ", article=" + article + ", price="
				+ price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, article, mail, name, price);
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
		return Objects.equals(address, other.address) && Objects.equals(article, other.article)
				&& Objects.equals(mail, other.mail) && Objects.equals(name, other.name)
				&& Objects.equals(price, other.price);
	}

}
