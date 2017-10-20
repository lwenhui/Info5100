import java.io.*;
import java.util.*;

public class Atm {
	private double availableAmountInMachine;
	private double transactionFee;
	private HashMap<String, UserData> userData;//key:bankAccountNumber; Value: UserData
	
	public static final int MAX_TRANACTION_NUMBER = 10;
	public Atm() {}
	public Atm(double availableAmountInMachine, double transactionFee, HashMap<String, UserData> userData) {
		this.availableAmountInMachine = availableAmountInMachine;
		this.transactionFee = transactionFee;
		this.userData = userData;
	}

	public void setAvailableAmountInMachine(double availableAmountInMachine) {
		this.availableAmountInMachine = availableAmountInMachine;
	}
	public double getAvailableAmountInMachine() {
		return availableAmountInMachine;
	}
	public void setTransactionFee(double transactionFee) {
		this.transactionFee = transactionFee;
	}
	public double getTransactionFee() {
		return this.transactionFee;
	}
	public void setUserData(HashMap<String, UserData> userData) {
		this.userData = userData;
	}
	public HashMap<String, UserData> getUserData() {
		return this.userData;
	}

	public boolean isLoginSuccess (String bankAccountNumber, String password) {
		if (!isValidBankAccount(bankAccountNumber)) {
			System.out.println("Bank account number is invalid!");
			return false;
		} else {
			UserData userInfo = this.userData.get(bankAccountNumber);
			if (password.equals(userInfo.getPassword())) {
				System.out.println("************************************************************");
				System.out.println("Successfully log in!");
				System.out.println("************************************************************");
			 	return true;
			}
			return false;
		}
	}

	public void resetPassword(String password, String bankAccountNumber){
		UserData userInfo = this.userData.get(bankAccountNumber);
		userInfo.setPassword(password);
		System.out.println("************************************************************");
		System.out.println("Congratulation! You have successfully reset your password!");
		System.out.println("************************************************************");
	}

	public boolean isValidUser(String bankAccountNumber, String nameLowerCase, int yearOfBirth, String phoneNumber) {
		boolean isValid = isValidBankAccount(bankAccountNumber);
		if (isValid) {
			UserData userInfo = this.userData.get(bankAccountNumber);
			if (nameLowerCase.equals(userInfo.getUser().getName().toLowerCase()) 
				&& yearOfBirth == userInfo.getUser().getYearOfBirth() 
				&& phoneNumber.equals(userInfo.getUser().getPhoneNumber())) {
				return true;
			}
		} else {
			System.out.println("Bank account number is invalid!");
			return false;
		}
		return false;
	}
	public boolean isValidBankAccount(String bankAccountNumber) {
		if(bankAccountNumber == null) {
			return false;
		}
		return (this.userData.containsKey(bankAccountNumber));
	}

	public String generateUniqueBankAccountNumber() {
		String bankAccountNumber ="" + (int)((Math.random() * 9 + 1) * 100000);
		while(this.userData.containsKey(bankAccountNumber) ){
			bankAccountNumber ="" + (int)((Math.random() * 9 + 1) * 100000);
		}
		return bankAccountNumber;
	}

	public double availableBalance(String bankAccountNumber){
		if (bankAccountNumber == null) {
			System.out.println("while check availableBalance, bankAccountNumber is null!");
			return -1;
		}
		double userBalance = this.userData.get(bankAccountNumber).getUserBalance();
		
		return userBalance;
	}

	public void withDrawal(String bankAccountNumber, double withDrawalAmount){
		if (bankAccountNumber == null) {
			System.out.println("withDrawal account is null.");
			return;
		}
		if (withDrawalAmount + this.transactionFee > this.availableBalance(bankAccountNumber)) {
			System.out.println("************************************************************");
			System.out.println("You do not have enough money in your account.(Remember we charge 2 dollor for each transaction.)");
			System.out.println("************************************************************");
			return;
		}
		if (withDrawalAmount > this.availableAmountInMachine) {
			System.out.println("************************************************************");
			System.out.println("Sorry, this machine does not have enough money.");
			System.out.println("************************************************************");
			return;
		}

		this.availableAmountInMachine -= withDrawalAmount;
		UserData userInfo = this.userData.get(bankAccountNumber);
		double chargeAmount = withDrawalAmount + this.transactionFee;
		double userBalance = this.availableBalance(bankAccountNumber) - chargeAmount;
		userInfo.setUserBalance(userBalance);
		System.out.println("************************************************************");
		System.out.println("Here is your money, and we also charge you a " + this.transactionFee + " dollor transation fee from your account.");
		System.out.println("************************************************************");
		userInfo.setRecentTransaction("withDrawal - " + withDrawalAmount + " - " + userBalance);
		
	}

	public void deposit(String bankAccountNumber, double depositAmount) {
		if (bankAccountNumber == null) {
			System.out.println("Deposit account is null.");
			return;
		}
		UserData userInfo = this.userData.get(bankAccountNumber);
		double actualDepositAmount = depositAmount - this.transactionFee;
		double userBalance = this.availableBalance(bankAccountNumber) + actualDepositAmount;
		userInfo.setUserBalance(userBalance);

		this.availableAmountInMachine += depositAmount;
		System.out.println("************************************************************");
		System.out.println("You deposit " + depositAmount + " , and we charge you a transation fee of " + this.transactionFee);
		System.out.println("************************************************************");
		userInfo.setRecentTransaction("Deposit - " + depositAmount + " - " + userBalance);

	}

	public void recentTransactions(String bankAccountNumber) {
		if (bankAccountNumber == null) {
			System.out.println("recentTransactions account is null.");
			return;
		}
		UserData userInfo = this.userData.get(bankAccountNumber);
		while (userInfo.getRecentTransaction().size() > MAX_TRANACTION_NUMBER) {
			userInfo.getRecentTransaction().poll();
		}

		System.out.println("************************************************************");
		System.out.println("Here is your recent transations: ");
		System.out.println("Transation Name - Amount of Transation - Available Balance");
		Queue<String> queue = userInfo.getRecentTransaction();
		for (String item : queue) {
			System.out.println (item);
		}
		System.out.println("************************************************************");
	}

	public StringBuffer importFileToStringBuffer(String fileName) {
		FileInputStream inputStream = null;
		InputStreamReader inputReader = null;
		BufferedReader bufferReader = null;

		StringBuffer strBuffer = new StringBuffer();

		try{
			inputStream = new FileInputStream(fileName);
			inputReader = new InputStreamReader(inputStream);
			bufferReader = new BufferedReader(inputReader);

			String line = null;

			while((line = bufferReader.readLine()) != null) {
				strBuffer.append(line);
				strBuffer.append("\n");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Can not find the file.");
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				bufferReader.close();
				inputReader.close();
				inputStream.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return strBuffer;
	} 
	
	public void storeUserData(StringBuffer strBuffer){
		String[] userInfo = null;
		String[] userArrs = strBuffer.toString().split("\n");
		for (String userArr : userArrs) {
			userInfo = userArr.split("\\|");
			User newUser = new User(userInfo[4], Integer.parseInt(userInfo[5]), userInfo[6], userInfo[7],userInfo[0]);

			Queue<String> tempQueue = new LinkedList<>();
			String[] queueString = userInfo[3].split(",");
			for (String queue : queueString) {
				tempQueue.offer(queue);
			}

			UserData newUserData = new UserData(newUser, userInfo[1], Double.parseDouble(userInfo[2]),tempQueue);
			this.userData.put(userInfo[0], newUserData);
		}
	}

	public void writeAllUserInfoIntoFile() {
		StringBuffer stringBuffer = new StringBuffer();

		Iterator iter = this.userData.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String bankAccountNumber = (String) entry.getKey();
			UserData userData = (UserData) entry.getValue();
			stringBuffer.append(bankAccountNumber);
			stringBuffer.append("|");
			stringBuffer.append(userData.getPassword());
			stringBuffer.append("|");
			stringBuffer.append(userData.getUserBalance());
			stringBuffer.append("|");

			Queue<String> queue = userData.getRecentTransaction();
			int size = queue.size();
			for (int i = 0; i < size - 1; i++) {
				stringBuffer.append(queue.poll());
				stringBuffer.append(",");
			}
			stringBuffer.append(queue.poll()); //The last one should not have a ","

			stringBuffer.append("|");
			stringBuffer.append(userData.getUser().getName());
			stringBuffer.append("|");
			stringBuffer.append(userData.getUser().getYearOfBirth());
			stringBuffer.append("|");
			stringBuffer.append(userData.getUser().getAddress());
			stringBuffer.append("|");
			stringBuffer.append(userData.getUser().getPhoneNumber());
			stringBuffer.append("\n");
		}
		

		try{
			FileWriter writer = new FileWriter("./userdata.txt");
			writer.write(stringBuffer.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//txt : bankAccountNumber, password, userBalance, recentTransaction, name, yearOfBirth, address, phoneNumber
}
