public class IceCream extends DessertItem {
	private int cost;
	public IceCream() {};
	public IceCream(String name, int cost) {
		super(name);
		this.cost = cost;
	}
	public int getCost() {
		return this.cost;
	}
	public String toString() {
		return super.name + Checkout.appendSpaceAfterName(super.name.length());
	}
}