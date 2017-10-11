import java.math.BigDecimal;

class Candy extends DessertItem {
	private double weight;
	private int pricePerPound;
	private int cost;
	public Candy(){}
	public Candy(String name, double weight, int pricePerPound){
		super(name);
		this.weight = weight;
		this.pricePerPound = pricePerPound;
	}
	public int getCost() {
		return new BigDecimal(this.weight * this.pricePerPound).setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
	}
	public String toString(){
		double priceByDollar = this.pricePerPound / DessertShoppe.CENTS_TO_DOLLAR;
		if (priceByDollar > 1) {
			return this.weight + "lbs. @ " + DessertShoppe.cents2dollarsAndCentsmethod(this.pricePerPound) + " /lb." + "\n" + super.getName() + Checkout.appendSpaceAfterName(super.getName().length());
		} else {
			return this.weight + "lbs. @ ." + this.pricePerPound + " /lb." + "\n" + super.getName() + Checkout.appendSpaceAfterName(super.getName().length());
		}
	}
}