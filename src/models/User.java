package models;

public class User {
	
	
	private String phoneNumber;
	private String userName;
	private String password;
	private String email;
	private String adress;
	protected long money;
	
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public User(String user, String pass , String mail, String phone, String adress,Long money){
		this.userName = user;
		this.email = mail;
		this.password = pass;
		this.adress = adress;
		this.phoneNumber=phone;
		this.money=money;
	}
	public long getMoney() {
		return money;
	}
	public void setMoney(long money) {
		this.money = money;
	}
}
