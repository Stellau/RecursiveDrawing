/* *********************************************************************
 * Program Name: Display.java
 * Date Created: May 7th, 2018
 * Author(s): Yuk Tong (Stella), Lau
 * Package: sierprinski
 * Description: This program tests out the implementations of
 * TrianglePanel by adding an object of its type to a JFrame object to
 * observe the implementations. When the program is run, there would be a
 * button name Increment in the south of the frame. As the button is clicked
 * the order would increase and the Sierprinski triangles would be drawn
 * corresponding to the order.
 * Last Updated: May 8th, 2018
 * ******************************************************************* */

package sierprinski;

//importations
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;

public class Display {
	
	//data members
	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;
	public static final int LEFT_X = 750;
	public static final int TOP_Y = 100;
	
	public Display() {
		//creates a TrianglePanel object named panel
		TrianglePanel panel = new TrianglePanel();
		//sets the border of panel with a border created by a lowered beveled edge
		panel.setBorder(BorderFactory.createLoweredBevelBorder());
		//sets the name of the subPanel of panel to be SubPanel Border
		panel.subPanel.setBorder(BorderFactory.createTitledBorder("SubPanel Border"));
		//creates a new JFrame object named frame with title "Display"
		JFrame frame = new JFrame("Display");
		//sets the layout of the frame
		frame.setLayout(new BorderLayout());
		//adds and centers panel in JFrame
		frame.add(panel, BorderLayout.CENTER);
		//adds and sets subPanel to be at the bottom, "south" in JFrame
		frame.add(panel.subPanel, BorderLayout.SOUTH);
		//sets the characteristics of the frame with the data members
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocation(LEFT_X, TOP_Y);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {
		//tests the implementation in Display by creating an anonymous object of Display
		new Display();
	}

}

