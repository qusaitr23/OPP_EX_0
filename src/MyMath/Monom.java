package MyMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and b is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author qusai trabeh.
 *
 */
public class Monom implements function{

	private double _coefficient; 
	private int _power;

	private void set_coefficient(double a){
		this._coefficient = a;
	}	
	private void set_power(int p) {
		this._power = p;
	}
	
	/**
	 * Creates a new "Monom" of shape a*x^b, where a is a real number ands b is an none negative integer examples:[ax,x,ax^b,a,-x]
	 * @param s - summed valid String 
	 */	
	public Monom(String s) {
		this(Monom_string(s));
	}
	private static Monom Monom_string(String s) {
		s = s.toLowerCase();
		double a = 0;
		int b = 0;
		int indexOfX = s.indexOf("x");
		int indexOfP = s.indexOf("^");
		if(indexOfX == -1) {
			
			a = Double.parseDouble(s);
			b = 0;
		}
		if(indexOfX == 0) {
			a = 1;
			if (indexOfP == -1) {
				b = 1;
			}	
			else {
				b = Integer.parseInt(s.substring(indexOfP + 1, s.length() ) );
			}
		}
		if(indexOfX == 1) {
			if(s.charAt(0) == '-') {
				a = -1;
				if (indexOfP == -1) {
					b = 1;
				}	
				else {
					b = Integer.parseInt(s.substring(indexOfP + 1, s.length() ) );
				}
			}
			else if(s.charAt(0) == '+') {
				a = 1;
				if (indexOfP == -1) {
					b = 1;
				}	
				else {
					b = Integer.parseInt(s.substring(indexOfP + 1, s.length() ) );
				}
			}
			else {
				a = Double.parseDouble(s.charAt(0) + "");
				if (indexOfP == -1) {
					b = 1;
				}	
				else {
					b = Integer.parseInt(s.substring(indexOfP + 1, s.length() ) );
				}
			}
		}
		if (indexOfX > 1) {
			a = Double.parseDouble(s.substring(0, indexOfX));
			if (indexOfP == -1) {
				b = 1;
			}	
			else {
				b = Integer.parseInt(s.substring(indexOfP + 1, s.length() ) );
			}
		}
		return new Monom(a,b);
	}	

	/**
	 * Create a new "monom" of shape a*x^b
	 * @param a is real number
	 * @param b in an integer (summed non negative)
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}	

	/**
	 * Create a new copy of "ot" Monom
	 * @param ot - the Monom to copy
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	/**
	 * @param m - Monom 
	 * @return true if Monom m equals to this Monom, false otherwise
	 */
	public boolean equals(Monom m) {
		return m != null && this.get_coefficient() == m.get_coefficient() && this.get_power() == m.get_power();
	}

	/**
	 * @return the coefficient of this Monom
	 */
	public double get_coefficient() {
		return this._coefficient;
	}

	/**
	 * @return the power of this Monom
	 */
	public int get_power() {
		return this._power;
	}	

	/**
	 * Compute a new Monom which is the derivative of this Monom
	 * @return 
	 */
	public Monom derivative() {
		Monom der = null;
		if(this.get_power() > 0) {
//			der = new Monom(Math.round(this.get_coefficient() * this.get_power() * 1000) / 1000.0, this.get_power() - 1 );
			der = new Monom(this.get_coefficient() * this.get_power(), this.get_power() - 1 );
		}
		if(this.get_power() == 0) {
			der = new Monom(0 ,0);
		}

		return der;
	}

	/**
	 * Add m to this Monom
	 * @param m
	 */
	public void add(Monom m) {
		if(m != null && this._power == m._power) {
			this._coefficient += m._coefficient;
		}
	}

	/**
	 * Multiply this Monom by Monom m
	 * @param m
	 */
	public void multiply(Monom m) {
		if(m != null) {
			this._coefficient *= m._coefficient;
			this._power += m._power;
		}
	}

	@Override
	public double f(double x) {
		return this._coefficient*Math.pow(x, this._power);
	}

	/**
	 * returns a String such that it can be used for init an equal(s) Polynom
	 */
	public String toString() {
		String s = "";
		// ax^b,a=0      = 0
		if(this.get_coefficient() == 0) {                              
			return s += "0";
		}
		//ax^b a = 1 
		if(this.get_coefficient() == 1)
		{
			//a = 1 ,b = 1        = x
			if(this.get_power() == 1) {
				return s += "x";
			}
			//a = 1 ,b = 0        = 1
			else if(this.get_power() == 0) {
				return s += "1";
			}
			//a = 1 ,b > 1        = x^b
			else {
				return s += "x^" + this.get_power();
			}
		}
		//ax^b a = -1
		else if(this.get_coefficient() == -1)
		{
			//a = -1 ,b = 1        = -x
			if(this.get_power() == 1) {
				return s += "-x";
			}
			//a = -1 ,b = 0        = -1
			else if(this.get_power() == 0) {
				return s += "-1";
			}
			//a = -1 ,b > 1        = -x^b
			else {
				return s += "-x^" + this.get_power();
			}
		}	
		//ax^b a != +1,-1 
		else {
			//a != +1,-1  ,b = 1        = ax
			if(this.get_power() == 1) {
				return s += this.get_coefficient() + "x";
			}
			//a != +1,-1  ,b = 0        = a
			else if(this.get_power() == 0) {
				return s += this.get_coefficient();
			}
			//a != +1,-1  ,b > 1        = ax^b
			else {
				return s += this.get_coefficient() + "x^" + this.get_power();
			}
		}
	}

	/**
	 * @return true if the coefficient is 0 and false otherwise 
	 */
	public boolean isZero() {
		boolean ans = false;
		if(this.get_coefficient() == 0) {
			ans = true;
		}
		return ans;
	}

}
