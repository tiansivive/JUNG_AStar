package utilities.transformers;

import gui.CustomVisualizationViewer;

import java.awt.Color;
import java.awt.Paint;

import org.apache.commons.collections15.Transformer;

import dataStructure.graph.RoadNetworkGraph;
import dataStructure.graph.Vertex;
import edu.uci.ics.jung.graph.Graph;

public class GUI_VertexColoringTransformer implements Transformer<Vertex,Paint> {


	private CustomVisualizationViewer<Vertex, ?> vv;
	public GUI_VertexColoringTransformer(CustomVisualizationViewer<Vertex, ?> vv){
		this.vv = vv;
	}

	@Override
	public Paint transform(Vertex v) {

		if(vv.getPickedVertexState().getPicked().contains(v)){
			return Color.YELLOW;
		}else{
			Graph<Vertex, ?> g = vv.getGraphLayout().getGraph();
			if(g instanceof RoadNetworkGraph){

				if(((RoadNetworkGraph<Vertex, ?>) g).isInitialVertex(v)){
					return Color.GREEN;	
				}
				if(((RoadNetworkGraph<Vertex, ?>) g).isEndVertex(v)){
					return Color.MAGENTA;
				}
				if(((RoadNetworkGraph<Vertex, ?>) g).isPointToTraverse(v)){
					return Color.DARK_GRAY;
				}


				switch(v.getType()){
					case BUILDING:{
						return Color.ORANGE;
					}
					case GAS_STATION:{
						return Color.BLUE;
					}
					case INTERSECTION:{
						return Color.RED;
					}
					default:{
						return Color.BLACK;
					}		
				}
			}
			
			return Color.BLACK;
		}


	}

}
