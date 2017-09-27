package Entities;

public class Users {
	
	private String username;
	private String password;
	private String email;
	private String name;
	private String surname;
	private int tel;
	private String birthday;
	private String imagePath;
	private String country;
	private String city;
	private int postalCode;
	private String streetAddress;
	private int addressNumber;
	private String host;
	private String tenant;
	
	public Users(String username,String password,String email,String name,String surname,int tel,String birthday,String imagePath,String country,String city,int postalCode,String streetAddress,int addressNumber, String host, String tenant) {
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		this.setName(name);
		this.setSurname(surname);
		this.setTel(tel);
		this.setBirthday(birthday);
		this.setImagePath(imagePath);
		this.setCountry(country);
		this.setCity(city);
		this.setPostalCode(postalCode);
		this.setStreetAddress(streetAddress);
		this.setAddressNumber(addressNumber);
		this.setHost(host);
		this.setTenant(tenant);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public int getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(int addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
}
