package gui.mouse.plugins.menus.dialogs;

import dataStructure.graph.Vertex;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class VertexPriorityDialog<V> extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4642561891243761662L;

	private final JPanel contentPanel = new JPanel();

	Vertex v;
	private JSlider priority_slider;
	JButton btnLowPriority;
	JButton btnHighPriority;
	JButton btnDefaultPriority;
	JButton okButton;
	JButton cancelButton;
	/**
	 * Create the dialog.
	 */
	public VertexPriorityDialog(V ver) {
		
		super();
		try{
			this.v = (Vertex)ver;
		}catch(ClassCastException e){
			e.printStackTrace();
		}
		setBounds(100, 100, 532, 193);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			priority_slider = new JSlider();
			priority_slider.setSnapToTicks(true);
			priority_slider.setPaintLabels(true);
			priority_slider.setPaintTicks(true);
			priority_slider.setMinimum(-10);
			priority_slider.setMinorTickSpacing(1);
			priority_slider.setMaximum(-1);
			if((-1*v.getOrder()) < priority_slider.getMinimum() || (-1*v.getOrder()) > priority_slider.getMaximum()) { 
				priority_slider.setValue(priority_slider.getMaximum());
			} else {
				priority_slider.setValue(-1*v.getOrder());
			}
		}
		
		btnLowPriority = new JButton("Lowest Priority");
		btnLowPriority.addActionListener(this);
		
		btnHighPriority = new JButton("Highest Priority");
		btnHighPriority.addActionListener(this);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(btnLowPriority)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(priority_slider, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnHighPriority))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnHighPriority)
						.addComponent(priority_slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLowPriority))
					.addGap(130))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnDefaultPriority = new JButton("Default Priority");
			btnDefaultPriority.addActionListener(this);
			buttonPane.add(btnDefaultPriority);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() instanceof JButton){
			JButton source = (JButton)(e.getSource());
			
			if(source.equals(btnDefaultPriority)){
				v.setOrder(0); //DEFAULT priority
				this.dispose();
			}else{
				if(source.equals(btnHighPriority)){
					priority_slider.setValue(priority_slider.getMaximum());
				}else{
					if(source.equals(btnLowPriority)){
						priority_slider.setValue(priority_slider.getMinimum());
					}else{
						if(source.equals(okButton)){
							v.setOrder(-1*priority_slider.getValue());
							System.out.println("ok"); //TODO: remove this?
							this.dispose();
						}else{
							if(source.equals(cancelButton)){
								System.out.println("cancel"); //TODO: remove this?
								this.dispose();
							}
						}
					}
				}
				
			}
		}
		
		
	}
}
