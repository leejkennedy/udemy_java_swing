package gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Person;

public class TablePanel extends JPanel {
	private static final long serialVersionUID = -7321066520502701842L;
	private JTable table;
	private PersonTableModel tableModel;
	
	public TablePanel(){
		tableModel = new PersonTableModel();
		table = new JTable(tableModel);
		
		setLayout(new BorderLayout());

		add(new JScrollPane(table),BorderLayout.CENTER);
	}

	public void setData(List<Person> db){
		tableModel.setData(db);
	}
	
	public void refresh(){
		tableModel.fireTableDataChanged();
		//this isn't working
	}
}
