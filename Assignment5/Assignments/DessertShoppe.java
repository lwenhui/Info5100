import java.math.BigDecimal;

public class DessertShoppe {
	public static final double CENTS_TO_DOLLAR = 100.0;
	public static final double TAX_RATE = 0.065;
	public static final int MAX_SIZE_OF_NAME = 25;
	public static final int WIDTH_ON_RECEIPT = 34;
	public static final int WIDTH_OF_COST = 4;
	public static final int LENGTH_OF_TAX = 3;
	public static final int LENGTH_OF_TOTAL_COST = 11;
	public static final String NAME_OF_SHOP = "M & M DESSERT SHOPPE";
	public static final int DOZEN = 12;
	public static String cents2dollarsAndCentsmethod(int cents) {
		double dollor = new BigDecimal((double)cents / CENTS_TO_DOLLAR).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		String result = "";
		result += dollor;
		return result;
	}
}