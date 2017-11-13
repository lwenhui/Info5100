/* good work
 * score 10+ extra credit 2; Total score 10
 */
//1.Reformate license key.
class FormateLicenseKey { // score 2
	public char toUpper(char input) {
		if (input >= 'a' && input <= 'z') {
			input = (char)(input - 32);
		}
		return input;
	}
	public String reformate(String lincenseKey, int k) {
		String NewLicenseKey = "";
		if (lincenseKey == null || lincenseKey.length() == 0) {
			return NewLicenseKey;
		}

		int count = k;

		char[] lincenseKeyToChar = lincenseKey.toCharArray();
		for (int i = lincenseKeyToChar.length - 1; i >= 0; i--) {
			if (lincenseKeyToChar[i] == '-') {
				continue;
			}
			if (count == 0) {
				NewLicenseKey = "-" + NewLicenseKey;
				count = k;
			}
			lincenseKeyToChar[i] = toUpper(lincenseKeyToChar[i]);
			NewLicenseKey = lincenseKeyToChar[i] + NewLicenseKey;
			count --;
		}
		return NewLicenseKey;
	}
}

//2.paper&scissor&rock
class Tool { // score 2
	public boolean WIN = true; // don't declare any variable in the class level unless it is a meaningful data.
	public boolean FAIL = false;
	protected int strength;
	protected char type;
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public boolean doubleStrengthFight (Tool tool) { // access is recommended to be private
		if (this.strength * 2 > tool.strength) {
			return WIN;
		} else {
			return FAIL;
		}
	}
	public boolean halfStrengthFight (Tool tool) { // access is recommended to be private
		if (this.strength > tool.strength * 2) {
			return WIN;
		} else {
			return FAIL;
		}
	}

}
class Rock extends Tool {
	public Rock(int strength) {
		super.setStrength(strength);
		type = 'r';
	}
	public boolean fight(Tool tool) {
		if (tool.type == 's') {
			return super.doubleStrengthFight(tool);
		}
		if (tool.type == 'p') {
			return super.halfStrengthFight(tool);
		}
		return FAIL;
	}
}
class Paper extends Tool {
	public Paper(int strength) {
		super.setStrength(strength);
		type = 'p';
	}
	public boolean fight(Tool tool) {
		if (tool.type == 'r') {
			return super.doubleStrengthFight(tool);
		}
		if (tool.type == 's') {
			return super.halfStrengthFight(tool);
		}
		return FAIL;
	}
}
class Scissors extends Tool {
	public Scissors(int strength) {
		super.setStrength(strength);
		type = 's';
	}
	public boolean fight(Tool tool) {
		if (tool.type == 'p') {
			return super.doubleStrengthFight(tool);
		}
		if (tool.type == 'r') {
			return super.halfStrengthFight(tool);
		}
		return FAIL;
	}
}

//3.IPAddress
class IpAddress { // score 2
	private String dottedDecimal;
	private int firstOctet;
	private int secondOctet;
	private int thirdOctet;
	private int fourthOctet;

	public IpAddress(String dottedDecimal) {
		this.dottedDecimal = dottedDecimal;

		String[] octet = dottedDecimal.split("\\.");
		firstOctet = Integer.parseInt(octet[0]);
		secondOctet = Integer.parseInt(octet[1]);
		thirdOctet = Integer.parseInt(octet[2]);
		fourthOctet = Integer.parseInt(octet[3]);
	}
	public String getDottedDecimal() {
		return dottedDecimal;
	}
	public int getOctet(int position) {
		if (position <= 0 || position > 4) {
			System.out.println("Position should between 1 and 4!");
			return -1;
		}
		if (position == 1) {
			return firstOctet;
		} else if (position == 2) {
			return secondOctet;
		} else if (position == 3) {
			return thirdOctet;
		} else {
			return fourthOctet;
		}
	}
}
//4.registration system
class Student{
	private String name;
	private int id;
	public Student() {}
	public Student(String name, int id) {
		this.name = name;
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
}
class Course { // score 2
	private final static int MAX_STUDENT_NUMBER = 10;
	private String name;
	private int numberOfStudent;
	private Student[] student;
	public Course() {}
	public Course(String name) {
		this.name = name;
		this.student = new Student[MAX_STUDENT_NUMBER];
		this.numberOfStudent = 0;
	}
	public Student[] getStudents() {
		return this.student;
	}
	public boolean isFull() {
		if (this.numberOfStudent > MAX_STUDENT_NUMBER) {
			return true;
		}
		return false;
	}
	public void registerStudent (Student student) { // check the condition of a single student registering multiple times
		if (!this.isFull()) {
			this.student[numberOfStudent] = student; /////////
			this.numberOfStudent++;
		}
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setNumberOfStudent (int numberOfStudent) {
		this.numberOfStudent = numberOfStudent;
	}
	public int getNumberOfStudent () {
		return this.numberOfStudent;
	}
}

//5.Int to roman
class IntToRoman { // score 2
	public String intToRoman(int num) {
		String result = "";
		if (num < 0) {
			return result;
		}
		int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		
		int digit = 0;
		while (num > 0) {
			int times = num / nums[digit];
			num = num - nums[digit] * times;
			for (int i = times; i > 0; i--) {
				result += romanSymbols[digit]; 
			}
			digit++;
		}
		return result;
	}
}

//Extra credit.Find median
class FindMedian{ // extra credit 2
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int len = nums1.length + nums2.length;
		if(len % 2 == 1) {
			return findKTh(nums1, 0, nums2, 0, len / 2 + 1);
		} else {
			return (findKTh(nums1, 0, nums2, 0, len / 2) + findKTh(nums1, 0, nums2, 0, len / 2 + 1)) / 2.0;
		}
	}
	public int findKTh(int[] nums1, int nums1_start, int[] nums2, int nums2_start, int k) {
		if (nums1_start >= nums1.length) {
			return nums2[nums2_start + k - 1];
		}
		if (nums2_start >= nums2.length) {
			return nums1[nums1_start + k - 1];
		}
		if (k == 1){
			return Math.min(nums1[nums1_start], nums2[nums2_start]);
		}

		int nums1_key = nums1_start + k / 2 - 1 < nums1.length ? nums1[nums1_start + k / 2 - 1] : Integer.MAX_VALUE;
		int nums2_key = nums2_start + k / 2 - 1 < nums2.length ? nums2[nums2_start + k / 2 - 1] : Integer.MAX_VALUE;

		if (nums1_key < nums2_key) {
			return findKTh(nums1, nums1_start + k / 2, nums2, nums2_start, k - k / 2);
		} else {
			return findKTh(nums1, nums1_start, nums2, nums2_start + k / 2, k - k / 2);
		}
	}
}

public class Assignments {
	public static void main (String args[]) {
		//1.Reformate license key.
		FormateLicenseKey LicenseKey = new FormateLicenseKey();
		//String inputLincenseKey = "2-4A0r7-4k";
		String inputLincenseKey = "2-4A0r7-4k";
		int k = 3;
		String result = LicenseKey.reformate(inputLincenseKey, k);
		System.out.println("1.Reformate license key. Input license key : " +
							inputLincenseKey + ", k : " + k + ", result : " +  result);

		//2.paper&scissor&rock
		System.out.println();
		System.out.println("2.paper&scissor&rock: ");
		Scissors s = new Scissors(5);
        Paper p = new Paper(7);
        Rock r = new Rock(15);

        System.out.println(s.fight(p) + " , "+ p.fight(s) );
        System.out.println(p.fight(r) + " , "+ r.fight(p) );
        System.out.println(r.fight(s) + " , "+ s.fight(r) );

        //3.IPAddress
        System.out.println();
		System.out.println("3.IPAddress:  ");
        IpAddress ip = new IpAddress("216.27.6.136");
        System.out.println(ip.getDottedDecimal());
        System.out.println(ip.getOctet(4));
        System.out.println(ip.getOctet(1));
        System.out.println(ip.getOctet(3));
        System.out.println(ip.getOctet(2));

        //5.Int to roman
        System.out.println();
		System.out.println("5.Int to roman:  ");
        IntToRoman numbers = new IntToRoman();
        //int nums = 32;
        //int nums = 3999;
        int nums = 1;
        String roman = numbers.intToRoman(nums);
        System.out.println("nums : " + nums + " -> roman: " + roman);

        //Extra credit.Find median
        System.out.println();
		System.out.println("Extra credit.Find median:  ");
        FindMedian median = new FindMedian();
        int[] nums1 = {1, 2};
        int[] nums2 = {2, 3};

        double medianValue = median.findMedianSortedArrays(nums1, nums2);
        System.out.println("The median is : " + medianValue);
	}
}
