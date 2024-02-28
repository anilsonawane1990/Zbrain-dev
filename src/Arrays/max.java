package Arrays;

public class max {

	public static void main(String[] args) {
		int a[] = { 1, 3, 4, 5, 6, 7, 8, 9 };
		int max = 0;
		for (int i = 0; i < a.length; i++) {
			if (max < a[i]) {
				max = a[i];
			}

		}
		System.out.println(max);
	}

}
