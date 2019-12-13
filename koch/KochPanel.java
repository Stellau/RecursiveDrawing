/* *********************************************************************
 * Program Name: KochPanel.java
 * Date Created: June 4th, 2018
 * Author(s): Yuk Tong (Stella), Lau
 * Package: sierprinski
 * Description: This program contains 13 data members, a constructor,
 * a setBase method, a midPoint method, and a drawEdges method. The
 * setBase method is called in the constructor to initialize the three
 * points. The other data members are initialized in the constructor.
 * In the recursive method drawEdges, the midPoint function is called to
 * find the coordinate of the local point p5 and the p4 and p6 are found
 * by shifting from p1. After the points are set, the drawEdges method
 * draws the Koch Snowflake recursively.
 * Last Updated: June 6th, 2018
 * ******************************************************************* */

package koch;

//importations
//imports JPanel so the class can extend it
import javax.swing.JPanel;
//imports Graphics so that the paintComponent method can be overridden and lines can be drawn
import java.awt.Graphics;
//imports Graphics2D so the Graphics object in paintComponent can be type-casted
import java.awt.Graphics2D;
//imports Line2D so the lines that construct the snowflake can be drawn
import java.awt.geom.Line2D;
//imports Point2D to create the three Point2D data members
import java.awt.geom.Point2D;
//imports the JButton class so the JButton data member can be declared
import javax.swing.JButton;
//imports the JLabel class so the JLabel data member can be declared
import javax.swing.JLabel;
//imports ActionEvents so the method actionPerformed can be implemented
import java.awt.event.ActionEvent;
//imports ActionListener so an action listener can be added to the buttons
import java.awt.event.ActionListener;

public class KochPanel extends JPanel {
	
	//data members
	private static final long serialVersionUID = 1L;
	//sets the value of WIDTH and HEIGHT according to the display dimension
	public static final int WIDTH = Display.WIDTH - 25;
	public static final int HEIGHT = Display.HEIGHT - 75;
	//in order for the entire snow flake to be shown on the panel, the size of the base triangle must be reduced
	//side stands for the lengths of the sides of the base case equilateral triangle
    public static final double SIDE = 0.6 * (WIDTH -50);
	//height stands for the height of the base case equilateral triangle
	public static final double THEIGHT = SIDE/(2*Math.tan(Math.toRadians(30)));
	//represents the level of the Koch Snow flake
	private int level;
	//declares data members
	private JButton incrementButton;
	private JButton decrementButton;
	private JLabel label;
	public JPanel subPanel;
	private Point2D.Double p1, p2, p3;
	
	public KochPanel() {
		//initializes data members
		level = 1;
		subPanel = new JPanel();
		label = new JLabel("n: " + level);
		incrementButton = new JButton("+");
		decrementButton = new JButton("-");
		//adds an anonymous listener to incrementButton
		incrementButton.addActionListener(new ActionListener() {
			//implements the actionPerformed method in ActionListener
			public void actionPerformed(ActionEvent event) {
				//increments the level each time the button is clicked
				level++;
				//updates the label
				label.setText("n = " + level);
			}
		});
		//adds an anonymous listener to decrementButton
		decrementButton.addActionListener(new ActionListener() {
			//implements the actionPerformed method in ActionListener
			public void actionPerformed(ActionEvent event) {
				//only decrements the level if it is greater than 1
				if(level > 1) {
					//decrements the level each time the button is clicked
					level--;
					//updates the label
					label.setText("n = " + level);
				}
			}
		});
		//adds the buttons and label to the sub-panel since it is where they should be displayed
		subPanel.add(incrementButton);
		subPanel.add(decrementButton);
		subPanel.add(label);
		//sets the base case of the Koch Snowflake
		setBase();
	}
	
	//recursive method draw edges that draws the snowflake
	public void drawEdges(Graphics2D pen, int level, Point2D.Double p1, Point2D.Double p2) {
		if(level == 1) {//base case if level is equal to 1 draws the line that connects p1 and p2
			Line2D.Double line = new Line2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			pen.draw(line);
		}else {
			//midPoint12 is the midPoint between p1 and p2
			Point2D.Double midPoint12 = midPoint(p1, p2);
			//dx is one third the x-distance of p1 and p2 from p1
			double dx = Math.abs(p1.getX() - p2.getX())/3.0;
			//dx is one third the y-distance of p1 and p2 from p1
			double dy = Math.abs(p2.getY() - p1.getY())/3.0;
			//declares the three points p4, p5, and p6
			Point2D.Double p4;
			Point2D.Double p5;
			Point2D.Double p6;
			//there is always two cases, either the x-coordinate of p1 is greater than that of p2 or the reverse
			if(p1.getX() > p2.getX()) {//if the x-coordinate of p1 is greater than that of p2
				if(p1.getY() == p2.getY()) {//if the two points have the same y-coordinates
					//p4 is shifted the distance dx to the left of p1
					p4 = new Point2D.Double(p1.getX() - dx, p1.getY());
					//p5 is shifted up by half of dx divided by tan(30) from the midpoint
					p5 = new Point2D.Double(midPoint12.getX(), midPoint12.getY() - dx/(2*Math.tan(Math.toRadians(30))));
					//p6 is shifted the distance 2*dx to the left of p1
					p6 = new Point2D.Double(p1.getX() - 2*dx, p1.getY());
				}else if(p1.getY() < p2.getY()) {//if the y-coordinate of p1 is less than that of p2
					//p4 is shifted the distance dx to the left of p1 and the distance dy downward of p1
					p4 = new Point2D.Double(p1.getX() - dx, p1.getY() + dy);
					//p5 is shifted the distance dy * Math.cos(Math.toRadians(30)) to the left of the midpoint
					//and has the same y-coordinate as p4
					p5 = new Point2D.Double(midPoint12.getX() - dy * Math.cos(Math.toRadians(30)), p1.getY() + dy);
					//p6 is shifted the distance 2*dx to the left of p1 and the distance 2*dy downward of p1
					p6 = new Point2D.Double(p1.getX() - 2*dx, p1.getY() + 2*dy);
				}else {//if the y-coordinate of p1 is greater than that of p2
					//p4 is shifted the distance dx to the left of p1 and the distance dy up of p1
					p4 = new Point2D.Double(p1.getX() - dx, p1.getY() - dy);
					//p5 is shifted the distance dy * Math.cos(Math.toRadians(30)) to the right of the midpoint
					//and shifted up from p1 by the distance 2*dy or dy/sin(30)
					p5 = new Point2D.Double(midPoint12.getX() + dy * Math.cos(Math.toRadians(30)), p1.getY() - 2*dy);
					//p6 is shifted the distance 2*dx to the left of p1 and the distance 2*dy up of p1
					p6 = new Point2D.Double(p1.getX() - 2*dx, p1.getY() - 2*dy);
				}
			}else {//if the x-coordinate of p1 is less than that of p2
				if(p1.getY() == p2.getY()) {//if the two points have the same y-coordinates
					//p4 is shifted the distance dx to the right of p1
					p4 = new Point2D.Double(p1.getX() + dx, p1.getY());
					//p5 is shifted down by half of dx divided by tan(30) from the midpoint
					p5 = new Point2D.Double(midPoint12.getX(), midPoint12.getY() + dx/(2*Math.tan(Math.toRadians(30))));
					//p6 is shifted the distance 2*dx to the right of p1
					p6 = new Point2D.Double(p1.getX() + 2*dx, p1.getY());
				}else if (p1.getY() < p2.getY()) {//if the y-coordinate of p1 is less than that of p2
					//p4 is shifted the distance dx to the right of p1 and the distance dy downward of p1
					p4 = new Point2D.Double(p1.getX() + dx, p1.getY() + dy);
					//p5 is shifted the distance dy * Math.cos(Math.toRadians(30)) to the left of the midpoint
					//and shifted downward from p1 by 2*dy or dy/sin(30)
					p5 = new Point2D.Double(midPoint12.getX() - dy * Math.cos(Math.toRadians(30)), p1.getY() + 2*dy);
					//p6 is shifted the distance 2*dx to the right of p1 and the distance 2*dy downward of p1
					p6 = new Point2D.Double(p1.getX() + 2*dx, p1.getY() + 2*dy);
				}else {//if the y-coordinate of p1 is greater than that of p2
					//p4 is shifted the distance dx to the right of p1 and the distance dy up of p1
					p4 = new Point2D.Double(p1.getX() + dx, p1.getY() - dy);
					//p5 is shifted the distance dy * Math.cos(Math.toRadians(30)) to the right of the midpoint
					//and shifted up from p1 by the distance dy
					p5 = new Point2D.Double(midPoint12.getX() + dy * Math.cos(Math.toRadians(30)), p1.getY() - dy);
					//p6 is shifted the distance 2*dx to the right of p1 and the distance 2*dy up of p1
					p6 = new Point2D.Double(p1.getX() + 2*dx, p1.getY() - 2*dy);
				}
			}
			//calls the method on each side recursively
			drawEdges(pen, level-1, p1, p4);
			drawEdges(pen, level-1, p4, p5);
			drawEdges(pen, level-1, p5, p6);
			drawEdges(pen, level-1, p6, p2);
		}
		//repaints the panel;
		repaint();
	}

	//helper method midPoint that calculates the midpoint of two points
	public Point2D.Double midPoint(Point2D.Double p1, Point2D.Double p2) {
		return new Point2D.Double((p1.getX()+p2.getX())/2, (p1.getY()+p2.getY())/2);
	}
	
	//overrides the paintComponent method of JPanel
	public void paintComponent(Graphics g) {
		//type-casts the Graphics object g into a Graphics2D object
		Graphics2D pen = (Graphics2D) g;
		//calls the recursive method on the three side of the base case
		drawEdges(pen, level, p1, p2);
		drawEdges(pen, level, p2, p3);
		drawEdges(pen, level, p3, p1);
	}
	
	public void setBase() {
		//the x-coordinates of the three base points
		double[] x = {WIDTH/2 + 10, WIDTH/2 - SIDE/2 + 10, WIDTH/2 + SIDE/2 + 10};
		//the y-coordinates of the three base points
		double[] y = {25, 25 + THEIGHT, 25 + THEIGHT};
		//initializes the points
		p1 = new Point2D.Double(x[0], y[0]);
		p2 = new Point2D.Double(x[1], y[1]);
		p3 = new Point2D.Double(x[2], y[2]);
	}
	
}
