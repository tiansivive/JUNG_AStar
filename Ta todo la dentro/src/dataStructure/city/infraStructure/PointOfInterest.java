package dataStructure.city.infraStructure;

import java.awt.Point;
import java.io.Serializable;


public class PointOfInterest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3738590622246075385L;
	private Point pos;
	private float width;
	private float length;
	
	private String type; 
	
	
	public Point getPos() {
		return pos;
	}
	public void setPos(Point pos) {
		this.pos = pos;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}