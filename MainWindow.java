import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Main Window with action listeners for the two starting buttons
 * @author Kaan
 *
 */

public class MainWindow extends JPanel {
	JButton addFiles = new JButton("Open Files");
	JButton startCount = new JButton("Count Words!");
	JFrame frame = new JFrame();
	File[] files;

	public MainWindow() {
		setLayout(new BorderLayout());
		setLayout(new GridLayout(1, 4));
		add(addFiles);
		add(startCount);
		add(addFiles, BorderLayout.NORTH);
		add(startCount, getLayout());
		
		/**
		 * action listener for Open Files button implements file method
		 */
		addFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				file();
			}
		});
		
		/**
		 * action listener for Count words button implements worker method
		 */
		startCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("will count and open up new frames");
				worker();
			}
		});

	}
	/**
	 * method that uses JFileChooser to pick multiple files
	 * and add retreive them
	 * @return files
	 */
	public File[] file() {
		// TODO Auto-generated method stub
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);

		// Show the dialog; wait until dialog is closed
		chooser.showOpenDialog(frame);

		// Retrieve the selected files.
		files = chooser.getSelectedFiles();
		System.out.println(files.length);
		System.out.println(files[0]);
		return files;

	}
	/**
	 * worker method implements myrunnable passing filenames as string
	 * and starts the thread
	 */
	public void worker() {
		for (int i = 0; i < files.length; i++) {
			String filenames = files[i].toString();
			Runnable r = new MyRunnableCount(filenames);
			Thread t = new Thread(r);
			t.start();
		}
	}
}
