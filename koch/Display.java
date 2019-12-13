/* *********************************************************************
 * Program Name: Display.java
 * Date Created: June 4th, 2018
 * Author(s): Yuk Tong (Stella), Lau
 * Package: fills
 * Description: This program tests out the implementations of KochPanel
 * by adding an object of its type to a JFrame object to observe the 
 * implementations. When the program is run, there would be two buttons 
 * named + and - in the south of the frame. As the + button is clicked, 
 * the level would increase and the Koch Snowflakes would be drawn 
 * corresponding to the level. As the - button is clicked, the level would 
 * decrease and the Sierpinski triangles would be drawn corresponding to 
 * the level.
 * Last Updated: June 6th, 2018
 * ******************************************************************* */

package koch;

//importations
//imports the BorderLayout class so that the orientations of the panel and subPanel can be set
import java.awt.BorderLayout;
//imports the BorderFactory class so the title of the border can be set
import javax.swing.BorderFactory;
//imports the JFrame class so a new JFrame object can be instantiated
import javax.swing.JFrame;

public class Display {

	//data members
	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;
	public static final int LEFT_X = 750;
	public static final int TOP_Y = 100;
	
	public Display() {
		//creates a KochPanel object named panel
		KochPanel panel = new KochPanel();
		//sets the border of panel with a border created by a lowered beveled edge
		panel.setBorder(BorderFactory.createLoweredBevelBorder());
		//sets the name of the subPanel of panel to be SubPanel Border
		panel.subPanel.setBorder(BorderFactory.createTitledBorder("Level"));
		//creates a new JFrame object named frame with title "Koch Snowflake"
		JFrame frame = new JFrame("Koch Snowflake");
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

