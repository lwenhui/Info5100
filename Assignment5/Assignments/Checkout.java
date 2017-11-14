import java.math.BigDecimal;
import java.util.ArrayList;

public class Checkout {
	private ArrayList<DessertItem> dessertItems;
	public Checkout() {
		this.dessertItems = new ArrayList<DessertItem>();
	}
	public int numberOfItems() {
		return this.dessertItems.size();
	}
	public void enterItem(DessertItem item) {
		this.dessertItems.add(item);
	}
	public void clear() {
		this.dessertItems = new ArrayList<DessertItem>();
	}
	public int totalCost() {
		int totalCost = 0;
		for (int i = 0; i < dessertItems.size(); i++) {
			totalCost += dessertItems.get(i).getCost();
		}
		return totalCost;
	}
	public int totalTax() {
		return new BigDecimal(totalCost() * DessertShoppe.TAX_RATE).setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
	}
	public String toString() {
		// candy's weight is not formatted correctly and its displaying a huge floating point in display
		StringBuilder display = new StringBuilder();

		int len = DessertShoppe.NAME_OF_SHOP.length();
		int spaceNum = (DessertShoppe.WIDTH_ON_RECEIPT - len) / 2;
		displayHead(spaceNum, display, DessertShoppe.NAME_OF_SHOP);
		display.append("\n");

		StringBuilder dashString = generateDashString(len);
		displayHead(spaceNum, display, dashString.toString());
		display.append("\n");
		display.append("\n");

		for (int i = 0; i < this.dessertItems.size(); i++) {
			display.append(this.dessertItems.get(i).toString());

			int lengthBtw = DessertShoppe.WIDTH_ON_RECEIPT - DessertShoppe.MAX_SIZE_OF_NAME - DessertShoppe.WIDTH_OF_COST;
			displaySpace(lengthBtw, display);

			display.append(DessertShoppe.cents2dollarsAndCentsmethod(this.dessertItems.get(i).getCost()));
			display.append("\n");
		}
		display.append("\n");
		display.append("TAX");
		int lengthBtw = DessertShoppe.WIDTH_ON_RECEIPT - DessertShoppe.LENGTH_OF_TAX - DessertShoppe.WIDTH_OF_COST;
		displaySpace(lengthBtw, display);
		if (this.totalTax() > 100) {
			display.append(DessertShoppe.cents2dollarsAndCentsmethod(this.totalTax()));
		} else {
			display.append(" .");
			display.append(this.totalTax());
		}
		
		display.append("\n");
		display.append("Total Cost");
		lengthBtw = DessertShoppe.WIDTH_ON_RECEIPT - DessertShoppe.LENGTH_OF_TOTAL_COST - DessertShoppe.WIDTH_OF_COST;
		displaySpace(lengthBtw, display);
		display.append(DessertShoppe.cents2dollarsAndCentsmethod(this.totalCost() + this.totalTax()));

		return display.toString();
	}
	public void displaySpace(int spaceNum, StringBuilder display) {
		for (int i = 0; i < spaceNum; i++) {
			display.append(" ");
		}
	}
	public void displayHead(int len, StringBuilder display, String headName) {
		displaySpace(len,display);
		display.append(headName);
		displaySpace(len,display);
	}
	public StringBuilder generateDashString(int len) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < len; i++) {
			result.append("-");
		}
		return result;
	}
	public static String appendSpaceAfterName(int length) {
		StringBuilder space = new StringBuilder();
		int len = DessertShoppe. MAX_SIZE_OF_NAME - length;
		for (int i = 0; i < len; i++) {
			space.append(" ");
		}
		return space.toString();
	}
}
