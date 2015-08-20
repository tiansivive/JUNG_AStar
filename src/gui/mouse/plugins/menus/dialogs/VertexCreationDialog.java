package gui.mouse.plugins.menus.dialogs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import dataStructure.graph.Vertex;

public class VertexCreationDialog extends JDialog{
	
	private static final long serialVersionUID = 5558121960281019154L;

	private Vertex vertex;
	private JButton button;
	private JComboBox<?> comboBox;
	
	
	public VertexCreationDialog(){
		
		super(new JFrame(), true);
		String[] options = {"Intersection", "PointOfInterest"};
		comboBox = new JComboBox<>(options);
		
		button = new JButton();
		button.setText("OK");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });
			
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		BoxLayout box = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(box);
		getContentPane().add(comboBox);
		getContentPane().add(button);
		this.setVisible(true);
		this.pack();
	
		
	}
	
}
