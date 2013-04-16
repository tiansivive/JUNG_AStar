package dataStructure;

import java.awt.Point;
import magicNumbers.Values;

public class Vertex{
	
	private int id;
	private String name;
	
	private boolean visited;
	private boolean processing;
	
	private Point pos;
	
	public Vertex(){	
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = null;
		this.visited = false;
		this.processing = false;
		this.pos = null;
	}
	public Vertex(Point p){	
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = null;
		this.visited = false;
		this.processing = false;
		this.pos = p;
	}	
	public Vertex(int x, int y){	
		Values.VerticesCurrentID++;		
		this.id = Values.VerticesCurrentID;
		this.name = null;
		this.visited = false;
		this.processing = false;
		this.pos = new Point(x,y);
	}
	
	@Override
	public String toString(){
		
		String toReturn = "";
		if(name != null){
			toReturn += name;
		}else{
			toReturn += "V" +id;
		}
		return toReturn;
	}
	
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public boolean isProcessing() {
		return processing;
	}
	public void setProcessing(boolean processing) {
		this.processing = processing;
	}
	public Point getPos() {
		return pos;
	}
	public void setPos(Point pos) {
		this.pos = pos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}