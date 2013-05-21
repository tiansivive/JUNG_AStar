package utilities;

import gui.CustomVisualizationViewer;

import java.awt.Color;
import java.awt.Paint;

import org.apache.commons.collections15.Transformer;

import dataStructure.graph.Vertex;

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
			

		}
	
}
