package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import magicNumbers.Values;

import utilities.enums.DialogResponse;

import algorithms.AStar;

import dataStructure.city.infraStructure.Vehicle;

public class VehicleDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5450835213857209412L;
	private final JPanel contentPanel = new JPanel();

	private DialogResponse response;
	
	private GroupLayout gl_contentPanel;

	
	
	private JFormattedTextField currentFuelField;
	private JFormattedTextField fuelTankField;
	private JFormattedTextField consumptionField;
	private JFormattedTextField speedLimitField;
	
	private JLabel currentFuelLabel;	
	private JLabel fuelTankLabel;
	private JLabel consumptionLabel;	
	private JLabel speedLimitLabel;
	
	private JPanel buttonPane;

	private JButton okButton;
	private JButton cancelButton;
	
	
	
	/**
	 * Create the dialog.
	 */
	public VehicleDialog() {
		setBounds(100, 100, 333, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		response = null;
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		
		currentFuelField = new JFormattedTextField();	
		fuelTankField = new JFormattedTextField();	
		consumptionField = new JFormattedTextField();	
		speedLimitField = new JFormattedTextField();
		
		currentFuelField.setValue(Values.default_vehicle_fuel);
		fuelTankField.setValue(Values.default_vehicle_fuel_tank_capacity);
		consumptionField.setValue(Values.default_vehicle_consumption);
		speedLimitField.setValue(Values.default_vehicle_speed);
		
		currentFuelLabel = new JLabel("Current Fuel");	
		fuelTankLabel = new JLabel("Fuel Tank Capacity");
		consumptionLabel = new JLabel("Consumption");	
		speedLimitLabel = new JLabel("Speed Limit");
		
	
		gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(consumptionLabel)
						.addComponent(fuelTankLabel)
						.addComponent(speedLimitLabel)
						.addComponent(currentFuelLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(consumptionField, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addComponent(fuelTankField, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addComponent(speedLimitField, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addComponent(currentFuelField, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(currentFuelLabel)
						.addComponent(currentFuelField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(fuelTankLabel)
						.addComponent(fuelTankField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(consumptionLabel)
						.addComponent(consumptionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(speedLimitLabel)
						.addComponent(speedLimitField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		


		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("OK");
		okButton.addActionListener(this);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);
		
		
		getRootPane().setDefaultButton(okButton);

	}



	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() instanceof JButton){

			JButton source = (JButton) e.getSource();

			if(source.equals(okButton)){

				response = DialogResponse.OK;
				
				Values.default_vehicle_consumption = (double) consumptionField.getValue();
				Values.default_vehicle_fuel = (double) currentFuelField.getValue();
				Values.default_vehicle_fuel_tank_capacity =(double) fuelTankField.getValue();
				Values.default_vehicle_speed =(int) speedLimitField.getValue();

				this.setVisible(false);
			}else{
				if(source.equals(cancelButton)){
					response = DialogResponse.CANCEL;
					this.setVisible(false);
				}

			}

		}
	}


	public DialogResponse getResponse() {
		return response;
	}

	public void setResponse(DialogResponse response) {
		this.response = response;
	}
}
