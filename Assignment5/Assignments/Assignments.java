/* Good Work
 * Score 9.5 + extra credit 2; Total score 10
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Assignments {
	public static void main(String[] args) {

		Checkout checkout = new Checkout();

		checkout.enterItem(new Candy("Peanut Butter Fudge", 2.25, 399));
		checkout.enterItem(new IceCream("Vanilla Ice Cream", 105));
		checkout.enterItem(new Sundae("Choc. Chip Ice Cream", 145, "Hot Fudge", 50));
		checkout.enterItem(new Cookie("Oatmeal Raisin Cookies", 4, 399));

		System.out.println("\nNumber of items: " + checkout.numberOfItems() + "\n");
		System.out.println("\nTotal cost: " + checkout.totalCost() + "\n");
		System.out.println("\nTotal tax: " + checkout.totalTax() + "\n");
		System.out.println("\nCost + Tax: " + (checkout.totalCost() + checkout.totalTax()) + "\n");
		System.out.println(checkout);

		checkout.clear();

		checkout.enterItem(new IceCream("Strawberry Ice Cream", 145));
		checkout.enterItem(new Sundae("Vanilla Ice Cream", 105, "Caramel", 50));
		checkout.enterItem(new Candy("Gummy Worms", 1.33, 89));
		checkout.enterItem(new Cookie("Chocolate Chip Cookies", 4, 399));
		checkout.enterItem(new Candy("Salt Water Taffy", 1.5, 209));
		checkout.enterItem(new Candy("Candy Corn", 3.0, 109));

		System.out.println("\nNumber of items: " + checkout.numberOfItems() + "\n");
		System.out.println("\nTotal cost: " + checkout.totalCost() + "\n");
		System.out.println("\nTotal tax: " + checkout.totalTax() + "\n");
		System.out.println("\nCost + Tax: " + (checkout.totalCost() + checkout.totalTax()) + "\n");
		System.out.println(checkout);

		//Extra Credit
		Spiral spiralOrderOfMatrix = new Spiral();
		int[][] matrix = {{1, 2, 3}, 
						  {4, 5, 6},
                          {7, 8, 9}};
        List<Integer> result = new ArrayList<Integer>();
        result = spiralOrderOfMatrix.spiralOrder(matrix);
        Iterator itera = result.iterator();
        System.out.println();
        System.out.println("Spiral order is : ");
        while (itera.hasNext()) {
            System.out.print(itera.next() + " ,");
        }
        System.out.println();
	}
}
