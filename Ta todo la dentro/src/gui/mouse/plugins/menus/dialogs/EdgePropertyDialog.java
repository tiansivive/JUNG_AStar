package gui.mouse.plugins.menus.dialogs;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import magicNumbers.Values;
import dataStructure.graph.Edge;

public class EdgePropertyDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7410686613203025421L;
	
	public static boolean templateEnabled = false;
		
	private final JPanel contentPanel = new JPanel();
	
	private Edge edge;
	
	private JPanel buttonPane;
	
	private JLabel setAsDefaultLabel;
	private JLabel speedLimitLabel;
	private JLabel capacityLabel;
	private JLabel distanceLabel;
	private JFormattedTextField speedLimitField;
	private JFormattedTextField capacityField;
	private JFormattedTextField distanceField;	

	private JCheckBox templateCheckBox;
	private JRadioButton speedLimitRadioButton ;
	private JRadioButton capacityRadioButton;
	private JRadioButton distanceRadioButton;
	
	private JButton okButton;
	private JButton cancelButton;
	private GroupLayout gl_contentPanel;
	
	private DialogResponse response;


	public EdgePropertyDialog(Edge e) {
		
	    super(new JFrame(), true);    
	    this.edge = e;
	    setResponse(null);
	    
	    setTitle(Values.edge_properties_editor_frame_name);
		setBounds(100, 100, 455, 302);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

		initFields();
		initLayouts();
		initButtonPanel();
		
		getRootPane().setDefaultButton(okButton);
		
	}
	
	
	private void initFields(){
		
		speedLimitLabel = new JLabel("Speed Limit:");
		capacityLabel = new JLabel("Capacity:");	
		distanceLabel = new JLabel("Distance:");
		setAsDefaultLabel = new JLabel("Set as default");
		
		speedLimitField = new JFormattedTextField();	
		capacityField = new JFormattedTextField();
		distanceField = new JFormattedTextField();
		
		capacityField.setHorizontalAlignment(JTextField.RIGHT);
	    distanceField.setHorizontalAlignment(JTextField.RIGHT);
	    speedLimitField.setHorizontalAlignment(JTextField.RIGHT);
	    capacityField.setValue(edge.getCapacity());
        distanceField.setValue(edge.getDistance());
        speedLimitField.setValue(edge.getSpeedLimit());

		templateCheckBox = new JCheckBox("Use current values as template");
		templateCheckBox.setToolTipText(
							"Checking this box means this dialog will not open when creating an Edge." +
							" To re enable forcibly edit an Edge's properties by right-clicking and then de-select this box");
		templateCheckBox.addActionListener(this);
		
		speedLimitRadioButton = new JRadioButton("");
		capacityRadioButton = new JRadioButton("");
		distanceRadioButton = new JRadioButton("");
		speedLimitRadioButton.addActionListener(this);
		capacityRadioButton.addActionListener(this);
		distanceRadioButton.addActionListener(this);
		
	}
	private void initLayouts(){
		
		gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGap(19)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(speedLimitLabel)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(distanceLabel)
								.addComponent(capacityLabel)))
								.addGap(36)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(templateCheckBox)
								.addContainerGap())
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, 257, Short.MAX_VALUE)
								.addComponent(speedLimitRadioButton)
								.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(capacityField, Alignment.LEADING)
								.addComponent(speedLimitField, Alignment.LEADING)
								.addComponent(distanceField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
								.addGap(22)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(capacityRadioButton)
								.addComponent(distanceRadioButton))
								.addContainerGap()))))
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addContainerGap(342, Short.MAX_VALUE)
								.addComponent(setAsDefaultLabel)
								.addContainerGap())
				);
		
		gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGap(14)
						.addComponent(setAsDefaultLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(speedLimitLabel)
								.addComponent(speedLimitField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
								.addComponent(speedLimitRadioButton))
								.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(capacityLabel)
								.addComponent(capacityField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
								.addComponent(capacityRadioButton))
								.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(distanceRadioButton)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(distanceField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(distanceLabel)))
								.addGap(18)
								.addComponent(templateCheckBox)
								.addGap(28))
				);
		contentPanel.setLayout(gl_contentPanel);
	}
	private void initButtonPanel(){

		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				okHandler();
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);


		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelHandler();
			}
		});
		buttonPane.add(cancelButton);
	}
	

	private void okHandler() {
		
		Double distanceValue =  (Double)this.distanceField.getValue();
		Double capacityValue =  (Double)this.capacityField.getValue();
		int speedLimitValue =  (int)this.speedLimitField.getValue();
	
		if(templateCheckBox.isSelected()){
			EdgePropertyDialog.templateEnabled = true;
			
			Values.default_edge_distance = distanceValue;
			Values.default_edge_capacity = capacityValue;
			Values.default_edge_speedLimit = speedLimitValue;
		}else{
			EdgePropertyDialog.templateEnabled = false;
		
			if(distanceRadioButton.isSelected()){
				Values.default_edge_distance = distanceValue;
			}
			if(capacityRadioButton.isSelected()){
				Values.default_edge_capacity = capacityValue;
			}
			if(speedLimitRadioButton.isSelected()){
				Values.default_edge_speedLimit = speedLimitValue;
			}
		}
	    edge.setCapacity(capacityValue);
	    edge.setDistance(distanceValue);
	    edge.setSpeedLimit(speedLimitValue);

        response = DialogResponse.OK;
        this.setVisible(false);
	}


	private void cancelHandler() {
		
		response = DialogResponse.CANCEL;
		this.setVisible(false);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() instanceof JCheckBox){
			
			JCheckBox source = (JCheckBox) e.getSource();
			
			if(source.isSelected()){
				distanceField.setEnabled(false);
				speedLimitField.setEnabled(false);
				capacityField.setEnabled(false);
				distanceRadioButton.setEnabled(false);
				speedLimitRadioButton.setEnabled(false);
				capacityRadioButton.setEnabled(false);
			}else{
				distanceField.setEnabled(true);
				speedLimitField.setEnabled(true);
				capacityField.setEnabled(true);	
				distanceRadioButton.setEnabled(true);
				speedLimitRadioButton.setEnabled(true);
				capacityRadioButton.setEnabled(true);
			}

		}else{
			if(e.getSource() instanceof JRadioButton){
				JRadioButton source = (JRadioButton) e.getSource();
				
				if(source.equals(distanceRadioButton)){
					if(distanceRadioButton.isSelected()){
						distanceField.setEnabled(false);
					}else{
						distanceField.setEnabled(true);
					}
				}else{
					if(source.equals(speedLimitRadioButton)){				
						if(speedLimitRadioButton.isSelected()){
							speedLimitField.setEnabled(false);
						}else{
							speedLimitField.setEnabled(true);
						}
					}else{
						if(source.equals(capacityRadioButton)){				
							if(capacityRadioButton.isSelected()){
								capacityField.setEnabled(false);
							}else{
								capacityField.setEnabled(true);
							}
						}
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
}
