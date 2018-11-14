package MyMath;

public class Test {

	public static void main(String[] args) {
		Polynom p1 = new Polynom("-13x^5+2x^6-7x^3-28x^2+19x-0.5");
		Polynom p2 = new Polynom("1x+26x^4+0.5");
		Polynom p3 = (Polynom)p1.copy();
		p3.add(p2);

		System.out.println("p3 String : " + p3.toString());
		System.out.println("f(0.5) : " + p3.f(0.5));
		System.out.println("f(1.5) : " + p3.f(1.5));
		System.out.println("root(0.5, 1.5, 0.001) : " + p3.root(0.5, 1.5, 0.001));
		System.out.println("Derivative : " + p3.derivative().toString());
		System.out.println("area(0, 1, 0.001) : " + p3.area(0, 1, 0.001));
		p3.Graph(-2, 4);
		
	}

}
