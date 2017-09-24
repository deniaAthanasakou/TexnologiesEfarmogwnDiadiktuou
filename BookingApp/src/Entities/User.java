package Entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private int userId;

	@Column(name="address_number")
	private int addressNumber;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String city;

	private String country;

	private String email;

	private String name;

	private String password;

	@Lob
	private byte[] photo;

	@Column(name="postal_code")
	private String postalCode;

	@Column(name="role_admin")
	private byte roleAdmin;

	@Column(name="role_host")
	private byte roleHost;

	@Column(name="role_tenant")
	private byte roleTenant;

	@Column(name="street_address")
	private String streetAddress;

	private String surname;

	private String tel;

	private String username;

	public User() {
	}
	
	public User(String username,String password,String email,String name,String surname,String tel,Date birthday,byte[] photo,String country,String city,String postalCode,String streetAddress,int addressNumber, byte host, byte tenant) {
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		this.setName(name);
		this.setSurname(surname);
		this.setTel(tel);
		this.setBirthday(birthday);
		this.setPhoto(photo);
		this.setCountry(country);
		this.setCity(city);
		this.setPostalCode(postalCode);
		this.setStreetAddress(streetAddress);
		this.setAddressNumber(addressNumber);
		this.setRoleHost(host);
		this.setRoleTenant(tenant);
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAddressNumber() {
		return this.addressNumber;
	}

	public void setAddressNumber(int addressNumber) {
		this.addressNumber = addressNumber;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public byte getRoleAdmin() {
		return this.roleAdmin;
	}

	public void setRoleAdmin(byte roleAdmin) {
		this.roleAdmin = roleAdmin;
	}

	public byte getRoleHost() {
		return this.roleHost;
	}

	public void setRoleHost(byte roleHost) {
		this.roleHost = roleHost;
	}

	public byte getRoleTenant() {
		return this.roleTenant;
	}

	public void setRoleTenant(byte roleTenant) {
		this.roleTenant = roleTenant;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}