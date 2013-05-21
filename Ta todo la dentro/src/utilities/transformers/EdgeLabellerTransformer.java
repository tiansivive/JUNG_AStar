package utilities.transformers;

import org.apache.commons.collections15.Transformer;

import utilities.EdgeLabel;
import dataStructure.graph.Edge;

public class EdgeLabellerTransformer implements Transformer<Edge, String>{

	private EdgeLabel labelType;

	public EdgeLabellerTransformer(){
		labelType = EdgeLabel.NAME;
	}

	@Override
	public String transform(Edge e) {

		switch(labelType){
			case NAME:{
				return e.toString();
			}
			case SPEED_LIMIT:{
				return Integer.toString(e.getSpeedLimit());
			}
			case DISTANCE:{
				return Double.toString(e.getDistance());
			}
			case WEIGHT:{
				return Double.toString(e.getWeight());
			}
			case CAPACITY:{
				return Double.toString(e.getCapacity());
			}
			default:{
				return e.toString();
			}
		}


	}


	public EdgeLabel getLabelType() {
		return labelType;
	}
	public void setLabelType(EdgeLabel labelToShow) {
		this.labelType = labelToShow;
	}

}
