import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;
import java.util.*;

public class Assignments {
	public static final int MAX_FAIL_LOGIN_TIMES = 3;
	public static final int TRANSACTION_FEE = 2;
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String args[]) {
		//1.Exception
		int upperBound = 7;
		int lowerBound = 0;
		int index = 8;
		try {
			if (index < lowerBound || index >= upperBound) {
				throw new MyIndexOutOfBoundException(lowerBound, upperBound, index);
			}
		} catch (MyIndexOutOfBoundException e) {
			System.out.println(e.toString());
		}

		//2.Modify the following parse() method so that it will compile
		File file = new File("/");
		//File file = new File("./Assignments.java");
		parse(file);

		//3.ATM
		User lucy = new User("Lucy", 1992, "3205 11th AVE NE, Seattle, WA, 98105", "2069921903", "123456");
		User david = new User("David", 1988, "4520 12th AVE NE, Seattle, WA, 98105", "2061234567", "987666");
		User jack = new User("Jack", 1979, "8212 3th AVE NE, Seattle, WA, 98105", "2064564567", "");
		UserData lucyData = new UserData(lucy, "1928");
		UserData davidData = new UserData(david, "3746", 10000.0);

		HashMap<String, UserData> userDatas = new HashMap<String, UserData>();
		userDatas.put("123456", lucyData);
		userDatas.put("987666", davidData);

		Atm atm = new Atm(5000.0, TRANSACTION_FEE, userDatas);

		//Extra credit:Implement the above "ATM" by storing the above data in a file, rather than in a dataStructure
		//user data store in a txt file, each user's info stores in one line, each item seperates by "|"
		//Read all the info in the txt and then store then in the data structure
		String fileName = "./userdata.txt";
		StringBuffer stringBuffer = atm.importFileToStringBuffer(fileName);
		atm.storeUserData(stringBuffer);
		

		System.out.println("3.");
		System.out.println("Welcome to use the ATM.");
		System.out.println("Are you a new user? (Y/N)");
		String input = scanner.next().toUpperCase();
		if (input.equals("Y")) {
			System.out.println("Please enter your name: ");
			String name = scanner.next();
			System.out.println("Please enter your year of birth: ");
			int yearOfBirth = scanner.nextInt();
			System.out.println("Please enter your address: ");
			String address = scanner.next();
			System.out.println("Please enter your phoneNumber: ");
			String phoneNumber = scanner.next();
			System.out.println("Please enter your password (4 numbers): ");
			String password = scanner.next();

			String bankAccountNumber = atm.generateUniqueBankAccountNumber();

			User newUser = new User(name, yearOfBirth, address, phoneNumber, bankAccountNumber);
			UserData newUserData = new UserData(newUser, password);
			userDatas.put(bankAccountNumber, newUserData);
			atm.setUserData(userDatas);

			
			chooseService(bankAccountNumber, atm);

		}else if (input.equals("N")) {
			
			System.out.println("Please enter your bank account number: ");
			String bankAccountNumber = scanner.next();
			System.out.print("Please enter your password (4 numbers): ");
			String password = scanner.next();
			boolean isSuccess = isLogin(atm, bankAccountNumber, password);////
			 if (isSuccess) {
			 	chooseService(bankAccountNumber, atm);
			 } else {
			 	System.out.println("************************************************************");
				System.out.println("Log failed, there are must be something wrong...");
				System.out.println("************************************************************");
			 }
		} else {
			System.out.println("You can only enter Y or N!");
		}

	}
	public static void parse(File file) {
		RandomAccessFile input = null;
		String line = null;

		try {
			input = new RandomAccessFile(file, "r");
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			return;
		} catch (FileNotFoundException e) {
			System.out.println("2. Don't find a file!");
		} catch (IOException ex) {
			System.out.println("2. IO exception!");
		}

		finally {
			if (input != null) {
				try{
					input.close();
				} catch (IOException e){
					System.out.println("2. IO exception in finally!");
				}
				
			}
		}
	}

	public static boolean isLogin (Atm atm, String bankAccountNumber, String password) {
		//login for MAX 3 times
		if (!atm.isValidBankAccount(bankAccountNumber)) {
			System.out.println("************************************************************");
			System.out.println("Bank account number is invalid!");
			return false;
		}
		boolean successfullyLogin = atm.isLoginSuccess(bankAccountNumber, password);
		if (successfullyLogin) {
			return true;
		} else {
			for (int i = 0; i < MAX_FAIL_LOGIN_TIMES - 1; i++) {
				System.out.println("Failed to login, do you want to try again? (Y/N)");
				String input = scanner.next().toUpperCase();
				if (input.equals("Y")) {
					System.out.print("Please enter your password (4 numbers): ");
					password = scanner.next();
					successfullyLogin = atm.isLoginSuccess(bankAccountNumber, password);
					if(successfullyLogin) {
						return true;
					}
				}
				if (input.equals("N")) {
					return false;
				}
			}
			//login failed
			System.out.println("You failded three times! Do you need to use FORGET PASSWORD to reset your password? (Y/N)");
			String input = scanner.next().toUpperCase();
			//forget password
			if (input.equals("Y")) {
				return forgetPassword(bankAccountNumber, atm);
			}
			return false;
		}
	}
	public static boolean forgetPassword(String bankAccountNumber, Atm atm) {
		System.out.println("Please enter your name: ");
		String nameLowerCase = scanner.next().toLowerCase();
		System.out.println("Please enter your year of birth: ");
		int yearOfBirth = scanner.nextInt();
		System.out.println("Please enter your phoneNumber: ");
		String phoneNumber = scanner.next();

		if (atm.isValidUser(bankAccountNumber, nameLowerCase, yearOfBirth, phoneNumber)) {
			System.out.println("Please enter your new password (4 numbers): ");
			String newPassword = scanner.next();
			atm.resetPassword(newPassword, bankAccountNumber);
			return true;
		}
		System.out.println("************************************************************");
		System.out.println("Sorry! Failed to reset your password. Some of your information is wrong.");
		System.out.println("************************************************************");
		return false;
	}
	public static void chooseService(String bankAccountNumber, Atm atm) {
		System.out.println();
		System.out.println("Enter a number to choose your service : ");
		System.out.println("1. Check your available balance.");
		System.out.println("2. Withdrawal");
		System.out.println("3. Deposit");
		System.out.println("4. Check your recent transactions");
		System.out.println("5. Change your password");
		System.out.println("6. Exit");
		System.out.println();
		int serviceNum = scanner.nextInt();
		if (serviceNum == 1) {
			double userBalance = atm.availableBalance(bankAccountNumber);
			System.out.println("************************************************************");
			System.out.println("Your available balance is: " + userBalance);
			System.out.println("************************************************************");
			chooseService(bankAccountNumber, atm);
		} else if (serviceNum == 2) {
			System.out.println("Please enter the amount you want to withdrawal: ");
			double withDrawalAmount = scanner.nextDouble();
			atm.withDrawal(bankAccountNumber, withDrawalAmount);

			chooseService(bankAccountNumber, atm);
		} else if (serviceNum == 3) {
			System.out.println("Please enter the amount you want to deposit: ");
			double depositAmount = scanner.nextDouble();
			atm.deposit(bankAccountNumber, depositAmount);

			chooseService(bankAccountNumber, atm);
		} else if (serviceNum == 4) {
			atm.recentTransactions(bankAccountNumber);
			chooseService(bankAccountNumber, atm);
		} else if (serviceNum == 5) {
			forgetPassword(bankAccountNumber, atm);
			chooseService(bankAccountNumber, atm);
		} else if (serviceNum == 6) {
			exit(atm);
		} else {
			System.out.println("You enter a wrong number! We don't have this service.");
		}
	}
	public static void exit(Atm atm) {
		System.out.println("************************************************************");
		System.out.println("Thank you for using this ATM. Have a nice day!");
		System.out.println("************************************************************");

		atm.writeAllUserInfoIntoFile();
	}

}