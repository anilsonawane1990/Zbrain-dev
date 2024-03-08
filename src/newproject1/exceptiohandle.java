package newproject1;

public class exceptiohandle {

	public static void main(String[] args) {
		try {
			int a = 10 / 0;
		} catch (ArithmeticException b) {
			System.out.println(b.getMessage());
			b.getStackTrace();
		}

		finally {
			System.out.println("show code");
		}

	}

}
