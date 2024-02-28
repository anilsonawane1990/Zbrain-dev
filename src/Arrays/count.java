package Arrays;

public class count {

	public static void main(String[] args) {
		String a = "anil balu sonawane";
		int c = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != ' ') {
				c++;
			}

		}
		System.out.println(c);
	}

}
