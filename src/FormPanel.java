import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okBtn;
	private FormListener formListener;
	private JList<AgeCategoryID> ageList;
	private JComboBox<String> employeeTypeCombo;
	private JCheckBox citizenCheck;
	private JTextField taxField;
	private JLabel taxLabel;
	private JRadioButton optMale;
	private JRadioButton optFemale;
	private ButtonGroup genderGroup;
	
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		nameLabel = new JLabel("Name:");
		nameField = new JTextField(10);
		occupationLabel = new JLabel("Occupation:");
		occupationField = new JTextField(10);
		ageList = new JList<AgeCategoryID>();
		employeeTypeCombo = new JComboBox<String>();
		citizenCheck = new JCheckBox();
		taxField = new JTextField(10);
		taxLabel = new JLabel("Tax ID:");
		optMale = new JRadioButton("Male");
		optFemale = new JRadioButton("Female");
		genderGroup = new ButtonGroup();

		// set up gender radio buttons

		optMale.setActionCommand("Male");
		optFemale.setActionCommand("Female");
		optMale.setSelected(true);
		genderGroup.add(optMale);
		genderGroup.add(optFemale);
				
		// set up Tax ID
		citizenCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isTicked = citizenCheck.isSelected();
				taxLabel.setEnabled(isTicked);
				taxField.setEnabled(isTicked);
			};
		});

		taxLabel.setEnabled(false);
		taxField.setEnabled(false);
		
		// set up list model
		DefaultListModel<AgeCategoryID> ageModel = new DefaultListModel<AgeCategoryID>();
		ageModel.addElement(new AgeCategoryID(1, "Under 18"));
		ageModel.addElement(new AgeCategoryID(2, "18 - 65"));
		ageModel.addElement(new AgeCategoryID(3, "Over 65"));
		ageList.setModel(ageModel);

		ageList.setPreferredSize(new Dimension(110, 80));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1);

		// set up combo box
		DefaultComboBoxModel<String> employeeTypeModel = new DefaultComboBoxModel<String>();
		employeeTypeModel.addElement("Employed");
		employeeTypeModel.addElement("Self-Employed");
		employeeTypeModel.addElement("Unemployed");
		employeeTypeCombo.setModel(employeeTypeModel);
		employeeTypeCombo.setEditable(true);

		okBtn = new JButton("OK");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategoryID ageCategoryID = (AgeCategoryID) ageList
						.getSelectedValue();
				String employmentCategory = (String) employeeTypeCombo
						.getSelectedItem();
				String taxID = (String) taxField.getText();
				boolean usCitizen = citizenCheck.isSelected();

				String gender = genderGroup.getSelection().getActionCommand();

				FormEvent ev = new FormEvent(this, name, occupation,
						ageCategoryID.getID(), employmentCategory, taxID,
						usCitizen, gender);
				if (formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
		});

		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponents();
	}

	private void layoutComponents() {

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		// //// First row ////////////////
		gc.gridy = 0;

		gc.weightx = 1;
		gc.weighty = .1;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(nameLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameField, gc);

		// //// Next row ////////////////

		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = .1;
		gc.gridx = 0;

		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(occupationLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(occupationField, gc);

		// //// Next row ////////////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Age"), gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(ageList, gc);

		// //// Next row ////////////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Employment"), gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(employeeTypeCombo, gc);

		// //// Next row ////////////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("US Citizen:"), gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(citizenCheck, gc);

		// //// Next row ////////////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(taxLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(taxField, gc);

		// //// Next row ////////////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .05;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Gender:"), gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(optMale, gc);
		
		// //// Next row ////////////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .1;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(optFemale, gc);

		// //// last row ////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 2;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(okBtn, gc);		
	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}

}

class AgeCategoryID {
	private int id;
	private String text;

	public AgeCategoryID(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public String toString() {
		return text;
	}

	public int getID() {
		return id;
	}

}
