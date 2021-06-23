package models;

public class Customer extends User{
	
	private Cart cart = new Cart();
	
	private String firstName;
	private String lastName;

	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Customer(String user, String pass , String mail, String firstName, String last, String phone, String adress,Long money){
		super(user,pass,mail,phone,adress,money);
		this.firstName = firstName;
		this.lastName=last;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
}
