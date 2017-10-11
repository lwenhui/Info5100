public class Sundae extends IceCream {
	private String nameOfTopping;
	private int costOfTopping;
	
	public Sundae() {}
	public Sundae(String iceCreamName, int iceCreamCost, String nameOfTopping, int costOfTopping) { 
		super(iceCreamName, iceCreamCost);
		this.nameOfTopping = nameOfTopping;
		this.costOfTopping = costOfTopping;
	}
	public int getCost() {
		return super.getCost() + this.costOfTopping;
	}
	public String toString() {
		return this.nameOfTopping + " Sundae with" + "\n" + super.name + Checkout.appendSpaceAfterName(super.name.length());
	}
}