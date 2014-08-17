package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.Controller;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private Toolbar toolbar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private PersonFileFilter personFilter;
	private Controller controller;
	private TablePanel tablePanel;
	private PreferencesDialog preferencesDialog;
	private Preferences preferences;
	
	public MainFrame() {
		super("Hello World");

		setLayout(new BorderLayout());

		toolbar = new Toolbar();
		formPanel = new FormPanel();
		tablePanel = new TablePanel();
		controller = new Controller();
		tablePanel.setData(controller.getPeople());
		preferencesDialog = new PreferencesDialog(this);
		preferences = Preferences.userRoot().node("db");
		
		tablePanel.setPersonTableListener(new PersonTableListener() {
			public void rowDeleted(int row) {
				controller.removePerson(row);
			}
		});
		
		preferencesDialog.setPreferencesListener(new PreferencesListener(){
			public void PreferencesListener(String user, String password,
					int port) {
				preferences.put("UserName", user);
				preferences.put("Password", password);
				preferences.putInt("Port", port);
			}
		});

		String user = preferences.get("UserName", "");
		String password = preferences.get("Password", "");
		Integer port = preferences.getInt("Port", 3306);
		preferencesDialog.setDefaults(user, password, port);

		fileChooser = new JFileChooser("E:\\Downloads\\JAVA_SWING_DATA_FILES");
		personFilter = new PersonFileFilter();
		fileChooser.addChoosableFileFilter(personFilter);
		fileChooser.setFileFilter(personFilter);

		setJMenuBar(createMenuBar());

		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				controller.addPerson(e);
				tablePanel.refresh();
			}
		});

		add(formPanel, BorderLayout.WEST);
		add(toolbar, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);

		setMinimumSize(new Dimension(500, 400));
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");
		JMenu fileMenu = new JMenu("File");
		JMenuItem prefsItem = new JMenuItem("Preferences...");
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
		showFormItem.setSelected(true);

		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		windowMenu.add(prefsItem);

		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		prefsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preferencesDialog.setVisible(true);
			}
		});
		
		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
				formPanel.setVisible(menuItem.isSelected());
			}
		});

		// mnemonics + accelerators
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exportDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				ActionEvent.CTRL_MASK));
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				ActionEvent.CTRL_MASK));
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		windowMenu.setMnemonic(KeyEvent.VK_W);
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,
				ActionEvent.CTRL_MASK));

		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadfromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this,
								"Could not load data from file.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				;
			}
		});

		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.savetoFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this,
								"Could not save data to file.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				;
			}
		});

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you really want to exit ?", "Confirm exit...",
						JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});

		return menuBar;
	}
}
