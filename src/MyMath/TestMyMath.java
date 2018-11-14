package MyMath;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * @author qusai trabeh.
 * this class tests both classes.
 *
 */
class TestMyMath extends Polynom {
	private static Polynom p = new Polynom("-x^3+3x^2-5x-6");
	private static Monom m = new Monom(1, 2);



	@Test
	void testPolynomPolynom() {
		Polynom_able test = new Polynom(p);
		if (!p.equals(test))
			fail("the copy constactor fail");
	}


	@Test
	void testPolynomString() {
		Polynom_able test = new Polynom();
		test.add(new Monom(-1, 3));
		test.add(new Monom(3, 2));
		test.add(new Monom(-5, 1));
		test.add(new Monom(-6, 0));
		if (!p.equals(test))
			fail("the String constactor Fail");
	}


	@Test
	void testF() {
		if (p.f(5) != -81)
			fail("expected another result");
	}

	
	@Test
	void testAddPolynom_able() {
		Polynom_able test = new Polynom(p);
		test.add(p);
		Polynom test2 = new Polynom("-2x^3+6x^2-10x-12");
		if (!test2.equals(test))
			fail("AddPolynom_able Fail");
	}

	
	@Test
	void testAddMonom() {
		Polynom test = new Polynom(p);
		test.add(new Monom(-1, 1));
		if (!test.equals(new Polynom("-x^3+3x^2-6x-6")))
			fail("Add Fail");
	}

	
	@Test
	void testToString() {
		String test = "-x^3+3.0x^2-5.0x-6.0";
		if (!test.equals(p.toString()))
			fail("ToString not as expected");
	}

	@Test
	void testSubstract() {
		Polynom test = new Polynom(p);
		test.substract(new Polynom("-x^3+3x^2-5x-6"));
		if (!test.equals(new Polynom("0")))
			fail("Substract Fail");
	}

	
	@Test
	void testMultiply() {
		Polynom test = new Polynom(p);
		test.multiply(new Polynom("x^2-5+x"));
		if (!test.equals(new Polynom("-x^5+2x^4+3x^3-26x^2+19x+30")))
			fail("Multiply Fail");
	}

	
	@Test
	void testEqualsPolynom_able() {
		Polynom_able test = new Polynom();
		test.add(new Monom(-1, 3));
		test.add(new Monom(3, 2));
		test.add(new Monom(-5, 1));
		test.add(new Monom(-6, 0));
		if (!p.equals(test))
			fail("EqualsPolynom_able Fail");
	}


	@Test
	void testIsZero() {
		if (p.isZero())
			fail("IsZero Fail");
	}

	
	@Test
	void testRoot() {
		double x = p.root(-2, 0, 0.0001);
		if (x != -0.7624969482421875)
			fail("Root Fail");
	}

	
	@Test
	void testCopy() {
		Polynom test = (Polynom) p.copy();
		if (!test.equals(p))
			fail("Copy Fail");
	}


	@Test
	void testDerivative() {
		Polynom test = (Polynom) p.copy();
		Polynom test2 = new Polynom("5");
		if (!test.derivative().equals(new Polynom("-3x^2+6x-5")) && !test2.derivative().equals(new Polynom("0")))
			fail("Derivative Fail");
	}


	@Test
	void testArea() {
		double x = p.area(-4, -2, 0.0001);
		if ((int) x != 134)
			fail("the area function have to return : 134");
	}


	@Test
	void testMonomString() {
		Monom test = new Monom("x^2");
		if (!test.equals(new Monom(1, 2)))
			fail("Monom(String) Fail");
	}


	@Test
	void testMonomMonom() {
		Monom test = new Monom(m);
		if (!test.equals(m))
			fail("Monom(Monom) Fail");
	}


	@Test
	void testEqualsMonom() {
		Monom test = new Monom(m);
		if (test.get_coefficient() != m.get_coefficient() || test.get_power() != m.get_power())
			fail("Monom equals Fail");
	}


	@Test
	void MtestGet_coefficient() {
		Monom test = new Monom(5, 4);
		if (test.get_coefficient() != 5)
			fail("Coefficient Fail");
	}


	@Test
	void MtestGet_power() {
		Monom test = new Monom(5, 4);
		if (test.get_power() != 4)
			fail("GetPower Fail");
	}


	@Test
	void MtestDerivative() {
		if (!m.derivative().equals(new Monom("2x")))
			fail("Monom Derivative Fail");
	}


	@Test
	void testAdd() {
		Monom test = new Monom(5, 4);
		test.add(new Monom(3, 4));
		if (test.get_coefficient() != 8)
			fail("Monom Add Fail");
	}


	@Test
	void MtestMultiply() {
		Monom test = new Monom(5, 4);
		test.multiply(m);
		if (!test.equals(new Monom(5, 6)))
			fail("Monom Multiply Fail");
	}


	@Test
	void MtestF() {
		if (m.f(4) != 16)
			fail("F(x) Fail");
	}


	@Test
	void MtestToString() {
		if (!m.toString().equals("x^2"))
			fail("MonomToString Fail");
	}


	@Test
	void MtestIsZero() {
		if (m.isZero())
			fail("MonomIsZero Fail");

	}
}
