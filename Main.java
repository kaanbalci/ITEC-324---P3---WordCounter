import java.awt.Dimension;

import javax.swing.JFrame;
/**
 * main driver for program word count
 * @author Kaan
 *
 */
public class Main {
	public static void main(String args[]) {
	   JFrame window = new JFrame("Project 3 - Word Count");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(new MainWindow());
		window.setMinimumSize(new Dimension(300, 200));

		// makes it so users can't resize window
		window.setResizable(false);

		window.pack();
		window.setVisible(true);
   }
}