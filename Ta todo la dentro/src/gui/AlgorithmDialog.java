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

import utilities.enums.DialogResponse;

import algorithms.AStar;

import dataStructure.city.infraStructure.Vehicle;

public class AlgorithmDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5450835213857209412L;
	private final JPanel contentPanel = new JPanel();


	private int algorithmType;
	private DialogResponse response;
	private Vehicle vic;
	
	private GroupLayout gl_contentPanel;
	private ButtonGroup group;
	
	private JRadioButton algorithmDistance_RadioButton;
	private JRadioButton algorithmTime_Radiobutton;
	
	
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
	public AlgorithmDialog(Vehicle v) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setAlgorithmType(AStar.DISTANCE);
		vic = v;
		response = null;
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		group = new ButtonGroup();
		
		algorithmDistance_RadioButton = new JRadioButton("Distance");
		algorithmTime_Radiobutton = new JRadioButton("Time");
		algorithmDistance_RadioButton.setSelected(true);
		algorithmDistance_RadioButton.addActionListener(this);
		algorithmTime_Radiobutton.addActionListener(this);
		group.add(algorithmTime_Radiobutton);
		group.add(algorithmDistance_RadioButton);
		
		
		currentFuelField = new JFormattedTextField();	
		fuelTankField = new JFormattedTextField();	
		consumptionField = new JFormattedTextField();	
		speedLimitField = new JFormattedTextField();
		
		currentFuelField.setValue(vic.getCurrentFuel());
		fuelTankField.setValue(vic.getMaxCapacity());
		consumptionField.setValue(vic.getConsumption());
		speedLimitField.setValue(vic.getMaxSpeed());
		
		currentFuelLabel = new JLabel("Current Fuel");	
		fuelTankLabel = new JLabel("Fuel Tank Capacity");
		consumptionLabel = new JLabel("Consumption");	
		speedLimitLabel = new JLabel("Speed Limit");
		
	
		gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(currentFuelLabel))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGap(19)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(algorithmDistance_RadioButton)
									.addComponent(algorithmTime_Radiobutton))
								.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
									.addComponent(consumptionLabel)
									.addComponent(fuelTankLabel)))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addContainerGap()
								.addComponent(speedLimitLabel))))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(currentFuelField, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addComponent(fuelTankField, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addComponent(consumptionField, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addComponent(speedLimitField, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(currentFuelField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(currentFuelLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(algorithmDistance_RadioButton)
						.addComponent(fuelTankField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(fuelTankLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(consumptionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(consumptionLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(speedLimitField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(speedLimitLabel)))
						.addComponent(algorithmTime_Radiobutton))
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
		
		if(e.getSource() instanceof JRadioButton){
			JRadioButton source = (JRadioButton) e.getSource();
			
			if(source.equals(algorithmDistance_RadioButton)){
				
				if(algorithmDistance_RadioButton.isSelected()){
					setAlgorithmType(AStar.DISTANCE);
				}else{
					algorithmDistance_RadioButton.setSelected(true);
				}
			}else{
				if(source.equals(algorithmTime_Radiobutton)){
					
					if(algorithmTime_Radiobutton.isSelected()){
						setAlgorithmType(AStar.TIME);
					}else{
						algorithmTime_Radiobutton.setSelected(true);
					}
				}
			}
		}else{
			
			if(e.getSource() instanceof JButton){
				
				JButton source = (JButton) e.getSource();
				
				if(source.equals(okButton)){
					
					response = DialogResponse.OK;
					
	
					vic.setConsumption((double) consumptionField.getValue());
					vic.setCurrentFuel((double) currentFuelField.getValue());
					vic.setMaxCapacity((double) fuelTankField.getValue());
					vic.setMaxSpeed((int) speedLimitField.getValue());
					
					this.setVisible(false);
				}else{
					if(source.equals(cancelButton)){
						response = DialogResponse.CANCEL;
						this.setVisible(false);
					}
					
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



	public int getAlgorithmType() {
		return algorithmType;
	}



	public void setAlgorithmType(int algorithmType) {
		this.algorithmType = algorithmType;
	}
}
