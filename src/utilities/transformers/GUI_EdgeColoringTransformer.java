package utilities.transformers;

import gui.CustomVisualizationViewer;

import java.awt.Color;
import java.awt.Paint;

import org.apache.commons.collections15.Transformer;

import dataStructure.graph.Edge;
import dataStructure.graph.RoadNetworkGraph;
import edu.uci.ics.jung.graph.Graph;

public class GUI_EdgeColoringTransformer implements Transformer<Edge,Paint> {

	
	private CustomVisualizationViewer<?, Edge> vv;
	public GUI_EdgeColoringTransformer(CustomVisualizationViewer<?, Edge> vv){
		this.vv = vv;
	}
	@Override
	public Paint transform(Edge e) {
		
		if(vv.getPickedEdgeState().getPicked().contains(e)){
			return Color.CYAN;
		}else{
			Graph<?,Edge> graph = vv.getGraphFromCurrentLayout();
			try{
				if(graph instanceof RoadNetworkGraph){
					if(((RoadNetworkGraph<?, Edge>) graph).isSelected(e)){	
						return Color.MAGENTA;
					}
				}
			}catch(ClassCastException ex){	
				ex.printStackTrace();
			}
		}
		return Color.BLACK;
	}


	

}