package sierprinski;

import javax.swing.JFrame;

public class Test {
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;
	public static final int LEFT_X = 750;
	public static final int TOP_Y = 100;

	public static void main(String[] args) {
		
		TrianglePanel t = new TrianglePanel();
		JFrame f = new JFrame("Display");
		f.add(t);
		f.setSize(WIDTH, HEIGHT);
		f.setLocation(LEFT_X, TOP_Y);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

}

