package MyMath;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import MyMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author qusai trabeh
 *
 */
public class Polynom implements Polynom_able{
	private ArrayList<Monom> polynom;

	private int size() {
		return polynom.size();
	}

	/**
	 * creates a new zero Polynom. 
	 */
	public Polynom(){		
		polynom = new ArrayList<>();
	}

	/**
	 * Creates a new copy of p Polynom
	 * @param p - Polynom to copy
	 */
	public Polynom(Polynom p) {
		polynom = new ArrayList<>();
		Iterator<Monom> Itr = p.iteretor();
		while(Itr.hasNext()) {
			polynom.add(new Monom(Itr.next()));
		}
	}

	public Polynom(String s) {
		polynom = new ArrayList<>();
		if(s.length() > 0) {
			int start = 0;

			for (int i = 1; i < s.length(); i++) {	
				if(s.charAt(i) == '+') {
					this.add(new Monom(s.substring(start, i)));	
					start = i + 1 ;
				}
				else if(s.charAt(i) == '-') {
					this.add(new Monom(s.substring(start, i)));	
					start = i;	
				}
			}
			this.add(new Monom(s.substring(start)));
		}
	}
	/**
	 *Sorts the Polynom according to the leading Monom in the shape of c1x^b1 + c2x^b2 + ... + cnx^bn , when b1 < b2 < ... < bn 
	 */
	public void sort(){
		Collections.sort(this.polynom, new Monom_Comperator());
	}

	@Override
	public double f(double x) {
		double sum = 0;
		Iterator<Monom> Itr = this.iteretor();
		while(Itr.hasNext()) {
			sum += Itr.next().f(x);
		}		
		return sum;
	}

	@Override
	public void add(Polynom_able p1) {
		if(p1!=null) {
			Iterator<Monom> Itr = p1.iteretor();
			while(Itr.hasNext()) {
				this.add(Itr.next());
			}
		}
	}


	@Override
	public void add(Monom m2) {
		if(m2 != null && m2.get_coefficient() != 0) {
			Monom toAdd = new Monom(m2);
			boolean added = false;

			for (int i = 0; i < polynom.size(); i++) {
				if(polynom.get(i).get_power() == toAdd.get_power()) {
					polynom.get(i).add(toAdd);
					added = true;
				}
			}
			for (int i = 0; i < polynom.size(); i++) {
				if(polynom.get(i).isZero()) {
					polynom.remove(i);
				}
			}
			if(!added) {
				polynom.add(toAdd);
			}		
		}
		this.sort();
	}

	@Override
	public String toString() {
		if(this.size() == 0) {
			return "zero Polynom";
		}
		String s = ""; 
		Iterator<Monom> it=this.iteretor();
		while(it.hasNext()) {
			Monom m = it.next();
			if(m.get_coefficient() > 0) {
				s += "+" + m.toString();
			}
			if(m.get_coefficient() < 0) {
				s += "" + m.toString();
			}				
		}
		if(this.size()  > 1 && s.charAt(0) == '+')
			return s.substring(1, s.length());
		if(this.size() == 0)
			return "zero Polynom";
		if(s.charAt(0) == '+') {
			return s.substring(1);
		}
		return s;

	}

	@Override
	public void substract(Polynom_able p1) {
		Polynom p =new Polynom((Polynom)p1);
		Polynom minusOne = new Polynom();
		minusOne.add(new Monom(-1,0));
		p.multiply(minusOne);
		this.add(p);		
	}
	@Override
	public void multiply(Polynom_able p1) {
		Polynom p =new Polynom((Polynom)p1);
		Polynom neww =new Polynom();
		for (int i = 0; i < this.size(); i++) {
			for (int j = 0; j < p.size(); j++) {
				Monom temp = new Monom(this.polynom.get(i));
				temp.multiply(p.polynom.get(j));
				neww.add(temp);
			}
		}
		this.polynom = neww.polynom;
	}

	@Override
	public boolean equals(Polynom_able p1) {
		Polynom p = (Polynom)p1;
		if(this.size() != p.size()) {
			return false;
		}
		boolean equals = true;
		for (int i = 0; i < this.size(); i++) {
			if(!this.polynom.get(i).equals(p.polynom.get(i))) {
				equals = false;
			}
		}
		return equals;
	}

	@Override
	public boolean isZero() {
		return polynom.isEmpty();
	}

	/**
	 * calculates the root between x0 and x1  [x0,.....,x1] uses the algo of https://oregonstate.edu/instruct/mth251/cq/Stage4/Lesson/bisection.html 
	 * @param eps - accuracy  for example 0.0001
	 * @return root 
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		if ( this.f(x0) * this.f(x1) > 0) {
			return 0;
		}
		while (x0 < x1) {
			if(Math.abs(this.f(x0)) <= eps ) {
				return x0;
			}
			if(Math.abs(this.f(x1)) <= eps ) {
				return x1;
			}

			double middle = (x0 + x1)/2.0;
			if(this.f(x0) * this.f(middle) <= 0) {
				x1 = middle;
			}
			else {
				x0 = middle;
			}
		}
		return 0;
	}

	@Override
	public Polynom_able copy() {
		Polynom_able p1 = new Polynom();
		Iterator<Monom> Itr = this.iteretor();
		while(Itr.hasNext()) {
			p1.add(new Monom(Itr.next()));
		}	
		return p1;
	}

	@Override
	public Polynom_able derivative() {
		Polynom_able p1 = new Polynom();
		Iterator<Monom> Itr = this.iteretor();
		while(Itr.hasNext()) {
			p1.add(Itr.next().derivative());
		}	
		return p1;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		double area = 0;
		while(x0<= x1) {
			area += this.f(x0) * eps; 			
			x0 += eps;
		}
		return area;
	}

	@Override
	public Iterator<Monom> iteretor() {
		Iterator<Monom> a = polynom.iterator();
		return a;
	}
	/**
	 *
	 */
	@Override
	public void Graph(double x0 ,double x1) {
		Graph frame = new Graph(this , x0 , x1);
		frame.setVisible(true);

	}
	@Override
	public double areaUnderX(double x0, double x1) {
		double area = 0;
		double eps = 0.01;
		while(x0<= x1) {
			if(this.f(x0) < 0)
				area += this.f(x0) * eps; 			
			x0 += eps;
		}
		if(area<0)
			return -area;
		else return area;
	}

}
