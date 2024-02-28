package Arrays;

public class test {

	public static void main(String[] args) {

		int a[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
		System.out.println(a.length);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		System.out.println();
		int b[][] = { { 1, 2, 3, 6 }, { 3, 4, 6 }, { 5, 6, 8 } };
		System.out.println(b.length);
		System.out.println(b[1].length);
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b.length; j++) {
				System.out.print(b[i][j] + " ");

			}
			System.out.println();
		}

	}

}
