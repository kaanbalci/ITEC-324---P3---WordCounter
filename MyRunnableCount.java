import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * @author Kaan runnable class with run method used to create windows for the
 *         files that are chosen and takes care of the counting of the words and
 *         file output
 */
public class MyRunnableCount implements Runnable {
	/**
	 * initialize
	 */
	private String filename;
	JFrame newWindowFrame = new JFrame();
	JLabel label1 = new JLabel();
	final JTextArea textArea = new JTextArea(50, 50);
	String line = "";
	int threadcount = 0;

	/**
	 * constructor that passes in filename as string
	 * 
	 * @param filename
	 */
	public MyRunnableCount(String filename) {
		this.filename = filename;
	}

	/**
	 * run method counts words in file and creates new frame for each file runs
	 * thread
	 */
	public void run() {
		int count = 0;
		try {
			Scanner in = new Scanner(new File(filename));
			while (in.hasNext()) {
				in.next();
				count++;

			}
			/**
			 * Swing components
			 */
			newWindowFrame.setLayout(new BorderLayout());
			FileReader fr = new FileReader(filename);
			BufferedReader bf = new BufferedReader(fr);
			String newLine = System.getProperty("line.separator");
			String words = Integer.toString(count);
			textArea.setEditable(false);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.read(bf, null);
			label1.setText("Number of words in file = " + words + newLine);
			newWindowFrame.setTitle("Word Count for " + filename);
			newWindowFrame.add(new JScrollPane(textArea));
			label1.setBounds(150, 0, 100, 25);
			newWindowFrame.add(label1, BorderLayout.NORTH);
			newWindowFrame.add(textArea, BorderLayout.CENTER);
			newWindowFrame.getDefaultCloseOperation();
			newWindowFrame.setMinimumSize(new Dimension(300, 300));
			newWindowFrame.setResizable(true);
			newWindowFrame.pack();
			newWindowFrame.setVisible(true);

		} catch (FileNotFoundException e) {
			// System.out.println("sorry");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// System.out.println("sorry");
		}
		File results = new File("results.txt");
		String newLine = System.getProperty("line.separator");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(results,
					true));
			writer.write("Thread " + threadcount + ": the file \"" + filename
					+ "\" has " + count + " words." + newLine);
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadcount++;
	}

}