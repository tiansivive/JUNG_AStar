package gui.mouse.plugins.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

import magicNumbers.Values;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import gui.mouse.plugins.menus.auxiliarInterfaces.VertexMenuListener;

public class DeleteVertexMenuOption<V> extends JMenuItem implements VertexMenuListener<V> {

	private static final long serialVersionUID = -8276877989145392847L;
	private V vertex;
	private VisualizationViewer<V, ?> visComp;

	/** Creates a new instance of DeleteVertexMenuItem */
	public DeleteVertexMenuOption() {
		super(Values.vertex_popupMenu_delete_option);
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				visComp.getPickedVertexState().pick(vertex, false); //marks vertex as not picked
				visComp.getGraphLayout().getGraph().removeVertex(vertex); //removes the vertex
				visComp.repaint();
			}
		});
	}

	public void setVertexAndView(V vert, VisualizationViewer<V, ?> visComp) {
		this.vertex = vert;
		this.visComp = visComp;
		this.setText("Delete " + vert.toString()); //TODO something else in message
	}
	
}
