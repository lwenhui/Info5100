import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Queue;

public class AtmTest {

	@Test
	public void testIsLoginSuccess() {
		User lucy = new User("Lucy", 1992, "3205 11th AVE NE, Seattle, WA, 98105", "2069921903", "123456");
		User david = new User("David", 1988, "4520 12th AVE NE, Seattle, WA, 98105", "2061234567", "987666");
		User jack = new User("Jack", 1979, "8212 3th AVE NE, Seattle, WA, 98105", "2064564567", "");
		UserData lucyData = new UserData(lucy, "1928");
		UserData davidData = new UserData(david, "3746", 10000.0);

		HashMap<String, UserData> userDatas = new HashMap<String, UserData>();
		userDatas.put("123456", lucyData);
		userDatas.put("987666", davidData);

		Atm atm = new Atm(5000.0, Assignments.TRANSACTION_FEE, userDatas);

		boolean exceptedOutput = true;

		boolean output = atm.isLoginSuccess("123456", "1928");
		Assert.assertEquals(exceptedOutput, output);
	}

	@Test
	public void testIsValidUser() {
		User lucy = new User("Lucy", 1992, "3205 11th AVE NE, Seattle, WA, 98105", "2069921903", "123456");
		User david = new User("David", 1988, "4520 12th AVE NE, Seattle, WA, 98105", "2061234567", "987666");
		User jack = new User("Jack", 1979, "8212 3th AVE NE, Seattle, WA, 98105", "2064564567", "");
		UserData lucyData = new UserData(lucy, "1928");
		UserData davidData = new UserData(david, "3746", 10000.0);

		HashMap<String, UserData> userDatas = new HashMap<String, UserData>();
		userDatas.put("123456", lucyData);
		userDatas.put("987666", davidData);

		Atm atm = new Atm(5000.0, Assignments.TRANSACTION_FEE, userDatas);

		boolean exceptedOutput = false;

		boolean output = atm.isValidUser("123456", "lucy", 1992, "1231231233");
		Assert.assertEquals(exceptedOutput, output);
	}

	@Test
	public void testIsValidBankAccount() {
		User lucy = new User("Lucy", 1992, "3205 11th AVE NE, Seattle, WA, 98105", "2069921903", "123456");
		User david = new User("David", 1988, "4520 12th AVE NE, Seattle, WA, 98105", "2061234567", "987666");
		User jack = new User("Jack", 1979, "8212 3th AVE NE, Seattle, WA, 98105", "2064564567", "");
		UserData lucyData = new UserData(lucy, "1928");
		UserData davidData = new UserData(david, "3746", 10000.0);

		HashMap<String, UserData> userDatas = new HashMap<String, UserData>();
		userDatas.put("123456", lucyData);
		userDatas.put("987666", davidData);

		Atm atm = new Atm(5000.0, Assignments.TRANSACTION_FEE, userDatas);

		boolean exceptedOutput = false;

		boolean output = atm.isValidBankAccount("1234567");
		Assert.assertEquals(exceptedOutput, output);

		output = atm.isValidBankAccount("");
		Assert.assertEquals(exceptedOutput, output);

		exceptedOutput = true;
		output = atm.isValidBankAccount("123456");
		Assert.assertEquals(exceptedOutput, output);
	}

	@Test
	public void testAvailableBalance() {
		User lucy = new User("Lucy", 1992, "3205 11th AVE NE, Seattle, WA, 98105", "2069921903", "123456");
		User david = new User("David", 1988, "4520 12th AVE NE, Seattle, WA, 98105", "2061234567", "987666");
		User jack = new User("Jack", 1979, "8212 3th AVE NE, Seattle, WA, 98105", "2064564567", "");
		UserData lucyData = new UserData(lucy, "1928");
		UserData davidData = new UserData(david, "3746", 10000.0);

		HashMap<String, UserData> userDatas = new HashMap<String, UserData>();
		userDatas.put("123456", lucyData);
		userDatas.put("987666", davidData);

		Atm atm = new Atm(5000.0, Assignments.TRANSACTION_FEE, userDatas);

		double exceptedOutput = 10000.0;

		double output = atm.availableBalance("987666");
		Assert.assertEquals(exceptedOutput, output, 0.0);
	}

	@Test
	public void testWithDrawal() {
		User lucy = new User("Lucy", 1992, "3205 11th AVE NE, Seattle, WA, 98105", "2069921903", "123456");
		User david = new User("David", 1988, "4520 12th AVE NE, Seattle, WA, 98105", "2061234567", "987666");
		User jack = new User("Jack", 1979, "8212 3th AVE NE, Seattle, WA, 98105", "2064564567", "");
		UserData lucyData = new UserData(lucy, "1928");
		UserData davidData = new UserData(david, "3746", 100.0);

		HashMap<String, UserData> userDatas = new HashMap<String, UserData>();
		userDatas.put("123456", lucyData);
		userDatas.put("987666", davidData);

		Atm atm = new Atm(5000.0, Assignments.TRANSACTION_FEE, userDatas);

		double exceptedOutput = 78.0;

		atm.withDrawal("987666", 20);


		UserData userInfo = atm.getUserData().get("987666");
		double output = userInfo.getUserBalance();
		Assert.assertEquals(exceptedOutput, output, 0.0);

		atm.withDrawal("987666", 90);
		output = userInfo.getUserBalance();
		Assert.assertEquals(exceptedOutput, output, 0.0);
	}
	@Test
	public void testDeposit() {
		User lucy = new User("Lucy", 1992, "3205 11th AVE NE, Seattle, WA, 98105", "2069921903", "123456");
		User david = new User("David", 1988, "4520 12th AVE NE, Seattle, WA, 98105", "2061234567", "987666");
		User jack = new User("Jack", 1979, "8212 3th AVE NE, Seattle, WA, 98105", "2064564567", "");
		UserData lucyData = new UserData(lucy, "1928");
		UserData davidData = new UserData(david, "3746", 100.0);

		HashMap<String, UserData> userDatas = new HashMap<String, UserData>();
		userDatas.put("123456", lucyData);
		userDatas.put("987666", davidData);

		Atm atm = new Atm(5000.0, Assignments.TRANSACTION_FEE, userDatas);

		double exceptedOutput = 178.0;

		atm.deposit("987666", 80);


		UserData userInfo = atm.getUserData().get("987666");
		double output = userInfo.getUserBalance();
		Assert.assertEquals(exceptedOutput, output, 0.0);
	}

	@Test
	public void testRecentTransactions() {
		User lucy = new User("Lucy", 1992, "3205 11th AVE NE, Seattle, WA, 98105", "2069921903", "123456");
		User david = new User("David", 1988, "4520 12th AVE NE, Seattle, WA, 98105", "2061234567", "987666");
		User jack = new User("Jack", 1979, "8212 3th AVE NE, Seattle, WA, 98105", "2064564567", "");
		UserData lucyData = new UserData(lucy, "1928");
		UserData davidData = new UserData(david, "3746", 10000.0);

		HashMap<String, UserData> userDatas = new HashMap<String, UserData>();
		userDatas.put("123456", lucyData);
		userDatas.put("987666", davidData);

		Atm atm = new Atm(5000.0, Assignments.TRANSACTION_FEE, userDatas);

		atm.deposit("987666", 80);
		atm.withDrawal("987666", 90);


		UserData userInfo = atm.getUserData().get("987666");
		Queue<String> queue = userInfo.getRecentTransaction();
		StringBuilder out = new StringBuilder();
		for (String item : queue) {
			out.append(item);
			out.append("\n");
		}
		String  output = out.toString();
		String  exceptedOutput = "Deposit - 80.0 - 10078.0" + "\n"
				+ "withDrawal - 90.0 - 9986.0" + "\n";
		Assert.assertEquals(exceptedOutput, output);
	}
}