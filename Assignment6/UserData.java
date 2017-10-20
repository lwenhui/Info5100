import java.util.Queue;
import java.util.LinkedList;

public class UserData {
	private User user;
	private String password;
	private double userBalance;
	private Queue<String> recentTransaction;
	public UserData() {}
	public UserData(User user, String password) {
		this.user = user;
		this.password = password;
		userBalance = 0;
		recentTransaction = new LinkedList<String>();
	}
	public UserData(User user, String password, double userBalance) {
		this.user = user;
		this.password = password;
		this.userBalance = userBalance;
		this.recentTransaction = new LinkedList<String>();
	}
	public UserData(User user, String password, double userBalance, Queue<String> recentTransaction) {
		this.user = user;
		this.password = password;
		this.userBalance = userBalance;
		this.recentTransaction = recentTransaction;
	}
	public void setUser (User user) {
		this.user = user;
	}
	public User getUser() {
		return this.user;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}
	public void setUserBalance(double userBalance) {
		this.userBalance = userBalance;
	}
	public double getUserBalance() {
		return this.userBalance;
	}
	public void setRecentTransaction(Queue<String> recentTransaction) {
		this.recentTransaction = recentTransaction;
	}
	public void setRecentTransaction(String recentTransaction) {
		this.recentTransaction.offer(recentTransaction);
	}
	public Queue<String> getRecentTransaction() {
		return this.recentTransaction;
	}
}