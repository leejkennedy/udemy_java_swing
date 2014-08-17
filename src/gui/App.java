package gui;
import javax.swing.SwingUtilities;

public class App {

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});	
	}

}