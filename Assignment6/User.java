public class User{
	private String name;
	private int yearOfBirth;
	private String address;
	private String phoneNumber;
	private String bankAccountNumber;

	public User() {}
	public User(String name, int yearOfBirth, String address, String phoneNumber, String bankAccountNumber) {
		this.name = name;
		this.yearOfBirth = yearOfBirth;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.bankAccountNumber = bankAccountNumber;
	}
	
	public String getName() {
		return this.name;
	}
	public int getYearOfBirth() {
		return this.yearOfBirth;
	}
	public String getAddress() {
		return this.address;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public String getBankAccountNumber() {
		return this.bankAccountNumber;
	}
}