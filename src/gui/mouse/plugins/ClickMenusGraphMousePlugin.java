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

public class ClickMenusGraphMousePlugin<V,E> extends AbstractPopupGraphMousePlugin{

	private JPopupMenu edgeClickPopup;
	private JPopupMenu vertexClickPopup;
	private JPopupMenu clickAnywherePlugin;

	public ClickMenusGraphMousePlugin(){	
		this(MouseEvent.BUTTON3_MASK);
	}

	public ClickMenusGraphMousePlugin(int modifiers) {
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
				vertexClickPopup.show(vv, e.getX(), e.getY());
			} else {
				final E edge = pickSupport.getEdge(vv.getGraphLayout(), pos.getX(), pos.getY());
				if(edge != null) {
					System.out.println(edge + " was right clicked");
					updateEdgeMenu(edge, vv, pos);
					edgeClickPopup.show(vv, e.getX(), e.getY());
				}else{
					System.out.println("Right Click elsewhere");
				}
			}
		}
	}

	
	@SuppressWarnings("unchecked")
	private void updateEdgeMenu(E edge, VisualizationViewer<V, E> vv, Point2D pos) {
		if (edgeClickPopup == null){
			return;
		}else{
			Component[] menuComps = edgeClickPopup.getComponents();
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

	@SuppressWarnings("unchecked")
	private void updateVertexMenu(V v, VisualizationViewer<V, E> vv, Point2D pos) {

		if(vertexClickPopup == null){
			return;
		}else{
			Component[] menuComps = vertexClickPopup.getComponents();
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

	public JPopupMenu getEdgeClickPopup() {
		return edgeClickPopup;
	}
	public void setEdgeClickPopup(JPopupMenu edgePopup) {
		this.edgeClickPopup = edgePopup;
	}
	public JPopupMenu getVertexClickPopup() {
		return vertexClickPopup;
	}
	public void setVertexClickPopup(JPopupMenu vertexPopup) {
		this.vertexClickPopup = vertexPopup;
	}
	public JPopupMenu getClickAnywherePlugin() {
		return clickAnywherePlugin;
	}
	public void setClickAnywherePlugin(JPopupMenu clickAnywherePlugin) {
		this.clickAnywherePlugin = clickAnywherePlugin;
	}
}
