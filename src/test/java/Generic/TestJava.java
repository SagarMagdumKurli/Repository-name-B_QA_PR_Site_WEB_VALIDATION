package Generic;

import java.util.HashMap;
import java.util.Map;

public class TestJava {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestJava TJ = new TestJava();

		// TJ.NumberSwapping();
		// TJ.PrintPrimeNumber();
		// TJ.TestDuplicate();

//		TJ.SecondHighValue();
		
		TJ.ReverseString();

	}

	public void NumberSwapping() {
		int x = 5;

		int y = 10;

		y = y - x; // 5

		x = x + y;

		System.out.println("X=" + x + "Y=" + y);

	}

	public void PrintPrimeNumber() {
		int num = 100;
		boolean a = true;

		for (int i = 2; i <= num; i++) {

			for (int k = 2; k <= i / 2; k++) {
				if (i % k == 0) {
					a = false;
					System.out.println("This Number is not prime :" + i);
					break;
				}

			}

			if (a) {
				System.out.println("THis nuber is prime:" + i);
			}

			a = true;

		}

	}

	public void TestDuplicate() {

		String SK = "automation";
		char[] CH = SK.toCharArray();

		Map<Character, Integer> HMP = new HashMap<>();

		for (char KY : CH) {
			if (HMP.containsKey(KY)) {
				HMP.put(KY, HMP.get(KY) + 1);
			} else {
				HMP.put(KY, 1);
			}

		}

		System.out.println(HMP);

		System.out.println("Check Entery set: " + HMP.entrySet());
	}

	public void SecondHighValue() {
		// TODO Auto-generated method stub
		int[] a = { 2, 5, 3, 7 };

		int max = Integer.MIN_VALUE;
		int secondMax = Integer.MIN_VALUE;

		for (int num : a) {
			if (num > max) {
				secondMax = max;
				max = num;
			} else if (num > secondMax && num != max) {
				secondMax = num;
			}
		}
		System.out.println("Second Max:" + secondMax);

	}

	
	public void ReverseString() {

		String S = "Sagar";
		String J = "";

		for (int i = S.length() - 1; i >= 0; i--) {
			J = J + S.charAt(i);
		}

		System.out.println("Reverse String:" + J);

	}

}
