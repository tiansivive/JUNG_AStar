package gui.mouse.plugins.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

import magicNumbers.Values;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import gui.mouse.plugins.menus.auxiliarInterfaces.EdgeMenuListener;

public class DeleteEdgeMenuOption<E> extends JMenuItem implements EdgeMenuListener<E> {

	private static final long serialVersionUID = -5113645917376893190L;
	
	private E edge;
    private VisualizationViewer<?, E> visComp;
    
    /** Creates a new instance of DeleteEdgeMenuItem */
    public DeleteEdgeMenuOption() {
        super(Values.edge_popupMenu_delete_option);
        this.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                visComp.getPickedEdgeState().pick(edge, false);
                visComp.getGraphLayout().getGraph().removeEdge(edge);
                visComp.repaint();
            }
        });
    }

    public void setEdgeAndView(E edge, VisualizationViewer<?, E> visComp) {
        this.edge = edge;
        this.visComp = visComp;
        this.setText(edge.toString());
    }

}
