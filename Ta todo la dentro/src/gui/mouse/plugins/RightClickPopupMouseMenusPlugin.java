package gui.mouse.plugins;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JPopupMenu;

import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractPopupGraphMousePlugin;
import gui.mouse.plugins.menus.auxiliarInterfaces.EdgeMenuListener;
import gui.mouse.plugins.menus.auxiliarInterfaces.MenuPointListener;
import gui.mouse.plugins.menus.auxiliarInterfaces.VertexMenuListener;

public class RightClickPopupMouseMenusPlugin<V,E> extends AbstractPopupGraphMousePlugin{

	private JPopupMenu edgePopup;
	private JPopupMenu vertexPopup;

	public RightClickPopupMouseMenusPlugin(){	
		this(MouseEvent.BUTTON3_MASK);
	}

	public RightClickPopupMouseMenusPlugin(int modifiers) {
		super(modifiers);
	}

	@Override
	protected void handlePopup(MouseEvent e) {

		@SuppressWarnings("unchecked")
		final VisualizationViewer<V,E> vv = (VisualizationViewer<V,E>) e.getSource();
		Point2D pos = e.getPoint();

		GraphElementAccessor<V,E> pickSupport = vv.getPickSupport();
		if(pickSupport != null) {
			final V vertex = pickSupport.getVertex(vv.getGraphLayout(), pos.getX(), pos.getY());
			if(vertex != null) {
				System.out.println(vertex + " was right clicked");
				updateVertexMenu(vertex, vv, pos);
				vertexPopup.show(vv, e.getX(), e.getY());
			} else {
				final E edge = pickSupport.getEdge(vv.getGraphLayout(), pos.getX(), pos.getY());
				if(edge != null) {
					System.out.println(edge + " was right clicked");
					updateEdgeMenu(edge, vv, pos);
					edgePopup.show(vv, e.getX(), e.getY());
				}
			}
		}
	}

	private void updateEdgeMenu(E edge, VisualizationViewer<V, E> vv, Point2D pos) {
		if (edgePopup == null){
			return;
		}else{
			Component[] menuComps = edgePopup.getComponents();
			for (Component comp: menuComps) {
				if (comp instanceof EdgeMenuListener) {
					((EdgeMenuListener<E>)comp).setEdgeAndView(edge, vv);
				}
				if (comp instanceof MenuPointListener) {
					((MenuPointListener)comp).setPoint(pos);
				}
			}
		}
	}

	private void updateVertexMenu(V v, VisualizationViewer<V, E> vv, Point2D pos) {

		if(vertexPopup == null){
			return;
		}else{
			Component[] menuComps = vertexPopup.getComponents();
			for (Component comp: menuComps) {
				if (comp instanceof VertexMenuListener) {
					((VertexMenuListener<V>)comp).setVertexAndView(v, vv);
				}
				if (comp instanceof MenuPointListener) {
					((MenuPointListener)comp).setPoint(pos);
				}
			}
		}
	}

	public JPopupMenu getEdgePopup() {
		return edgePopup;
	}
	public void setEdgePopup(JPopupMenu edgePopup) {
		this.edgePopup = edgePopup;
	}
	public JPopupMenu getVertexPopup() {
		return vertexPopup;
	}
	public void setVertexPopup(JPopupMenu vertexPopup) {
		this.vertexPopup = vertexPopup;
	}
}
