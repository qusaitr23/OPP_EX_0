package MyMath;

import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
public class Graph extends JFrame {
	
	/**
	 * @author qusai trabeh
	 */
	private static final long serialVersionUID = 1L;

	public Graph(Polynom_able p, double x0, double x1) {
		setTitle(" Polynom Graph ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1600, 1600);

		DataTable data = new DataTable(Double.class, Double.class);
		DataTable kizon = new DataTable(Double.class, Double.class);

			double eps = 0.01;
		for (double x = x0; x <= x1; x+=eps) {
			
			double y = p.f(x);
			
			if(p.f(x-eps) < p.f(x) && p.f(x) > p.f(x+eps)) {
			kizon.add(x,y);	
				System.out.println("("+x+","+(p.f(x))+") MAX");
			}
			if(p.f(x-eps) > p.f(x) && p.f(x) < p.f(x+eps)) {
				kizon.add(x,y);	
				System.out.println("("+x+","+p.f(x)+") MIN");
			}
			data.add(x, y);	
		}
		
		XYPlot plot = new XYPlot(data, kizon); // create new plot
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines); //add database to plot
        Color color1 = new Color(0.0f, 0.3f,1.0f); // set color1 
        Color color2 = new Color(255,0,0); // set color2
        plot.getPointRenderers(data).get(0).setColor(color1); // color1 for polynom function 
        plot.getPointRenderers(kizon).get(0).setColor(color2); // color2 for min max points
        plot.getLineRenderers(data).get(0).setColor(color1);	}
}