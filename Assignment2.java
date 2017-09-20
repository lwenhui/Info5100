import java.util.ArrayList;

//1. Write a java function to calculate the salary of an employee based on the following rules
class Salary{
	
	private static final double HOURSECTION1 = 36.0;
	private static final double HOURSECTION2 = 36.0 + 5;
	private static final double HOURSECTION3 = 48.0;

	private static final double SALARYRATE1 = 15.0;
	private static final double SALARYRATE2 = 15.0 * 1.5;
	private static final double SALARYRATE3 = 15.0 * 2;

	public double employeeSalary (double hours) {
		double amount = 0;

		if(hours <= 0) {
			return 0.0;
		} else if (hours <= HOURSECTION1) {
			amount = hours * SALARYRATE1;
		} else if (hours > HOURSECTION1 && hours <= HOURSECTION2) {
			amount = HOURSECTION1 * SALARYRATE1 + (hours - HOURSECTION1) * SALARYRATE2;
		} else if (hours > HOURSECTION2 && hours < HOURSECTION3) {
			amount = HOURSECTION1 * SALARYRATE1 
			         + (HOURSECTION2 - HOURSECTION1) * SALARYRATE2
			         + (hours - HOURSECTION2) * SALARYRATE3;
		} else {
			amount = HOURSECTION1 * SALARYRATE1 
			         + (HOURSECTION2 - HOURSECTION1) * SALARYRATE2
			         + (HOURSECTION3 - HOURSECTION2) * SALARYRATE3;
		}
		return amount;
	}
}

//2. Write a java function that adds all the digits of an integer until it is single digit.
class AddDigits {
	public int addDigits( int input){
		if (input == 0) {
			return 0;
		}

		int result = 0;
		int digit = 0;
		while (input != 0) {
			digit = input % 10;
			result = (result * 10 + digit) % 9;
			input /= 10;
		}

		if (result == 0) {
			return 9;
		} else {
			return result;
		}
	}
}

//	3. Write a java function to print all perfect number between 1 and n.
class PerfectNumber {
	public  void printPerfectNumbers(int n){
		if (n == 0) {
			System.out.println("Input number should be larger than 1");
			return;
		}

		ArrayList<Integer> perfectNums = new ArrayList<Integer>();
		for (int i = 2; i <= n; i ++) {
			int sum = 0;
			for (int j = 1; j < i; j++) {
				if (i % j == 0) {
					sum += j;
				}
			}
			if (sum == i) {
				perfectNums.add(i);
			}
		}
		System.out.println("3. Perfect number between 1 and " + n + " is : ");
		for (int i = 0; i < perfectNums.size(); i++) {
				System.out.print(perfectNums.get(i) + " ");
		}
		System.out.println();
	}
}

//4.Write a java class called pizza with (Add detail as needed)
class Pizza {
	private String pizzaType;
	private double unitPrice;
	private int loyaltyPoints;

	public Pizza() {}

	public Pizza(String pizzaType, double unitPrice, int loyaltyPoints) {
		this.pizzaType = pizzaType;
		this.unitPrice = unitPrice;
		this.loyaltyPoints = loyaltyPoints;
	}

	public void setPizzaType (String pizzaType) {
		this.pizzaType = pizzaType;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public String getPizzaType() {
		return this.pizzaType;
	}
	public double getUnitPrice() {
		return this.unitPrice;
	}
	public int getLoyaltyPoints() {
		return this.loyaltyPoints;
	}
}

//5.Write a java class called customer (Add detail as needed)
class PizzaOrder {
	//String pizzaType;
	private Pizza pizza;
	private int nums;
	//double unitPrice;

	public PizzaOrder() {}

	public PizzaOrder(Pizza pizza, int nums) {
		this.pizza = pizza;
		this.nums = nums;
	}

	public double sumOfOneTypeOfPizza () {
		return this.nums * this.pizza.getUnitPrice();
	}

	public void setPizza () {
		this.pizza = pizza;
	}
	public void setNums () {
		this.nums = nums;
	}
	public Pizza getPizza () {
		return this.pizza;
	}
	public int getNums () {
		return this.nums;
	}
}
class Customer {
	private String name;
	private PizzaOrder[] order;

	public Customer() {}

	public Customer(String name, PizzaOrder[] order) {
		this.name = name;
		this.order = order;
	}

	public double sumPrice() {
		double sum = 0;
		for (int i = 0; i < this.order.length; i++) {
			sum += this.order[i].sumOfOneTypeOfPizza();
		}
		return sum;
	}

	public void setName() {
		this.name = name;
	}
	public void setOrder() {
		this.order = order;
	}
	public String getName() {
		return this.name;
	}
	public PizzaOrder[] getOrder() {
		return this.order;
	}
}

//6. Write a Java program that generates an isosceles right angled triangle made of asterisks. 
class PrintIsoscelesTriangle {
	public void printIsoscelesTriangle(int n) {
		if(n < 2) {
			System.out.println("Length should be larger than 1");
			return;
		}
		for (int i = 0; i < (n - 1); i++) {
			for (int j = 0; j <= i; j ++) {
				if (j == 0 || j == i) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		for (int i = 0; i < n; i++) {
			System.out.print("*");
		}
		System.out.println();
	}
}

public class Assignment2 {
	public static void main (String args[]) {
		//1.Write a java function to calculate the salary of an employee based on the following rules.
		Salary salary = new Salary();

		//double employeeSalaryAmount = salary.employeeSalary(24.2);
		//double employeeSalaryAmount = salary.employeeSalary(36);
		//double employeeSalaryAmount = salary.employeeSalary(37.99);
		//double employeeSalaryAmount = salary.employeeSalary(41);
		//double employeeSalaryAmount = salary.employeeSalary(48);
		//double employeeSalaryAmount = salary.employeeSalary(57.23);
		double employeeSalaryAmount = salary.employeeSalary(47.3);
		System.out.println("1. The salary of the employee's is : " + employeeSalaryAmount);

		System.out.println();
		//2. Write a java function that adds all the digits of an integer until it is single digit.
		AddDigits digit = new AddDigits();

		//int result = digit.addDigits(120);
		int result = digit.addDigits(1239);
		//int result = digit.addDigits(39);
		System.out.println("2. The result of add digits of 1239 is " + result);

		System.out.println();
		//3.Write a java function to print all perfect number between 1 and n.
		PerfectNumber num = new PerfectNumber();
		num.printPerfectNumbers(30);

		System.out.println();
		//4,5
		Pizza superme = new Pizza("Supreme", 10, 2);
		Pizza meatlover = new Pizza("MeatLover", 7.99, 1);

		PizzaOrder[] wenhuiOrder = new PizzaOrder[2];
		wenhuiOrder[0] = new PizzaOrder(superme, 3);
		wenhuiOrder[1] = new PizzaOrder(meatlover, 2);

		Customer Wenhui = new Customer("wenhui", wenhuiOrder);

		double totalPrice = Wenhui.sumPrice();
		System.out.println ("4&5. The total price of " + Wenhui.getName() + "'s order is : "+ totalPrice);

		System.out.println();
		//6. Write a Java program that generates an isosceles right angled triangle made of asterisks. 
		int lengthOfTriangle = 6;
		System.out.println("6. Below is an isosceles right angled triangle, input is: " + lengthOfTriangle);
		PrintIsoscelesTriangle triangle = new PrintIsoscelesTriangle();
		triangle.printIsoscelesTriangle(lengthOfTriangle);

	}
}