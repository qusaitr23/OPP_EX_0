package MyMath;
/** This interface represents a simple function of type y=f(x), where both y and x are real numbers.
**/
public interface function {
	
	/**
	 * This method calculates the value of y=f(x), where both y and x are real numbers.
	 * @param x - real number
	 * @return y - real number, when y=f(x)
	 */
	public double f(double x);
}
