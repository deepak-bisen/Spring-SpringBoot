package sp.beans;

public class Student {
	private int rollno;
	private String name;
	private String email;
	private Address address;

	public int getRollno() {
		return rollno;
	}

	public void setRollno(int rollno) {
		this.rollno = rollno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void display() {
		System.out.println("name: " + name);
		System.out.println("rollno: " + rollno);
		System.out.println("email: " + email);
	}

}
