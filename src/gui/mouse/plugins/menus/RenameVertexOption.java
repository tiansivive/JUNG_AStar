package gui.mouse.plugins.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dataStructure.graph.Vertex;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import gui.mouse.plugins.menus.auxiliarInterfaces.VertexMenuListener;
import gui.mouse.plugins.menus.dialogs.RenameVertexDialog;

import javax.swing.JMenuItem;

import magicNumbers.Values;

public class RenameVertexOption<V> extends JMenuItem implements VertexMenuListener<V>{


	private static final long serialVersionUID = 2206429093229288744L;

	private VisualizationViewer<V, ?> vv;
	private Vertex vert;
	
	
	public RenameVertexOption() {
		super("Rename Vertex");
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				RenameVertexDialog dialog = new RenameVertexDialog(vert);
				vv.repaint();
			}
		});
	}
	
	
	
	@Override
	public void setVertexAndView(V v, VisualizationViewer<V, ?> visView) {
		
		vert = (Vertex) v;
		vv = visView;
		this.setText("Rename");
	}

	
	
	
	
}
