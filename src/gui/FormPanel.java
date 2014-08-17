package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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

import enums.AgeCategory;
import enums.EmploymentCategory;
import enums.Gender;

public class FormPanel extends JPanel {
	private static final long serialVersionUID = 6560669076257177720L;
	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okBtn;
	private FormListener formListener;
	private JList<AgeCategoryType> ageList;
	private JComboBox<String> empCombo;
	private JCheckBox citizenCheck;
	private JTextField taxField;
	private JLabel taxLabel;
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;
	
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		nameLabel = new JLabel("Name: ");
		nameField = new JTextField(10);
		occupationLabel = new JLabel("Occupation: ");
		occupationField = new JTextField(10);
		ageList = new JList<AgeCategoryType>();
		empCombo = new JComboBox<String>();
		citizenCheck = new JCheckBox();
		taxField = new JTextField(10);
		taxLabel = new JLabel("Tax ID: ");
		maleRadio = new JRadioButton("Male");
		femaleRadio = new JRadioButton("Female");
		genderGroup = new ButtonGroup();
		okBtn = new JButton("OK");
		
		// set up mnemonics
		okBtn.setMnemonic(KeyEvent.VK_O);
		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameField);
		occupationLabel.setDisplayedMnemonic(KeyEvent.VK_C);
		occupationLabel.setLabelFor(occupationField);
				
		// Set up gender radios
		maleRadio.setActionCommand("Male");
		femaleRadio.setActionCommand("Female");
		maleRadio.setSelected(true);
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);

		// Set up tax ID
		taxLabel.setEnabled(false);
		taxField.setEnabled(false);
		
		citizenCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean isTicked = citizenCheck.isSelected();
				taxLabel.setEnabled(isTicked);
				taxField.setEnabled(isTicked);
			}
		});
		
		// set up list box
		DefaultListModel<AgeCategoryType> ageModel = new DefaultListModel<AgeCategoryType>();
		ageModel.addElement(new AgeCategoryType(AgeCategory.CHILD, "Under 18"));
		ageModel.addElement(new AgeCategoryType(AgeCategory.ADULT, "18 - 65"));
		ageModel.addElement(new AgeCategoryType(AgeCategory.SENIOR, "Over 65"));
		ageList.setModel(ageModel);

		ageList.setPreferredSize(new Dimension(110, 70));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1);

		// set up combo box
		DefaultComboBoxModel<String> empModel = new DefaultComboBoxModel<String>();
		empModel.addElement("Employed");
		empModel.addElement("Self-Employed");
		empModel.addElement("Unemployed");
		empCombo.setModel(empModel);
		empCombo.setSelectedIndex(0);
		empCombo.setEditable(true);
		
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategoryType ageCategory = (AgeCategoryType) ageList.getSelectedValue();
				String employmentCategoryName = (String) empCombo.getSelectedItem();
				String taxID = taxField.getText();
				boolean usCitizen = citizenCheck.isSelected();
				String genderName = genderGroup.getSelection().getActionCommand();
				EmploymentCategory employmentCategory;
				Gender gender;

				if (genderName.equals("Male")) {
					gender = Gender.MALE;
				} else {
					gender = Gender.FEMALE;
				}
				
				if (employmentCategoryName.equals("Employed")) {
					employmentCategory = EmploymentCategory.EMPLOYED;
				} else if (employmentCategoryName.equals("Self-Employed")) {
					employmentCategory = EmploymentCategory.SELFEMPLOYED;
				} else if (employmentCategoryName.equals("Unemployed")) {
					employmentCategory = EmploymentCategory.UNEMPLOYED;
				} else {
					employmentCategory = EmploymentCategory.OTHER;
				}				
				
				FormEvent ev = new FormEvent(this, name, occupation,
						ageCategory.getID(), employmentCategory, 
						usCitizen, taxID, gender);
				
				if (formListener != null) {
					formListener.formEventOccurred(ev);
				}
				
				nameField.setText("");
				occupationField.setText("");
				empCombo.setSelectedIndex(0);
				ageList.setSelectedIndex(1);
				citizenCheck.setSelected(false);
				taxField.setText("");
				maleRadio.setSelected(true);
				nameField.requestFocus();
				
			}
		});

		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponents();
		
	}

	public void layoutComponents() {

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		// ////////// First row ///////////////////////////////////

		gc.gridy = 0;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(nameLabel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameField, gc);

		// //////////Second row ///////////////////////////////////

		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(occupationLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(occupationField, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Age: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(ageList, gc);

		// //////////Next row ///////////////////////////////////
		
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Employment: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(empCombo, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("US Citizen: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(citizenCheck, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(taxLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(taxField, gc);

		// //////////Next row ///////////////////////////////////
		
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Gender: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(maleRadio, gc);
		
		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(femaleRadio, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 2.0;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(okBtn, gc);		
	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
}

class AgeCategoryType {
	private AgeCategory id;
	private String text;

	public AgeCategoryType(AgeCategory id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
	
	public AgeCategory getID() {
		return id;
	}
}