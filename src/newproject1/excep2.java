package newproject1;

public class excep2 {

	public static void main(String[] args) {
		try {
			int a = 10 / 0;
		} catch (ArithmeticException r) {
			System.out.println(r.getMessage());
			r.getStackTrace();
		} finally {
			System.out.println("frfr");
		}

	}

}
