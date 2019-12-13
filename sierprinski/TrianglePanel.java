/* *********************************************************************
 * Program Name: TrianglePanel.java
 * Date Created: May 7th, 2018
 * Author(s): Yuk Tong (Stella), Lau
 * Package: sierprinski
 * Description: This program contains 11 data members, a constructor,
 * a setTriangle method, a midPoint method, a createTriangles method, and
 * a displayTriangles method. The constructor and the setTriangle method
 * initialize all the data member except for the Point data members. The
 * createTriangles method uses the helper method midPoint to find the
 * midpoints of the three sides of a fundamental triangle, creates three
 * inner triangles using the midpoints and the vertices of the fundamental
 * triangles, and stores them is an array. The displayTriangles method is
 * a recursive method that draws the Sierprinski triangles recursively.
 * Last Updated: May 8th, 2018
 * ******************************************************************* */

package sierprinski;

//importations
//imports JPanel so the class can extend it
import javax.swing.JPanel;
//imports Point so the three Point data members can be declared
import java.awt.Point;
//imports graphics so that the paintComponent method can be overridden and the displayTriangles
//method can accept a Graphics parameter
import java.awt.Graphics;
//imports the class Polygon so the triangles can be created
import java.awt.Polygon;
//imports the JButton class so the JButton data member can be declared
import javax.swing.JButton;
//imports the JLabel class so the JLabel data member can be declared
import javax.swing.JLabel;
//imports ActionEvents so the method cctionPerformed can be implemented
import java.awt.event.ActionEvent;
//imports ActionListener so an action listener can be added to the button
import java.awt.event.ActionListener;

public class TrianglePanel extends JPanel {
	
	//data members
	private static final long serialVersionUID = 1L;
	//sets the value of WIDTH and HEIGHT according to the display dimension
	public static final int WIDTH = Display.WIDTH - 25;
	public static final int HEIGHT = Display.HEIGHT - 75;
	//represents the number of clicks on the button
	private int order;
	//declare data members
	private JButton button;
	private JLabel label;
	public JPanel subPanel;
	private Point p0, p1, p2;
	private Polygon triangle;
	
	//constructor
	public TrianglePanel() {
		//initializes data members
		order = 0;
		subPanel = new JPanel();
		label = new JLabel("n = " + order);
		button = new JButton("Increment");
		//adds listener to button
		button.addActionListener(new ActionListener() {
			//implements the actionPerformed method in ActionListener
			public void actionPerformed(ActionEvent event) {
				//increments the order each time the button is clicked
				order++;
				//updates the label
				label.setText("n = " + order);
			}
		});
		//adds the button and label to the sub-panel since it is where they should be displayed
		subPanel.add(button);
		subPanel.add(label);
		//sets the Triangle
		setTriangle();
	}
	
	//overrides the paintComponent method of JPanel
	public void paintComponent(Graphics pen) {
		//calls the recursive method displayTriangles to draw the Sierprinski triangles
		//so that they would be drawn in the Display file
		displayTriangles(pen, triangle, order);
	}
	
	//sets triangle
	public void setTriangle() {
		//arrays that hold the positions of the vertices 
		int[] x = {WIDTH/2, 25, WIDTH-25};
		int[] y = {10, HEIGHT-25,HEIGHT-25};
		//initializes the data member triangle using a Polygon constructor and passes it the 
		//vertices and number of sides of a triangle
		//triangle is set as the fundamental triangle when n = 0
		triangle = new Polygon(x, y, 3);
	}
	
	//createTriangles creates the three inner triangles in the fundamental triangles
	public Polygon[] createTriangles(Polygon triangle) {
		//initializes the three Point object vertices to the vertices of the Polygon passed
		//NOTE: triangle.xpoints[] holds the x-coordinates of triangle
		//triangle.ypoints[] holds the y-coordinates of triangle
		p0 = new Point(triangle.xpoints[0],triangle.ypoints[0]);
		p1 = new Point(triangle.xpoints[1],triangle.ypoints[1]);
		p2 = new Point(triangle.xpoints[2],triangle.ypoints[2]);
		//use the midPoint method to find the three midpoints on the three sides of triangle
		Point m01 = midPoint(p0, p1);
		Point m12 = midPoint(p1, p2);
		Point m20 = midPoint(p2, p0);
		//three inner triangles have to be drawn in the fundamental triangle
		//these two arrays store the vertices of the top inner triangle
		int[] t0X = {p0.x, m01.x, m20.x};
		int[] t0Y = {p0.y, m01.y, m20.y};
		//these two arrays store the vertices of the left inner triangle
		int[] t1X = {m01.x, p1.x, m12.x};
		int[] t1Y = {m01.y, p1.y, m12.y};
		//these two arrays store the vertices of the right inner triangle
		int[] t2X = {m20.x, m12.x, p2.x};
		int[] t2Y = {m20.y, m12.y, p2.y};
		//creates three Polygon objects that represent the three inner triangle and pass them the
		//arrays that contain their vertices
		Polygon t0 = new Polygon(t0X, t0Y, 3);
		Polygon t1 = new Polygon(t1X, t1Y, 3);
		Polygon t2 = new Polygon(t2X, t2Y, 3);
		//stores the three inner triangles in an array
		Polygon[] p = {t0, t1, t2};
		//returns the array
		return p;
	}
	
	//recursive method
	public void displayTriangles(Graphics pen, Polygon triangle, int order) {
		//end case
		if(order == 0) {
			//calls the setTriangle method to initialize the triangle
			setTriangle();
			//draws the fundamental triangles
			pen.drawPolygon(triangle);
		}else {
			//creates the three inner triangles in the fundamental triangles
			Polygon[] sierprinski = createTriangles(triangle);
			//displays the inner triangles for the three inner triangles till order reaches 0
			displayTriangles(pen, sierprinski[0], order-1);
			displayTriangles(pen, sierprinski[1], order-1);
			displayTriangles(pen, sierprinski[2], order-1);
			//draws all the inner triangles
			pen.drawPolygon(sierprinski[0]);
			pen.drawPolygon(sierprinski[1]);
			pen.drawPolygon(sierprinski[2]);
		}
		//repaints
		repaint();
	}
	
	//helper method midPoint that calculates the midpoint of two points
	public Point midPoint(Point p1, Point p2) {
		return new Point((p1.x+p2.x)/2, (p1.y+p2.y)/2);
	}

}

