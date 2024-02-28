package opps;

public class poly {

	public static void main(String[] args) {
		poly p = new poly();
		p.a();
		p.a('g');
		p.a(64);
		p.a(4);
		p.a(3, 'f');
	}

	public void a(int a) {
		System.out.println("method aaaaaa");
	}

	public void a(char d) {
		System.out.println("method aaaaa");
	}

	public void a(int g, char f) {
		System.out.println("method aaaa");
	}

	public void a(float r) {
		System.out.println("method aa");
	}

	public void a() {
		System.out.println("method a");

	}

}
