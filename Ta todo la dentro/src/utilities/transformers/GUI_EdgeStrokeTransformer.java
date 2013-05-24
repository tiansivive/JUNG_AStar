package utilities.transformers;

import java.awt.BasicStroke;
import java.awt.Stroke;

import org.apache.commons.collections15.Transformer;

import dataStructure.graph.Edge;
import dataStructure.graph.RoadNetworkGraph;
import dataStructure.graph.Vertex;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class GUI_EdgeStrokeTransformer implements Transformer<Edge, Stroke> {

	
	private VisualizationViewer<Vertex, Edge> vv;
	
	
	public GUI_EdgeStrokeTransformer(VisualizationViewer<Vertex, Edge> vviewer) {
		this.vv = vviewer;
	}


	@Override
	public Stroke transform(Edge edge) {
		
		
		Graph<Vertex, Edge> g = vv.getGraphLayout().getGraph();
		if(g instanceof RoadNetworkGraph){
			
			if(((RoadNetworkGraph<Vertex, Edge>)g).isSelected(edge)){
				return new BasicStroke(3);
			}
		}
		
		return new BasicStroke();
	}

}
