/*
 * 
 * This is a dialog for adding new Employees and saving records to file
   Cleared a few comments and renamed object/ variable names
 * 
 * */

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class AddRecordDialog extends JDialog implements ActionListener {
	
	JTextField idField, ppsField, surnameField, firstNameField, salaryField;
	JComboBox<String> genderCombo, departmentCombo, fullTimeCombo;
	JButton save, cancel;
	EmployeeDetails empDetails = new EmployeeDetails();
	
	// constructor for add record dialog
	public AddRecordDialog(EmployeeDetails parent) {
		setTitle("Add Record");
		setModal(true);
		this.empDetails = parent;
		this.empDetails.setEnabled(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane(dialogPane());
		setContentPane(scrollPane);
		
		getRootPane().setDefaultButton(save);
		
		setSize(500, 370);
		setLocation(350, 250);
		setVisible(true);
	}// end AddRecordDialog
	
	
	
	

	// initialize dialog container
	public Container dialogPane() {
		JPanel panel, buttonPanel;
		panel = new JPanel(new MigLayout());
		buttonPanel = new JPanel();
		JTextField field;
		
		
		panel.setBorder(BorderFactory.createTitledBorder("Employee Details"));

		panel.add(new JLabel("ID:"), "growx, pushx");
		panel.add(idField = new JTextField(20), "growx, pushx, wrap");
		idField.setEditable(false);
		

		panel.add(new JLabel("PPS Number:"), "growx, pushx");
		panel.add(ppsField = new JTextField(20), "growx, pushx, wrap");

		panel.add(new JLabel("Surname:"), "growx, pushx");
		panel.add(surnameField = new JTextField(20), "growx, pushx, wrap");

		panel.add(new JLabel("First Name:"), "growx, pushx");
		panel.add(firstNameField = new JTextField(20), "growx, pushx, wrap");

		panel.add(new JLabel("Gender:"), "growx, pushx");
		panel.add(genderCombo = new JComboBox<String>(this.empDetails.gender), "growx, pushx, wrap");

		panel.add(new JLabel("Department:"), "growx, pushx");
		panel.add(departmentCombo = new JComboBox<String>(this.empDetails.department), "growx, pushx, wrap");

		panel.add(new JLabel("Salary:"), "growx, pushx");
		panel.add(salaryField = new JTextField(20), "growx, pushx, wrap");

		panel.add(new JLabel("Full Time:"), "growx, pushx");
		panel.add(fullTimeCombo = new JComboBox<String>(this.empDetails.fullTime), "growx, pushx, wrap");

		buttonPanel.add(save = new JButton("Save"));
		save.addActionListener(this);
		save.requestFocus();
		buttonPanel.add(cancel = new JButton("Cancel"));
		cancel.addActionListener(this);

		panel.add(buttonPanel, "span 2,growx, pushx,wrap");
		// parent.example();
		// loop through all panel components and add fonts and listeners
		 for (int i = 0; i < panel.getComponentCount(); i++) {
			 panel.getComponent(i).setFont(empDetails.font1); //calling empdetails obj
			
			if (panel.getComponent(i) instanceof JComboBox) {
				panel.getComponent(i).setBackground(Color.WHITE);
			}// end if
			
			
			else if(panel.getComponent(i) instanceof JTextField){
				field = (JTextField) panel.getComponent(i);
				if(field == ppsField)
					field.setDocument(new JTextFieldLimit(9));
				else
				field.setDocument(new JTextFieldLimit(20));
			}// end else if
			
		}// end for  
		
		idField.setText(Integer.toString(this.empDetails.getNextFreeId()));
		
		
		return panel;
	}

	// add record to file
	public void addRecord() {
		boolean fullTime = false;
		Employee theEmployee;

		if (((String) fullTimeCombo.getSelectedItem()).equalsIgnoreCase("Yes"))
		  fullTime = true;
		
		// create new Employee record with details from text fields - CAN CREATE STRING WITH THESE VALUES AND PASS THEM IN INSTEAD
		theEmployee = new Employee(Integer.parseInt(idField.getText()), ppsField.getText().toUpperCase(), surnameField.getText().toUpperCase(),
				firstNameField.getText().toUpperCase(), genderCombo.getSelectedItem().toString().charAt(0),
				departmentCombo.getSelectedItem().toString(), Double.parseDouble(salaryField.getText()), fullTime);
		empDetails.currentEmployee = theEmployee;
		empDetails.addRecord(theEmployee);
		empDetails.displayRecords(theEmployee);
		
		
	}
	
	
	
	
	

	// check for input in text fields
	public boolean checkInput() {
		boolean valid = true;
		// if any of inputs are in wrong format, colour text field and display message
		if (ppsField.getText().isEmpty() || empDetails.correctPps(ppsField.getText().trim(), -1) ) {
			ppsField.setBackground(new Color(255, 150, 150));
			valid = false;
		}// end if
		
		if (surnameField.getText().isEmpty()) {
			surnameField.setBackground(new Color(255, 150, 150));
			valid = false;
		}// end if
		
		if (firstNameField.getText().isEmpty()) {
			firstNameField.setBackground(new Color(255, 150, 150));
			valid = false;
		}// end if
		
		if (genderCombo.getSelectedIndex() == 0) {
			genderCombo.setBackground(new Color(255, 150, 150));
			valid = false;
		}// end if
		
		if (departmentCombo.getSelectedIndex() == 0) {
			departmentCombo.setBackground(new Color(255, 150, 150));
			valid = false;
		}// end if
		
		try {// try to get values from text field
			Double.parseDouble(salaryField.getText());
			// check if salary is greater than 0
			if (Double.parseDouble(salaryField.getText()) < 0) {
				salaryField.setBackground(new Color(255, 150, 150));
				valid = false;
			}// end if
		}// end try
		
		catch (NumberFormatException num) {
			salaryField.setBackground(new Color(255, 150, 150));
			valid = false;
		}// end catch
		if (fullTimeCombo.getSelectedIndex() == 0) {
			fullTimeCombo.setBackground(new Color(255, 150, 150));
			valid = false;
		}// end if
		return valid;
	}// end checkInput

	// set text field to white colour
	public void setToWhite() {
		ppsField.setBackground(Color.WHITE);
		surnameField.setBackground(Color.WHITE);
		firstNameField.setBackground(Color.WHITE);
		salaryField.setBackground(Color.WHITE);
		genderCombo.setBackground(Color.WHITE);
		departmentCombo.setBackground(Color.WHITE);
		fullTimeCombo.setBackground(Color.WHITE);
	}// end setToWhite

	// action performed
	public void actionPerformed(ActionEvent e) {
		// if chosen option save, save record to file
		if (e.getSource() == save) {
			// if inputs correct, save record
			if (checkInput()) {
				addRecord();// add record to file
				dispose();// dispose dialog
				this.empDetails.changesMade = true;
			}// end if
			// else display message and set text fields to white colour
			else {
				JOptionPane.showMessageDialog(null, "Wrong values or format! Please check!");
				setToWhite();
			}// end else
		}// end if
		else if (e.getSource() == cancel)
			dispose();// dispose dialog
	}// end actionPerformed
}// end class AddRecordDialog