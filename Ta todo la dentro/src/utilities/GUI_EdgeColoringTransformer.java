package utilities;

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
	public Paint transform(Edge arg0) {

		Graph<?,Edge> graph = vv.getGraphFromCurrentLayout();
		try{
			if(graph instanceof RoadNetworkGraph){
				
				if(((RoadNetworkGraph<?, Edge>) graph).isSelected(arg0)){
					
					return Color.MAGENTA;
				}else{
					//System.out.println("Not selected");
				}
				
			}else{
				
			}
		
		}catch(ClassCastException e){

			e.printStackTrace();
		}
		
		
		return Color.BLACK;
	}


	

}