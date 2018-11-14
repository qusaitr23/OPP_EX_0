package MyMath;

import java.util.Comparator;

/**
 * This class represents a Object "Monom_Comperator", which is  compares between two Monoms
 * @param o1 - the first object to be compared
 * @param o2 - the second object to be compared
 * @return a -1,0,1 as the first argument is less than, equal to, or greater than the second
 */
public class Monom_Comperator implements Comparator<Monom> {

	@Override
	public int compare(Monom o1, Monom o2) {
		if (o1.get_power() > o2.get_power()) {
			return -1;
		}
		else if(o1.get_power() < o2.get_power()) {
			return 1;
		}
		else{
			return 0;
		}
	}
}
