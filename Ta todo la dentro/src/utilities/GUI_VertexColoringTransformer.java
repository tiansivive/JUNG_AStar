package utilities;

import java.awt.Color;
import java.awt.Paint;

import magicNumbers.Values;

import org.apache.commons.collections15.Transformer;

import dataStructure.graph.Vertex;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class GUI_VertexColoringTransformer implements Transformer<Vertex,Paint> {

	
		private VisualizationViewer<Vertex, ?> vv;
		public GUI_VertexColoringTransformer(VisualizationViewer<Vertex, ?> vv){
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
