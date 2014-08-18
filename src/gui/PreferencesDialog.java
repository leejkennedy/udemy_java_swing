package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class PreferencesDialog extends JDialog {
	private static final long serialVersionUID = 3195174470241088921L;

	private JButton okButton;
	private JButton cancelButton;
	private JSpinner portSpinner;
	private SpinnerNumberModel portSpinnerModel;
	private JTextField userNameField;
	private JTextField passwordField;
	private PreferencesListener preferencesListener;
	
	public PreferencesDialog(JFrame parent) {
		super(parent, "Preferences", false);

		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");

		portSpinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
		portSpinner = new JSpinner(portSpinnerModel);

		userNameField = new JTextField(10);
		passwordField = new JPasswordField(10);
		// ((JPasswordField) passwordField).setEchoChar('#');

		layoutControls();

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer port = (Integer) portSpinner.getValue();
				String user = userNameField.getText();
				char[] password = ((JPasswordField) passwordField)
						.getPassword();

				if (preferencesListener != null) {
					preferencesListener.PreferencesSet(user, new String(
							password), port);
				}

				setVisible(false);
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		setSize(400, 300);
		setLocationRelativeTo(parent);
	}

	public void setDefaults(String user, String password, int port) {
		userNameField.setText(user);
		passwordField.setText(password);
		portSpinner.setValue(port);
	}

	public void setPreferencesListener(PreferencesListener preferencesListener) {
		this.preferencesListener = preferencesListener;
	}

	private void layoutControls() {

		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();

		controlsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		controlsPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();

		gc.gridy = 0;

		// /////////// first row ///////////////
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		controlsPanel.add(new JLabel("User: "), gc);

		gc.gridx++;
		controlsPanel.add(userNameField, gc);

		// /////////// next row///////////////
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		controlsPanel.add(new JLabel("Password: "), gc);

		gc.gridx++;
		controlsPanel.add(passwordField, gc);

		// /////////// next row///////////////
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		controlsPanel.add(new JLabel("Port: "), gc);

		gc.gridx++;
		controlsPanel.add(portSpinner, gc);

		// /////////// buttons panel///////////////
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);
		setLayout(new BorderLayout());
		add(controlsPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
		// /////////// control layout finished ///////////////////////////

	}

}
