import java.util.Arrays;

public class Assignment3 {
	public static void main(String args[]) {
		//3. Write a Java function to remove vowels in a string. 
		RemoveVowels removeVowels = new RemoveVowels();
		String result = removeVowels.removeVowelsFromString("hello");
		//String result = removeVowels.removeVowelsFromString("helabcsaueikklo");
		//String result = removeVowels.removeVowelsFromString("ae");
		System.out.println("3. remove vowels of hello is : " + result);

		//4. Write a java function to check if two strings are Anagrams or not.
		AngramNumber anagrams = new AngramNumber();
		boolean isAnagrams = anagrams.checkIfTwoStringsAreAnagrams("tea", "eat");
		System.out.println("4. anagrams? : " + isAnagrams);

		//5. Create a calculator that can perform the following features.
		Calculator calculator = new Calculator();

		double add = calculator.add(3.0, 90.8);
		System.out.println("5.1 3.0 + 90.8 = " + add);

		double sub = calculator.sub(9.0, 8.0);
		//double sub = calculator.sub(8.0, 8.0);
		//double sub = calculator.sub(7.0, 8.0);
		System.out.println("5.2 9.0 - 8.0 = " + sub);

		double mult = calculator.mult(3.0, 8.2);
		//double mult = calculator.mult(0.0, 8.2);
		System.out.println("5.3 3.0 * 8.2 = " + mult);

		double div = calculator.div(77, 8.9);
		//double div = calculator.div(77, 0);
		//double div = calculator.div(7, 8.9);
		System.out.println("5.4 77 / 8.9 = " + div);

		double squareRoot = calculator.squareRoot(9);
		//double squareRoot = calculator.squareRoot(0);
		System.out.println("5.5 9's squareRoot is : " + squareRoot);

		double square = calculator.square(8.9);
		//double square = calculator.square(-8.9);
		System.out.println("5.6 8.9's square is : " + square);

		double cube = calculator.cube(7.0);
		//double cube = calculator.cube(-7.0);
		System.out.println("5.7 7.0's cube is : " + cube);

		double celsius = calculator.fahrenheitToCelsius(87.3);
		System.out.println("5.8 87.3 fahrenheit = " + celsius + " celsius");

		double fahrenheit = calculator.celsiusToFahrenheit(39.2);
		System.out.println("5.9 39.2 celsius = " + fahrenheit + " fahrenheit");

		double inches = calculator.feetToInches(39.1);
		System.out.println("5.10 39.1 feet = " + inches + " inches");
		double feet = calculator.inchesToFeet(22.3);
		System.out.println("5.11 22.3 inches = " + feet + " feet");

		//
		//double[] solution = calculator.quardraticEquation(4, 0, 1);
		double[] solution = calculator.quardraticEquation(4, 0, -1);
		//double[] solution = calculator.quardraticEquation(4, 2, -1);
		//double[] solution = calculator.quardraticEquation(1, 2, 1);
		//double[] solution = calculator.quardraticEquation(0, 0, 1);
		//double[] solution = calculator.quardraticEquation(0, 1, 1);
		if (solution != null) {
			for (int i = 0; i < 2; i++) {
				System.out.println("result[ " + i + " ] = " + solution[i]);
			}
		}
	}
}

//3. Write a Java function to remove vowels in a string. 
class RemoveVowels {
	public String removeVowelsFromString(String input){
		if (input == null) {
			return null;
		}
		String vowels = "aeiouAEIOU";
        char[] inputChar = input.toCharArray();
        String result = "";

        for(int i = 0; i < inputChar.length; i++) {
	        if (vowels.contains(inputChar[i] + "")) {
	        	continue;
	        }
	        result += inputChar[i];
	    }
	    return result;
    }
}

//4. Write a java function to check if two strings are Anagrams or not.
class AngramNumber {
	public boolean checkIfTwoStringsAreAnagrams(String s1, String s2){
		if (s1 == null || s2 == null) {
			return true;
		}
		if (s1.length() != s2.length()) {
			return false;
		}

		char[] s1Char = s1.toCharArray();
		char[] s2Char = s2.toCharArray();

		Arrays.sort(s1Char);
		Arrays.sort(s2Char);

		return Arrays.equals(s1Char,s2Char);
	}
}

//5. Create a calculator that can perform the following features.
class Calculator {
	public double add (double parameter1, double parameter2) {
		return parameter1 + parameter2;
	}
	public double sub (double parameter1, double parameter2) {
		return parameter1 - parameter2;
	}
	public double mult (double parameter1, double parameter2) {
		return parameter1 * parameter2;
	}
	public double div (double parameter1, double parameter2) {
		if (parameter2 == 0) {
			System.out.println("Dividend should not be 0!");
		}
		return parameter1 / parameter2;
	}
	public double squareRoot (double parameter) {
		return Math.sqrt(parameter);
	}
	public double square (double parameter) {
		return parameter * parameter;
	}
	public double cube (double parameter) {
		return parameter * parameter * parameter;
	}
	public double fahrenheitToCelsius(double fahrenheit){
		return (fahrenheit - 32.0) / 1.8;
	}
	public double celsiusToFahrenheit (double celsius){
		return celsius * 1.8 + 32.0;
	}
	public double feetToInches (double feet){
		return feet * 12.0;
	}
	public double inchesToFeet (double inches){
		return inches / 12.0;
	}
	public double[] quardraticEquation(double parameter1, double parameter2, double parameter3) {
		double[] result = new double[2];
	
		if (parameter1 == 0) {
			if (parameter2 == 0) {
				System.out.println("This is not a quardratic equation! parameter1 and parameter2 both are 0");
				return null;
			} else {
				result[0] = -parameter3 / parameter2;
				result[1] = result[0];
				return result;
			}
		}
		double delta = square(parameter2) - 4 * parameter1 * parameter3;

		if (delta == 0) {
			result[0] = div(-parameter2, 2 * parameter1);
			result[1] = result[0];
		} else if (delta > 0) {
			delta = squareRoot(delta);
			result[0] = div(-parameter2 + delta, 2 * parameter1);
			result[1] = div(-parameter2 - delta, 2 * parameter1);
		} else {
			System.out.println("This quardratic equation has no solution");
			return null;
		}
		return result;
	}
}
//无解时打印会出错   除数为0时怎么处理？ 最后一题返回值是否正确？是否需要封装函数或者简化写法？