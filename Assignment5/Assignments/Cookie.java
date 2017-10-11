import java.math.BigDecimal;

public class Cookie extends DessertItem {
	private int number;
	private int pricePerDozen;
	private int cost;
	public Cookie() {}
	public Cookie(String name, int number, int pricePerDozen) {
		super(name);
		this.number = number;
		this.pricePerDozen = pricePerDozen;
	}
	public int getCost() {
		return new BigDecimal(this.number * this.pricePerDozen / DessertShoppe.DOZEN).setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
	}
	public String toString(){
		double priceByDollar = this.pricePerDozen / DessertShoppe.CENTS_TO_DOLLAR;
		if (priceByDollar > 1) {
			return this.number + " @ " + DessertShoppe.cents2dollarsAndCentsmethod(this.pricePerDozen) + " /dz." + "\n" + super.getName() + Checkout.appendSpaceAfterName(super.getName().length());
		} else {
			return this.number + " @ ." + this.pricePerDozen + " /dz." + "\n" + super.getName() + Checkout.appendSpaceAfterName(super.getName().length());
		}
	}
}