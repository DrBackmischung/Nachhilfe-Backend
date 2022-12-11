package de.wi2020sebgroup1.nachhilfe.gateway.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

	@Id
	private String id;
	private String userName;
	private String lastName;
	private String firstName;
	private String gender;
	private String mail;
	private String phone;
	private String street;
	private String houseNr;
	private String zipCode;
	private String city;
	private String password;
	
	public User(String id, String userName, String lastName, String firstName, String gender, String mail, String phone,
			String street, String houseNr, String zipCode, String city, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.lastName = lastName;
		this.firstName = firstName;
		this.gender = gender;
		this.mail = mail;
		this.phone = phone;
		this.street = street;
		this.houseNr = houseNr;
		this.zipCode = zipCode;
		this.city = city;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", gender=" + gender + ", mail=" + mail + ", phone=" + phone + ", street=" + street + ", houseNr="
				+ houseNr + ", zipCode=" + zipCode + ", city=" + city + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, firstName, gender, houseNr, id, lastName, mail, password, phone, street, userName,
				zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(city, other.city) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(gender, other.gender) && Objects.equals(houseNr, other.houseNr)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(mail, other.mail) && Objects.equals(password, other.password)
				&& Objects.equals(phone, other.phone) && Objects.equals(street, other.street)
				&& Objects.equals(userName, other.userName) && Objects.equals(zipCode, other.zipCode);
	}
	
}
