/* SearchByIdDialog - Version 2
 * -Renamed variables, erased extra comments, searchPanel.add()
 * This is the dialog for Employee search by ID
 * */

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class SearchByIdDialog extends JDialog implements ActionListener {
	EmployeeDetails empDetails;
	JButton search, cancel;
	JTextField searchField;
	
	// constructor for SearchByIdDialog 
	public SearchByIdDialog(EmployeeDetails empDeatils) {
		setTitle("Search by Surname");
		setModal(true);
		this.empDetails = empDeatils;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane(searchPane());
		setContentPane(scrollPane);

		getRootPane().setDefaultButton(search);
		
		setSize(500, 190);
		setLocation(350, 250);
		setVisible(true);
	}
	
	// initialize search container
	public Container searchPane() {
		JPanel searchPanel = new JPanel(new GridLayout(3, 1));
		JPanel textPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel searchLabel;

		searchPanel.add(new JLabel("Search by ID"));

		textPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		textPanel.add(searchLabel = new JLabel("Enter ID:"));
		searchLabel.setFont(this.empDetails.font1);
		textPanel.add(searchField = new JTextField(20));
		searchField.setFont(this.empDetails.font1);
		searchField.setDocument(new JTextFieldLimit(20));
		
		buttonPanel.add(search = new JButton("Search"));
		search.addActionListener(this);
		search.requestFocus();
		
		buttonPanel.add(cancel = new JButton("Cancel"));
		cancel.addActionListener(this);

		searchPanel.add(textPanel, buttonPanel);

		return searchPanel;
	}

	// action listener for save and cancel button
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == search) {
			
			try {
				Double.parseDouble(searchField.getText());
				this.empDetails.searchByIdField.setText(searchField.getText());

				this.empDetails.searchEmployeeById();
				dispose();
			} catch (NumberFormatException num) {
				// display message and set colour to text field if entry is wrong
				searchField.setBackground(new Color(255, 150, 150));
				JOptionPane.showMessageDialog(null, "Wrong ID format!");
			}
			
		} else if (e.getSource() == cancel)
			dispose();
	}
	
}