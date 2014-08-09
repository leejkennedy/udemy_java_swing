import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;

	public MainFrame() {

		super("Hello World");
		setLayout(new BorderLayout());

		toolbar = new Toolbar();
		textPanel = new TextPanel();
		formPanel = new FormPanel();

		toolbar.setStringListener(new StringListener() {
			public void TextEmitted(String text) {
				textPanel.appendText(text);
			}
		});

		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCategoryID = e.getAgeCategoryID();
				String employmentCategory = e.getEmploymentCategory();
				String taxID = e.getTaxID();
				boolean usCitizen = e.isUsCitizen();
				String gender = e.getGender();

				textPanel.appendText(
				name + ";" 
				+ occupation + ";"
				+ ageCategoryID + ";" 
				+ employmentCategory + ";"
				+ usCitizen + ";" 
				+ taxID + ";" 
				+ gender + ";\n");
			}
		});

		add(formPanel, BorderLayout.WEST);
		add(toolbar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);

		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

}