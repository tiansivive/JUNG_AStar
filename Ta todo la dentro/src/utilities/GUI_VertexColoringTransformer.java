package utilities;

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
		public Paint transform(Vertex arg0) {
			
							
			if(vv.getPickedVertexState().getPicked().contains(arg0)){
				return Color.YELLOW;
			}else{
			/*
			if(arg0.getZone() == null){
				return Values.default_unassigned_zone_color;
			}else{
				return arg0.getZone().getObjectsColor(Vertex.class);
			}*/
				return Color.BLUE;
			}
			

		}
	
}
