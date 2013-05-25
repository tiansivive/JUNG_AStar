package utilities.transformers;

import java.awt.BasicStroke;
import java.awt.Stroke;

import org.apache.commons.collections15.Transformer;

import dataStructure.graph.Edge;
import dataStructure.graph.RoadNetworkGraph;
import dataStructure.graph.Vertex;
import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.graph.Graph;
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
			
			RoadNetworkGraph<Vertex, Edge> graph = (RoadNetworkGraph<Vertex, Edge>) g;
			if(graph.isSelected(edge)){
				return new BasicStroke(4);
			}else{
				return new BasicStroke(1);
				/*if(GUI_EdgeShapeTransformer.type == EdgeShapeType.LINE){
					
					Pair<Vertex> endpoints = graph.getEndpoints(edge);
					
					Collection<Edge> parallel = graph.findEdgeSet(endpoints.getFirst(), endpoints.getSecond());
					if(parallel.size() > 1){//More than one edge with these endpoints
						return new BasicStroke(1);
					}
				}*/
			}
		}
		
		return new BasicStroke(1);
	}

}
