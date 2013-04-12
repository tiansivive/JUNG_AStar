package gui.mouse.plugins.menus;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import logic.graph.Edge;
import magicNumbers.Values;


public class EdgePropertyDialog extends JDialog {

	private static final long serialVersionUID = 283753001223446744L;
	
	private Edge edge;
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JFormattedTextField speedLimitFormattedTextField;
    private JFormattedTextField distanceFormattedTextField;
    private JFormattedTextField weightFormattedTextField;
    private JFormattedTextField capacityFormattedTextField;
  
    
    /** Creates new form EdgePropertyDialog */
    public EdgePropertyDialog(Edge edge) {
        super(new JFrame(), true);
        initComponents();
        this.edge = edge;
        setTitle(Values.edge_properties_editor_frame_name);
        this.capacityFormattedTextField.setValue(edge.getCapacity() );
        this.weightFormattedTextField.setValue(edge.getWeight());
        this.distanceFormattedTextField.setValue(edge.getDistance());
        this.speedLimitFormattedTextField.setValue(edge.getSpeedLimit());
    }
    
 
    private void initComponents() {
        jButton1 = new JButton();
        jLabel1 = new JLabel("Capacity:");
        jLabel2 = new JLabel("Weight:");
        jLabel3 = new JLabel("Distance:");
        jLabel4 = new JLabel("SpeedLimit:");
        capacityFormattedTextField = new JFormattedTextField();
        weightFormattedTextField = new JFormattedTextField();
        distanceFormattedTextField = new JFormattedTextField();
        speedLimitFormattedTextField = new JFormattedTextField();
  
        capacityFormattedTextField.setHorizontalAlignment(JTextField.RIGHT);
        weightFormattedTextField.setHorizontalAlignment(JTextField.RIGHT);
        distanceFormattedTextField.setHorizontalAlignment(JTextField.RIGHT);
        speedLimitFormattedTextField.setHorizontalAlignment(JTextField.RIGHT);
        
        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jButton1.setText("OK");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                okButtonHandler(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this.getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(capacityFormattedTextField,  GroupLayout.PREFERRED_SIZE, 50,  GroupLayout.PREFERRED_SIZE))
                            .addGroup( GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(weightFormattedTextField,  GroupLayout.PREFERRED_SIZE, 50,  GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(distanceFormattedTextField,  GroupLayout.PREFERRED_SIZE, 50,  GroupLayout.PREFERRED_SIZE))
                            .addGroup( GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(speedLimitFormattedTextField,  GroupLayout.PREFERRED_SIZE, 50,  GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap( GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap( GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(capacityFormattedTextField,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(weightFormattedTextField,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(distanceFormattedTextField,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(speedLimitFormattedTextField,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );
        pack();
    }

    private void okButtonHandler(java.awt.event.ActionEvent evt) {
        edge.setCapacity((Double)this.capacityFormattedTextField.getValue());
        edge.setWeight((Double)this.weightFormattedTextField.getValue());
        edge.setDistance((Double)this.distanceFormattedTextField.getValue());
        edge.setSpeedLimit((int)this.speedLimitFormattedTextField.getValue());
        dispose();
    }
       
}