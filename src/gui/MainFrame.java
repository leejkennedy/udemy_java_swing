package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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
	
	public MainFrame() {
		super("Hello World");

		setLayout(new BorderLayout());

		toolbar = new Toolbar();
		formPanel = new FormPanel();
		tablePanel = new TablePanel();
		controller = new Controller();
		tablePanel.setData(controller.getPeople());
		fileChooser = new JFileChooser();
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

		setMinimumSize(new Dimension(500,400));
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");
		JMenu fileMenu = new JMenu("File");
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
		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		
		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)e.getSource();
				formPanel.setVisible(menuItem.isSelected());
			}
		});
		
		//mnemonics + accelerators
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exportDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		windowMenu.setMnemonic(KeyEvent.VK_W);

		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
					System.out.println("MainFrame");
					System.out.println("MainFrame|importDataItem");
					System.out.println("MainFrame|" + fileChooser.getSelectedFile());
					System.out.println("MainFrame");
				};
			}
		});
		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
					System.out.println("MainFrame");
					System.out.println("MainFrame|EXportDataItem");
					System.out.println("MainFrame|" + fileChooser.getSelectedFile());
					System.out.println("MainFrame");
				};
			}
		});
		
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit ?", "Confirm exit...",JOptionPane.OK_CANCEL_OPTION); 
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}	
		});
		
		return menuBar;
	}
}
