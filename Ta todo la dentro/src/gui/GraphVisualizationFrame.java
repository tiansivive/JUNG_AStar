package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import logic.graph.Edge;
import logic.graph.GraphNetwork;
import logic.graph.Vertex;
import magicNumbers.Values;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import factories.GUI_EdgeFactory;
import factories.GUI_VertexFactory;

public class GraphVisualizationFrame extends JFrame{

	private static final long serialVersionUID = -55705117335683992L;

	private String title;
	private Layout<Vertex,Edge> roadNetworkLayout;
	private GraphNetwork graph;
	private VisualizationViewer<Vertex, Edge> vv;
	private EditingModalGraphMouse<Vertex, Edge> gm;
	
	
	private GUI_VertexFactory vertexFactory;
	private GUI_EdgeFactory edgeFactory;
	
	
	public GraphVisualizationFrame(){
		
		this.setTitle(title);
		graph = new GraphNetwork();
		roadNetworkLayout = new CustomLayout<Vertex, Edge>(graph.getRoadNetwork());
		roadNetworkLayout.setSize(new Dimension(Values.window_initial_x_resolution, Values.window_initial_y_resolution));
		
		vv = new VisualizationViewer<Vertex,Edge>(roadNetworkLayout);
		vv.setPreferredSize(new Dimension(Values.window_initial_x_resolution +72, Values.window_initial_y_resolution +45)); //Valores para manter a proporcao da resolucao da janela
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Vertex>());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<Edge>());
		
		vertexFactory = new GUI_VertexFactory();
		edgeFactory = new GUI_EdgeFactory();
		
		gm = new EditingModalGraphMouse<Vertex, Edge>(vv.getRenderContext(), vertexFactory, edgeFactory);
		
		
		
		
		/*
		 try { // code retrieved from http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
	            // Set System L&F
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }*/
	}
	
	
	
}
