package gui.mouse.plugins.menus;

import dataStructure.graph.Edge;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import gui.mouse.plugins.menus.auxiliarInterfaces.EdgeMenuListener;
import gui.mouse.plugins.menus.dialogs.RenameEdgeDialog;
import gui.mouse.plugins.menus.dialogs.RenameVertexDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class RenameEdgeOption<E> extends JMenuItem implements EdgeMenuListener<E>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8116156621842124053L;

	private VisualizationViewer<?, E> vv;
	private Edge ed;
	
	
	public RenameEdgeOption() {
		super("Rename Edge");
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				RenameEdgeDialog dialog = new RenameEdgeDialog(ed);
				vv.repaint();
			}
		});
	}
	
	
	
	@Override
	public void setEdgeAndView(E e, VisualizationViewer<?, E> visView) {
		
		ed = (Edge) e;
		vv = visView;
		this.setText("Rename");
	}



}
